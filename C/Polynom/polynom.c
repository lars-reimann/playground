#include <stdio.h>

int powerSlow(int a, int b) {
    int res = 1;
    int i;
    for (i = 0; i < b; i++) {
        res *= a;
    }
    return res;
}

int powerFastIt(int a, int b) {
    int res = 1;
    int mask = 1 << 30; // 2^31 ist kleinste negative Zahl im Zweierkomplement
    int i;
    for (i = 30; i >= 0; i--) {
        res *= res;
        if (b & mask) {
            res *= a;
        }
        mask >>= 1;
    }
    return res;
}

int powerFastRec(int a, int b) {
    if (b == 0) {
        return 1;
    }
    if (b % 2 == 0) {
        int temp = powerFastRec(a, b/2);
        return temp * temp;
    }
    return a * powerFastRec(a, b - 1);
}

int computeResult(int *array, int x, int length) {
    int res = 0;
    int i;
    for (i = 0; i < length; i++) {
        res += array[i] * powerFastIt(x, i);
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

