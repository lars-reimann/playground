#include <stdio.h>

/*
 * Testet, ob die eingegebene Zahl ein Palindrom ist. 
 */
int isPalindromic(int nForwards) {
    int nBackwards = 0;
    
    /* Dreht die eingegebene Zahl um, indem die Ziffern von hinten nach vorne
     * in nBackwards geschoben werden.
     */
    int n = nForwards;
    while (n != 0) {
        nBackwards = 10 * nBackwards + n % 10;
        n /= 10;
    }
    
    return nForwards == nBackwards;
}

/*
 * Berechnet die Loesung zu Problem 4 von Projekt Euler.
 * -----------------------------------------------------------------------------
 * A palindromic number reads the same both ways. The largest palindrome made
 * from the product of two 2-digit numbers is 9009 = 91 99.
 * 
 * Find the largest palindrome made from the product of two 3-digit numbers.
 * -----------------------------------------------------------------------------
 * Hier waehlen wir wieder einen Brute-Force-Ansatz. Wir testen alle
 * Kombinationen von Zahlen i, j, wobei 0 <= i <= j < 1000. Wir koennen j >= i
 * waehlen, da die Multiplikation kommutativ ist.
 */
int solve() {
    int max = -1;
    
    int i;
    int j;
    for (i = 0; i < 1000; i++) {
        for (j = i; j < 1000; j++) {
            int n = i * j;
            if (n > max && isPalindromic(i * j)) {
                max = n;
            }
        }
    }
    return max;    
}

int main() {
    int res = solve();
    printf("%i\n", res);
    return 0;
}
