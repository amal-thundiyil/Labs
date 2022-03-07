#include <stdio.h>

#include "lib_mylib.h"

int main() {
    int n, bin;
    printf("Enter a decimal number: ");
    scanf("%d", &n);
    bin = convert(n);
    printf("%d in decimal =  %d in binary", n, bin);
    return 0;
}