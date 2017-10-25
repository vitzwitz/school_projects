%||***********************||
%|| Calls the code here.  ||
%||***********************||

% x-values
x_pt = [-1 -0.5 0 0.5 1];

% Test Shape 1

% Original function values
y_pt = [-0.36236 -0.76484 -0.98007 -0.95534 -.69671];
% Adapted Function Values
y_pta = [-0.36236 -0.76484 -0.98007 0.44466 -.69671];
% Function of Shape
q =@(xs) -cos(xs-0.2);
% Derivative
dq =@(xs) -sin(0.2-xs);
% Run it with the original and adapted numbers. 
fprintf('\n\n This is data for the first shape with its original points: \n -cos(x-0.2) \n\n')
reflector(x_pt,y_pt,q,dq)
fprintf('\n\n This is data for the first shape with a changed function value: \n -cos(x-0.2)\n\n')
reflector(x_pt,y_pta,q,dq)

% Test Shape 2- Parabola
fprintf('\n\n This data is for the parabola: \n\n')

% y-coordinates at given x-coordinates
y_pt2 = [0 0.75 1 0.75 0];
% Function
p =@(xs) 1-xs.^2;
% Derivative
dp =@(xs) -2*xs;
% Run it with the points for Shape 2
reflector(x_pt,y_pt2,p,dp)

% Test Shape 3- Circular arc
fprintf('\n\n This data is for the circular arc: \n\n')

% y-coordinates at given x-coordinates
y_pt3 = [2 2.17945 sqrt(5) 2.17945 2];
% Function
f =@(xs) sqrt(5-xs.^2);                                  
% Derivative
df =@(xs) -xs./(sqrt(5-xs.^2));
% Run it with the points for shape 3
reflector(x_pt,y_pt3,f,df)

% ***** End of script for call function *****
% ***********************************************************
% Natural Cubic Spline Interpolation to Create "Surfaces"   *
% ***********************************************************
