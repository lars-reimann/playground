#include <stdio.h>
#include <stdlib.h>

int main() {
    int number;
    int i;

    printf("Zu testende Zahl eingeben: ");
    scanf(" %d", &number);

    if (number %2 == 0) {
        printf("Keine Primzahl (durch 2 teilbar).\n");
        return 0;
    } else {
        for (i = 3; i * i <= number; i +=2) {
            if (number % i == 0) {
                printf("Keine Primzahl (durch %d teilbar).\n", i);
                return 0;
            }
        }
        printf("Primzahl.\n");
    }
    return 0;
}
