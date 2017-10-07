def permutations(s):
    s = sorted(s)
    n = len(s)
    while True:

        # Visit
        print("".join(s))

        # Find first swap index
        i = n - 2
        while i >= 0 and s[i] >= s[i+1]:
            i -= 1

        # Last permutation visited
        if i < 0:
            return

        # Find second swap index
        j = n - 1
        while s[i] >= s[j]:
            j -= 1

        # Swap
        s[i], s[j] = s[j], s[i]

        # Reverse
        s[i+1:] = reversed(s[i+1:])


permutations("hello")
