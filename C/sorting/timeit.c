#include <time.h>
#include <sys/time.h>

#include "timeit.h"

double timeit(void (*f)()) {
    struct timespec start, end;

    clock_gettime(CLOCK_MONOTONIC, &start);
    (*f)();
    clock_gettime(CLOCK_MONOTONIC, &end);

    return (end.tv_sec - start.tv_sec) + (end.tv_nsec - start.tv_nsec) / 1000000000.0;
}
