% Cubic Spline Interpolation - Natural Spline
% INPUT: X and Y are the vectors of given x-coordinates and y-coordinates
%        respectively

% Baillie, Borden, Miskovitz
 
function reflector(X,Y,f,df)

if length(X) ~= length(Y)                % Stops if length(X) =/= length(Y)
 erro('vectors X and Y must be of same length');
end

n = length(X);                          % Number of points interpolating

% Vector h with subintervals:
h = zeros(n-1,1);                       % Step-size of x
for j = 1:n-1
 h(j) = X(j+1) - X(j);
end

% Coefficient matrix A:
A = zeros(n);                           % Creates empty matrix
 
% Natural Spline boundary conditions:
A(1,1)= 1;                              % First row
A(n,n) = 1;                             % Last row

for i = 2:n-1
 A(i,i-1) = h(i-1);                 
 A(i,i) = 2*(h(i-1)+h(i));              % Diagonal elements
 A(i,i+1) = h(i);
end
 
% Vector b:                             % RHS vector
b = zeros(n,1);
 
for i = 2:n-1   
 b(i) = (3/h(i))*(Y(i+1)-Y(i)) - (3/h(i-1))*(Y(i)-Y(i-1));
end
 
% Coefficient vector cj:
cj = A\b;                          
 
% Coefficient vector bj:
bj = zeros(n-1,1);
for i = 1:n-1
 bj(i) = (1/h(i))*(Y(i+1)-Y(i)) - (1/3*h(i))*(2*cj(i)+cj(i+1));
end

% Coefficient vector dj:
dj = zeros(n-1,1);
for i = 1:n-1
 dj(i) = (1/(3*h(i))) * (cj(i+1)-cj(i));                    
end
 
% Making a matrix P with all polynomials
P = zeros(n-1,4);
for i = 1:n-1
 P(i,1) = dj(i);
 P(i,2) = cj(i);           
 P(i,3) = bj(i);
 P(i,4) = Y(i);
end

% ||**************************************************************||
% ||  Generating Shapes and Comparing them to Original Functions  ||
% ||**************************************************************||
figure
plot(X,Y,'or','LineWidth',3)    % Data Points
resolution = 20;                % 20 equally spaced points
for i = 1:n-1
    
 % Constructing Interpolating function for i interval
 s = @(x) Y(i) + bj(i).*(x-x(i)) + cj(i).*(x-x(i)).^2 + dj(i).*(x-x(i)).^3;
 xs = linspace(X(i),X(i+1),resolution);    

hold on
plot(xs,s(xs),'k*','LineWidth',2)
hold on
plot(xs,f(xs),'g','LineWidth',2); 
hold on
    
legend('Data Points','Reflector','Function of Shape','Location','best')
% legend('boxoff')
title('Comparing Reflector with Function of Shape using 20 equally spaced points')
xlabel('x'); ylabel('y'); hold off;
end
 xlim([-1 1]);
  
% ||***********************************************************||
% ||  Interpolating Shape Function & Calculating Bound Error   ||
% ||***********************************************************||

figure
resolution = 5;                              % 5 equally spaced points
plot(X,Y,'or','LineWidth',3)                 % Data Points
for i = 1:n-1
    
    % Interpolating function for interval i
s = @(x) Y(i) + bj(i).*(x-x(i)) + cj(i).*(x-x(i)).^2 + dj(i).*(x-x(i)).^3;
xs = linspace(X(i),X(i+1),resolution); 

hold on
% Graph
plot(xs,s(xs),'k--','LineWidth',2)
hold on
plot(xs,f(xs),'m','LineWidth',2);    
xlabel('x'); ylabel('y');
legend('Data Points','Reflector','Function of Shape','Location','best')
title('Comparing Reflector with Function of Shape using 5 equally spaced points');
hold off; xlim([-1 1]);
end

% Bound Error
for i = 1:n
bound_error = abs(f(xs)-s(xs));
fprintf('The bound error at x = %d is %f.\n',X(i),bound_error(1,i));
end
fprintf('\n\n__________________________________________________\n\n')


% ||***************************************************||
% ||  Differentiating Function of Shape and Reflector  ||
% ||***************************************************|| 
figure
resolution = 20;                % 20 equally spaced points
for i = 1:n-1
    
 % Constructing Derivative of spline function for i interval
   ds =@(x) bj(i) + 2*cj(i).*(x-x(i)) + 3*dj(i).*(x-x(i)).^2; 
   xs = linspace(X(i),X(i+1),resolution);
   
hold on   
plot(xs,ds(xs),'k*','LineWidth',2)   
hold on
plot(xs,df(xs),'b','LineWidth',2)               
title('Slopes of Reflector and Function of the Shape');
xlabel('x'); ylabel('y');
legend('Derivative of Spline','Derivative of Function','Location','best')
hold off; xlim([-1 1]);
 
end

 % Derivatives of Spline at Data Points
   der_f = df(X);
   der_s = ds(X);
for i = 1:n
fprintf('The derivative of the reflector at %d is %f.\n',X(i),der_s(1,i))
fprintf('The derivative of the function at %d is %f.\n\n',X(i),der_f(1,i));
end
fprintf('\n__________________________________________________\n\n')

% Bound Error for Derivatives
for i = 1:n
bound_error_der = abs(df(xs)-ds(xs));
fprintf('The bound error of the derivative at x = %d is %f.\n',X(i),bound_error_der(1,i));
end

fprintf('\n__________________________________________________\n')
fprintf('\n__________________________________________________\n')
end
% Sources: 
% (1) https://dafeda.wordpress.com/2010/11/28/cubic-spline-interpolation-code/
% (2) https://www.math.uh.edu/~jingqiu/math4364/spline.pdf
% (3) Wolfram Alpha
% (4) MathWorks