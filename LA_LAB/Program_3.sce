clc;
printf("Amal Thundiyil - 2020400066 SE IT - D")
A =  [1 3 5; 2 4 1; 1 2 3];
printf("The matrix A is");
disp(A);
L = tril(A);
printf("The lower triangular matrix of A is");
disp(L);
U = triu(A);
printf("The upper triangular matrix of A is");
disp(U)
