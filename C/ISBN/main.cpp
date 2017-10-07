#include <stdlib.h>
#include <stdio.h>

int main() {
    char number[11];
    int i;
    int sum = 0;

    printf("ISBN-Nummer eingeben: ");
    scanf(" %s", &number);

    for (i = 0; i < 10; i++) {
        int value;
        if (number[9 - i] == 'X') {
            value = 10;
        } else if(number[9 - i] >= '0' && number[9 - i] <= '9') {
            value = number[9 - i] - '0';
        }
        sum += (i + 1) * value;
    }

    if (sum % 11 == 0) {
        printf("Gueltig\n");
    } else {
        printf("Ungueltig\n");
    }
}
