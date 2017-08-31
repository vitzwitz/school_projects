%% Descriptions

% Variables:
% P: the price of a product defined at time t
% D: total amount of demand in the global market of the product at time t
% S: the total supply amount in the global market of the product at time t

% Functions
% F_d: the price fluctuations of related goods
% F_s: the cost of resource, prices of other commodities, and taxes and subsidies

% Constants:
% a: amplitude for F_d(t)
% w_1: angular frequency for F_d(t)
% **********************************
% c: the production technology
% b: amplitude for F_s(t)
% w_2: angular frequency for F_s(t)
% **********************************
% P_d: the demand threshold
% P_s: the supply threshold
%
% alpha: the rate of change in price
% beta: the collectibility factor  (Think: Collectibles, Worth more/higher 
%                                           demand than appears)
% beta_1: the saturation factor    (Think: Product has hit max possible
%                                          customers)   
% gamma: the factor of high price
% delta: the factor of high demand

% Oscillatory Functions
F_d =@(t) a*sin(w_1*t);
F_s =@(t) c + b*sin(w_2*t);
% Coupled System of Differential Equations
PDS =@(t,XO) [alpha*(XO(2) - XO(3));...
    beta*(P_d - XO(1)).*(1 - beta_1*(P_d - XO(1)).^2) + a*sin(w_1*t);...
    - gamma*(P_s - XO(1)) + delta*(XO(2) - XO(3)) + c + b*sin(w_2*t)];

%% Original Model

% Wrapping Paper:
% High thresholds, low saturation, low collectability,
% high demand, low price often needed,
% low price flutuations, low cost of resources

alpha = 1/2;        % price stays around the same
beta = 1/8;         % low collectibility
beta_1 = 1/8;       % low saturation factor
delta = 3/4;        % often needed
gamma = 1/4;        % Cheap
P_d = 2;            % from 1 < beta_1*P_d^2
P_s = 2;            % Always pop, so high supply threshold
c = 5/4;            % high thresholds, so high labor needed
w_1 = 1;            % w = 1/T -> needed entire season, so T = 1
w_2 = 1;       
a = 1;              % Needed equally throughout the seasons   
b = 1;              % 
T = 0.4;                  
x0 = [5; 5; 5];

rap =@(t,X1) [alpha.*(X1(2) - X1(3));...
    beta*(P_d - X1(1)).*(1 - beta_1*(P_d - X1(1)).^2) + a*sin(w_1*t);...
    - gamma*(P_s - X1(1)) + delta*(X1(2) - X1(3)) + c + b*sin(w_2*t)];


% Numerically Integrate
[t,X1] = ode45(rap,[0,T],x0);

% Time Series 
figure;
plot(t,X1(:,1),'r-x','linewidth',2)
hold on
plot(t,X1(:,2),'g-x','linewidth',2)
hold on
plot(t,X1(:,3),'b-x','linewidth',2)
hold on

% Holiday Ornaments
% Low thresholds, high saturation, 
% high countability, low demand, high price,
% not often needed, high price flutuations,
% high cost of resources

alpha = 7/8;        % price changes often because of holidays
beta = 7/8;         % high collectibility factor
beta_1 = 7/8;       % high saturat
delta = 1/4;        % low demand
gamma = 3/4;        % overly priced & worse than first product
P_d = 1/2;          % from 1 < beta_1*P_d^2
P_s = 1/2;          % Not often pop, so low supply threshold 
c = 1/4;            % low labor need because low thresholds
w_1 = 2*pi;         % w = 1/T -> needed in beginning of season because high
w_2 = 2*pi;         % saturation, so T = 1/2
a = 2*pi;              % when popular, it's very popular
b = 2*pi;
T = 0.4;                
x0 = [5; 5; 5];

orn =@(t,X2) [alpha.*(X2(2) - X2(3));...
    beta*(P_d - X2(1)).*(1 - beta_1*(P_d - X2(1)).^2) + a*sin(w_1*t);...
    - gamma*(P_s - X2(1)) + delta*(X2(2) - X2(3)) + c + b*sin(w_2*t)];


% Numerically Integrate
[t,X2] = ode45(orn,[0,T],x0);

