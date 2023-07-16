% 1)
disp("1)")

X1 = [1240 * ones(1, 3) 1245 * ones(1,4) 1250 * ones(1, 5) 1255 * ones(1, 3) 1260 * ones(1, 2)];
X2 = [1235 * ones(1, 2) 1240 * ones(1,2) 1245 * ones(1, 3) 1250 * ones(1, 4) 1255 * ones(1, 3) 1260 * ones(1, 1)];

n1 = length(X1);
n2 = length(X2);

sigma_1 = 5.5;
sigma_2 = 8;

alpha = 0.01;

z = norminv (1 - alpha / 2, 0, 1);
U = [-inf, -z, z, inf];
fprintf('Regiunea de respingere U = [%4.2f, %4.2f] U [%4.2f, %4.2f]\n', U)

z_0 = (mean(X1) - mean(X2)) / sqrt(sigma_1 ^ 2 \ n1 + sigma_2 ^ 2 \ n2);
if z_0 < -z || z_0 > z
    disp('Ipoteza nula H0 este respinsa, deci mediile vor fi diferite')
else
    disp('Ipoteza nula H0 este acceptata, deci mediile vor fi egale')
end

%2
disp("2)")

X1 = [4.95 5.24 5.13 5.07 4.83 5.04 4.92 5.06 5.15 5.23 5.16 5.28];
X2 = [5.32 5.13 5.41 5.13 4.92 4.83 5.68 5.56 5.72 4.83];

% a)
fprintf('\na)\n')

n_1 = length(X1);
n_2 = length(X2);

m_1 = mean(X1);
m_2 = mean(X2);

v_1 = var(X1);
v_2 = var(X2);

alpha = 0.02;

f1 = finv(alpha/2, n_1-1, n_2 -1);
f2 = finv(1-alpha/2, n_1-1, n_2 -1);

fprintf('Regiunea de respingere U = (-inf, %.2f] U [%.2f, +inf)\n',f1,f2);

f0 = v_1/v_2;
fprintf('f0 = %.2f\n',f0);
if (f0 > -inf && f0<=f1) || (f0>=f2 && f0 < +inf)
   fprintf('Ipoteza H0 este respinsa, deci dispersiile vor fi diferite\n')
else
   fprintf('Ipoteza H0 este acceptata, deci dispersiile vor fi egale\n')
end


% b)
fprintf('\nb)\n')

sp = sqrt(((n_1-1)*v_1 + (n_2-1)*v_2)/(n_1+n_2-2));

t = tinv(alpha, n_1+n_2-2);

fprintf('Regiunea de respingere U = (-inf, %.2f]\n',t);

t0 = (m_1-m_2)/(sp*sqrt(1/n_1+1/n_2));
fprintf('t0 = %.2f\n',t0);
if (t0 > -inf && t0<=t)
   fprintf('Ipoteza H0 este respinsa, deci dispersiile vor fi diferite\n')
else
   fprintf('Ipoteza H0 este acceptata, deci dispersiile vor fi egale\n')
end
