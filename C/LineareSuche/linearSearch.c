#include <stdio.h>

/*
 * Gibt den Index des ersten Auftreten von n im gegebenen Array aus. Sollte n
 * nicht vorkommen, wird -1 zurueckgegeben.
 */
int search(int *array, int length, int n) {
    int i;
    for (i = 0; i < length; i++) {
        if (array[i] == n) {
            return i;
        }
    }
    return -1;
}

int main() {
    int length;
    
    // Eingabe
    printf("Wie viele Zahlen sollen eingegeben werden? --- ");
    scanf("%i", &length);
    int array[length];
    int i;
    for (i = 0; i < length; i++) {
        printf("Wert eingeben: ");
        scanf("%i", &array[i]);
    }
    int n;
    printf("Wonach soll gesucht werden? --- ");
    scanf("%i", &n);
    
    // Suchen
    int index = search(array, length, n);

    // Ausgabe
    printf("%i\n", index);
    
    return 0;
}
