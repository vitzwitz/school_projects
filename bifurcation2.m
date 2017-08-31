function bifurcation2

%This script produces a bifurcation diagram for the system in model 2
%It's a transcritical bifurcation

h  = linspace(0,2,25);      % In order to change the linespec of the fixed
h_l = linspace(0,1,25);     % evaluated, an array of h must be created, 
h_r = linspace(1,2,25);     % instead of just a range.

% h_l exists to the left of the critical h value, while h_r exists to the
% right of the critical h value.

f1 =@(h) 0;                 %Equilibrium points
f2 =@(h) 1 - h;


plot(h_l,zeros(size(h_l)),'m--',h_r,zeros(size(h_r)),'m-')
hold on
plot(h_l,f2(h_l),'b',h_r,f2(h_r),'b--')




%% Solid lines represent when fixed point is stable, and dashed
%% lines represent when the fixed point is unstable.

  
ylim([-1 1])

title('Transcritical Bifurcation in Model 2')
xlabel('h')
ylabel('x*')