% Time Series 
plot(t,X2(:,1),'r','linewidth',2)
hold on
plot(t,X2(:,2),'g','linewidth',2)
hold on
plot(t,X2(:,3),'b','linewidth',2)
hold off
ylim([0 50])
xlabel('time')
ylabel('P(t), D(t), S(t)')
legend('P(t) for Paper','D(t) for Paper','S(t) for Paper',...
    'P(t) for Ornaments','D(t) for Ornaments','S(t) for Ornaments',...
    'Location','bestoutside')
title('Time Series for Wrapping Paper and Ornaments')

%% Changing Model

%% ||********************************************************************||
%  ||     Changing Saturation Factor to terms of the Demand variable     ||
%  ||********************************************************************||


% Wrapping Paper:
alpha = 0.5;        % price stays around the same
beta = 1/8;         % low collectibility
delta = 3/4;        % often needed
gamma = 1/4;        % Cheap
P_d = 2;            % from 1 < beta_1*P_d^2
P_s = 2;            % Always pop, so high supply threshold
c = 5/4;            % high thresholds, so high labor needed
w_1 = 1;            % w = 1/T -> needed entire season, so T = 1
w_2 = 1;       
a = 1;              % Needed equally throughout the seasons   
b = 1;              % 
T = 0.4;                     
x0 = [5; 5; 5];

raps =@(t,XS1) [alpha*(XS1(2) - XS1(3));...
    beta*(P_d - XS1(1)).*(1 - (1/(P_d - XS1(2))).*(P_d - XS1(1)).^2) + a*sin(w_1*t);...
    - gamma*(P_s - XS1(1)) + delta*(XS1(2) - XS1(3)) + c + b*sin(w_2*t)];
% Numerically Integrate
[t,XS1] = ode45(raps,[0,T],x0);

% Time Series 
figure;
plot(t,XS1(:,1),'r-x','linewidth',2)
hold on
plot(t,XS1(:,2),'g-x','linewidth',2)
hold on
plot(t,XS1(:,3),'b-x','linewidth',2)
hold on

% Holiday Ornaments
% Low thresholds, high saturation, high countability, low demand, high
% price, not often needed, high price flutuations, high cost of resources

alpha = 7/8;        % price changes often because of holidays
beta = 7/8;         % high collectibility factor
delta = 1/4;        % low demand
gamma = 3/4;        % overly priced & worse than first product
P_d = 1/2;          % from 1 < beta_1*P_d^2
P_s = 1/2;          % Not often pop, so low supply threshold 
c = 1/4;            % low labor need because low thresholds
w_1 = 2*pi;         % w = 1/T -> needed in beginning of season because high
w_2 = 2*pi;         % saturation, so T = 1/2
a = 2*pi;              % when popular, it's very popular
b = 2*pi;
T = 0.4;              
x0 = [5; 5; 5];

orns =@(t,XS2) [alpha*(XS2(2) - XS2(3));...
    beta*(P_d - XS2(1)).*(1 - (1/(P_d - XS2(2))).*(P_d - XS2(1)).^2) + a*sin(w_1*t);...
    - gamma*(P_s - XS2(1)) + delta*(XS2(2) - XS2(3)) + c + b*sin(w_2*t)];


% Numerically Integrate
[t,XS2] = ode45(orns,[0,T],x0);

% Time Series 
plot(t,XS2(:,1),'r','linewidth',2)
hold on
plot(t,XS2(:,2),'g','linewidth',2)
hold on
plot(t,XS2(:,3),'b','linewidth',2)
hold off
xlabel('time')
ylabel('P(t), D(t), S(t)')
legend('P(t) for Paper','D(t) for Paper','S(t) for Paper',...
    'P(t) for Ornaments','D(t) for Ornaments','S(t) for Ornaments',...
    'Location','bestoutside')
title({'Time Series for Wrapping Paper and Ornaments','For Saturation Factor Change'})

%% ||**********************************************||
%  ||     Adjusting Price Fluctuations Function    ||
%  ||**********************************************||

% Wrapping Paper:
alpha = 0.5;        % price stays around the same
beta = 1/8;         % low collectibility
delta = 3/4;        % often needed
gamma = 1/4;        % Cheap
P_d = 2;            % from 1 < beta_1*P_d^2
P_s = 2;            % Always pop, so high supply threshold
c = 5/4;            % high thresholds, so high labor needed
w_1 = 1;            % w = 1/T -> needed entire season, so T = 1
w_2 = 1;       
a = 1;              % Needed equally throughout the seasons   
b = 1;              % 
T = 0.4;                   
x0 = [5; 5; 5];

