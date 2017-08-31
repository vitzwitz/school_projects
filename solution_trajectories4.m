function solution_trajectories4(x_0)
%This script solves ODEs for the tree population models, and plots the
%solution curves
%x_0 represents IC
%t represents tau and x represents x from the dimensionless model

% f = @(t,x) x - x^2 - hx    Model 2
% Bounds are set up for:     x(0) = 0.9                            


f1 = @(t,x) x - x.^2 - 0.9*x;             %h = hc - 0.1

[tout,xout2] = ode45(f1,[0,30],x_0);     %Numerically integration that find
                                           %solution curves

plot(tout,xout2)
hold on 

f2 = @(t,x) x - x.^2 - x;                    %h = hc

[tout2,xout3] = ode45(f2,[0,30],x_0);

plot(tout2,xout3)

hold on

f3 = @(t,x) x - x.^2 - 1.1*x;             %h = hc + 0.1
 
[tout3,xout4] = ode45(f3,[0,30],x_0);
 
plot(tout3,xout4)

title('Solution Trajectories of Model 2 with x(0)=0.9')
ylabel('Dimensionless Population Variable, x')
xlabel('Dimensionless Time Variable, tau')
legend('h=hc-0.1','hc','hc + 0.1')

ylim([-0.1,0.9])

grid on


end