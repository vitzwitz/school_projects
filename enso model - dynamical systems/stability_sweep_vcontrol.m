function stability_sweep_vcontrol
% parameter sweep for Fitzhugh-nagumo
% vary k over a range and plot the fixed points
% including stability 

set(0,'defaultaxesfontsize',16);
set(0,'defaultlinelinewidth',2);

% declare x & y as symbolic
syms x y           %% but X = [u;v]

% set parameter values
% k will be set later because it will be varied
a = -0.1;
b = 0.5;
e = 0.01;
s = 0;

%% Open document
fileID = fopen('stablity_vcontrol.txt','w');

figure(1)
clf(1)
hold on
% sweep over k values, here from 0 to 0.3
% in increments of 0.005
for k=0:0.005:0.3 

    % solve for fixed points
    % coordinates are returned as x,y
    % note that "equations" to be solved are implicit as expression=0
    soln=solve(x.*(-x.^2 + (a+1).*x - a)-y, e*(b*x-y)-k*y,[x y]);

    % convert solution components back from symbolic
    soln.x=eval(soln.x);
    soln.y=eval(soln.y);

    % loop over all solutions found
    for j=1:length(soln.x)

        % test whether solutions are real
        % using a tolerance of 1e-10 for imaginary part magnitude
        if(abs(imag(soln.x(j))<1e-10)&&...
           abs(imag(soln.y(j))<1e-10))

            xx=(real(soln.x(j)));
            yy=(real(soln.y(j)));
            
            % evaluate Jacobian at the trivial fixed point
            J=[-3*xx.^2 + 2*xx*(a+1)-a     -1;
               e*b                        -(e+k)];

            % find eigenvalues
            myeigs=eig(J);
            
            % check sign of real part of eigenvalues
            if((real(myeigs(1)))<0)...
                &&((real(myeigs(2)))<0)...

                % if all are negative, the fixed point is stable
                % plot with filled circles
                %% Create document
                 fps = [xx yy];               
                 formatSpec = 'Fixed point at (%f,%f) is stable \n';
                 fprintf(fileID,formatSpec,fps);
                 plot(k,xx,'ko','markerfacecolor','k')

            else
                
                % otherwise, fixed point is unstable
                % plot with open circle
                 fpu = [xx yy];
                 formatSpec1 = 'Fixed point at (%f,%f) is unstable \n';
                 fprintf(fileID,formatSpec1,fpu);
                 plot(k,xx,'ko')

            end
        end
    end
end
fclose(fileID)
% plot bifurcation plot for x*
xlabel('k'),ylabel('x^*'),grid on