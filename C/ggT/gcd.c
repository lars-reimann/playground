#include <stdio.h>

int gcd(int a, int b, int *aI, int *bI) {
	int res;
	
	if (a == 0) {
		res = b;
		
		*aI = 0;
		*bI = 1;
	} else {
		res = gcd(b % a, a, aI, bI);
		
		int tmp = *aI;
		*aI = (*bI) - (b / a) * (*aI);
		*bI = tmp;
	}
	return res;
}

int main() {
	int a = 345;
	int b = 1235;
	int aI;
	int bI;
	int res = gcd(a, b, &aI, &bI);
	
	printf("%i = %i*%i + %i*%i", res, a, aI, b, bI);
	
	return 0;
}
