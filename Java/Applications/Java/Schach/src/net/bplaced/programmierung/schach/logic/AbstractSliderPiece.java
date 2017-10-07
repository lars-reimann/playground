package src.net.bplaced.programmierung.schach.logic;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractSliderPiece extends AbstractPiece {

    @Override
    public Map<Integer, Move> generateMoves(final Piece[] pieces,
            final int pos, final boolean validate) {
        final Map<Integer, Move> moves = new HashMap<Integer, Move>(28);
        final int[] changes = getChanges();
        for (int change : changes) {
            moves.putAll(generateMoves(pieces, pos, change, validate));
            moves.putAll(generateMoves(pieces, pos, -change, validate));
        }
        return moves;
    }

    protected Map<Integer, Move> generateMoves(final Piece[] pieces,
            final int pos, final int change, final boolean validate) {
        final Map<Integer, Move> moves = new HashMap<Integer, Move>(7);
        for (int i = 1; ; i++) {
            final Piece piece = pieces[newPosition(i, pos, change)];
            if (piece == DefaultPiece.INVALID) {
                break;
            } else if (piece == DefaultPiece.NULL) {
                final Move move = NormalMove.getInstance();
                addMove(pieces, moves, move, validate, pos, newPosition(i, pos, change));
            } else {
                if (piece.getColor() != getColor()) {
                    final Move move = NormalMove.getInstance();
                    addMove(pieces, moves, move, validate, pos, newPosition(i, pos, change));
                }
                break;
            }
        }
        return moves;
    }

    protected abstract int[] getChanges();

    protected int newPosition(final int i, final int pos, final int change) {
        return pos + i * change;
    }
}