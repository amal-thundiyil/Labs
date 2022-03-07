clc;
i = 2;
A = [];
for j=1:3,
    if i == j then
        A(i,j) = 2;
    elseif abs(i - j) == 1 then
        A(i,j) = - 1;
    else 
        A(i,j) = 0;
    end;
end;
printf("Matrix A is");
disp(A);
