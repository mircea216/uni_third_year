%6
m=input('Read m: ');
clf;
s=input('Read sigma: ');
x= m-3*s : 0.01 : m+3*s;
hold on;
plot(x,normpdf(x,m,s));
plot(x,normcdf(x,m,s));

