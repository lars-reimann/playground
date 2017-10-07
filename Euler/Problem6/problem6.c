#include <stdio.h> 

/*
 * Berechnet die Loesung zu Problem 6 von Projekt Euler.
 * -----------------------------------------------------------------------------
 * The sum of the squares of the first ten natural numbers is,
 * 12 + 22 + ... + 102 = 385
 * The square of the sum of the first ten natural numbers is,
 * (1 + 2 + ... + 10)^2 = 55^2 = 3025
 * Hence the difference between the sum of the squares of the first ten natural
 * numbers and the square of the sum is 3025 - 385 = 2640.
 * 
 * Find the difference between the sum of the squares of the first one hundred
 * natural numbers and the square of the sum.
 * -----------------------------------------------------------------------------
 * Wir berechnen jeweils ueber die geschlossene Form die Summe der ersten 100
 * natuerlichen Zahlen und quadrieren diese anschliessend. Dies ist im uebrigen
 * auch die Summe der ersten 100 Zahlen hoch 3. Dann berechnen wir die Summe
 * der ersten hundert Quadratzahlen und berechnen die Differenz.
 */
int solve() {
    int sumNormal = 100*(100 + 1)/2;
    int sumSquare = 100*(2*100 + 1)*(100 + 1)/6;
    int sumCube = sumNormal * sumNormal;
    return sumCube - sumSquare;
}

int main() {
    int res = solve();
    printf("%i\n", res);
    return 0;
}
