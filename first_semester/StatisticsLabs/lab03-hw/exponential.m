niu=2;
N=1000;

x=random("exp",niu,[1,N]);

[u,v]=sort(x);
fprintf("%d\n",x)
m=min(x); 
M=max(x);  
fprintf("Minimul: %d\n",m)
fprintf("Maximul: %d\n",M)
nr_clase=1+10/3*log10(N);   
n=fix(nr_clase);
fprintf("Nr de clase: %d\n",n)
d=(M-m)/n;
fprintf("Lungimea claselor:%d\n",d)
i=1:n+1;
c_i=m+(i-1)*d ;
[f, middle] = hist(x, n)

%Tabelul sistematizat 
fprintf("| Nr. |    Clasa    | Frecventa absoluta | Mijlocul clasei | Frecventa relativa |\n");
fprintf("---------------------------------------------------------------------------------\n");
for i=1:n
  str1 = mat2str(i);
  str2 = mat2str(c_i(i));
  str3 = mat2str(c_i(i+1));
  str4 = mat2str(f(i));
  str5 = mat2str(middle(i));
  str6 = mat2str(N);
  if i==n
    fprintf("| %3s | [%.4s,%.4s] | %10s\t | %10.4s\t   | %6s/%.6s        |\n",str1,str2,str3,str4,str5,str4,str6);
  else
    fprintf("| %3s | [%.4s,%.4s) | %10s\t | %10.4s\t   | %6s/%.6s        |\n",str1,str2,str3,str4,str5,str4,str6);
  end
  fprintf("---------------------------------------------------------------------------------\n");
end

figure
hold on
hist(x,n)

hold on 
plot(middle,f)
hold off

a=mean(x);
fprintf("Media aritmetica: %d\n", a);
g=geomean(x);
fprintf("Media geometrica: %d\n",g);
h=harmmean(x);
fprintf("Media armonica: %d\n",h);
 %Mediana
 med=median(x);
 fprintf("Mediana: %d\n",med);
 %Modul
 i = find(f == max(f));
 mod = middle(i);
 fprintf("Modul: %d\n",mod)
 
%Cuartilele
cuartile = prctile(x, [25 50 75]);
fprintf("Q1(cuartila inferioara): %f\n",cuartile(1));
fprintf("Q2: %.2f\n",cuartile(2));
fprintf("Q3(cuartila superioara): %f\n",cuartile(3));

dis=var(x);
fprintf("Dispersia: %f\n",var(x));
abt=std(x);
fprintf("Abaterea standard: %f\n",std(x));

moment1 = moment(x,1);
fprintf("Momentul centrat de ordin 1  : %f\n", moment1);
moment2 = moment(x,2);
fprintf("Momentul centrat de ordin 2  : %f\n", moment2);
moment3 = moment(x,3);
fprintf("Momentul centrat de ordin 3  : %f\n", moment3);
moment4 = moment(x,4);
fprintf("Momentul centrat de ordin 4  : %f\n", moment4);