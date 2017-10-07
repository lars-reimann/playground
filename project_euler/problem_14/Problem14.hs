collatzLength :: Integer -> Integer
collatzLength 1 = 1
collatzLength n
    | even n    = 1 + collatzLength (n `div` 2)
    | odd n     = 1 + collatzLength (3 * n + 1)
