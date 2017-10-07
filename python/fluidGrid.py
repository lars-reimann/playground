from decimal import *


def dec_to_str(n):
    n = str(n)
    if "." in n:
        n = n.rstrip("0").rstrip(".")
    return n


def print_grid(context, columns, gutter):
    con = Decimal(context)
    col = Decimal(columns)
    gut = Decimal(gutter)

    # Width
    for i in range(1, columns + 1):
        current = (i / col - gut * (col - i) / (con * col)) * 100
        print(".grid-" + str(i) + " { width: " + dec_to_str(current) + "%; }")

    # Display, float and margin
    print("")
    for i in range(1, columns):
        print(".grid-" + str(i) + ",")
    print(".grid-" + str(columns) + " {")
    print("    display: block;")
    print("    float: left;")
    margin = dec_to_str(gut / con * 100)
    print("    margin: 0 " + margin + "% " + margin + "% 0;")
    print("}")

    # Omega
    print("")
    print(".omega {")
    print("    margin-right: 0;")
    print("}")
