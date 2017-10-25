function [y,xi] = r2d2(a,h,n)

% Runge Kutta Method 4th Order 
% function @(x,y) e.g. f=@(x,y)(x+y); 
% a = the point up to which you obtain the results 
% x0 = initial condition of x 
% y0 = initial condition of y 
% step size
% y = ['theta'; 'eta'; 'mhat'; 'i'; 'omega'];x

% IC's:
x0 = 0;
xi = x0:h:a;
y0 = [1, 0, 0, 0, 0];

% Differential Equations
% if xi == 0
%     fO =@(xi) -xi/3;
%     feta =@(xi) -1/3;
% else 
%     fO =@(xi,eta) eta;
%     feta =@(xi,O,eta) -(O^n) - (2/(xi)).*eta;
% end
fO = @(xi,eta) thetaFunc(xi,eta);
feta = @(xi,O,eta,n) etaFunc(xi,O,eta,n);
fm =@(xi,O,n) 4*pi*(xi.^2)*(O^n);
fi =@(xi,O,n) ((8*pi)/3).*(xi^.4).*(O^n);
fw =@(xi,m,O,n) 4*pi*m*xi*(O^n);

f =@(xi,yy,n) [fO(xi,yy(2)), feta(xi,yy(1),yy(2),n), fm(xi,yy(1),n), fi(xi,yy(1),n), fw(xi,yy(3),yy(1),n)];
y = zeros(length(xi),5);
y(1,:) = y0;

for i=1:(length(xi)-1)
    k1 = f(xi(i),y(i,:),n) ;
    k2 = f(xi(i)+0.5*h,y(i,:)+0.5*h*k1,n);
    k3 = f((xi(i)+0.5*h),(y(i,:)+0.5*h*k2),n);
    k4 = f((xi(i)+h),(y(i,:)+k3*h),n);
    
    y(i+1,:) = y(i,:) + (1/6)*(k1+2*k2+2*k3+k4)*h;
    
    if(y(i+1,1)<0)
        xi = xi(1:i);
        xi(i)
        y = y(1:i,:);
        break
    end
end


% Sources:
% (1) http://www.teihal.gr/gen/profesors/christodoulou/Publications/An%20algorithm%20using.pdf
% (2) Mathworks - https://www.mathworks.com/matlabcentral/fileexchange/29851-runge-kutta-4th-order-ode
% (3) Faber
% (4) Cahill