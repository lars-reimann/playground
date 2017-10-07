def combinations(n, t):

    # Initialize
    comb = [i for i in range(0, t)]
    comb.append(n)  # sentinel
    comb.append(0)  # sentinel

    while True:

        # Visit
        yield comb[:t]

        # Find i
        i = 0
        while comb[i] + 1 == comb[i+1]:
            comb[i] = i  # initial value
            i += 1

        # Done?
        if i >= t:
            return

        # Increase comb[i]
        comb[i] += 1
