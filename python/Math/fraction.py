from numbers import Rational


class Fraction(Rational):
    def __init__(self, numerator, denominator):
        if denominator == 0:
            raise ZeroDivisionError("divison by zero")
        self.numerator = numerator
        self.denominator = denominator

    def __repr__(self):
        return "Fraction(%i, %i)" % (self.numerator, self.denominator)

    def __str__(self):
        if self.denominator == 1: # denominator = 1, iff denominator divides numerator
            return "%i" % (self.numerator)
        else:
            return "%i/%i" % (self.numerator, self.denominator)

    def __lt__(self, other):
        numSelf = self.numerator * other.denominator
        numOther = other.numerator * self.denominator
        return numSelf < numOther

    def __add__(self, other):
        numerator = self.numerator * other.denominator + other.numerator * self.denominator
        denominator = self.numerator * other.denominator
        return Fraction(numerator, denominator)

    def __mul__(self, other):
        numerator = self.numerator * other.numerator
        denominator = self.denominator * other.denominator
        return Fraction(numerator, denominator)

    def __sub__(self, other):
        numerator = self.numerator * other.denominator - other.numerator * self.denominator
        denominator = self.numerator * other.denominator
        return Fraction(numerator, denominator)

    def __div__(self, other):
        numerator = self.numerator * other.denominator
        denominator = self.denominator * other.numerator
        return Fraction(numerator, denominator)

    def __abs__(self):
        self.numerator

    def numerator():
        doc = "The numerator property."
        def fget(self):
            return self.numerator
        def fdel(self):
            del self.numerator
        return locals()
    numerator = property(**numerator())

    def denominator():
        doc = "The denominator property."
        def fget(self):
            return self.denominator
        def fdel(self):
            del self.denominator
        return locals()
    denominator = property(**denominator())

    def __neg__(self):
        """Returns the additive inverse of self."""
        return Fraction(-self.numerator, self.denominator)

    def __pos__(self):
        return Fraction(self.numerator, self.denominator)
