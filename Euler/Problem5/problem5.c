#include <stdio.h> 

/*
 * Berechnet den groessten gemeinsamen Teiler von a und b. Dabei nutzen wir
 * den euklidischen Algorithmus.
 */
int gcd(int a, int b) {
    while (b != 0) {
        int r = a % b;
        a = b;
        b = r;
    }
    return a;
}

/*
 * Berechnet die Loesung zu Problem 5 von Projekt Euler.
 * -----------------------------------------------------------------------------
 * 2520 is the smallest number that can be divided by each of the numbers from 1
 * to 10 without any remainder.
 * 
 * What is the smallest positive number that is evenly divisible by all of the
 * numbers from 1 to 20?
 * -----------------------------------------------------------------------------
 * Wir zaehlen die Zahlen von 1 bis 20 hoch und berechnen jeweils das kleinste
 * gemeinsame Vielfache von der neuen und der bisherigen Zahl. Dies ist unser
 * neues Zwischenergebnis.
 */
int solve() {
    int res = 1;
    int i;
    for (i = 2; i <= 20; i++) {
        res *= i / gcd(i, res);
    }
    return res;
}

int main() {
    int res = solve();
    printf("%i\n", res);
    return 0;
}
