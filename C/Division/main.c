#include <stdio.h>
#include <stdlib.h>

int main() {
    int dividend;
    int divisor;
    int decimalPlaces;
    int i;

    printf("Bitte geben Sie den Dividend ein: ");
    scanf("%d", &dividend);
    fflush(stdin);

    printf("Bitte geben Sie den Divisor ein: ");
    scanf("%d", &divisor);
    fflush(stdin);

    printf("Bitte geben Sie die Anzahl der Nachkommastellen an: ");
    scanf("%d", &decimalPlaces);
    fflush(stdin);

    /* Vorkommateil */
    printf("%d.", dividend / divisor);
    dividend = 10 * (dividend % divisor);

    /* Nachkommateil */
    for (i = 0; i < decimalPlaces && dividend != 0; i++) {
        printf("%d\n", dividend / divisor);
        dividend = 10 * (dividend % divisor);
    }

    return 1;
}
