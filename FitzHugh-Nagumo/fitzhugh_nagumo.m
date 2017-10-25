% Math 761, Mathematical Biology
% L. Munoz, Mar. 2016
% Function for plotting phase portrait and time-series graphs
% for predator-prey model, with carrying capacity for prey and
% saturation (Holling functional response curve) on predation rate.
% Model source: various, described in these locations:
% http://www.cengage.com/math/book_content/0495108243_zill/projects_archive/debv4e/AModelingApplication6.pdf
% http://www.cds.caltech.edu/~murray/amwiki/index.php/Predator_prey

function fitzhugh_nagumo

fs = 18; % Font size for plots
% Parameter values   
a = 0.1;
I_a = 0.5;  % applied current
e = 0.01;   % epsilon
b = 1;
l = 0.5;    % alpha

ICs = [-5 45; 3 -35]; % Initial conditions
finaltime = 20;
t = 0:(finaltime/1000):finaltime; % times at which solution will be plotted

h = figure; % generate a phase plot figure
hold on; % "hold" the figure. This allows successive plots to be overlaid on the same figure

slq = 205; % side length of square quiver plot region

% Add a quiver plot (field of velocity vectors)   
[vgrid, wgrid] = meshgrid(-50:(slq/25):50, -100:(slq/25):100); % set up grid for use with quiver
dv = dv_dt(vgrid,wgrid,a,I_a,e);
dw = dw_dt(vgrid,wgrid,b,l);
L = sqrt(dv.^2+dw.^2); % Compute lengths of velocity vectors
% In this example, I'm plotting normalized velocity vectors
% (dividing velocities by L) since otherwise there is a large
% disparity in orders of magnitude between vectors, which makes
% shorter vectors harder to see on the quiver plot.
q=quiver(vgrid,wgrid,dv./L,dw./L)
set(q,'Linewidth',2.0)

% Plot nullclines
vvalues = -4:0.001:4;

% vNC1
plot(vvalues,(-vvalues.^3 + (a+1)*vvalues.^2 - a*vvalues + I_a),'r','linewidth',2.0);

% wNC1
plot(vvalues, b*vvalues/l,'b','linewidth',2.0);
grid on;

% For each initial condition listed above, simulate x(t), plot x2 vs. x1,
% and plot x(t) vs. t
for j = 1:size(ICs,1)  % j is a counter variable, starting at 1
    % and ending at the number of rows in ICs
    
    % Use ode45 to compute approximate solution
    [T, X] = ode45(@(t,x) dxdt(t,x,a,I_a,e,b,l),[0 20],ICs(j,:));
    
    % Add solution curve to phase plane:
    figure(h)
    p(j) = plot(X(:,1),X(:,2),'linewidth',2); % plot x2 vs. x1
    
    % Highlight limit cycle (plot later times in a different color).
    % Note that not all systems will have a limit cycle.
    plot(X(5*round(end/20):end,1),X(5*round(end/20):end,2),'g','linewidth',3); % plot x2 vs. x1
    
    % Create a time-series plot
    hts(j) = figure;
    p1=plot(T,X(:,1),'b', T, X(:,2),'k--');
%     xlim([-25 50])
%     ylim([-50 100])
    set(p1,'linewidth',2);
    l1=legend('v','w');
    xlabel('time');
    ylabel('state variables');
    title({'FitzHugh Nagumo', 'of a neuron'})
    set(gca,'fontsize',fs)
end

figure(h)
% Add labels to plot:
title({'FitzHugh Nagumo', 'of a neuron'})
xlabel('v, membrane potential of the cell');
ylabel('w, recovery variable');
set(gca,'fontsize',fs)
%axis([-4 4 -4 4])


% Bendixson's criterion
x = -1:0.01:2;
y = -150:1:150;
[xx,yy] = ndgrid(x,y);
mesh(x,y,-10*(30*x.^2 - 22*x - 10.5))

%% Hints
% 1) Compute Fbc = df1/dx1 + df2/dx2 (or = dF/dx + dG/dy in Edelstein-Keshet's
% notation) 
% 2) Use surf, mesh, or contour to plot Fbc vs. N vs. P

xlabel('v')
ylabel('w')
zlabel('F_{BC}')
title('Bendixson''s Criterion Check')
set(gca,'fontsize',fs)

% Below, nested functions were used in order to produce a set of functions
% that can be called by both ode45 and quiver. There are other ways to
% organize these computations, so you may use a different approach if
% desired.    a,I_a,e,b,v

function out = dxdt(t,x,a,I_a,e,b,l)
% This function provides the structure of the ODE (function returns dx/dt = f(x))
out(1,:) = dv_dt(x(1),x(2),a,I_a,e);
out(2,:) = dw_dt(x(1),x(2),b,l);

% Subfunctions called by dxdt:
function dv_out = dv_dt(v,w,a,I_a,e)
dv_out = ((v.*(a-v).*(v-1) - w + I_a)/e);

function dw_out = dw_dt(v,w,b,l)
dw_out = b*v-l*w;