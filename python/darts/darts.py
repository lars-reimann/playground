class Score:
    def __init__(self, value, text, area):
        self.value = value
        self.text = text
        self.area = area

    def __str__(self):
        return self.text

singles = [Score(25, "25", 1.031)] + [Score(i, str(i), 5.844) for i in range(20, 0, -1)]
doubles = [Score(50, "BULL", 0.196)] + [Score(2 * i, "D" + str(i), 0.773) for i in range(20, 0, -1)]
triples = [Score(3 * i, "T" + str(i), 0.479) for i in range(20, 0, -1)]

solution = []
best_solution = []
best_value = -1

def is_solvable(remainder, ndarts):
    if remainder == 0:
        return True
    elif remainder > 0:
        if ndarts == 1:
            for double in doubles:
                if (remainder - double.value < 0):
                    continue
                solution.append(double)
                if is_solvable(remainder - double.value, ndarts - 1):
                    return True
                solution.pop()
        elif ndarts > 1:
            for triple in triples:
                if (remainder - triple.value <= 0):
                    continue
                solution.append(triple)
                if is_solvable(remainder - triple.value, ndarts - 1):
                    return True
                solution.pop()
            for single in singles:
                if (remainder - single.value <= 0):
                    continue
                solution.append(single)
                if is_solvable(remainder - single.value, ndarts - 1):
                    return True
                solution.pop()
            for double in doubles:
                if (remainder - double.value < 0):
                    continue
                solution.append(double)
                if is_solvable(remainder - double.value, ndarts - 1):
                    return True
                solution.pop()
    return False

def assess(solution, ndarts):
    value = 0
    for throw in solution:
        value += throw.area
        ndarts -= 1
    return value + ndarts * 5.845

def checkout(remainder, ndarts):
    global best_solution
    global best_value
    if remainder == 0:
        value = assess(solution, ndarts)
        if (value > best_value):
            best_solution = solution[:]
            best_value = value
    elif remainder > 0:
        if ndarts == 1:
            for double in doubles:
                if (remainder - double.value < 0):
                    continue
                solution.append(double)
                checkout(remainder - double.value, ndarts - 1)
                solution.pop()
        elif ndarts > 1:
            for single in singles:
                if (remainder - single.value <= 0):
                    continue
                solution.append(single)
                checkout(remainder - single.value, ndarts - 1)
                solution.pop()
            for double in doubles:
                if (remainder - double.value < 0):
                    continue
                solution.append(double)
                checkout(remainder - double.value, ndarts - 1)
                solution.pop()
            for triple in triples:
                if (remainder - triple.value <= 0):
                    continue
                solution.append(triple)
                checkout(remainder - triple.value, ndarts - 1)
                solution.pop()

"""
for i in range(1, 171):
    best_solution = []
    best_value = -1
    checkout(i, 3)
    print(i, end=" ")
    for throw in best_solution:
        print(throw, end=" ")
    print(" ")
"""
checkout(71, 3)
#TODO check if missing a triple/double and hitting the single one instead leaves an option
#TODO change for loops + continue to something with a clear starting point
# TODO refactor program
