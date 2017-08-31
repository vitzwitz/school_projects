function white_comiskey

% Bifurcation Diagram
%
% Comparing the probability of becoming a drug user and
% the death/removal rate of a drug users

% Constants

% Descriptions

% beta_1  = probablility of a susceptible individual becoming a drug user
% delta_1 = removal/death rate for drug users

u = 0.05;       % natural death rate
p = 0.1;        % proportion= drug users who enter treatment:time

% Domains
x = -0.15:0.01:0.85;
[X,Y] = meshgrid(-0.15:0.01:0.85,0:0.01:1);
                        % delta_1 -> x
                        % beta_1 - > y
bnd = Y > u + p + X;    % Condition for stability    
colors = zeros(size(X)) + bnd; scatter(X(:),Y(:),3,colors(:),'filled')
hold on
f =@(x) p + u + x;
plot(x,f(x),'k--','LineWidth',3)
hold off
xlim([-0.15 0.85])
ylim([0 1])
title('Stability Bifurcation Diagram')
xlabel('Delta_1')
ylabel('Beta_1')
legend({'Stable','Beta_1< Delta_1 + p + mu'},'Location','southeast')

%% Phase Portrait

 set(0,'defaultaxesfontsize',10);
s = -0.5:0.06:1.5;  % For visualling Purposes only
u = -0.5:0.06:2;    % Domain & Range must be positive 
                    % because you cannot have negative
                    % populations

m = 0.05;           % Natural Death Rate
p = 0.1;            % Proportion = 

b1 = 0.2;
b3 = 0.8;

Ld1 = 0.01;     % Unstable
Hd1 = 0.2;      % Stable

d2 = 0.05;

arrowscale = 10;

% Collected constants

B  = b3 - b1;
DL = d2 - Ld1;
DH = d2 - Hd1;
wL = b3 - p - m - Ld1;
wH = b3 - p - m - Hd1;
g = m + d2;

% Nontrivial Fixed Points

% delta_1 < delta_2
sstar_2a = -([b1*wL + B*DL + b3*g]+sqrt((b1*wL + B*DL + b3*g)^2 + 4*b1 + B*(wL*DL - b3*g)))/(2*b1*B);
sstar_3a = -([b1*wL + B*DL + b3*g]-sqrt((b1*wL + B*DL + b3*g)^2 + 4*b1 + B*(wL*DL - b3*g)))/(2*b1*B);

ustar_2a = (wL - B*sstar_2a)/b3;
ustar_3a = (wL - B*sstar_3a)/b3;

% delta_1 > delta_2
sstar_2b = -([b1*wH + B*DH + b3*g]+sqrt((b1*wH + B*DH + b3*g)^2 + 4*b1 + B*(wH*DH - b3*g)))/(2*b1*B);
sstar_3b = -([b1*wH + B*DH + b3*g]-sqrt((b1*wH + B*DH + b3*g)^2 + 4*b1 + B*(wL*DH - b3*g)))/(2*b1*B);

ustar_2b = (wH - B*sstar_2b)/b3;
ustar_3b = (wH - B*sstar_3b)/b3;


% Coupled ODEs

% Saddle Node = (1,0)
fsL =@(s,u) m + d2 + (Ld1 - d2)*u - (m + d2)*s - b1*u.*s;
fuL =@(s,u) (b3 - p*m - Ld1)*u + (b1 - b3)*u.*s - b3*u.^2;

% Stable Node = (1,0)
fsH =@(s,u) m + d2 + (Hd1 - d2)*u - (m + d2)*s - b1*u.*s;
fuH =@(s,u) (b3 - p*m - Hd1)*u + (b1 - b3)*u.*s - b3*u.^2;



% Saddle Node -> (1,0)

% Directional Field
[ss,uu] = ndgrid(s,u);

dsdt1 = @(s,u) m + d2 + (Ld1 - d2)*u - (m + d2)*s - b1*u.*s;
dudt1 = @(s,u) (b3 - p*m - Ld1)*u + (b1 - b3)*u.*s - b3*u.^2;
figure
quiver(ss,uu,dsdt1(ss,uu),dudt1(ss,uu),arrowscale,'linewidth',1,'color','k')
hold on

% Nullclines %

fu1 =@(s) (wL - B*s)./b3;                       % u-Nullcline
line([-0.5 2], [0 0],'Markersize',10)                         % u-Nullcline
fs1 =@(s) ((m + d2)*(s-1))./(DL + b1*s);        % s-Nullcline

plot(s,fu1(s),'b','linewidth',1.3)
hold on
plot(s,fs1(s),'c','linewidth',1.3)
hold on

