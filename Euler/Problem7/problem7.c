#include <stdio.h> 

/*
 * Testet, ob die gegebene Zahl n eine Primzahl ist. Dabei wird ausserdem eine
 * Liste der Primzahlen kleiner n uebergeben. Es reicht aus zu testen, ob eine
 * dieser Primzahlen n teilt. Des Weiteren koennen wir stoppen, sobald die
 * Primzahlen groesser als die Wurzel von n werden.
 */
int isPrime(int *primes, int end, int n) {
    int i;
    for (i = 0; i <= end && primes[i] * primes[i] <= n; i++) {
        if (n % primes[i] == 0) {
            return 0;
        }
    }
    return 1;
}
/*
 * Berechnet die Loesung zu Problem 7 von Projekt Euler.
 * -----------------------------------------------------------------------------
 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see
 * that the 6th prime is 13.
 * 
 * What is the 10 001st prime number?
 * -----------------------------------------------------------------------------
 * Wir erzeugen hier einfach alle 10001 Primzahlen und geben die letzte aus.
 * Dabei halten wir Buch ueber die bisher gefundenen Primzahlen und nutzen diese
 * Liste, um die Suche nach neuen Primzahlen zu beschleunigen.
 * Weiterhin fuegen wir 2 und 3 explizit in die Liste ein und nutzen aus, dass
 * jede weitere Primzahl die Form 6k - 1 oder 6k + 1 haben muss, wobei k eine
 * natuerliche Zahl ist. Sonst waere sie durch 2 oder 3 teilbar.
 */
int solve() {
    int n = 10001;
    int primes[n];
    primes[0] = 2;
    primes[1] = 3;
    int end = 1;
    
    int i = 6;
    while (end < n)  {
        if (isPrime(primes, end, i - 1)) {
            primes[end + 1] = i - 1;
            end++;
        }
        if (isPrime(primes, end, i + 1)) {
            primes[end + 1] = i + 1;
            end++;
        }
        i += 6;
    }
    return primes[n - 1];
}

int main() {
    int res = solve();
    printf("%i\n", res);
    return 0;
}

