function excitable

r = 0.98;
x = -pi:0.01:pi;     %Theta in radians

f =@(t,x) r+sin(x);

[ta,xa] = ode45(f, [0 10], -2);     % Left of Stable FP
[tb,xb] = ode45(f, [0 10], -1.7);   % Right of Stable FP
[tc,xc] = ode45(f, [0 10], -1.4);   % Left of Unstable FP
[td,xd] = ode45(f, [0 10], -1);     % Right of Unstable FP    
[t1,x1] = ode45(f, [0 10], 0);
[t2,x2] = ode45(f, [0 10], 1);
[t3,x3] = ode45(f, [0 10], pi);
[t4,x4] = ode45(f, [0 10], 2*pi);

%% Plots
plot(ta,xa,'c')
hold on
plot(tb,xb,'m')
hold on
plot(tc,xc,'g*')
hold on
plot(td,xd,'r*')
hold on
plot(t1,x1,'c')
hold on
plot(t2,x2,'m')
hold on
plot(t3,x3,'b')
hold on
plot(t4,x4,'ks')

ylim([-4 12])
xlabel('time')
ylabel('theta')
title('Trajectories For Excitable Dynamics')
legend('Left of Stable FP x= -2','Right of St. FP x= -1.7','Left of Unst. FP x= -1.4','Right of Unst. FP x= -1','x = 0','x = 1','x = pi','x = 2pi','Location','eastoutside')

end