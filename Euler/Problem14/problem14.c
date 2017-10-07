#include <stdio.h> 
#include <stdlib.h>

/*
 * Berechnet die Loesung zu Problem 14 von Projekt Euler.
 * -----------------------------------------------------------------------------
 * The following iterative sequence is defined for the set of positive integers:
 * n -> n/2 (n is even)
 * n -> 3n + 1 (n is odd)
 * Using the rule above and starting with 13, we generate the following sequence:
 * 13 -> 40 -> 20 -> 10 -> 5 -> 16 -> 8 -> 4 -> 2 -> 1
 * It can be seen that this sequence (starting at 13 and finishing at 1) contains
 * 10 terms. Although it has not been proved yet (Collatz Problem), it is thought
 * that all starting numbers finish at 1.
 * 
 * Which starting number, under one million, produces the longest chain?
 * 
 * NOTE: Once the chain starts the terms are allowed to go above one million.
 * -----------------------------------------------------------------------------
 * Wir nutzen dynamische Programmierung zur Loesung des Problems. Wir speichern
 * dazu die Laenge der bisher berechneten Folgen ab. Sobald wir eine Zahl
 * treffen, fuer die wir die Laenge schon bestimmt haben, brechen wir die
 * Auswertung fuer diese Zahl ab und nutzen unser bisheriges Ergebnis.
 */
int solve() {
    int maxNumber = 1;
    
    int *count = (int *) malloc(1000000 * sizeof(int));
    
    // Initialisierung
    count[0] = 0;
    count[1] = 1;
    
    // Berechnen
    int i;
    for (i = 2; i < 1000000; i++) {
        count[i] = 0;
        
        // Wir verwenden long long int, da es bei int zu einem Ueberlauf kommt
        long long int n = i;
        while (n >= i) {
            count[i]++;
            if (n % 2 == 0) {
                n /= 2;
            } else {
                n = 3 * n + 1;
            }
        }
        count[i] += count[n];
        if (count[i] > count[maxNumber]) {
            maxNumber = i;
        }
    }
    
    free(count);
    return maxNumber;
}

int main() {
    int res = solve();
    printf("%i\n", res);
    return 0;
}


