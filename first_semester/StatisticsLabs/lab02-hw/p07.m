%7
lambda=input('Read lambda: ');
clf;
[m,s]=expstat(lambda);
x= max(0,m-3*s) : 0.01 : m+3*s;
hold on;
plot(x,exppdf(x,lambda))
stairs(x,expcdf(x,lambda));
