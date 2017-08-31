function fO = thetaFuncdwarf(xi,O)

xiZeros = (xi==0);
G =@(O) ((4*nthroot(O.^2,3) + 5))./(nthroot(O,3).*(3*(nthroot(O.^2,3) + 1)).^(3/2));
Gprime =@(O) -(8 + 16*nthroot(O.^(-2),3) + 5*nthroot(O.^(-4),3))./((9*(1 + nthroot(O.^2,3))).^(5/2));

fO = zeros(size(xi));
fO(xiZeros) = sqrt(abs(O./Gprime(O)));
fO(~xiZeros) = sqrt(abs((-xi(~xiZeros).*O)./(xi(~zeros).*Gprime(O) + 2*G(O))));