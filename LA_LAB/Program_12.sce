clc;
A=[1+%i*3 5 3+%i*5 7+%i*10; 3+%i*2 5 7+%i*8 44+%i*20; 9 7 0+%i*99 56+%i*19; 5 96 24+%i*10 100+%i*54];
printf("Matrix A is:");
disp(A);
printf("The imaginary part of the matrix is");
disp(imag(A));
printf("The real matrix is");
disp(real(A));
printf("The lower triangular matrix is:");
disp(tril(A));
printf("The upper traingular matrix is:");
disp(triu(A));
printf("Diagonal matrix of A is");
disp(eye(4,4).*A);
printf("The determinnant of A is:");
disp(det(A));
printf("The inverse of A is");
disp(inv(A));

