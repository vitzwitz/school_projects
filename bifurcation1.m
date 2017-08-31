function bifurcation1

%This script produces a bifurcation diagram for the system in model 1
%It's a saddle node bifurcation

h = 0:0.001:0.25;

f1 = @(h)[1 - sqrt(1-4.*h)]/2;         %Stable fixed point
f2 = @(h)[1 + sqrt(1-4.*h)]/2;         %Unstable fixed point


plot(h,f1(h))           %Solid lines represent stable

hold on

plot(h,f2(h),'--')      %Dashed lines represent unstable

ylim([0 1])
xlim([0,0.5])

legend('stable fps','unstable fps')
title('Saddle Node Bifurcation in Model 1')
xlabel('h')
ylabel('x')
