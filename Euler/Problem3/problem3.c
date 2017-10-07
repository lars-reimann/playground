#include <stdio.h>

/*
 * Berechnet die Loesung zu Problem 3 von Projekt Euler.
 * -----------------------------------------------------------------------------
 * The prime factors of 13195 are 5, 7, 13 and 29.
 * 
 * What is the largest prime factor of the number 600851475143 ?
 * -----------------------------------------------------------------------------
 * Wir beginnen mit der zu zerlegenden Zahl und teilen sie durch ihre Teiler.
 * Die Teiler sind dabei immer kleiner als die verbleibende Zahl, sodass am Ende
 * der groesste Primfaktor uebrig bleibt.
 */
int solve() {
    long long int n = 600851475143;
    
    // Reduzieren der Zahl
    int i;
    for (i = 2; i < n; i++) {
        while (n % i == 0) {
            n /= i;
        }
    }
    
    return i;    
}

int main() {
    int res = solve();
    printf("%i\n", res);
    return 0;
}
