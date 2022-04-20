clc;
clear all;
A = [20 2 3 6; 2 15 4 4; 2 7 15 4; 3 3 1 9];
B = [42; 48; 110; 69];
n = 5;
x = 0;
y = 0;
z = 0;
w = 0;
for i=1:n
    printf("\nIteration number = %g", i);
    x = (B(1) - (A(1, 2) * y) - (A(1,3) * z) - (A(1, 4) * w)) / A(1,1);
    y = (B(2) - (A(2, 1) * x) - (A(2,3) * z) - (A(2, 4) * w)) / A(2,2);
    z = (B(3) - (A(3, 1) * x) - (A(3,2) * y) - (A(3, 4) * w)) / A(3,3);
    w = (B(4) - (A(4, 1) * x) - (A(4,2) * y) - (A(4, 3) * w)) / A(4,4);
    printf("\nValue of x = %g", x);
    printf("\nValue of y = %g", y);
    printf("\nValue of z = %g", z);
    printf("\nValue of w = %g", w);
    printf("\n---------------");
end;
