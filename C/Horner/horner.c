#include <stdio.h>

int computeResult(int *array, int x, int length) {
    int res = 0;
    int i;
    for (i = length - 1; i >= 0; i--) {
        res = array[i] + x * res;
    }
    return res;
}

int main() {
    int length;
    
    // Eingabe
    printf("Wie viele Zahlen sollen eingegeben werden? --- ");
    scanf("%i", &length);
    int array[length];
    int i;
    for (i = 0; i < length; i++) {
        printf("a%i: ", i);
        scanf("%i", &array[i]);
    }
    
    int x;
    printf("Fuer welche Stelle x soll das Polynom ausgerechnet werden? --- ");
    scanf("%i", &x);
    
    int res = computeResult(array, x, length);
    printf("%i\n", res);
    
    return 0;
}
