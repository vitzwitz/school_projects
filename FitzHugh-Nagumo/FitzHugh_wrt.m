function FitzHugh_wrt

u = -0.5:0.05:1.5; v = -0.05:0.01:0.2;

%% Constants %%
a = 0.1;
b = 0.5;
e = 0.01;

fu = @(t,u,v) (a-u).*(u-1).*u-v;
fv = @(t,u,v) e.*(b*u-v);

%% Initial Condition of u(0) = 0.1; v(0) = 0 %%
dXdt1 =@(t,X) [((a-X(1)).*(X(1)-1).*X(1))-X(2);  e.*(b*X(1)-X(2))];
[t,Xout] = ode45(dXdt1,[0 300],[0.1;0]);
plot(t,Xout(:,1),'r','linewidth',1.5)   %% u trajectory
hold on
plot(t,Xout(:,2),'b','linewidth',1.5)  %% v trajectory
hold on

%% Initial Condition of u(0) = 0.25; v(0) = 0 %%

dXdt2 =@(t,X) [((a-X(1)).*(X(1)-1).*X(1))-X(2);  e.*(b*X(1)-X(2))];
[t,Xout] = ode45(dXdt2,[0 300],[0.25;0]);
plot(t,Xout(:,1),'kx','linewidth',1.5) %% u trajectory
hold on
plot(t,Xout(:,2),'g','linewidth',2) %% v trajectory
hold on

xlabel('time, [s]')
ylabel('u,v')
title('Trajectories of u,v vs. time when a = 0.1, b = 0.5')
legend('u-Traj [u0,v0]=[0.1;0]','v-Traj [u0,v0]=[0.1;0]','u-Traj [u0,v0]=[0.25;0]','v-Traj [u0,v0]=[0.25;0]','Location','southoutside')


end
