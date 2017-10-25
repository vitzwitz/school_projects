function phase_portrait_control

%% Phase Portrait for FitzHugh-Nagumo Model with control feedback

x = -1.5:0.05:2;
y = -1.5:0.05:2;

a = -0.1;
b = 0.5;
e = 0.01;
arrowscale = 12;

%% Coupled ODEs
k1 = 0.025;      % 1 stable fixed point, 2 unstable fixed points-> 3 fp's total
fx1=@(x,y) x.*(-x.^2 + (a+1).*x -(k1+a))-y;
fy1=@(x,y) e*(b*x-y)-k1*y;

k2 = 0.15;     % 2 stable fixed points, 1 unstable fixed point-> 3 fp's total
fx2=@(x,y) x.*(-x.^2 + (a+1).*x -(k2+a))-y;
fy2=@(x,y) e*(b*x-y)-k2*y;

k3 = 0.3;     % 2 stable fixed points-> 2 fp's total
fx3=@(x,y) x.*(-x.^2 + (a+1).*x -(k3+a))-y;
fy3=@(x,y) e*(b*x-y)-k3*y;
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%         k = 0.025
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% Directional Field
[xx,yy] = ndgrid(x,y);

dxdt1 = @(x,y) x.*(-x.^2 + (a+1).*x -(k1+a))-y;
dydt1 = @(x,y) e*(b*x-y)-k1*y;
figure
quiver(xx,yy,dxdt1(xx,yy),dydt1(xx,yy),arrowscale,'linewidth',1,'color','k')
hold on
 %% Nullclines %%

fu1 =@(x) (a-x).*(x-1).*x-k1*x;       %% u-Nullcline
fv1 =@(x) x*e*b*(1/k1+e);             %% v-Nullcline


plot(x,fu1(x),'c','linewidth',1.3)
hold on
plot(x,fv1(x),'b','linewidth',1.3)
hold on



%%% Initial Value [u;v] = [0.4; 0.2]
dXdt =@(t,X) [(X(1).*(-X(1).^2 + (a+1).*X(1) -(k1+a))-X(2));  e*(b*X(1)-X(2))-k1*X(2)];
[t,Xout] = ode45(dXdt,[0 1000],[0.4;0.02]);
plot(Xout(:,1),Xout(:,2),'g','linewidth',1.5)
hold on
%%% Initial Value [u;v] = [1; 0.2]
dXdt =@(t,X) [(X(1).*(-X(1).^2 + (a+1).*X(1) -(k1+a))-X(2));  e*(b*X(1)-X(2))-k1*X(2)];
[t,Xout] = ode45(dXdt,[0 1000],[1;0.2]);
plot(Xout(:,1),Xout(:,2),'m','linewidth',1.5)
hold off

xlim([-0.5 1.5])
ylim([-0.5 0.5])
xlabel('u')
ylabel('v')
title({'FitzHugh-Nagumo with Control Feedback';' a=-0.1, b=0.5 e=0.01 k=0.025'})
legend('Flow','u-NC','v-NC','IC = [0.4;0.02]','IC = [1; 0.2]','Location','southoutside','Orientation','horizontal')


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%       k = 0.15
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

%% Directional Field
[xx,yy] = ndgrid(x,y);

dxdt2 = @(x,y) x.*(-x.^2 + (a+1).*x -(k2+a))-y;
dydt2 = @(x,y) e*(b*x-y)-k2*y;
figure
quiver(xx,yy,dxdt2(xx,yy),dydt2(xx,yy),arrowscale,'linewidth',1,'color','k')
hold on
 %% Nullclines %%

fu2 =@(x) (a-x).*(x-1).*x-k2*x;       %% u-Nullcline
fv2 =@(x) x*e*b*(1/k2+e);             %% v-Nullcline


plot(x,fu2(x),'c','linewidth',1.3)
hold on
plot(x,fv2(x),'b','linewidth',1.3)
hold on




%%% Initial Value [u;v] = [0.6; -0.25]
dXdt =@(t,X) [(X(1).*(-X(1).^2 + (a+1).*X(1) -(k2+a))-X(2));  e*(b*X(1)-X(2))-k2*X(2)];
[t,Xout] = ode45(dXdt,[0 1000],[0.6;-.25]);
plot(Xout(:,1),Xout(:,2),'g','linewidth',1.5)
hold on
%%% Initial Value [u;v] = [-0.2;0.25]
dXdt =@(t,X) [(X(1).*(-X(1).^2 + (a+1).*X(1) -(k2+a))-X(2));  e*(b*X(1)-X(2))-k2*X(2)];
[t,Xout] = ode45(dXdt,[0 1000],[-0.2;0.25]);
plot(Xout(:,1),Xout(:,2),'m','linewidth',1.5)
hold off

xlim([-0.75 1.5])
ylim([-0.5 0.5])
xlabel('u')
ylabel('v')
title({'FitzHugh-Nagumo with Control Feedback';' a=-0.1, b=0.5 e=0.01 k = 0.15'})
legend('Flow','u-NC','v-NC','IC = [0.6; -0.25]','IC = [-0.2;0.25]','Location','southoutside','Orientation','horizontal')


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%                      k = 0.3
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% Directional Field
[xx,yy] = ndgrid(x,y);

dxdt3 = @(x,y) x.*(-x.^2 + (a+1).*x -(k3+a))-y;
dydt3 = @(x,y) e*(b*x-y)-k3*y;
figure
quiver(xx,yy,dxdt3(xx,yy),dydt3(xx,yy),arrowscale,'linewidth',1,'color','k')
hold on

 %% Nullclines %%

fu3 =@(x) (a-x).*(x-1).*x-k3*x;       %% u-Nullcline
fv3 =@(x) x*e*b*(1/k3+e);             %% v-Nullcline


plot(x,fu3(x),'c','linewidth',1.3)
hold on
plot(x,fv3(x),'b','linewidth',1.3)
hold on



%%% Initial Value [u;v] = [-0.4; -0.25]
dXdt =@(t,X) [(X(1).*(-X(1).^2 + (a+1).*X(1) -(k3+a))-X(2));  e*(b*X(1)-X(2))-k3*X(2)];
[t,Xout] = ode45(dXdt,[0 1000],[-0.4;-0.25]);
plot(Xout(:,1),Xout(:,2),'g','linewidth',1.5)
hold on
%%% Initial Value [u;v] = [0.55; 0.25]
dXdt =@(t,X) [(X(1).*(-X(1).^2 + (a+1).*X(1) -(k3+a))-X(2));  e*(b*X(1)-X(2))-k3*X(2)];
[t,Xout] = ode45(dXdt,[0 1000],[0.55;0.25]);
plot(Xout(:,1),Xout(:,2),'m','linewidth',1.5)
hold off

xlim([-0.75 1.5])
ylim([-0.5 0.5])
xlabel('u')
ylabel('v')
title({'FitzHugh-Nagumo with Control Feedback';' a=-0.1, b=0.5 e=0.01 k=0.3'})
legend('Flow','u-NC','v-NC','IC = [-0.4;-0.25]','IC = [0.55;0.25]','Location','southoutside','Orientation','horizontal')
end