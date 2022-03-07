#include <math.h>

double findSQRT(double N) { return sqrt(N); }

long long convert(int n) {
    long long bin = 0;
    int rem, i = 1;

    while (n != 0) {
        rem = n % 2;
        n /= 2;
        bin += rem * i;
        i *= 10;
    }

    return bin;
}