package net.bplaced.programmierung.schach.logic;

import java.util.HashMap;
import java.util.Map;

import net.bplaced.programmierung.schach.logic.AbstractBothCastlingsDisablingMove.BlackBothCastlingsDisablingMove;
import net.bplaced.programmierung.schach.logic.AbstractBothCastlingsDisablingMove.WhiteBothCastlingsDisablingMove;
import net.bplaced.programmierung.schach.logic.AbstractKingsideCastlingMove.BlackKingsideCastlingMove;
import net.bplaced.programmierung.schach.logic.AbstractKingsideCastlingMove.WhiteKingsideCastlingMove;

public abstract class AbstractKingKingsideCastling extends AbstractKing {

    public static final class WhiteKingKingsideCastling extends
            AbstractKingKingsideCastling {

        private static final WhiteKingKingsideCastling INSTANCE;

        static {
            INSTANCE = new WhiteKingKingsideCastling();
        }

        public static final Piece getInstance() {
            return INSTANCE;
        }

        private WhiteKingKingsideCastling() {

        }

        @Override
        public ChessColor getColor() {
            return ChessColor.WHITE;
        }
        
        protected Map<Integer, Move> generateCastlings(Piece[] pieces,
                final int pos, final boolean validate) {
            final Map<Integer, Move> moves = new HashMap<Integer, Move>(
                    1);
            // TODO Separate Methode
            if (pieces[pos + 1] == DefaultPiece.NULL
                    && pieces[pos + 2] == DefaultPiece.NULL) {
                final Move move = WhiteKingsideCastlingMove.getInstance();
                addMove(pieces, moves, move, validate, pos, pos + 2);
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
                final Move move = WhiteBothCastlingsDisablingMove.getInstance();
                addMove(pieces, moves, move, validate, pos, newPosition(change, pos));
            }
            return moves;
        }
    }

    public static class BlackKingKingsideCastling extends
            AbstractKingKingsideCastling {

        private static final BlackKingKingsideCastling INSTANCE;

        static {
            INSTANCE = new BlackKingKingsideCastling();
        }

        public static final Piece getInstance() {
            return INSTANCE;
        }

        private BlackKingKingsideCastling() {

        }

        @Override
        public ChessColor getColor() {
            return ChessColor.BLACK;
        }
        
        protected Map<Integer, Move> generateCastlings(Piece[] pieces,
                final int pos, final boolean validate) {
            final Map<Integer, Move> moves = new HashMap<Integer, Move>(
                    1);
            // TODO Separate Methode
            if (pieces[pos + 1] == DefaultPiece.NULL
                    && pieces[pos + 2] == DefaultPiece.NULL) {
                final Move move = BlackKingsideCastlingMove.getInstance();
                addMove(pieces, moves, move, validate, pos, pos + 2);
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
                final Move move = BlackBothCastlingsDisablingMove.getInstance();
                addMove(pieces, moves, move, validate, pos, newPosition(change, pos));
            }
            return moves;
        }
    }

    @Override
    public Map<Integer, Move> generateMoves(Piece[] pieces, int pos,
            boolean validate) {
        final Map<Integer, Move> moves = new HashMap<Integer, Move>(
                9);
        for (int change : CHANGES) {
            moves.putAll(generateNormalMove(pieces, pos, change, validate));
            moves.putAll(generateNormalMove(pieces, pos, -change, validate));
        }
        moves.putAll(generateCastlings(pieces, pos, validate));
        return moves;
    }

    protected abstract Map<Integer, Move> generateNormalMove(
            Piece[] pieces, int pos, int change, boolean validate);

    protected abstract Map<Integer, Move> generateCastlings(
            Piece[] pieces, int pos, boolean validate);



    @Override
    public int getPosValue(final int pos) {
        return 0;
    }
}
