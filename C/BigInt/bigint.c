#include <stdio.h>
#include <stdlib.h>

int *getInput(int *length) {
    printf("Wie viele Ziffern hat die Zahl? --- ");
    scanf("%i", length);
    
    int *num = malloc(*length * sizeof(int));
    int i;
    for (i = 0; i < *length; i++) {
        printf("Naechste Ziffer: ");
        scanf("%i", &num[i]);    
    }
    
    return num;
}


int *add(int *num1, int l1, int *num2, int l2, int *length) {
    if (l1 <= l2) {
        *length = l2 + 1;
    } else {
        *length = l1 + 1;
    }
    int *res = malloc(*length * sizeof(int));
    int carry = 0;
    
    int index1 = l1 - 1;
    int index2 = l2 - 1;
    int i;
    for (i = *length - 1; i > 0; i--) {
        int nextRes = carry;
        if (index1 >= 0) {
            nextRes += num1[index1];
        }
        if (index2 >= 0) {
            nextRes += num2[index2];
        }
        res[i] = nextRes % 10;
        carry = nextRes / 10;
        index1--;
        index2--;
    }
    
    res[0] = carry;
    return res;
}

void undo(int *result) {
    while ()
}

void printResult(int *result, int length) {
    int i;
    int trailingZero = 1;
    for (i = 0; i < length; i++) {
        if (result[i] != '0') {
            trailingZero = 0;
        }
        
        if (!trailingZero) {
            printf("%i", result[i]);
        }
    }
    
    printf("\n");
}

int main() {
    int l1;
    int *num1 = getInput(&l1);
    
    int l2;
    int *num2 = getInput(&l2);
    
    int l3;
    int *res = add(num1, l1, num2, l2, &l3);
    printResult(res, l3);    
    
    free(num1);

    
    free(num2);
    free(res); // w
    return 0;
}
