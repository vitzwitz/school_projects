function feta = etaFunc(xi,O,eta,n)

xiZeros = (xi==0);

feta = zeros(size(xi));
feta(xiZeros) = -1/3;
feta(~xiZeros) = -(O^n) - (2./(xi(~xiZeros)))*eta;