#include <stdio.h>
#include <stdlib.h>

int *add(int *a, int *b, int n) {
    int *c = (int *) malloc((n + 1) * sizeof(int));
    int carry = 0;
    int i;
    for (i = n - 1; i >= 0; i--) {
        int temp = a[i] + b[i] + carry;
        
        // Summe
        c[i + 1] = temp % 2;
        
        // Neuer Uebertrag
        carry = temp / 2;
    }
    c[0] = carry;
    return c;
}

int *multiply(int *a, int *b, int n) {
	int c* = (int *) malloc((n + 1) * sizeof(int));
	int carry = 0;
	for (i = 0; i < n * n; i++) {
		
		c[i] = a[i] * b[i] + carry;
	}
}

int main() {
    int n = 10;
    int a[10] = {1, 0, 1, 0, 1, 1, 0, 1, 0, 1};
    int b[10] = {0, 1, 1, 0, 1, 0, 1, 1, 1, 0};
    
    int *c = add(a, b, n);
    int i;
    
    // Ausgabe von A
    printf(" ");
    for (i = 0; i < n; i++) {
        printf("%i", a[i]);
    }
    
    // Ausgabe von B
    printf("\n+");
    for (i = 0; i < n; i++) {
        printf("%i", b[i]);
    }
    
    // Ausgabe eines Trennstriches
    printf("\n");
    for (i = 0; i < n + 1; i++) {
        printf("-");
    }
    
    // Ausgabe von C
    printf("\n");
    for (i = 0; i < n + 1; i++) {
        printf("%i", c[i]);
    }
    printf("\n");
    
    free(c);
    return 0;
}
