import Test.QuickCheck

-- Problem 1
myLast :: [a] -> a
myLast [x]    = x
myLast (_:xs) = myLast xs

-- Problem 2
myInit :: [a] -> [a]
myInit [x]    = []
myInit (x:xs) = x:myInit xs

myButLast :: [a] -> a
myButLast = myLast . myInit

-- Problem 3
elementAt :: [a] -> Int -> a
elementAt (x:_) 0  = x
elementAt (_:xs) n = elementAt xs (n-1)

-- Problem 4
myLength :: [a] -> Int
myLength []     = 0
myLength (_:xs) = 1 + myLength xs

myLength' :: [a] -> Int
myLength' = foldr (\x acc -> acc+1) 0

-- Problem 5
myReverse :: [a] -> [a]
myReverse []     = []
myReverse (x:xs) = myReverse xs ++ [x]

myReverse' :: [a] -> [a]
myReverse' = foldl (flip (:)) []

-- Problem 6
isPalindrome :: Eq a => [a] -> Bool
isPalindrome xs = myReverse' xs == xs

-- Problem 7
data NestedList a = Elem a | List [NestedList a]

flatten :: NestedList a -> [a]
flatten (Elem x)      = [x]
flatten (List xs)     = concatMap flatten xs

-- Problem 8
compress :: Eq a => [a] -> [a]
compress (x:y:xs)
    | x == y    = compress (y:xs)
    | otherwise = x:compress (y:xs)
compress xs  = xs

-- QuickCheck
test1 xs = xs /= [] ==> myLast xs == last xs
test2 xs = xs /= [] ==> myInit xs == init xs
test3 xs n = 0 <= n && n < length xs ==> elementAt xs n == xs !! n
test4 xs = myLength xs == length xs
test5 xs = myLength' xs == length xs
test6 xs = myReverse xs == reverse xs
test7 xs = myReverse' xs == reverse xs
