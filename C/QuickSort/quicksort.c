#include <stdio.h>

void quicksort(int *array, int le, int ri) {
    if (le >= ri) {
        return;
    }
    int pivot = array[(le + ri) / 2];
    int i = le;
    int j = ri;
    while (i <= j) {
        while (array[i] < pivot) {
            i++;
        }
        while (array[j] > pivot) {
            j--;
        }
        if (i <= j) {
            int t = array[i];
            array[i] = array[j];
            array[j] = t;
            i++;
            j--;
        }
    }
    quicksort(array, le, j);
    quicksort(array, i, ri);
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

    quicksort(array, 0, length - 1);

    for (i = 0; i < length; i++) {
        printf("%i ", array[i]);
    }
    printf("\n");
    return 0;
}
