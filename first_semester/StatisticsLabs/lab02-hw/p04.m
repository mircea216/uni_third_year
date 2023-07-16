lambda=input('Read n: ');
clf;
x = 0:4*lambda;
hold on;
plot(x,poisspdf(x,lambda), "*");
stairs(x,poisscdf(x,lambda));
