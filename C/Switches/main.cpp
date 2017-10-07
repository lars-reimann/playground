#include <stdlib.h>
#include <stdio.h>

int main() {
    int switch1;
    int switch2;
    int switch3;
    int switch4;
    int count;

    printf("Weichenposition einstellen: ");
    scanf(" %d %d %d %d", &switch1, &switch2, &switch3, &switch4);

    printf("Anzahl Durchlaeufe: ");
    scanf(" %d", &count);

    for ( ; count > 0; count--) {
        if (switch1 == 0) {
            switch1 = 1;
            if (switch2 == 0) {
                switch2 = 1;
                printf("Ziel: 4");
            } else {
                switch2 = 0;
                if (switch4 == 0) {
                    switch4 = 1;
                    printf("Ziel: 4");
                } else {
                    switch4 = 0;
                    printf("Ziel: 5");
                }
            }
        } else {
            switch1 = 0;
            if (switch3 == 0) {
                switch3 = 1;
                if (switch4 == 0) {
                    switch4 = 1;
                    printf("Ziel: 4");
                } else {
                    switch4 = 0;
                    printf("Ziel: 5");
                }
            } else {
                switch3 = 0;
                printf("Ziel: 5");
            }
        }
        printf(" Neue Stellung: %d %d %d %d\n", switch1, switch2, switch3, switch4);
    }
}
