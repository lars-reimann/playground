#include <limits.h>
#include <stdlib.h>
#include "stdio.h"
#include <time.h>

#include "search.h"
#include "sort.h"
#include "timeit.h"
#include "utility.h"

#define END 1000
#define TRIES 100

static int *a;
static int len;
static int x;

int has_exact_sum(int *a, int len, int x) {
    mergesort(a, len);
    int i;
    for (i = 0; i < len; i++) {
        if (binary_search(a, len, x - a[i]) != -1) {
            return 1;
        }
    }
    return 0;
}

void f() {
    has_exact_sum(a, len, x);
}

int main() {
    int i, j;
    double *times = (double *) malloc(END * sizeof(double));
    times[0] = 0;
    srand(time(NULL));
    for (i = 1; i < END; i++) {
        times[i] = 0;
        for (j = 0; j < TRIES; j++) {
            a = random_int_array(i);
            len = i;
            x = rand();
            times[i] += timeit(*f);
            free(a);
        }
        times[i] /= TRIES;
    }
    for (i = 0; i < END; i++) {
        printf("%lf ", times[i]);
    }
    printf("\n");
    free(times);
    return 0;
}
