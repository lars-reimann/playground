#include <math.h>

#include "radixsort.h"

static double logab(double base, double x);
static int *msort(int *a, int mask, int shift);

static double logab(double base, double x) {
    return log(x) / log(base);
}

static int *msort(int **a, int len, int mask, int shift) {

}


void radixsort(int **a, int len) {
    int r = (int) ceil(logab(2, len));
    int mask = (1 << r) - 1;
    int end = (int) ceil(1.0 * sizeof(int) / r);
    int i;
    for (i = 0; i < end; i++) {
        msort(a, len, mask, 1 << (i * r))
        mask *= 1 << r
    }
}
