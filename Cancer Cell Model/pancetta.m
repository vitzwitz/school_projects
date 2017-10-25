function pancetta

A = [-0.3 0.2; 0.2 -0.3];
t = linspace(0,50);

%%Analytical Solutions
P =@(t) -0.5*(exp(-0.1*t) + exp(0.5*t));
Q =@(t) 0.5*(exp(-0.5*t) - exp(-0.1*t));

plot(t,P(t),'g','LineWidth',3)

hold on

plot(t,Q(t),'r','LineWidth',3)

hold on

X = A*x;

[t, X] = ode45(@dXdt,t,[1;0],[],A);

plot(t,X(:,1),'g','LineWidth','x')

hold on

plot(t,X(:,1),'r','LineWidth','x')

xlabel('Time')
ylabel('Cell Mass')
legend('Analytical P(t)','Analytical Q(t)','Numerical P(t)','Numerical Q(t)')
title('Pancetta Cancer Cell Model')

xlim([0 50])
ylim([0 50])




function out = dXdt(t,X,A)
out = A*X;

end
end