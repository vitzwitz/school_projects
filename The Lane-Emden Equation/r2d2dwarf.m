function [y,xi] = r2d2dwarf(a,h,theta0)

% Runge Kutta Method 4th Order 
% function @(x,y) e.g. f=@(x,y)(x+y); 
% a = the point up to which you obtain the results 
% x0 = initial condition of x
% y0 = initial condition of y 
% h = step size
% y = ['theta'; 'eta'; 'mhat'; 'i'; 'omega'];

% IC's:
x0 = 0;
xi = x0:h:a;
y0 = [theta0, 0, 0, 0, 0];


fO = @(xi,O) thetaFuncdwarf(xi,O);                  
feta = @(xi,O,eta) etaFuncdwarf(xi,O,eta);     
fm =@(xi,O) 4*pi*(xi.^2)*(O);           

%Calculated these too but not necessary for this code.
fi =@(xi,O) ((8*pi)/3).*(xi^.4).*(O); 
fw =@(xi,m,O) 4*pi*m*xi*(O);
%

f =@(xi,yy) [fO(xi,yy(2)), feta(xi,yy(1),yy(2)), fm(xi,yy(1)), fi(xi,yy(1)), fw(xi,yy(3),yy(1))];
y = zeros(length(xi),5);
y(1,:) = y0;

for i=1:(length(xi)-1)
    k1 = f(xi(i),y(i,:)) ;
    k2 = f(xi(i)+0.5*h,y(i,:)+0.5*h*k1);
    k3 = f((xi(i)+0.5*h),(y(i,:)+0.5*h*k2));
    k4 = f((xi(i)+h),(y(i,:)+k3*h));
    
    y(i+1,:) = y(i,:) + (1/6)*(k1+2*k2+2*k3+k4)*h;
    
    if(y(i+1,1)<0)
        xi = xi(1:i);
        xi(i);
        y = y(1:i,:);
        break
    end
end


% Sources:
% (1) http://www.teihal.gr/gen/profesors/christodoulou/Publications/An%20algorithm%20using.pdf
% (2) Mathworks - https://www.mathworks.com/matlabcentral/fileexchange/29851-runge-kutta-4th-order-ode
% (3) Faber
% (4) Cahill