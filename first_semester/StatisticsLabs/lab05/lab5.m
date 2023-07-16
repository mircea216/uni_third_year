X=[22.7*ones(1, 1) 22.8*ones(1,3) 22.9*ones(1,7) 23.0*ones(1,4) 23.1*ones(1,6) 23.2*ones(1,7) 23.3*ones(1,5) 23.4*ones(1,2)];
varianta = 0.35;
alpha = 0.05;
m_s = mean(X);
z = norminv(1-alpha/2,0,1);
sigma = sqrt(varianta);
n = length(X);
m1 = m_s-z*sigma/sqrt(n);
m2 = m_s+z*sigma/sqrt(n);
fprintf("1) [%4.2f, %4.2f] ", m1, m2);

X=[2.7*ones(1,1) 2.8*ones(1,2) 2.9*ones(1,5) 3.0*ones(1,3) 3.1*ones(1,5) 3.2*ones(1,4) 3.3*ones(1,2)];
alpha = 0.02;
media = mean(X);
s=std(X);
n=length(X);
t=tinv(1-alpha/2,n-1);
m1=media-t*s/sqrt(n);
m2=media+t*s/sqrt(n);
fprintf("\n2) [%4.2f, %4.2f] ", m1, m2);

X=[4.21, 4.03, 3.99, 4.05, 3.89, 3.98, 4.01, 3.92, 4.23, 3.85, 4.20];
alpha=0.05;
varianta=var(X);
n=length(X);
h1=chi2inv(1-alpha/2,n-1);
h2=chi2inv(alpha/2,n-1);
d1=(n-1)*varianta/h1;
d2=(n-1)*varianta/h2;
fprintf("\n3) Intervalul de incredere pentru s^2: [%4.2f, %4.2f] ", d1, d2);
fprintf("\n   Intervalul de incredere pentru s: [%4.2f, %4.2f] ", sqrt(d1), sqrt(d2));

X1=[1010,993,992,1008,1006,998,1008,994,996,1006,1005,1002,997,1004,1002,1010,1003];
X2=[1002,985,996,1010,1004,1003,1010,993,1002,1006,988,995];
m1=mean(X1);
m2=mean(X2);
s21=var(X1);
s22=var(X2);
s1=std(X1);
s2=std(X2);
alfa=0.05;
n1=length(X1);
n2=length(X2);
%a)
f1=finv(1-alfa/2,n1-1,n2-1);
f2=finv(alfa/2,n1-1,n2-1);
mi1=(1/f1)*(s21/s22);
mi2=(1/f2)*(s21/s22);
fprintf('\n4) a) (%4.2f, %4.2f) \n',mi1,mi2);

%b)
sp=sqrt(((n1-1)*s21+(n2-1)*s22)/(n1+n2-2));
t1=tinv(1-alfa/2,n1+n2-2);
mi1=(m1-m2)-t1*sp*sqrt(1/n1+1/n2);
mi2=(m1-m2)+t1*sp*sqrt(1/n1+1/n2);
fprintf('   b) (%4.2f, %4.2f) \n',mi1,mi2);

%c)
c=(s21/n1)/(s21/n1 + s22/n2);
n=1/((c*c)/(n1-1)+((1-c)*(1-c))/(n2-1));
t1=tinv(1-alfa/2,n);
mi1=(m1-m2)-t1*sqrt(s21/n1+s22/n2);
mi2=(m1-m2)+t1*sqrt(s21/n1+s22/n2);
fprintf('   c) (%4.2f, %4.2f) \n',mi1,mi2);
