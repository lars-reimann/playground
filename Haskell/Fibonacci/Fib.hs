memo :: (Int -> Int) -> (Int -> Int)
memo f = g
    where g n = table !! n
          table = [f n | n <- [0..]]

fib :: Int -> Int
fib n
    | n < 2 = 1
    | otherwise = mfib (n-2) + mfib (n-1)

mfib :: Int -> Int
mfib = memo fib
