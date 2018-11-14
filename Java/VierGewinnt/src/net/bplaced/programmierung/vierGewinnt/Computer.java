package net.bplaced.programmierung.vierGewinnt;
import java.util.List;
import java.util.Random;



public final class Computer {
    
    private final Engine engine;
    
    private long[][] transpositionIndex = new long[42][10];
    private int[][] transpositionValue = new int[42][10];
    private int insertionIndex = 0;
    
    private static final long[][][] hashValues = new long[42][3][2];
    
    static {
        final Random random = new Random();
        for (int i = 0; i < 42; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 2; k++) {
                    hashValues[i][j][k] = random.nextLong();
                }
            }
        }
    }
    
    public Computer(final Engine engine) {
        this.engine = engine;
    }

    private int searchRoot(final int player) {
        final List<Integer> moves = engine.genMoves();
        int bestMove = 0;
        int best = -1;
        for (int move : moves) {
            engine.doMove(move, player);
            final int value = -alphaBeta(1, -1, -best, -player);
            engine.undoMove(move, player);
            if (value > best) {
                best = value;
                bestMove = move;
            }
        }
        return bestMove;
    }
    
    private int alphaBeta(final int depth, final int alpha, final int beta, final int player) {
        if (depth == 10) {
            return 0;
        }
        long hashValue = 0;
        for (int i = 0; i < 42; i++) {
            final int piece;
            if ((engine.mapPlayerNeg & Data.MAPS_FIELD[i]) != 0) {
                piece = 0;
            } else if ((engine.mapPlayerPos & Data.MAPS_FIELD[i]) != 0) {
                piece = 1;
            } else {
                piece = 2;
            }
            final int playerIndex = player == -1 ? 0 : 1;
            hashValue ^= hashValues[i][piece][playerIndex];
        }
        int index = -1;
        for (int i = 0; i < 10; i++) {
            if (transpositionIndex[depth][i] == hashValue) {
                index = i;
                break;
            }
        }
        if (index >= 0) {
            return transpositionValue[depth][index];
        }
        if (engine.isWonForPlayer(player)) {
            return 1;
        } else if (engine.isWonForPlayer(-player)) {
            return -1;
        } else if (engine.isDraw()) {
            return 0;
        } 
        final List<Integer> moves = engine.genMoves();
        int best = alpha;
        for (int move : moves) {
            engine.doMove(move, player);
            final int value = -alphaBeta(depth + 1, -beta, -best, -player);
            engine.undoMove(move, player);
            if (value >= beta) {
                return beta;
            }
            if (value > best)
                best = value;
        }
        transpositionIndex[depth][insertionIndex] = hashValue;
        transpositionValue[depth][insertionIndex] = best;
        insertionIndex = (insertionIndex + 1) % 10; 
        return best;
    }

    public void doComputerMove(final int player) {
        engine.doMove(searchRoot(player), player);
    }
}
