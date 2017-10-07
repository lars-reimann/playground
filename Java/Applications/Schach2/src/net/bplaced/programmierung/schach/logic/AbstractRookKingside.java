package net.bplaced.programmierung.schach.logic;

import java.util.HashMap;
import java.util.Map;

import net.bplaced.programmierung.schach.logic.AbstractKingsideCastlingDisablingMove.BlackKingsideCastlingDisablingMove;
import net.bplaced.programmierung.schach.logic.AbstractKingsideCastlingDisablingMove.WhiteKingsideCastlingDisablingMove;

public abstract class AbstractRookKingside extends AbstractRook {
    
    public static class WhiteRookKingside extends AbstractRookKingside {

        private static final WhiteRookKingside INSTANCE;

        static {
            INSTANCE = new WhiteRookKingside();
        }

        public static final Piece getInstance() {
            return INSTANCE;
        }

        private WhiteRookKingside() {

        }

        @Override
        public ChessColor getColor() {
            return ChessColor.WHITE;
        }

        @Override
        protected Map<Integer, Move> generateMoves(final Piece[] pieces,
                final int pos, final int change, final boolean validate) {
            final Map<Integer, Move> moves = new HashMap<Integer, Move>(
                    14);
            for (int i = 1; pieces[newPosition(i, pos, change)] != DefaultPiece.INVALID
                    && (i == 1 || pieces[newPosition(i - 1, pos, change)] == DefaultPiece.NULL); i++) {
                final Piece piece = pieces[newPosition(i, pos, change)];
                if (piece == DefaultPiece.NULL || piece.getColor() != getColor()) {
                    final Move move = WhiteKingsideCastlingDisablingMove.getInstance();
                    addMove(pieces, moves, move, validate, pos, newPosition(i, pos, change));
                }
            }
            return moves;
        }

        
    }
    
    public static class BlackRookKingside extends AbstractRookKingside {

        private static final BlackRookKingside INSTANCE;

        static {
            INSTANCE = new BlackRookKingside();
        }

        public static final Piece getInstance() {
            return INSTANCE;
        }

        private BlackRookKingside() {

        }

        @Override
        public ChessColor getColor() {
            return ChessColor.BLACK;
        }

        @Override
        protected Map<Integer, Move> generateMoves(final Piece[] pieces,
                final int pos, final int change, final boolean validate) {
            final Map<Integer, Move> moves = new HashMap<Integer, Move>(
                    14);
            for (int i = 1; pieces[newPosition(i, pos, change)] != DefaultPiece.INVALID
                    && (i == 1 || pieces[newPosition(i - 1, pos, change)] == DefaultPiece.NULL); i++) {
                final Piece piece = pieces[newPosition(i, pos, change)];
                if (piece == DefaultPiece.NULL || piece.getColor() != getColor()) {
                    final Move move = BlackKingsideCastlingDisablingMove.getInstance();
                    addMove(pieces, moves, move, validate, pos,
                            newPosition(i, pos, change));
                }
            }
            return moves;
        }
    }

    @Override
    public int getPosValue(final int pos) {
        return 0; // TODO
    }
}
