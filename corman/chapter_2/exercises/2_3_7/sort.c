#include "sort.h"

static void swap(int *a, int *b) {
    int temp = *a;
    *a = *b;
    *b = temp;
}

void bubblesort(int *a, int len) {
    int i, j;
    int has_changed = 1;
    for (i = len - 1; has_changed; i--) {
        has_changed = 0;
        for (j = 0; j < i; j++) {
            if (a[j] > a[j + 1]) {
                swap(&a[j], &a[j + 1]);
                has_changed = 1;
            }
        }
    }
}

void insertionsort(int *a, int len) {
    int i;
    for (i = 1; i < len; i++) {
        int x = a[i];
        int j = i;
        while (j > 0 && a[j - 1] > x) {
            a[j] = a[j - 1];
            j--;
        }
        a[j] = x;
    }
}

static void merge(int *a, int l, int m, int r) {

    // Copy arrays
    int i;
    int left[m - l + 1];
    int right[r - m];
    for (i = 0; i < m - l + 1; i++) {
        left[i] = a[l + i];
    }
    for (i = 0; i < r - m; i++) {
        right[i] = a[m + 1 + i];
    }

    // Merge
    int iL = 0;
    int iR = 0;
    int iA = l;
    while (iL < m - l + 1 && iR < r - m) {
        if (left[iL] <= right[iR]) {
            a[iA++] = left[iL++];
        } else {
            a[iA++] = right[iR++];
        }
    }
    while (iL < m - l + 1) {
        a[iA++] = left[iL++];
    }
    while (iR < r - m) {
        a[iA++] = right[iR++];
    }
}

static void _mergesort(int *a, int l, int r) {
    if (l < r) {
        int m = l + (r - l) / 2;
        _mergesort(a, l, m);
        _mergesort(a, m + 1, r);
        merge(a, l, m, r);
    }
}

void mergesort(int *a, int len) {
    _mergesort(a, 0, len - 1);
}

static int partition(int *a, int l, int r) {
    int i = l;
    int j;
    for (j = l; j < r; j++) {
        if (a[j] <= a[r]) {
            swap(&a[i], &a[j]);
            i++;
        }
    }
    swap(&a[i], &a[r]);
    return i;
}

static void _quicksort(int *a, int l, int r) {
    if (l < r) {
        int q = partition(a, l, r);
        _quicksort(a, l, q - 1);
        _quicksort(a, q + 1, r);
    }
}

void quicksort(int *a, int len) {
    _quicksort(a, 0, len - 1);
}

void selectionsort(int *a, int len) {
    int i, j;
    for (i = 0; i < len; i++) {
        int min_index = i;
        for (j = i + 1; j < len; j++) {
            if (a[j] < a[i]) {
                min_index = j;
            }
        }
        swap(&a[i], &a[min_index]);
    }
}
