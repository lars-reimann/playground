package src.net.bplaced.programmierung.schach.logic;

import java.util.HashMap;
import java.util.Map;

import net.bplaced.programmierung.schach.logic.AbstractBothCastlingsDisablingMove.BlackBothCastlingsDisablingMove;
import net.bplaced.programmierung.schach.logic.AbstractBothCastlingsDisablingMove.WhiteBothCastlingsDisablingMove;
import net.bplaced.programmierung.schach.logic.AbstractKingsideCastlingMove.BlackKingsideCastlingMove;
import net.bplaced.programmierung.schach.logic.AbstractKingsideCastlingMove.WhiteKingsideCastlingMove;
import net.bplaced.programmierung.schach.logic.AbstractQueensideCastlingMove.BlackQueensideCastlingMove;
import net.bplaced.programmierung.schach.logic.AbstractQueensideCastlingMove.WhiteQueensideCastlingMove;

public abstract class AbstractKingBothCastlings extends AbstractKing {

    public static final class BlackKingBothCastlings extends
            AbstractKingBothCastlings {

        private static final BlackKingBothCastlings INSTANCE;

        static {
            INSTANCE = new BlackKingBothCastlings();
        }

        public static final Piece getInstance() {
            return INSTANCE;
        }

        private BlackKingBothCastlings() {
        }

        @Override
        public ChessColor getColor() {
            return ChessColor.BLACK;
        }
        
        protected Map<Integer, Move> generateCastlings(final Piece[] pieces,
                final int pos, final boolean validate) {
            final Map<Integer, Move> moves = new HashMap<Integer, Move>(
                    2);
            // TODO Separate Methode
            if (pieces[pos + 1] == DefaultPiece.NULL
                    && pieces[pos + 2] == DefaultPiece.NULL) {
                final Move move = BlackKingsideCastlingMove.getInstance();
                if (!validate || move.isValid(pieces, pos, pos + 2, getColor())) {
                    moves.put(pos + 2, move);
                }
            }
            if (pieces[pos - 1] == DefaultPiece.NULL
                    && pieces[pos - 2] == DefaultPiece.NULL) {
                final Move move = BlackQueensideCastlingMove.getInstance();
                if (!validate || move.isValid(pieces, pos, pos - 2, getColor())) {
                    moves.put(pos - 2, move);
                }
            }
            return moves;
        }
        
        protected Map<Integer, Move> generateNormalMove(final Piece[] pieces,
                final int pos, final int change, final boolean validate) {
            final Map<Integer, Move> moves = new HashMap<Integer, Move>(
                    1);
            if (isPossibleMove(pieces[newPosition(change, pos)])) {
                final Move move = BlackBothCastlingsDisablingMove.getInstance();
                if (!validate || move.isValid(pieces, pos, newPosition(change, pos), getColor())) {
                    moves.put(newPosition(change, pos), move);
                }
            }
            return moves;
        }
    }

    public static final class WhiteKingBothCastlings extends
            AbstractKingBothCastlings {

        private static final WhiteKingBothCastlings INSTANCE;

        static {
            INSTANCE = new WhiteKingBothCastlings();
        }

        public static final Piece getInstance() {
            return INSTANCE;
        }

        private WhiteKingBothCastlings() {
        }

        @Override
        public ChessColor getColor() {
            return ChessColor.WHITE;
        }
        
        protected Map<Integer, Move> generateCastlings(final Piece[] pieces,
                final int pos, final boolean validate) {
            final Map<Integer, Move> moves = new HashMap<Integer, Move>(
                    2);
            // TODO Separate Methode
            if (pieces[pos + 1] == DefaultPiece.NULL
                    && pieces[pos + 2] == DefaultPiece.NULL) {
                final Move move = WhiteKingsideCastlingMove.getInstance();
                if (!validate || move.isValid(pieces, pos, pos + 2, getColor())) {
                    moves.put(pos + 2, move);
                }
            }
            if (pieces[pos - 1] == DefaultPiece.NULL
                    && pieces[pos - 2] == DefaultPiece.NULL) {
                final Move move = WhiteQueensideCastlingMove.getInstance();
                if (!validate || move.isValid(pieces, pos, pos + 2, getColor())) {
                    moves.put(pos - 2, move);
                }
            }
            return moves;
        }
        
        protected Map<Integer, Move> generateNormalMove(final Piece[] pieces,
                final int pos, final int change, final boolean validate) {
            final Map<Integer, Move> moves = new HashMap<Integer, Move>(
                    1);
            if (isPossibleMove(pieces[newPosition(change, pos)])) {
                final Move move = WhiteBothCastlingsDisablingMove.getInstance();
                if (!validate || move.isValid(pieces, pos, newPosition(change, pos), getColor())) {
                    moves.put(newPosition(change, pos), move);
                }
            }
            return moves;
        }
    }

    @Override
    public Map<Integer, Move> generateMoves(final Piece[] pieces,
            int pos, boolean validate) {
        final Map<Integer, Move> moves = new HashMap<Integer, Move>(7);
        for (int change : CHANGES) {
            moves.putAll(generateNormalMove(pieces, pos, change, validate));
            moves.putAll(generateNormalMove(pieces, pos, -change, validate));
        }
        moves.putAll(generateCastlings(pieces, pos, validate));
        return moves;
    }

    protected  abstract Map<Integer, Move> generateNormalMove(
            Piece[] pieces, int pos, int i, boolean validate);

    protected abstract Map<Integer, Move> generateCastlings(Piece[] pieces, int pos, boolean validate);

    public boolean isPossibleMove(final Piece piece) {
        return piece != DefaultPiece.INVALID
                && (piece == DefaultPiece.NULL || piece.getColor() != getColor());
    }

    @Override
    public int getPosValue(final int pos) {
        return 0;
    }
}
