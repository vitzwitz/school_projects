function Jacobian
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% Model 2B %%
%% Code can be used for Model 2A as well, except 
%% constants defined below must change.

a = -0.1    ;
b = 0.5;
e = 0.01;

    %% Fixed Points %%
 x1_star = ((a+1)+sqrt((a-1)^2-4*b))/2;
 x2_star = ((a+1)-sqrt((a-1)^2-4*b))/2;
 x3_star = 0;

    %% Jacobian Matrix %%
 A = [2*x1_star*(a+1)-(3*x1_star^2+a) -1;e*b -e];
 B = [2*x2_star*(a+1)-(3*x2_star^2+a) -1;e*b -e];
 C = [2*x3_star*(a+1)-(3*x3_star^2+a) -1;e*b -e];

 %% Fixed Points %%
disp(x1_star)
disp(x2_star)
disp(x3_star) 
 
 %% Eigenvalues %% 
disp(eig(A))
disp(eig(B))
disp(eig(C))
disp(C)