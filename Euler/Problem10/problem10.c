#include <stdio.h> 
#include <stdlib.h>

/*
 * Berechnet die Loesung zu Problem 10 von Projekt Euler.
 * -----------------------------------------------------------------------------
 * The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
 * 
 * Find the sum of all the primes below two million.
 * -----------------------------------------------------------------------------
 * Wir berechnen die Primzahlen kleiner 2.000.000 mithilfe des Siebs des
 * Erathostenes und summieren sie alle auf.
 */
int solve() {
    int *primes = (int *) malloc(2000000 * sizeof(int));
    
    // Initialisierung
    primes[0] = 0;
    primes[1] = 0;
    int i;
    for (i = 2; i < 2000000; i++) {
        primes[i] = 1;
    }
    
    // Aussieben
    for (i = 2; i * i <= 2000000; i++) {
        if (primes[i]) {
            int j;
            for (j = 2 * i; j < 2000000; j += i) {
                primes[j] = 0;
            }
        }
    }
    
    // Aufsummieren
    int sum = 0;
    for (i = 2; i < 2000000; i++) {
        if (primes[i]) {
            sum += i;
        }
    }
    
    free(primes);
    return sum;
}

int main() {
    int res = solve();
    printf("%i\n", res);
    return 0;
}

