% Call R2D2

a = 10; % ???  Max xi
h = .01;  % ???  Step size
n = [0.5, 1, 2, 3];

y = cell(numel(n),1);
yn = cell(numel(n),1);
xi = cell(numel(n),1);

figure
hold on
for j = 1:numel(n)
  [y{j},xi{j}] = r2d2(a,h,n(j));

  y{j}(end,:);
  
plot(xi{j}',y{j}(:,1)); 
title('Temperature'); xlabel('theta'); ylabel('eta'); legend('n=0.5','n=1','n=2','n=3');
end
hold off

figure
hold on
for j = 1:numel(n)
  [y{j},xi{j}] = r2d2(a,h,n(j));
 
  yn{j} = (y{j}(:,1)).^n(j);
  yn{j}(end,:);
  
plot(xi{j}',yn{j}(:,1)); legend('theta');
title('Density'); xlabel('theta^n'); ylabel('eta'); legend('n=0.5','n=1','n=2','n=3');
end
hold off
