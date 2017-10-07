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

int binarySearch(int *array, int left, int right, int n) {
    if (left > right) {
        return -1;
    }
    if (left == right) {
        return array[left] == n ? left : -1;
    }
    
    int middle = (left + right) / 2;
    if (array[middle] == n) {
        return middle;
    }
    if (array[middle] < n) {
        return binarySearch(array, middle + 1, right, n);
    }
    return binarySearch(array, left, middle - 1, n);
}

void findMatch(int *array, int length, int n) {
    int foundMatch = 0;
    int i;
    for (i = 0; i < length && array[i] * 2 < n; i++) {
        int difference = n - array[i];
        int index = binarySearch(array, 0, length, difference);
        if (index != -1) {
            printf("%i + %i = %i\n", array[i], array[index], n);
            foundMatch = 1;
        }
    }
    if (array[i] * 2 == n) {
        printf("%i + %i = %i\n", array[i], array[i], n);
    } else if (!foundMatch) {
        printf("Keine Treffer gefunden.\n");
    }
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
    printf("Nach welcher Summe soll gesucht werden? --- ");
    scanf("%i", &n);
    
    // Sortieren
    sort(array, 0, length - 1);
    
    // Passende Eintraege finden
    findMatch(array, length, n);
    
    return 0;
}

