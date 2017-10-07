package src.net.bplaced.programmierung.schach.logic;

import java.util.HashMap;
import java.util.Map;

import net.bplaced.programmierung.schach.logic.AbstractQueensideCastlingDisablingMove.BlackQueensideCastlingDisablingMove;
import net.bplaced.programmierung.schach.logic.AbstractQueensideCastlingDisablingMove.WhiteQueensideCastlingDisablingMove;

public abstract class AbstractRookQueenside extends AbstractRook {
    
    public static final class WhiteRookQueenside extends AbstractRookQueenside {

        private static final WhiteRookQueenside INSTANCE;


        static {
            INSTANCE = new WhiteRookQueenside();
        }

        public static final Piece getInstance() {
            return INSTANCE;
        }

        private WhiteRookQueenside() {

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
                    final Move move = WhiteQueensideCastlingDisablingMove.getInstance();
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
    
    public static final class BlackRookQueenside extends AbstractRookQueenside {

        private static final BlackRookQueenside INSTANCE;

        

        static {
            INSTANCE = new BlackRookQueenside();
        }

        public static final Piece getInstance() {
            return INSTANCE;
        }

        private BlackRookQueenside() {

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
                    final Move move = BlackQueensideCastlingDisablingMove.getInstance();
                    addMove(pieces, moves, move, validate, pos, newPosition(i, pos, change));
                }
            }
            return moves;
        }

        @Override
        public ChessColor getColor() {
            return ChessColor.BLACK;
        }
    }

}
