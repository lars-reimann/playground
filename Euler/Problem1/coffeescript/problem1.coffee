sum = 0
for x in [1..999]
    sum += x if x % 3 == 0 || x % 5 == 0
console.log sum