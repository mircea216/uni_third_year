X=[6.16,6.55,5.32,6.26,6.10,5.61,5.87,6.10,6.83,7.07,5.60,6.91,6.22,5.98,6.21,5.94,5.96,6.45];
m0=0.4;
alfa=0.05;
ss2=0.4*0.4;
s2=var(X);
n=18;
h1=chi2inv(alfa/2,n-1);
h2=chi2inv(1-alfa/2,n-1);
U1=[-inf,h1];
U2=[h2,inf];
u=union(U1,U2);
v0=(n-1)*s2/ss2;
if v0<h1 && v0>h2
   disp('1) Se respinge H0');
else
  disp('1) Se accepta ipoteza nula');
end
[H,P,ci,stats]=vartest(X,ss2,'alpha',alfa,'tail','both');
if H==1
    disp('1) Se respinge H0');
else
  disp('1) Se accepta ipoteza nula');
end