X=[-1 0 1 2];
Y=[2 1 2 11];

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
p = polyfit(X, Y, 2)
fprintf('y=%6.3f x2 + %6.3f x + %6.3f \n', p(1), p(2), p(3));

% f)
a = min(X) : 0.01 : max(X);
b = p(1) * a.^2 + p(2)*a + p(3);
plot(a, b, 'r-')
val=polyval(p, 2.5);
fprintf('Valorile prognozate: y(2.5)=%6.4f\n', val);
val=polyval(p,3);
fprintf('si y(3)=%6.4f\n', val);