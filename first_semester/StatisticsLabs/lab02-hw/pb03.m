n=input('Read n: ');
x = 0:1:n;
p=input('Read p: ');
clf;
hold on;
plot(x,binopdf(x,n,p), "*");
stairs(x,binocdf(x,n,p));
