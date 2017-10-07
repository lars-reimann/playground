#include <stdint.h>
#include <stdio.h>

/**
 *
 */
static inline void printProgress(int complete, int total) {
    static int last = 0;
    int ratio = 100 * complete / total;

    if (ratio > last) {
        last = ratio;
        printf("%2d%%\n", ratio);
        printf("\033[F\033[J");
    }
}

/**
 *
 */
int collatzLength(int64_t n) {
    int length = 1;

    while (n != 1) {
        length++;
        if (n % 2 == 0) {
            n /= 2;
        } else {
            n = 3 * n + 1;
        }
    }

    return length;
}

int main() {
    int i,
        length,
        maxLength = 0,
        maxNumber;

    for (i = 1; i < 1000000; i++) {
        length = collatzLength(i);
        if (length > maxLength) {
            maxLength = length;
            maxNumber = i;
        }
        printProgress(i, 1000000);
    }
    printf("Ergebnis: %d\n", maxNumber);

    collatzLength(113383);
    return 0;
}
