clc;
A=[12 22 32 68; 42 52 62 77; 11 21 31 84; 24 55 66 10];
printf("The matrix A is:");
disp(A);
printf("Diagonal matrix of A is");
disp(eye(4,4).*A);
