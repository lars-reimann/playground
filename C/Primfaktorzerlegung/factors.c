#include <stdio.h>
#include <stdlib.h>

unsigned int getNumber() {
    unsigned int n;
    printf("Zahl eingeben: ");
    scanf("%u", &n);
    return n;
}

unsigned short *computePrimes(unsigned int n) {
    unsigned short *primes = (unsigned short *) malloc((n + 1) * sizeof(unsigned short));
    int i, j;

    // Initialisieren
    primes[0] = 0;
    primes[1] = 0;
    for (i = 2; i <= n; i++) {
        primes[i] = 1;
    }

    // Aussieben
    for (i = 2; i * i <= n; i++) {
        if (primes[i]) {
            for (j = 2 * i; j <= n; j += i) {
                primes[j] = 0;
            }
        }
    }

    return primes;
}

void factorize(int n, unsigned short *primes) {
    int i = 0;
    while (i <= n) {
        if (primes[i]) {
            int count = 0;
            while (n % i == 0) {
                n /= i;
                count++;
            }
            if (count > 0) {
                printf("%i^%i ", i, count);
            }
        }
        i++;
    }
    printf("\n");
}

int main() {
    unsigned int n = getNumber();
    unsigned short *primes = computePrimes(n);
    factorize(n, primes);
    free(primes);
    return 0;
}