% Fixed Points
plot([1],[0],'ko')
hold on

% Starting with low population of drug users compared to susceptibles
dXdtL =@(t,X) [m + d2 + (Ld1 - d2)*X(2) - (m + d2)*X(1) - b1*X(2).*X(1);...
    (b3 - p*m - Ld1)*X(2) + (b1 - b3)*X(2).*X(1) - b3*X(2).^2];
[t,Xout1] = ode45(dXdtL,[0 1000],[0.90;0.05]);
plot(Xout1(:,1),Xout1(:,2),'g','linewidth',3)
hold on

% Starting with high population of drug users compared to susceptibles
dXdtL =@(t,X) [m + d2 + (Ld1 - d2)*X(2) - (m + d2)*X(1) - b1*X(2).*X(1);...
    (b3 - p*m - Ld1)*X(2) + (b1 - b3)*X(2).*X(1) - b3*X(2).^2];
[t,Xout2] = ode45(dXdtL,[0 1000],[0.1;0.8]);
plot(Xout2(:,1),Xout2(:,2),'m','linewidth',3)

% Starting with equal population of drug users compared to susceptibles
dXdtL =@(t,X) [m + d2 + (Ld1 - d2)*X(2) - (m + d2)*X(1) - b1*X(2).*X(1);...
    (b3 - p*m - Ld1)*X(2) + (b1 - b3)*X(2).*X(1) - b3*X(2).^2];
[t,Xout5] = ode45(dXdtL,[0 1000],[0.4;0.4]);
plot(Xout5(:,1),Xout5(:,2),'r','linewidth',2)

% Graph Details
xlim([-0.5 1.5])
ylim([-0.5 1.5])
xlabel('Susceptibles/N, s')
ylabel('Drug Users/N, u')
title('Phase Portrait for Saddle Node')
hold off



% Stable Node

% Directional Field
[ss,uu] = ndgrid(s,u);

dsdt2 = @(s,u) m + d2 + (Hd1 - d2)*u - (m + d2)*s - b1*u.*s;
dudt2 = @(s,u) (b3 - p*m - Hd1)*u + (b1 - b3)*u.*s - b3*u.^2;
figure
quiver(ss,uu,dsdt2(ss,uu),dudt2(ss,uu),arrowscale,'linewidth',1,'color','k')
hold on

% Nullclines %

fu2 =@(s) (wH - B*s)/b3;                        % u-Nullcline
line([-0.5 2], [0 0],'MarkerSize',10)           % u-Nullcline
fs2 =@(s) ((m + d2)*(s-1))./(DH + b1*s);        % s-Nullcline

plot(s,fu2(s),'b','linewidth',1.3)
hold on
plot(s,fs2(s),'c','linewidth',1.3)
hold on

% Fixed Points
plot([1],[0],'.k','MarkerSize',20)
hold on


% Starting with low population of drug users compared to susceptibles
dXdtH =@(t,X) [m + d2 + (Hd1 - d2)*X(2) - (m + d2)*X(1) - b1*X(2).*X(1);...
    (b3 - p*m - Hd1)*X(2) + (b1 - b3)*X(2).*X(1) - b3*X(2).^2];
[t,Xout3] = ode45(dXdtH,[0 10000],[0.90;0.05]);
plot(Xout3(:,1),Xout3(:,2),'g','linewidth',3)
hold on

% Starting with high population of drug users compared to susceptibles
dXdtH =@(t,X) [m + d2 + (Hd1 - d2)*X(2) - (m + d2)*X(1) - b1*X(2).*X(1);...
    (b3 - p*m - Hd1)*X(2) + (b1 - b3)*X(2).*X(1) - b3*X(2).^2];
[t,Xout4] = ode45(dXdtH,[0 1000],[0.1;0.8]);
plot(Xout4(:,1),Xout4(:,2),'m','linewidth',2)

% Starting with low population of drug users compared to susceptibles
dXdtH =@(t,X) [m + d2 + (Hd1 - d2)*X(2) - (m + d2)*X(1) - b1*X(2).*X(1);...
    (b3 - p*m - Hd1)*X(2) + (b1 - b3)*X(2).*X(1) - b3*X(2).^2];
[t,Xout6] = ode45(dXdtH,[0 1000],[0.4;0.4]);
plot(Xout6(:,1),Xout6(:,2),'r','linewidth',2)

% Graph Details
xlabel('Susceptibles/N, s')
ylabel('Drug Users/N, u')
title('Phase Portrait for Stable Node')
xlim([0 1.5])
ylim([-0.5 1.5])
hold off

end
