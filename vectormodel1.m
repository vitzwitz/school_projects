function vectormodel1

x = -pi:0.01:pi;

f1 =@(x) 0.98 + sin(x)

plot(x,f1(x))
grid on

fun = f1;
x_star1 = fzero(fun,-1)
x_star2 = fzero(fun,-2)
end