rapsanta =@(t,Xf1) [ alpha*(Xf1(2) - Xf1(3));...
    beta*(P_d - Xf1(1)).*(1 - (1/(P_d - Xf1(2))).*(P_d - Xf1(1)).^2) + a*sin(w_1*t);...
    - gamma*(P_s - Xf1(1)) + delta*(Xf1(2) - Xf1(3)) + c + b*sin(-w_2*t)];
% Numerically Integrate
[t,Xf1] = ode45(rapsanta,[0,T],x0);

% Time Series 
figure;
plot(t,Xf1(:,1),'r-x','linewidth',2)
hold on
plot(t,Xf1(:,2),'g-x','linewidth',2)
hold on
plot(t,Xf1(:,3),'b-x','linewidth',2)
hold on

% Holiday Ornaments:
alpha = 7/8;        % price changes often because of holidays
beta = 7/8;         % high collectibility factor
delta = 1/4;        % low demand
gamma = 3/4;        % overly priced & worse than first product
P_d = 1/2;          % from 1 < beta_1*P_d^2
P_s = 3/4;          % Not often pop, so low supply threshold 
c = 1/4;            % low labor need because low thresholds
w_1 = 2*pi;         % w = 1/T -> needed in beginning of season because high
w_2 = 2*pi;         % saturation, so T = 1/2
a = 2*pi;              % when popular, it's very popular
b = 2*pi;
T = 0.4;              
x0 = [5; 5; 5];

ornsanta =@(t,Xf2) [alpha.*(Xf2(2) - Xf2(3));...
    beta*(P_d - Xf2(1)).*(1 - (1/(P_d - Xf2(2))).*(P_d - Xf2(1)).^2) + a*sin(w_1*t);...
    - gamma*(P_s - Xf2(1)) + delta*(Xf2(2) - Xf2(3)) + c + b*sin(-w_2*t)];


% Numerically Integrate
[t,Xf2] = ode45(ornsanta,[0,T],x0);

% Time Series 
plot(t,Xf2(:,1),'r','linewidth',2)
hold on
plot(t,Xf2(:,2),'g','linewidth',2)
hold on
plot(t,Xf2(:,3),'b','linewidth',2)
hold off
xlabel('time')
ylabel('P(t), D(t), S(t)')
legend('P(t) for Paper','D(t) for Paper','S(t) for Paper',...
    'P(t) for Ornaments','D(t) for Ornaments','S(t) for Ornaments',...
    'Location','bestoutside')
title({'Time Series for Wrapping Paper and Ornaments', 'For Price Fluctuation Factor Change'})

%% Equilbrium Functions

% Wrapping Paper:
alpha = 0.5;        % price stays around the same
beta = 1/8;         % low collectibility
delta = 3/4;        % often needed
gamma = 1/4;        % Cheap
P_d = 2;            % from 1 < beta_1*P_d^2
P_s = 2;            % Always pop, so high supply threshold
c = 5/4;            % high thresholds, so high labor needed
w_1 = 1;            % w = 1/T -> needed entire season, so T = 1
w_2 = 1;       
a = 1;              % Needed equally throughout the seasons   
b = 1;              % 
% t = 0:0.01:0.4;
elf = P_d - P_s;
wrap_eqP =@(t) P_s - (1/gamma)*(c + b*sin(-w_2*t));
wrap_eqD =@(t) P_d - ((beta*(elf + (1/gamma)*(c + b*sin(-w_2*t))).^3)./(beta*(elf + (1/gamma)*(c + b*sin(-w_2*t))) + a*sin(w_1*t)));
wrap_eqS =@(t) P_d - ((beta*(elf + (1/gamma)*(c + b*sin(-w_2*t))).^3)./(beta*(elf + (1/gamma)*(c + b*sin(-w_2*t))) + a*sin(w_1*t)));
% wrap_eq = [wrap_eqP(0:0.01:0.4); wrap_eqD(0:0.01:0.4); wrap_eqS(0:0.01:0.4)];

a11 = zeros(1,41);
a12 = zeros(1,41);
a13 = zeros(1,41);
a31 = zeros(1,41);
a32 = zeros(1,41);
a33 = zeros(1,41);
for i =1:41
a11(i) = 0;
a12(i) = alpha;
a13(i) = -alpha;
a31(i) = gamma;
a32(i) = delta;
a33(i) = -delta;
end

