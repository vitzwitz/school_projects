function potential
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%
%% V = cos@-r    Analytical Potential, where @ = theta
%%               Form for Model 1
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
r = 0.98;        %% Given r value

x = -2*pi:0.01:2*pi; %% Given domain

fV =@(x) cos(x) - r*x;    % Potential Equation
plot(x,fV(x))
xlabel('theta')
ylabel('Potential Energy, V')
title('Potential Energy wrt theta, V(theta)')

end