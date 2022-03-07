clc;
A=[1 3 5; 2 4 1;1 2 3]
printf("Matriix A is");
disp(A);
L=tril(A);
printf("The lower triangular matrix is");
disp(L);
U=triu(A);
printf("The upper triangular matrix is");
disp(U);
