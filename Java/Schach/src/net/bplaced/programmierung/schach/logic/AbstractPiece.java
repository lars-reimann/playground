package src.net.bplaced.programmierung.schach.logic;

import java.util.Map;

public abstract class AbstractPiece implements Piece {

    public void addMove(final Piece[] pieces,
            final Map<Integer, Move> moves, final Move move,
            final boolean validate, final int oldPos, final int newPos) {
        if (!validate || move.isValid(pieces, oldPos, newPos, getColor())) {
            moves.put(newPos, move);
        }
    }

    @Override
    public boolean checksEnemyKing(final Piece[] pieces, final int pos,
            final int kingPos) {
        return generateMoves(pieces, pos, false).containsKey(kingPos);
    }
}
