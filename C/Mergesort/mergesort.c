#include <stdio.h>
#include <limits.h>

void merge(int *array, int left, int middle, int right) {
    int i, j, k;
    
    // Linkes Feld von left bis middle (plus ein extra Element am Ende)
    int n1 = middle - left + 2;
    int l[n1];
    
    // Rechtes Feld von middle + 1 bis right (plus ein extra Element am Ende)
    int n2 = right - middle + 1;
    int r[n2];
    
    // Kopieren der Eintraege in neue Felder
    for (i = 0; i < n1; i++) {
        l[i] = array[left + i];
    }
    for (j = 0; j < n2; j++) {
        r[j] = array[middle + 1 + j];
    }
    
    // Trennzeichen hinzufuegen
    l[n1 - 1] = INT_MAX;
    r[n2 - 1] = INT_MAX;
    
    // Vereinigen
    i = 0;
    j = 0;
    for (k = left; k <= right; k++) {
        if (l[i] <= r[j]) {
            array[k] = l[i];
            i++;
        } else {
            array[k] = r[j];
            j++;
        }
    }
}

void sort(int *array, int left, int right) {
    if (left < right) {
        int middle = (left + right) / 2;
        sort(array, left, middle);
        sort(array, middle + 1, right);
        merge(array, left, middle, right);
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
    sort(array, 0, length - 1);

    // Ausgabe
    for (i = 0; i < length; i++) {
        printf("%i ", array[i]);
    }
    printf("\n");
    
    return 0;
}

