clc;
A=[1 3 5; 2 4 1;1 2 3]
printf("The matriix A is");
disp(A);
S=sum(A);
printf("The sum of all entries is");
disp(S);
P=prod(A);
printf("The product of all entries is");
disp(P);
B=sum(A,'r');
printf("The sum of the column is");
disp(B);
C=sum(A,'c');
printf("The sum for the row is");
disp(C);
D=prod(A,'r');
printf("The product of the column is");
disp(D);
E=prod(A,'c');
printf("The product for the row is");
disp(E);
