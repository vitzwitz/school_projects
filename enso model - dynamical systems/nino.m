function nino
% Basic ENSO model

set(0,'defaultaxesfontsize',16);
set(0,'defaultlinelinewidth',2);

% parameter values
ustar=-14.2;
tstar=28;
tbar=16;
delx=7.5;
B=940;
C=3;
r=1;

% derivative functions
du=@(u,tw,te) (B/(2*delx))*(te-tw)-C*(u-ustar);
dtw=@(u,tw,te) u/(2*delx)*(tbar-te)+r*(tstar-tw);
dte=@(u,tw,te) -u/(2*delx)*(tbar-tw)+r*(tstar-te);
x0=[-10; 30; 26]; 

% maximum time in years
tmax=80;

% solve the system
[tout,yout]=ode45(@(t,x) [du(x(1),x(2),x(3)); ...
    dtw(x(1),x(2),x(3)); ...
    dte(x(1),x(2),x(3))],...
    [0 tmax],x0);

% plot the output
figure

subplot(3,1,1)
plot(tout,yout(:,1),'k')
title('Time Series for u,Tw,Te')
ylabel('u')
ylim([-200 200])
grid on
hold on

subplot(3,1,2)
plot(tout,yout(:,2),'k')
ylabel('T_w')
grid on
hold on

subplot(3,1,3)
plot(tout,yout(:,3),'k')
xlabel('time (years)')
ylabel('T_e')
grid on
hold off

% Phase Portrait

figure

plot3(yout(:,1),yout(:,2),yout(:,3),'LineWidth',1.25)

xlabel('u');
ylabel('Tw');
zlabel('Te');
title('Phase Space of Unmodified System')
grid on

% Time Series Graphs

% derivative functions with pertubed u
du=@(u,tw,te) (B/(2*delx))*(te-tw)-C*(u-ustar);
dtw=@(u,tw,te) u/(2*delx)*(tbar-te)+r*(tstar-tw);
dte=@(u,tw,te) -u/(2*delx)*(tbar-tw)+r*(tstar-te);
x0=[-9.99; 30; 26]; 

% maximum time in years
tmax=80;

% re-solve the system
[tout,yout]=ode45(@(t,x) [du(x(1),x(2),x(3)); ...
    dtw(x(1),x(2),x(3)); ...
    dte(x(1),x(2),x(3))],...
    [0 tmax],x0);

% plot
figure
plot(tout,yout(:,1),'r')
xlabel('time')
ylabel('u')
ylim([-200 200])
grid on
title('Time Series of Pertrubed u')
end