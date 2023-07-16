%2)
X=[2.51*ones(1,1),2.52*ones(1,2),2.53*ones(1,4),2.54*ones(1,5),2.55*ones(1,7),2.56*ones(1,6),2.57*ones(1,7),2.58*ones(1,2),2.59*ones(1,1),];
m0=2.55;
alfa=0.05;
n=35;
sigma=std(X);
t_alfa=tinv(1-alfa,n-1);
U=[t_alfa,inf];
n=35;
t0=(mean(X)-m0)/(sigma/sqrt(n));
if t0>t_alfa
  disp('1) se respinge H0');
else
  disp('1) Se accepta ipoteza nula');
end
[H,P,ci,stats]=ttest(X,m0,'alpha',alfa,'tail','right');
if H==1
  disp('2) se respinge H0');
    else
    disp('2) Se accepta ipoteza nula');
end