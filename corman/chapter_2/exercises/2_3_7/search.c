#include "search.h"

int binary_search(int *a, int len, int x) {
    int l = 0;
    int r = len - 1;
    while (l <= r) {
        int m = l + (r - l) / 2;
        if (a[m] == x) {
            return m;
        } else if (a[m] < x) {
            l = m + 1;
        } else {
            r = m - 1;
        }
    }
    return -1;
}

int linear_search(int *a, int len, int x) {
    int i;
    for (i = 0; i < len; i++) {
        if (a[i] == x) {
            return i;
        }
    }
    return -1;
}
