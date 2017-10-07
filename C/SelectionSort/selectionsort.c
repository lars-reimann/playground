#include <stdio.h>

void sort(int *array, int length) {
    int i, j;
    for (i = 0; i < length - 1; i++) {
        int minPos = i;
        for (j = i + 1; j < length; j++) {
            if (array[j] < array[minPos]) {
                minPos = j;
            }
        }
        int temp = array[i];
        array[i] = array[minPos];
        array[minPos] = temp;
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
    sort(array, length);

    // Ausgabe
    for (i = 0; i < length; i++) {
        printf("%i ", array[i]);
    }
    printf("\n");
    
    return 0;
}

