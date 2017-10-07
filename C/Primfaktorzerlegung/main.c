#include <stdlib.h>
#include <stdio.h>

int main() {
    int number;
    int i;
    int j;

    printf("Zu zerlegende Zahl eingeben: ");
    scanf(" %d", &number);

    int primes[number + 1];

    primes[0] = 0;
    primes[1] = 0;
    primes[2] = 1;
    for (i = 3; i <= number; i++) {
        primes[i] = 1;
        if (i % 2 == 0) {
            primes[i] = 0;
        }
        for (j = 3; j * j <= i; j +=2) {
            if (i % j == 0) {
                primes[i] = 0;
                break;
            }
        }
    }

    int wasPrime = 0;

    for (i = 2; i <= number; i++) {
        while ((primes[i]) && (number % i == 0)) {
            if (wasPrime) {
                printf("*");
            }
            printf("%d", i);
            number /= i;
            wasPrime = 1;
        }
    }
    printf("\n");
    return 0;
}
