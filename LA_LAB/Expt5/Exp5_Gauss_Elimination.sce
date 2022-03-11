clc;
clear all;
a = [1, 1, 1; 1, 2, 3; 1, 3, 2];
disp(a);
b = [3; 0; 3];
disp(b);
c = [a b];
disp(c);
n = 3;
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
z = c(3,4);
y = c(2,4) - c(2,3) * z;
x = c(1,4) - c(1,3) * z - c(1,2) * y;
printf("x = ");
disp(x);
printf("y = ");
disp(y);
printf(" z = ");
disp(z);
