clc;
clear all;
A = [27, 6, -1; 6, 15, 2; 1, 1, 54];
B = [85; 72; 110];
n = 5;
x = 0;
y = 0;
z = 0;
for i=1:n
    printf("\nIteration number = %g", i);
    x = (B(1) - (A(1, 2) * y) - (A(1,3) * z)) / A(1,1);
    y = (B(2) - (A(2, 1) * x) - (A(2,3) * z)) / A(2,2);
    z = (B(3) - (A(3, 1) * x) - (A(3,2) * y)) / A(3,3);
    printf("\nValue of x = %g", x);
    printf("\nValue of y = %g", y);
    printf("\nValue of z = %g", z);
    printf("\n---------------");
end;
