clc;
n = 3;
A = [];
for i = 1:n,
    for j = 1:n,
        A(i,j) = i + j;
    end;
end;
printf("Matrix A is");
disp(A);
