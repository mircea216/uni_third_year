X=[20*ones(1,2) 21*ones(1,1) 22*ones(1,3) 23*ones(1,6) 24*ones(1,5) 25*ones(1,9) 26*ones(1,2) 27*ones(1,2)];
Y=[75*ones(1,3) 76*ones(1,2) 77*ones(1,2) 78*ones(1,5) 79*ones(1,8) 80*ones(1,8) 81*ones(1,1) 82*ones(1,1)];

% a)
media_x = mean(X)
media_y = mean(Y)

% b)
dispersia_x = var(X)
dispersia_y = var(Y)

% c)
covarianta = cov(X,Y)

% d)
coef_cor= corrcoef(X,Y)
coef_corelatie = coef_cor(1,2)

% e)
plot(X, Y, 'b*')
hold on
p = polyfit(X, Y, 1)
fprintf('y=%6.3f x + %6.3f \n', p(1), p(2));

% f)
a = min(X) : 0.01 : max(X);
b = p(1) * a + p(2);
plot(a, b, 'r-')
val=polyval(p, 2.5);
fprintf('Valorile prognozate: y(2.5)=%6.4f\n', val);
val=polyval(p,3);
fprintf('si y(3)=%6.4f\n', val);