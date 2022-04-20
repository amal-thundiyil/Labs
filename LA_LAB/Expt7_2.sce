clc;
clear all;
A = [16 2 3 8; 2 15 4 7; 9 7 22 8; 3 2 1 14];
B = [46; 52; 63; 71];
n = 5;
x = 0;
y = 0;
z = 0;
w = 0;
for i=1:n
    printf("\nIteration number = %g", i);
    x = (B(1) - (A(1, 2) * y) - (A(1,3) * z) - (A(1, 4) * w)) / A(1,1);
    y = (B(2) - (A(2, 1) * x) - (A(2,3) * z) - (A(1, 4) * w)) / A(1,1);
    z = (B(3) - (A(3, 1) * x) - (A(3,2) * y) - (A(1, 4) * w)) / A(1,1);
    w = (B(4) - (A(4, 1) * x) - (A(4,) * y) - (A(1, 4) * w)) / A(1,1);
    printf("\nValue of x = %g", x);
    printf("\nValue of y = %g", y);
    printf("\nValue of z = %g", z);
    printf("\n---------------");
end;
