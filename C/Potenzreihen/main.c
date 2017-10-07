#include <stdlib.h>
#include <stdio.h>
#include "powerseries.h";

int main() {
    int count;
    double result = 0.;
    double x;
    int i;

    printf("Anzahl Summanden: ");
    scanf(" %d", &count);

    printf("x: ");
    scanf(" %lf", &x);

    // Sinus
    for (i = 0; i < count; i++) {
        double change = next(x, 2 * i + 1);
        if (i % 2 == 0) {
            result += change;
        } else {
            result -= change;
        }
    }
    printf("sin(%lf) = %lf\n", x, result);

    result = 0.;

    // Cosinus
    for (i = 0; i < count; i++) {
        double change = next(x, 2 * i);
        if (i % 2 == 0) {
            result += change;
        } else {
            result -= change;
        }
    }
    printf("cos(%lf) = %lf\n", x, result);

    result = 0.;

    // Exponentialfunktion
    for (i = 0; i < count; i++) {
        result += next(x, i);
    }
    printf("e^%lf = %lf\n", x, result);

    result = 0.;
}

double next(double x, double fac) {
    double result = 1;
    int i;

    for (i = 1; i <= fac; i++) {
        result *= x / i;
    }

    return result;
}


