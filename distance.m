% Distance Matrix



fid = fopen('distance.txt', 'r') ;
data = fscanf(fid, '%f') ;
M = reshape(data, 312, 312);

tau = 1e7 ; delta = 2 ; epsilon = 1e-4 ; kMAX = 400 ; el = 5 ;
m = nnz(M) ; 

n = 312;
ind = randperm(n^2);
ind = ind(1:m)';
[ii,jj] = ind2sub([n,n],ind);
omega = [ii, jj] ;
            
% P_omega(M)
P_omegaM = sparse(ii,jj,M(ind),n,n) ;

timerVal = tic ;
N = size(M);
            
[XOPT, iter, RANK, Relative, Residual, BEST] = svt(N, M, omega, P_omegaM, delta, epsilon, tau, el, kMAX) ;
            
K = linspace(0,iter,iter); 
            
figure
title('SVT Errors')
plot(K, Relative, 'b')
hold on
plot(K, Residual, 'r')
hold on
plot(K, BEST, '*')
hold off

figure
title('Rank vs # of Iterations')
plot(K, RANK, 'm')



