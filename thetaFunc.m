function fO = thetaFunc(xi,eta)

xiZeros = (xi==0);

fO = zeros(size(xi));
fO(xiZeros) = -xi(xiZeros)/3;
fO(~xiZeros) = eta;