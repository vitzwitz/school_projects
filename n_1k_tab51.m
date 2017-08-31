% call svt

% NOTES
% M = [1 n+1 n+2 ..;2 2n+1 2n+2..; ... n^2]       
% P_omega(X-M) = P_omega(X) - P_omega(M)
% SETON

% max number of iterations
kMAX = 500;

% Times = zeros(1, 3) ;
% Iters = zeros(1, 3) ;
% Relative_error = zeros(1,3) ;
% x1 = 1; x2 = 1;

% Parameters for SVT
epsilon =  1e-4 ;
el = 5 ;


%mStuff = [6 5 4]*d_r;      

r = 10;
n = 10000;
% Threshold Paramter for different n values
tau = 5*n ;

d_r = r*(2*n - r) ;
m = 6*d_r; 
    %for r = [10 50 100]

        
        
        % Generating nxn matrix with rank r: M 
            ML = randn(n, r);
            MR = randn(n, r);
            M = ML*MR';
            
        % Computing little m for omega
          %  m = mStuff(x2)*n^2 ;
            
        % Parameter for SVT
            delta = 1.2*(n^2/m) ;

            
        % Generating Omega : is a n-length sequence of ordered pairs 
            ind = randperm(n^2);
            ind = ind(1:m)';
            [ii,jj] = ind2sub([n,n],ind);
            omega = [ii, jj] ;
            
        % P_omega(M)
            P_omegaM = sparse(ii,jj,M(ind),n,n) ;
            
        %for la = 1:5
            timerVal = tic ;
            N = [n,n];
            [XOPT, iter, RANK, Relative, Residual, BEST] = svt(N, M, omega, P_omegaM, delta, epsilon, tau, el, kMAX) ;
            K = linspace(0,iter,iter+1) ;
            
            figure
            title('SVT Errors')
            %plot(K,

       % end
        
%         Iters(x2) = Iters(x2)/5 ; 
%         Times(x2) = Times(x2)/5;
%         Relative_error(x2) = Relative_error(x2)/5 ;
%         x2 = x2 + 1 ;
   % end
    