function ENSO_sweep_near_fp
% parameter sweep for ENSO model
% vary k over a range and plot the fixed points
% including stability 

set(0,'defaultaxesfontsize',16);
set(0,'defaultlinelinewidth',2);

% declare x, y, and z as symbolic
syms x y z

% set parameter values
% k will be set later because it will be varied
ustar=-14.2;
tstar=28;
tbar=16;
delx=7.5;
B=940;
C=3;
r=1;

figure(1)
clf(1)
hold on
% sweep over k values, here from 0.5 to 29.5
% in increments of 1
for r=0.5:1:29.5 

    % solve for fixed points
    % coordinates are returned as x,y,z
    % note that "equations" to be solved are implicit as expression=0
    soln=solve((B/(2*delx))*(z-y)-C*(x-ustar)-k*(x-s_u),...
        x/(2*delx)*(tbar-z)+r*(tstar-y)-k*(x-s_w),...
        -x/(2*delx)*(tbar-y)+r*(tstar-z)-k*(x-s_e),[x y z]);
    

    % convert solution components back from symbolic
    soln.x=eval(soln.x);
    soln.y=eval(soln.y);
    soln.z=eval(soln.z);

    % loop over all solutions found
    for j=1:length(soln.x)

        % test whether solutions are real
        % using a tolerance of 1e-10 for imaginary part magnitude
        if(abs(imag(soln.x(j))<1e-10)&&...
           abs(imag(soln.y(j))<1e-10)&&...
           abs(imag(soln.z(j))<1e-10))

            xx=(real(soln.x(j)));
            yy=(real(soln.y(j)));
            zz=(real(soln.z(j)));
            
            % evaluate Jacobian at this fixed point
            J= [-C-k*xx                  -B/(2*delx)     B/(2*delx);
               (tbar-zz)/(2*delx)        -r-k*yy       -xx/(2*delx);
               (yy-tbar)/(2*delx)       xx/(2*delx)        -r-k*zz];

            % find eigenvalues
            myeigs=eig(J);
            
            % check sign of real part of eigenvalues
            if((real(myeigs(1)))<0)...
                &&((real(myeigs(2)))<0)...
                &&((real(myeigs(3)))<0)

                % if all are negative, the fixed point is stable
                % plot with filled circles
                plot(r,xx,'ko','markerfacecolor','k')

            else
                
                % otherwise, fixed point is unstable
                % plot with open circle
                plot(r,xx,'ko')

            end
        end
    end
end

% plot bifurcation plot for x*
xlabel('k'),ylabel('x^*'),grid on
