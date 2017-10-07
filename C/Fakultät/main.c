#include <stdio.h>

unsigned int fac(unsigned int n) {
    return n == 0 ? 1 : n * fac(n - 1);
}

int main() {
    unsigned int n;

    printf("n: ");
    scanf("%u", &n);
    printf("n! = %u\n", fac(n));

    return 0;
}
