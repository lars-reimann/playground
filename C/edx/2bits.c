#include <stdio.h>

int main(void) {
    printf("Enter a number: ");
    unsigned n;
    scanf("%u", &n);
    
    unsigned mask;
    int printZeros = 0;
    for (mask = 1 << (sizeof(unsigned) * 8 - 1) ; mask > 0; mask >>= 1) {
        if (n & mask) {
            printf("1");
            printZeros = 1;
        } else {
            if (printZeros) {
                printf("0");
            }
        }
    }
    printf("\n");
    return 0;
}