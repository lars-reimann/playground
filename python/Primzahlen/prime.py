import math


def isPrime(n, primes):
    end = math.floor(math.sqrt(n))
    for prime in primes:
        if prime > end:
            return True
        if n % prime == 0:
            return False
    return True


def findPrimes(end):
    """Bestimmt die Primzahlen zwischen 2 und der angegebenen Zahl."""
    primes = []
    for i in range(2, end + 1):
        if isPrime(i):
            primes.append(i)
    return primes

primes = findPrimes(1000)
for prime in primes:
    print(prime)
