#include <stdio.h>

void insertsort(int *array, int length) {
    int i;
    for (i = 1; i < length; i++) {
        int key = array[i];
        int j = i;
        while (j > 0 && array[j-1] > key) {
            array[j] = array[j-1];
            j--;
        }
        array[j] = key;
    }
}

int main() {
    int length;
    
    // Eingabe
    printf("Wie viele Zahlen sollen sortiert werden? --- ");
    scanf("%i", &length);
    int array[length];
    int i;
    for (i = 0; i < length; i++) {
        printf("Wert eingeben: ");
        scanf("%i", &array[i]);
    }
    
    // Sortieren
    insertsort(array, length);

    // Ausgabe
    for (i = 0; i < length; i++) {
        printf("%i ", array[i]);
    }
    printf("\n");
    
    return 0;
}
