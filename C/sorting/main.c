#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#include "mergesort.h"
#include "quicksort.h"
#include "timeit.h"

#define END 500

static void print_mathematica_list(double *a, int len);
static void f();

static int *a;
static int len;

static void print_mathematica_list(double *a, int len) {
    printf("{");
    if (len > 0) {
        printf("%lf", a[0]);

        int i;
        for (i = 1; i < len; i++) {
            printf(", %lf", a[i]);
        }
    }
    printf("}");
}

static void random_int_array() {

}

static void f() {
    quicksort(a, len);
}

int main() {
    int i, j, k;
    double average_times[END];

    srand(time(NULL));
    for (i = 1; i <= END; i++) {
        average_times[i - 1] = 0;
        for (j = 0; j < i; j++) {

            // Init random integer array
            a = (int *) malloc(i * sizeof(int));
            for (k = 0; k < i; k++) {
                a[k] = rand();
            }
            len = i;

            average_times[i - 1] += timeit(&f);
            free(a);
        }
        average_times[i - 1] /= i;
     }

    print_mathematica_list(average_times, END);
    printf("\n");

    return 0;
}
