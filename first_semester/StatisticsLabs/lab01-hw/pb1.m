%1
matrix=[1 2; 3 4; 5 6];
line1=matrix(1,:);
line3=matrix(3,:);
matrix(1,:)=line3;
matrix(3,:)=line1;
disp("The modified matrix is: ");
disp(matrix);