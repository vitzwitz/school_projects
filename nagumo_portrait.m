function nagumo_portrait(a,b)

%% a, b, e are positive constants
%%%%%%%%%%% Model 2A %%%%%%%%%%%%%
%% Part 1: a=0.1,   b=0.5,  e=0.01
%% Part 2: a=0.2,   b=0.3,  e=0.01
%% Part 3: a=0.1,   b=0.15, e=0.01
%%%%%%%%%%% Model 2B %%%%%%%%%%%%%
%% Part 1: a= -0.1, b=0.5,  e=0.01


e = 0.01; %% Same for all Parts


u = -0.5:0.05:1.5; v = -0.05:0.01:0.2;


[uu,vv] = ndgrid(u,v);

dudt = @(u,v) (a-u).*(u-1).*u-v;
dvdt = @(u,v) e.*(b*u-v);

arrowscale = 3;

quiver(uu,vv,dudt(uu,vv),dvdt(uu,vv),arrowscale,'linewidth',1,'color','k')
hold on

%% Nullclines %%

fu =@(u) (a-u).*(u-1).*u;       %% u-Nullcline
fv =@(u) u*b;                   %% v-Nullcline


plot(u,fu(u),'c','linewidth',1.3)
hold on
plot(u,fv(u),'b','linewidth',1.3)
hold on

%% fixed points
% %%%%Doesn't exist for set A or B
% u1 = ((a+1)+sqrt((a-1).^2-4*b))/2;
% v1 = (b*((a+1)+sqrt((a-1).^2-4*b)))/2;
% %%%%Doesn't exist for Set A or B
% u2 = ((a+1)-sqrt((a-1).^2-4*b))/2;
% v2 = (b*((a+1)-sqrt((a-1).^2-4*b)))/2;
% %%%%Exists for all sets
% u3 = 0;
% v3 = 0;
% 
% plot([u1],[v1],'go')
% hold on
% plot([u2],[v2],'bo')
% hold on
% plot([u3],[v3],'ro')
% 
% hold off

xlim([-0.5 1.5])
ylim([-0.05 0.2])
%% Recall %%
%% dudt = @(u,v) (a-u).*(u-1).*u-v;
%% dvdt = @(u,v) e.*(b*u-v)
%%% Initial Value [u;v] = [0 0.1]
dXdt =@(t,X) [((a-X(1)).*(X(1)-1).*X(1))-X(2);  e.*(b*X(1)-X(2))];
[t,Xout] = ode45(dXdt,[0 1000],[0.1;0]);
plot(Xout(:,1),Xout(:,2),'g','linewidth',1.5)
hold on
%%% Initial Value [u;v] = [0 0.25]
dXdt =@(t,X) [((a-X(1)).*(X(1)-1).*X(1))-X(2);  e.*(b*X(1)-X(2))];
[t,Xout] = ode45(dXdt,[0 1000],[0.25;0]);
plot(Xout(:,1),Xout(:,2),'m','linewidth',1.5)
hold off

xlim([-0.5 1.8])
ylim([-0.05 0.2])
xlabel('u')
ylabel('v')
title('FitzHugh-Nagumo a=0.1, b=0.5')
legend('vector field','u-NC','v-NC','Traj-[u0;v0]=[0.1;0]','Traj-[u0;v0]=[0.25;0]')

end