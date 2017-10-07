#include <stdio.h>

int main() {
    int x, y, z;
    
    printf("x eingeben: ");
    scanf("%i", &x);
    
    printf ("y eingeben: ");
    scanf("%i", &y);
    
    while (x > 0) {
        if (x % 2 == 1) {
            z += y;
        }
        x >>= 2;
        y <<= 2;
    }
        
    printf("Ergebnis: %i", z);
    return 0;
}
