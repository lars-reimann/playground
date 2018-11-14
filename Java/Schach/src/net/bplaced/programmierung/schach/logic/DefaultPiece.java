package src.net.bplaced.programmierung.schach.logic;

import java.util.Collections;
import java.util.Map;

public enum DefaultPiece implements Piece {

    INVALID,

    NULL;

    @Override
    public boolean checksEnemyKing(final Piece[] pieces, final int pos,
            final int kingPos) {
        return false;
    }

    @Override
    public Map<Integer, Move> generateMoves(final Piece[] pieces,
            final int pos, final boolean validate) {
        return Collections.emptyMap();
    }

    @Override
    public ChessColor getColor() {
        return ChessColor.NULL;
    }

    @Override
    public char getName() {
        return '0';
    }

    @Override
    public int getPieceValue() {
        return 0;
    }

    public void addMove(Piece[] pieces, Map<Integer, Move> moves,
            NormalMove move, boolean validate, final int newPos) {
        throw new AssertionError();
    }

    @Override
    public int getPosValue(final int pos) {
        return 0;
    }
}
