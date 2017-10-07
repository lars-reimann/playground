#include <stdio.h>
#include <stdlib.h>

#define SUCCESS 0
#define FAILURE 1

int increment(short bin[], int len) {
    int count = 0;
    int i;
    for (i = 0; i < len && bin[i] == 1; i++) {
        bin[i] = 0;
        count++;
    }
    if (i < len) {
        bin[i] = 1;
        count++;
    }
    return count;
}

int main(int argc, char **argv) {
    if (argc != 3) {
        printf("Usage: %s <length> <iterations>\n", argv[0]);
        return FAILURE;
    }
    int i;

    // Init array
    int len = atoi(argv[1]);
    short bin[len];
    for (i = 0; i < len; i++) {
        bin[i] = 0;
    }

    printf("<iteration>: <number of flips>\n");
    int avg = 0;
    int iter = atoi(argv[2]);
    for (i = 0; i < iter; i++) {
        int count = increment(bin, len);
        avg += count;
        printf("%i: %i\n", i, count);
    }
    printf("Average: %lf\n", 1.0 * avg / iter);

    return SUCCESS;
}
