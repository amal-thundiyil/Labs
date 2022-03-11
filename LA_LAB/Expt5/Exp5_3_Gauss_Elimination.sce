clc;
clear all;
a = [4 1 1 1; 1 5 2 1; 2 -3 3 2; 3 1 -1 5];
disp(a);
b = [2.4; 0.7; 3.5; 2.7];
disp(b);
c = [a b];
disp(c);
n = 4;
for i = 1:n;
    if c(i, i) == 0
        c(i, :) = c(i, :);
    else
        c(i, :) = c(i, :) / c (i, i);
    end
    disp(c);
    for j = 1:n-1
        if i + j < n + 1
            c(i + j, :) = c(i + j, :)  - c(i + j, i) * c(i, :);
        end
    end
end
disp(c);
x4 = c(4, 5);
x3 = c(3, 5) - x4 * c(3, 4);
x2 = c(2, 5) - x4 * c(2, 4) - x3 * c(2, 3);
x1 = c(1, 5) - x4 * c(1, 4) - x3 * c(1, 3) - x2 * c(1, 2);
printf("x1 = ");
disp(x1);
printf("x2 = ");
disp(x2);
printf("x3 = ");
disp(x3);
printf("x4 = ");
disp(x4);
