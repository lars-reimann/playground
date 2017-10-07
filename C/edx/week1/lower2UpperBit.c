#include <stdio.h>

int isLowercase(char c) {
    return 'a' <= c && c <= 'z';
}

int main(void) {
    printf("Enter a lowercase char: ");
    char c;
    scanf("%c", &c);
    if (!isLowercase(c)) {
        printf("That is not a lowercase char.\n");
    } else {

        // Das Bit mit der Wertigkeit 2^5 dient zum Umschalten
        printf("%c\n", c ^ ('a' - 'A'));
    }
    return 0;
}
