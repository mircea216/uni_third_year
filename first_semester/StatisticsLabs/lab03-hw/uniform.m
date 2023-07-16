a=10;   
b=20;  
N=1000;    
x=random('unif',a,b,1,N); 
x=sort(x);
x_min=min(x);   
x_max=max(x);
n=fix(1 + (10/3) *log10(N));
d=(x_max - x_min) / n; 
for i = 1 : n+1
  class(i) = x_min + (i - 1) * d;
end 
[f, middle] = hist(x, n);
i=1:n;
quantInfo=[i; class(i); class(i+1); f; middle; f/N];
fprintf('NrCrt. |        Clasa         |  Frecventa absoluta  | Mijlocul clasei | Frecventa relativa| \n');
fprintf('%3d | [%8.4f, %8.4f] |     %4d             |   %10.4f           | %10.6f         | \n', quantInfo);

clf  
hist(x, n);
hold on

plot(middle, f, 'r-', 'linewidth', 2);

media_a=mean(x);
fprintf('Media aritmetica   %10.6f \n', media_a);

if x>0
  media_geo=geomean(x);
  fprintf('Media geometrica   %10.6f \n', media_geo);
  
if x~=0
  media_h=harmmean(x);
  fprintf('Media armonica   %10.6f \n',media_h);


mediana=median(x);
fprintf('Mediana   %10.6f \n', mediana);

jmax=find(f==max(f));
mod=middle(jmax);
fprintf('Modurile distributiei statistice  ');
for k=1:length(mod)
  fprintf(' %10.6f ', mod(k));
end
fprintf('\n');

fprintf('Cuartila inferioara Q1   %8.4f \n',  prctile(x,25));
fprintf('Cuartila Q2   mediana = %8.4f \n',   prctile(x,5));
fprintf('Cuartila superioara Q3   %8.4f \n',  prctile(x,75));

fprintf('Dispersia   %6.4f \n', var(x));
fprintf('Abaterea standard   %8.4f \n',  std(x));

momOrd1 = moment(x,1);
momOrd2 = moment(x,2);
momOrd3 = moment(x,3);
momOrd4 = moment(x,4);

fprintf('Momentul de ordin 1     %8.4f \n', momOrd1);
fprintf('Momentul de ordin 2     %8.4f \n', momOrd2);
fprintf('Momentul de ordin 3     %8.4f \n', momOrd3);
fprintf('Momentul de ordin 4     %8.4f \n', momOrd4);