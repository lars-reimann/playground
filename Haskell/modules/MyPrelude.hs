module MyPrelude where
import qualified Prelude

data Bool = False | True deriving (Prelude.Show)

infixr 3 &&
(&&) :: Bool -> Bool -> Bool
False && _ = False
True  && x = x

infixr 2 ||
(||) :: Bool -> Bool -> Bool
False || x = x
True  || _ = True

not :: Bool -> Bool
not False = True
not True  = False

otherwise :: Bool
otherwise = True

data Maybe a = Nothing | Just a deriving (Prelude.Show)

maybe :: b -> (a -> b) -> Maybe a -> b
maybe d _ Nothing   = d
maybe _ f (Just x)  = f x

data Either a b = Left a | Right b deriving (Prelude.Show)

either :: (a -> c) -> (b -> c) -> Either a b -> c
either f _ (Left x)  = f x
either _ g (Right x) = g x

data Ordering = LT | EQ | GT

fst :: (a, b) -> a
fst (x, _) = x

snd :: (a, b) -> b
snd (_, y) = y

curry :: ((a, b) -> c) -> a -> b -> c
curry f x y = f (x, y)

uncurry :: (a -> b -> c) -> ((a, b) -> c)
uncurry f (x, y) = f x y
