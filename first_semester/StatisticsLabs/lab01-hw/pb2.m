%2
matrix=[1 2 3 ;4 5 6];
row2=matrix(:,2);
row3=matrix(:,3);
matrix(:,2)=row3;
matrix(:,3)=row2;
disp("The modified matrix is: ");
disp(matrix);