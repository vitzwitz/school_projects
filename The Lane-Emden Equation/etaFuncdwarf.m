function feta = etaFuncdwarf(xi,O,eta)

xiZeros = (xi==0);

feta = zeros(size(xi));
G =@(O) ((4*nthroot(O.^2,3) + 5))./(nthroot(O,3).*(3*(nthroot(O.^2,3) + 1)).^(3/2));
Gprime =@(O) -(8 + 16*nthroot(O.^(-2),3) + 5*nthroot(O.^(-4),3))./((9*(1 + nthroot(O.^2,3))).^(5/2));

feta(xiZeros) = -O./G(O) - eta.^2.*(Gprime(O)./G(O));
feta(~xiZeros) = (-O./G(O)) - (2*eta./xi(~xiZeros)) - (Gprime(O)./G(O)).*eta.^2;