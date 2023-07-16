x=0:1:16;
p=0.3;
plot(x,geopdf(x,p), 'rp');
stairs(x,geocdf(x,p), 'green');
hold on;
title('PDF/CDF geo')
p=0.4;
plot(x,geopdf(x,p), 'bp');
hold on;
stairs(x,geocdf(x,p), 'red');
hold off;