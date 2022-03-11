clc;
clear all;
a = [2 1 0 1; 5 -4 1 0; 3 0 2 0; 1 1 -1 1];
disp(a);
b = [2; 1; -2; 1];
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
w = c(4, 5);
z = c(3, 5) - w * c(3, 4);
y = c(2, 5) - w * c(2, 4) - z * c(2, 3);
x = c(1, 5) - w * c(1, 4) - z * c(1, 3) - y * c(1, 2);

printf("x = ");
disp(x);
printf("y = ");
disp(y);
printf(" z = ");
disp(z);
printf("w = ");
disp(w);
