#include <stdlib.h>
#include <stdio.h>

int main() {
    int votesA = 0;
    int votesB = 0;
    int votesC = 0;
    int seats = 0;
    int i;

    int seatsA = 0;
    int seatsB = 0;
    int seatsC = 0;

    int pointerA = 0;
    int pointerB = 0;
    int pointerC = 0;

    printf("Sitze: ");
    scanf(" %d", &seats);

    printf("Stimmen A: ");
    scanf(" %d", &votesA);

    printf("Stimmen B: ");
    scanf(" %d", &votesB);

    printf("Stimmen C: ");
    scanf(" %d", &votesC);

    int quotientsA[seats];
    int quotientsB[seats];
    int quotientsC[seats];

    for (i = 1; i <= seats; i++) {
        quotientsA[i - 1] = votesA / i;
        quotientsB[i - 1] = votesB / i;
        quotientsC[i - 1] = votesC / i;
    }

    while (seatsA + seatsB + seatsC < seats) {
        if (quotientsA[pointerA] > quotientsB[pointerB]) {
            if (quotientsA[pointerA] > quotientsC[pointerC]) {
                seatsA++;
                pointerA++;
            } else if (quotientsA[pointerA] == quotientsC[pointerC]) {
                seatsA++;
                pointerA++;
                seatsC++;
                pointerC++;
            } else {
                seatsC++;
                pointerC++;
            }
        } else if (quotientsA[pointerA] == quotientsB[pointerB]) {
            if (quotientsA[pointerA] > quotientsC[pointerC]) {
                seatsA++;
                pointerA++;
                seatsB++;
                pointerB++;
            } else if (quotientsA[pointerA] == quotientsC[pointerC]) {
                seatsA++;
                pointerA++;
                seatsB++;
                pointerB++;
                seatsC++;
                pointerC++;
            } else {
                seatsC++;
                pointerC++;
            }
        }  else {
            if (quotientsA[pointerB] > quotientsC[pointerC]) {
                seatsB++;
                pointerB++;
            } else if (quotientsA[pointerB] == quotientsC[pointerC]) {
                seatsB++;
                pointerB++;
                seatsC++;
                pointerC++;
            } else {
                seatsC++;
                pointerC++;
            }
        }
    }

    printf("Sitze A: %d\n", seatsA);
    printf("Sitze B: %d\n", seatsB);
    printf("Sitze C: %d\n", seatsC);
}
