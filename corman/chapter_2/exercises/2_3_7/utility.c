#include <stdlib.h>

#include "utility.h"

int *random_int_array(int len) {
    int *a = (int *) malloc(len * sizeof(int));
    int i;
    for (i = 0; i < len; i++) {
        a[i] = rand();
    }
    return a;
}
