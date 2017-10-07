#include <stdio.h>

int bubblesort(int *array, int length) {
    int count = 0;
    int end = length - 1;
    int hasChanged = 1;
    while (hasChanged) {
        hasChanged = 0;
        int i;
        for (i = 0; i < end; i++) {
            count++;
            if (array[i+1] < array[i]) {
                int t = array[i];
                array[i] = array[i+1];
                array[i+1] = t;
                hasChanged = 1;
            }
        }
        end--;
    }
    return count;
}

int main() {
    int length;
    printf("Wie viele Zahlen sollen sortiert werden? --- ");
    scanf("%i", &length);
    
    int array[length];
    int i;
    for (i = 0; i < length; i++) {
        printf("Wert eingeben: ");
        scanf("%i", &array[i]);
    }
    
    int count = bubblesort(array, length);
    
    printf("Vergleiche: %i\n", count);
    for (i = 0; i < length; i++) {
        printf("%i ", array[i]);
    }
    printf("\n");
    return 0;
}