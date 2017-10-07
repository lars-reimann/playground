#include <stdio.h> 
#include <stdlib.h>

// TODO Biginteger Klasse nutzen

/*
 * Berechnet n!.
 */
long long int factorial(int n) {
    long long int res = 1;
    int i;
    for (i = 1; i <= n; i++) {
        res *= i;
    }
    return res;
}

/*
 * Berechnet die fallende Potenz n^(k).
 */
long long int factorialPower(int n, int k) {
    long long int res = 1;
    int i;
    for (i = n; i >= n - k + 1; i--) {
        res *= i;
    }
    return res;
}

long long int binomial(int n, int k) {
    return factorialPower(n, k) / factorial(k);
}

/*
 * Berechnet die Loesung zu Problem 15 von Projekt Euler.
 * -----------------------------------------------------------------------------
 * Starting in the top left corner of a 2 x 2 grid, there are 6 routes (without
 * backtracking) to the bottom right corner.
 * 
 * How many routes are there through a 20 x 20 grid?
 * -----------------------------------------------------------------------------
 * Wir berechnen das Ergebnis ueber den Binomialkoeffizienten (20 + 20) ueber 20.
 * Es sind insgesamt 40 Entscheidungen zu treffen, ob man nach unten oder rechts
 * gehen soll. Davon muessen wir genau 20 auswaehlen, die uns nach unten (oder
 * nach rechts) fuehren.
 */
long long int solve() {
    int n = 20;
    return binomial(n + n, n);
}

int main() {
    long long int res = solve();
    printf("%lld\n", res);
    return 0;
}


