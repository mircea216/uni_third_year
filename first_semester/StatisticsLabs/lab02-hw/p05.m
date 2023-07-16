%5
n=input('Read n: ');
clf;
[m,s]=tstat(n);
x= m-3*s : 0.01 : m+3*s;
hold on;
plot(x,tpdf(x,n));
plot(x,tcdf(x,n));
