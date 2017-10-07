import Control.Monad.State

type Queue a = [a]

dequeue :: State (Queue a) a
dequeue = do
    q <- get
    if null q
        then error "Cannot dequeue from empty queue."
        else do
            put $ tail q
            return $ head q

enqueue :: a -> State (Queue a) ()
enqueue x = state $ \xs -> ((), xs ++ [x])

test1 =

