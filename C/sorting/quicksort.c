#include "quicksort.h"

static int partition(int *a, int l, int r);
static void quicksort_rec(int *a, int l, int r);
static void swap(int *i, int *j);

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

static void quicksort_rec(int *a, int l, int r) {
    if (l < r) {
        int m = partition(a, l, r);
        quicksort_rec(a, l, m - 1);
        quicksort_rec(a, m + 1, r);
    }
}

static void swap(int *i, int *j) {
    int temp = *i;
    *i = *j;
    *j = temp;
}

void quicksort(int *a, int len) {
    quicksort_rec(a, 0, len - 1);
}
