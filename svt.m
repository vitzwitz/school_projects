function [XOPT, iter, rr, RANK, Relative, Residual, BEST] = svt(n,M, omega, P_omegaM, delta, epsilon, tau, el, kMAX)

% For Fig 5.1 : rr
% For Fig 5.2 : RANK, Relative, Residual, BEST

% svt : Singular Value Thresholding Algorithm
% Description : Recover a low-rank matrix M from a subset of sampled
%               entries

% omega : sampled set
% P_omegaM : sampled entries
% delta : step size
% epsilon : tolerance
% tau : parameter (threshold)
% el : increment
% kMAX : maximum iteration count


timerVal = tic ;
% Initialize r & s integers for loop:
% *  Notated as vectors : Each element's index
% *  is the correlated to the k iteration for 
% *  main for loop
r = zeros(1,kMAX) ;
s = zeros(1,kMAX) ;

% Initialize X because used in Y definition
X = zeros(n(1), n(2)) ;

RAN = zeros(1,kMAX) ;
Rel = zeros(1,kMAX) ;
Res = zeros(1,kMAX) ;
best = zeros(1,kMAX) ;

% Define Y(0)  ***********************


% Norm of P_omega(M)
normProjM = normest(P_omegaM,1e-2);

% Define k_0 **************
k_0 = ceil(tau/(delta*normProjM));

Y = k_0*delta*P_omegaM ;

% r integer for first iteration
R = 0 ;

% For FIg 5.2
% flag1 = false;
% flag2 = false;
% flag3 = false;
% flag4 = false;

for k = 1:kMAX
    iter = k ;
    s(k) = R + 1 ;

    
    while true
       [U, S, V] = svds(Y, s(k));   %always nonincreading    % When used P_omega(M), r only = 0))
       if S(s(k), s(k)) <= tau
           break
       end
       s(k) = s(k) + el ;     
    end
    
    % Set r to the largest index of the singular value
    % that is greater than tau
    sigma = diag(S); 
    
    for SS = 1:s(k)
        if sigma(SS) > tau
            r(k) = SS ;
        end
    end
    
    
    R = r(k);       % r(k-1) for kth iteration

    % Resizing SVD pieces
    U = U(:,1:r(k));
    S = S(1:r(k),1:r(k)) - tau*eye(r(k)); %- tau*eye(r(k)); 
    V = V(:,1:r(k));

    
        
    % Redefine X
    % ***************************************************************
    % Set X to sum of the product of difference of singular values  |
    % and tau and the singular vectors from 1 to the r integer      |
    % ***************************************************************
    
    X = U*S*V' ;
    
    
    % P_omega(X)
    
    ind = sub2ind([n(1) n(2)],omega(:,1),omega(:,2));
    P_omegaX = sparse(omega(:,1),omega(:,2),X(ind),n(1),n(2));

    % If the residual error exceeds the tolerance, program will
    % break out of loop
        
    if epsilon >= abs( norm(P_omegaX - P_omegaM, 'fro')/norm(P_omegaM, 'fro') )
        break
    end
    
    % Redefine Y matrix: 
    % Comparing each index pair of Y with omega's ordered
    % pairs
    % *************************************************************
    % > If indices of Y are not an ordeded pair in omega:         |
    %       Set Y at those indices to 0                           |
    % > If indices of Y are an ordeded pair in omega:             |
    %       Set Y at those indices to the sum of old Y and the    |
    %       difference of matrices M and X by a factor of the     | 
    %       step size                                             |
    % *************************************************************
    
    % Redefine Y ************************    
    Y = Y + delta*(P_omegaM-P_omegaX) ;
    RAN(k) = rank(X) ;

    % Truncating SVD of M then producing M_i for Fig 5.2

    [Um, Sm, Vm] = svd(M) ;

    Um = Um(:,1:RAN(k));
    Sm = Sm(1:RAN(k),1:RAN(k)) ;
    Vm = Vm(:,1:RAN(k));
    M_i = Um*Sm*Vm' ;

    % Used for Fig 5.2
%     best(k) = norm(M - M_i, 'fro')/norm(M, 'fro') ;
%     Rel(k) = norm(X - M, 'fro')/norm(M, 'fro');
%     Res(k) = abs( norm(P_omegaX - P_omegaM, 'fro')/norm(P_omegaM, 'fro') ) ;

    % Used for Fig 5.2
%     if (RAN(k) == 1 && flag1 == false)
%         fprintf('Rank of X:')
%         disp(RAN(k))
%         flag1 = true ;
%         fprintf('For rank 1:\n')
%         elapsedTime = toc(timerVal) 
%         fprintf('Relative error = %f \n', Rel(k))
%         fprintf('best error = %f \n', best(k))
%         fprintf('k = %d\n ', k)
%         fprintf('Rank of X:')
%         disp(RAN(k))
%         
%     end
% 
%     if (RAN(k) == 2 && flag2 == false)
%         fprintf('Rank of X:')
%         disp(RAN(k))
%         flag2 = true ;
%         fprintf('For rank 2:\n')
%         elapsedTime = toc(timerVal) 
%         fprintf('Relative error = %f \n', Rel(k))
%         fprintf('best error = %f \n', best(k))
%         fprintf('k = %d\n ', k)
%         fprintf('************************ \n')
%     end
%     
%     if (RAN(k) == 3 && flag3 == false)
%         fprintf('Rank of X:')
%         disp(RAN(k))
%         flag3 = true ;
%         fprintf('For rank 2:\n')
%         elapsedTime = toc(timerVal) 
%         fprintf('Relative error = %f \n', Rel(k))
%         fprintf('best error = %f \n', best(k))
%         fprintf('k = %d\n ', k)
%         fprintf('************************ \n')
%     end
%     
%     if (RAN(k) == 4 && flag4 == false)
%         fprintf('Rank of X:')
%         disp(RAN(k))
%         flag4 = true ;
%         fprintf('For rank 3:\n')
%         elapsedTime = toc(timerVal) 
%         fprintf('Relative error = %f \n', Rel(k))
%         fprintf('best error = %f \n', best(k))
%         fprintf('k = %d\n ', k)
%         fprintf('************************ \n')
%     end
    

end
 
    Relative = Rel ;            % Used to produce Fig 5.2
    Residual = Res ;            % Used to produce Fig 5.2
    XOPT = X ;    
    RANK = RAN ;                % Used to produce Fig 5.2   
    BEST = best ;               % Used to produce Fig 5.2
    rr = [0 r(1:iter)];         % Used to produce Fig 5.1
end