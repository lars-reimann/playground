package src.net.bplaced.programmierung.schach.logic;

import java.util.HashMap;
import java.util.Map;

import net.bplaced.programmierung.schach.logic.AbstractBothCastlingsDisablingMove.BlackBothCastlingsDisablingMove;
import net.bplaced.programmierung.schach.logic.AbstractBothCastlingsDisablingMove.WhiteBothCastlingsDisablingMove;
import net.bplaced.programmierung.schach.logic.AbstractQueensideCastlingMove.BlackQueensideCastlingMove;
import net.bplaced.programmierung.schach.logic.AbstractQueensideCastlingMove.WhiteQueensideCastlingMove;

public abstract class AbstractKingQueensideCastling extends AbstractKing {

    public static final class BlackKingQueensideCastling extends
            AbstractKingQueensideCastling {

        private static final BlackKingQueensideCastling INSTANCE;

        static {
            INSTANCE = new BlackKingQueensideCastling();
        }

        public static final Piece getInstance() {
            return INSTANCE;
        }

        private BlackKingQueensideCastling() {

        }

        @Override
        public ChessColor getColor() {
            return ChessColor.BLACK;
        }
        
        protected Map<Integer, Move> generateNormalMove(final Piece[] pieces,
                final int pos, final int change, final boolean validate) {
            final Map<Integer, Move> moves = new HashMap<Integer, Move>(
                    1);
            final Piece piece = pieces[newPosition(change, pos)];
            if (piece != DefaultPiece.INVALID
                    && (piece == DefaultPiece.NULL || piece.getColor() != getColor())) {
                final Move move = BlackBothCastlingsDisablingMove.getInstance();
                addMove(pieces, moves, move, validate, pos, newPosition(change, pos));
            }
            return moves;
        }
        
        protected Map<Integer, Move> generateCastlings(Piece[] pieces,
                final int pos, final boolean validate) {
            final Map<Integer, Move> moves = new HashMap<Integer, Move>();
            // TODO Separate Methode
            if (pieces[pos - 1] == DefaultPiece.NULL
                    && pieces[pos - 2] == DefaultPiece.NULL) {
                final Move move = BlackQueensideCastlingMove.getInstance();
                addMove(pieces, moves, move, validate, pos, pos - 2);
            }
            return moves;
        }
    }

    public static final class WhiteKingQueensideCastling extends
            AbstractKingQueensideCastling {

        private static final WhiteKingQueensideCastling INSTANCE;

        static {
            INSTANCE = new WhiteKingQueensideCastling();
        }

        public static final Piece getInstance() {
            return INSTANCE;
        }

        private WhiteKingQueensideCastling() {

        }

        @Override
        public ChessColor getColor() {
            return ChessColor.WHITE;
        }
        
        protected Map<Integer, Move> generateNormalMove(final Piece[] pieces,
                final int pos, final int change, final boolean validate) {
            final Map<Integer, Move> moves = new HashMap<Integer, Move>(
                    1);
            final Piece piece = pieces[newPosition(change, pos)];
            if (piece != DefaultPiece.INVALID
                    && (piece == DefaultPiece.NULL || piece.getColor() != getColor())) {
                final Move move = WhiteBothCastlingsDisablingMove.getInstance();
                addMove(pieces, moves, move, validate, pos, newPosition(change, pos));
            }
            return moves;
        }
        
        protected Map<Integer, Move> generateCastlings(Piece[] pieces,
                final int pos, final boolean validate) {
            final Map<Integer, Move> moves = new HashMap<Integer, Move>();
            // TODO Separate Methode
            if (pieces[pos - 1] == DefaultPiece.NULL
                    && pieces[pos - 2] == DefaultPiece.NULL) {
                final Move move = WhiteQueensideCastlingMove.getInstance();
                addMove(pieces, moves, move, validate, pos, pos - 2);
            }
            return moves;
        }
    }



    @Override
    public Map<Integer, Move> generateMoves(Piece[] pieces, int pos,
            boolean validate) {
        final Map<Integer, Move> moves = new HashMap<Integer, Move>(
                8);
        for (int change : CHANGES) {
            moves.putAll(generateNormalMove(pieces, pos, change, validate));
            moves.putAll(generateNormalMove(pieces, pos, -change, validate));
        }
        moves.putAll(generateCastlings(pieces, pos, validate));
        return moves;
    }



    protected abstract Map<Integer, Move> generateCastlings(
            Piece[] pieces, int pos, boolean validate);



    protected abstract Map<Integer, Move> generateNormalMove(
            Piece[] pieces, int pos, int change, boolean validate);



    @Override
    public int getPosValue(final int pos) {
        return 0;
    }
}
