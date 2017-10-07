# -*- coding: utf-8 -*-
"""
Created on Sun Jan 13 09:56:12 2013

@author: lars
"""

def factorial(n):
    res = 1
    for i in xrange(1, n + 1):
        res *= i
    return res
        
def factorialPower(n, k):
    res = 1
    for i in xrange(n - k + 1, n + 1):
        res *= i
    return res
        
def binomial(n, k):
    return factorialPower(n, k) / factorial(k)
    
count = 0
for n in xrange(1, 101):
    for k in xrange(0, n + 1):
        if binomial(n, k) > 100000:
            count += 1
            print str(n) + "C" + str(k)
print count