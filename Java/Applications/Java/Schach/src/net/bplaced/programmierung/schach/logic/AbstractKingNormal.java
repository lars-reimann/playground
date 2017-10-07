package src.net.bplaced.programmierung.schach.logic;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractKingNormal extends AbstractKing {

    public static final class WhiteKingNormal extends AbstractKingNormal {

        private static final WhiteKingNormal INSTANCE;

        static {
            INSTANCE = new WhiteKingNormal();
        }

        public static final Piece getInstance() {
            return INSTANCE;
        }

        private WhiteKingNormal() {

        }

        @Override
        public ChessColor getColor() {
            return ChessColor.WHITE;
        }
    }

    public static final class BlackKingNormal extends AbstractKingNormal {

        private static final BlackKingNormal INSTANCE;

        static {
            INSTANCE = new BlackKingNormal();
        }

        public static final Piece getInstance() {
            return INSTANCE;
        }

        private BlackKingNormal() {

        }

        @Override
        public ChessColor getColor() {
            return ChessColor.BLACK;
        }
    }

    @Override
    public Map<Integer, Move> generateMoves(Piece[] pieces,
            final int pos, final boolean validate) {
        final Map<Integer, Move> moves = new HashMap<Integer, Move>(
                8);
        for (int change : CHANGES) {
            moves.putAll(generateNormalMove(pieces, pos, change, validate));
            moves.putAll(generateNormalMove(pieces, pos, -change, validate));
        }
        return moves;
    }

    protected Map<Integer, Move> generateNormalMove(final Piece[] pieces,
            final int pos, final int change, final boolean validate) {
        final Map<Integer, Move> moves = new HashMap<Integer, Move>(
                1);
        final Piece piece = pieces[newPosition(change, pos)];
        if (piece != DefaultPiece.INVALID
                && (piece == DefaultPiece.NULL || piece.getColor() != getColor())) {
            final Move move = NormalMove.getInstance();
            addMove(pieces, moves, move, validate, pos, newPosition(change, pos));
        }
        return moves;
    }

    @Override
    public int getPosValue(final int pos) {
        // TODO
        return 0;
    }
}
