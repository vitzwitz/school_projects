% Math Modelling
% Bri Miskovitz
% Final Project
% Part 1

function market(a,b,c,w_1,w_2,P_d,P_s,alpha,beta,beta_1,gamma,delta)

% Descriptions
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

%% Equations
% Oscillatory Functions
F_d =@(t) a*sin(w_1*t);
F_s =@(t) c + b*sin(w_2*t);

% Coupled System of Differential Equations
PDS =@(t,P,D,S) [alpha*(D - S); beta*(P_d - P).*(1 - beta_1*(P_d - P).^2) + F_d(t);...
    - gamma*(P_s - P) + delta*(D - S) + F_s(t)];

% Changing Collectabilitity factor: beta_1 -> D/(1-D)
PDCS =@(t,P,D,S) [alpha*(D - S); beta*(P_d - P).*(1 - (1/(P_d-D)).*(P_d - P).^2) + F_d(t);...
    - gamma*(P_s - P) + delta*(D - S) + F_s(t)];

%% Nullclines
% wronggggggggggg
% % constants for D-NC's
% a1 = beta*beta_1; b1 = -2*beta*beta_1*P_d; c1 = beta*(beta_1*P_d^2 - 1);
% d1 = beta*P_d;
% 
% fP =@(t,D) D;
% fP0 =@(t,P) a1*P.^3 + b1*P.^2 + c1*P + d + F_d(t);
% fS =@(t,D,S) (alpha*P_s - F_s(t) - delta*D + delta*S)/gamma;

%% 3D Phase Plots of D, P, S

figure; plot3(PDS(:,1),PDS(:,2),PDS(:,3),'linewidth',2);
grid on; box on;
xlabel('Price of Product at time t');
ylabel('Demand of Product in Global Market at Time t');
zlabel('Supply of Product in Global Market at Time t');
title('3D Phase Portrait of Price, Demand, and Supply of Product')

figure; plot3(PDCS(:,1),PDCS(:,2),PDCS(:,3),'linewidth',2);
grid on; box on;
xlabel('Price of Product at time t');
ylabel('Demand of Product in Global Market at Time t');
zlabel('Supply of Product in Global Market at Time t');
title('3D Phase Portrait of Price, Demand, and Supply of Product')

%% Time Series

figure;
plot(t,PDS(:,1),'r','linewidth',2)
hold on
plot(t,PDS(:,2),'g','linewidth',2)
hold on
plot(t,PDS(:,3),'b','linewidth',2)
hold off
xlabel('time (s)')
ylabel('Info on Product')
title('Time Series for Price, Demand, and Supply of Product')


%% Solving for Equilibrium Points:
% Original Model
syms P D S
eq1 = alpha*(D - S);
eq2 = beta*(P_d - P).*(1 - beta_1*(P_d - P).^2) + F_d(t);
eq3 = - gamma*(P_s - P) + delta*(D - S) + F_s(t);
sol = solve(eq1,eq2,eq3);  
sol.xo

%% Collectability Change Model
syms P D S
eq4 = alpha*(D - S);
eq5 = beta*(P_d - P).*(1 - (D./(1-D)).*(P_d - P).^2) + F_d(t);
eq6 = - gamma*(P_s - P) + delta*(D - S) + F_s(t);
sol = solve(eq4,eq5,eq6);  
sol.xo


for alpha = 10^(0:4)
PDS =@(t,P,D,S) [alpha*(D - S); beta*(P_d - P).*(1 - beta_1*(P_d - P).^2) + F_d(t);...
    - gamma*(P_s - P) + delta*(D - S) + F_s(t)];

% 3D Phase Plots of D, P, S
figure; plot3(PDS(:,1),PDS(:,2),PDS(:,3),'linewidth',2);
grid on; box on;
xlabel('Price of Product at time t');
ylabel('Demand of Product in Global Market at Time t');
zlabel('Supply of Product in Global Market at Time t');
title('3D Phase Portrait of Price, Demand, and Supply of Product')

% Time Series
figure;
plot(t,PDS(:,1),'r','linewidth',2)
hold on
plot(t,PDS(:,2),'g','linewidth',2)
hold on
plot(t,PDS(:,3),'b','linewidth',2)
hold off
xlabel('time (s)')
ylabel('Info on Product')
title('Time Series for Price, Demand, and Supply of Product')
end

%% Play with model:

% (1) squared term -> cubed
% (2) constant -> another function: alpha, beta_1, beta (???)
% (3) Necessity factor\Seasonal (add initial constand with collectibility or
%     "subtract from saturation")
% (4) amplitudes -> exponential terms
% (5) Combine F_s + F_d or F_s - F_d
% (6) Change to difference system of equations
end

