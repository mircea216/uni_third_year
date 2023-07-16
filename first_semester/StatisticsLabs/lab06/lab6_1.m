%1)
X=[3.5,3.3,3.6,3.2,3.4,3.1,3.5,3.7,3.3];
m0=3.6;
alfa=0.04;
sigma=0.18;
z_alfa=norminv(alfa,0,1);
U=[-inf,z_alfa];
n=length(X);
z0=(mean(X)-m0)/(sigma/sqrt(n));
if z0<z_alfa
  disp('1) Se respinge H0');
else
  disp('1) Se accpeta ipoteza nula');
end
