#include <stdlib.h>

#include "mergesort.h"

static void merge(int *a, int l, int m, int r);
static void mergesort_rec(int *a, int l, int r);
static int *slice(int *a, int start, int end);

static void merge(int *a, int l, int m, int r) {
    int *left = slice(a, l, m + 1);
    int *right = slice(a, m + 1, r + 1);
    int iL = 0, iR = 0, iA = l;

    while (iL < (m - l + 1) && iR < (r - m)) {
        if (left[iL] <= right[iR]) {
            a[iA++] = left[iL++];
        } else {
            a[iA++] = right[iR++];
        }
    }
    while (iL < (m - l + 1)) {
        a[iA++] = left[iL++];
    }
    while (iR < (r - m)) {
        a[iA++] = right[iR++];
    }

    free(left);
    free(right);
}

static void mergesort_rec(int *a, int l, int r) {
    if (l < r) {
        int m = l + (r - l) / 2;
        mergesort_rec(a, l, m);
        mergesort_rec(a, m + 1, r);
        merge(a, l, m, r);
    }
}

static int *slice(int *a, int start, int end) {
    int *res = (int *) malloc((end - start) * sizeof(int));
    int i;
    for (i = end - start - 1; i >= 0; i--) {
        res[i] = a[start + i];
    }
    return res;
}

void mergesort(int *a, int len) {
    mergesort_rec(a, 0, len - 1);
}
