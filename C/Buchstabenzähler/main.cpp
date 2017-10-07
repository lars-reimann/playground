#include <stdlib.h>
#include <stdio.h>

int main() {
    int count;
    int i;
    int j;
    int letterCount[26];

    for (i = 0; i < 26; i++) {
        letterCount[i] = 0;
    }

    printf("Anzahl Woerter: ");
    scanf("%d", &count);

    for (i = 1; i <= count; i++) {
        char word[100];

        printf(" %d. ", i);
        scanf("%s", word);

        for (j = 0; word[j] != 0 && j < 100; j++) {
            if (word[j] >= 'a' && word[j] <= 'z') {
                letterCount[word[j] - 'a']++;
            } else if (word[j] >= 'A' && word[j] <= 'Z') {
                letterCount[word[j] - 'A']++;
            }
        }
    }

    for (i = 0; i < 26; i++) {
        printf("%c: %d\n", 'a' + i, letterCount[i]);
    }
}
