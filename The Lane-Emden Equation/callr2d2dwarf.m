% Call R2D2

a = 12; %  Max xi
h = .01;  % Step size
theta0 = [0.01 0.1 1 10 100 1000 100000 100000]; % theta(0)

y = cell(numel(theta0),1);
xi = cell(numel(theta0),1);

figure
for j = 1:numel(theta0)
  [y{j},xi{j}] = r2d2dwarf(a,h,theta0(j));

  y{j}(end,:);
  hold on
plot(xi{j}',log(y{j}(:,1))); 
title('White Dwarf'); ylabel('Theta'); xlabel('Xi'); 
end
hold off
legend('theta0 = 0.01','theta0 = 0.1','theta0 = 1','theta0 = 10','theta0 = 100', 'theta0 = 1000','theta0 = 10000','theta0 = 100000');

xlim([0 12])