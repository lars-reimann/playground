sum_self_powers_mod 0 m = 0 `mod` m
sum_self_powers_mod n m = ((pow_mod n n m) + (sum_self_powers_mod (n - 1) m)) `mod` m

pow_mod _ 0 m = 1 `mod` m
pow_mod 0 _ m = 0 `mod` m
pow_mod a b m = let square k = k * k
                in if even b
                     then (square (pow_mod a (b `div` 2) m)) `mod` m
                     else (a * pow_mod a (b - 1) m) `mod` m