% Jacobian Matrix
J_paperP =@(t) [a11; a12; a13];
J_paperD =@(t) [beta*(1+(3*(P_d-wrap_eqP(t)).^2)./(P_d-wrap_eqD(t)));  -(beta*((P_d - wrap_eqP(t)).^3)/((P_d-wrap_eqP(t)).^2)); 0];
J_paperS =@(t) [a31, a32, a33];

% Characteristic Equation
pp =@(t,L) L.^3 -((beta*((P_d - wrap_eqP(t)).^3)/((P_d-wrap_eqP(t)).^2))-delta).*L.^2 +...
    (-delta*(beta*((P_d - wrap_eqP(t)).^3)/((P_d-wrap_eqP(t)).^2)) + alpha*beta*(1+(3*(P_d-wrap_eqP(t)).^2)./(P_d-wrap_eqD(t)))-alpha*gamma).*L+...
    -alpha*gamma*(beta*((P_d - wrap_eqP(t)).^3)/((P_d-wrap_eqP(t)).^2));
    

% Holiday Ornaments:
alpha = 7/8;        % price changes often because of holidays
beta = 7/8;         % high collectibility factor
delta = 1/4;        % low demand
gamma = 3/4;        % overly priced & worse than first product
P_d = 1/2;          % from 1 < beta_1*P_d^2
P_s = 3/4;          % Not often pop, so low supply threshold 
c = 1/4;            % low labor need because low thresholds
w_1 = 2*pi;         % w = 1/T -> needed in beginning of season because high
w_2 = 2*pi;         % saturation, so T = 1/2
a = 2*pi;              % when popular, it's very popular
b = 2*pi;

t = 0:0.01:0.4;
elf = P_d - P_s;


orn_eqP =@(t) P_s - (1/gamma)*(c + b*sin(-w_2*t));
orn_eqD =@(t) P_d - (beta*(elf + (1/gamma)*(c + b*sin(-w_2*t))).^3)./(beta*(elf + (1/gamma)*(c + b*sin(-w_2*t)) ) + a*sin(w_1*t));
orn_eqS =@(t) P_d - (beta*(elf + (1/gamma)*(c + b*sin(-w_2*t))).^3)./(beta*(elf + (1/gamma)*(c + b*sin(-w_2*t)) ) + a*sin(w_1*t));
% orn_eq = [orn_eqP(0:0.01:0.4); orn_eqD(0:0.01:0.4); orn_eqS(0:0.01:0.4)];


% Jacobian Matrix
J_orn =@(t) [0, alpha, -alpha;...
    beta*(1+(3*(P_d-orn_eqP(t)).^2)./(P_d-orn_eqD(t))), -(beta*((P_d - orn_eqP(t)).^3)/((P_d-orn_eqP(t)).^2)), 0;...
    gamma, delta, -delta];

% Characteristic Equation
po =@(t,L) L.^3 -((beta*((P_d - orn_eqP(t)).^3)/((P_d-orn_eqP(t)).^2))-delta).*L.^2 +...
    (-delta*(beta*((P_d - orn_eqP(t)).^3)/((P_d-orn_eqP(t)).^2)) + alpha*beta*(1+(3*(P_d-orn_eqP(t)).^2)./(P_d-orn_eqD(t)))-alpha*gamma).*L+...
    -alpha*gamma*(beta*((P_d - orn_eqP(t)).^3)/((P_d-orn_eqP(t)).^2));

figure
plot(t,wrap_eqP(t),'r-',t,orn_eqP(t),'bo','linewidth',2)
title('Price Equilibrium Functions')
xlabel('time')
ylabel('Equilibrium Functions')
legend('Wrapping Paper','Ornament','Location','bestoutside')

figure
subplot(2,2,[1,2]);
plot(t,wrap_eqD(t),'r-',t,orn_eqD(t),'bo','linewidth',2)
title('Demand & Supply Equilibrium Functions')
xlabel('time')
ylabel('Equilibrium Functions')
legend('Wrapping Paper','Ornament','Location','bestoutside')

subplot(2,2,[3,4]);
plot(t,wrap_eqS(t),'r-',t,orn_eqS(t),'bo','linewidth',2)
xlabel('time')
ylabel('Equilibrium Functions')
legend('Wrapping Paper','Ornament','Location','bestoutside')



