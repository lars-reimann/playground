package net.bplaced.programmierung.schach.logic;


import net.bplaced.programmierung.schach.logic.AbstractKingKingsideCastling.BlackKingKingsideCastling;
import net.bplaced.programmierung.schach.logic.AbstractKingKingsideCastling.WhiteKingKingsideCastling;
import net.bplaced.programmierung.schach.logic.AbstractKingNormal.BlackKingNormal;
import net.bplaced.programmierung.schach.logic.AbstractKingNormal.WhiteKingNormal;
import net.bplaced.programmierung.schach.logic.AbstractRookNormal.BlackRookNormal;
import net.bplaced.programmierung.schach.logic.AbstractRookNormal.WhiteRookNormal;
import net.bplaced.programmierung.schach.logic.AbstractRookQueenside.BlackRookQueenside;
import net.bplaced.programmierung.schach.logic.AbstractRookQueenside.WhiteRookQueenside;

public abstract class AbstractQueensideCastlingDisablingMove extends
        AbstractMove {

    public static final class BlackQueensideCastlingDisablingMove extends
    AbstractQueensideCastlingDisablingMove {

        private static final BlackQueensideCastlingDisablingMove INSTANCE;

        static {
            INSTANCE = new BlackQueensideCastlingDisablingMove();
        }

        public static final Move getInstance() {
            return INSTANCE;
        }

        private BlackQueensideCastlingDisablingMove() { }
        
        @Override
        public void execute(final Piece[] pieces, final int oldPos, final int newPos) {
            pieces[newPos] = BlackRookNormal.getInstance();
            pieces[oldPos] = DefaultPiece.NULL;
            if (pieces[25] instanceof AbstractKingBothCastlings) {
                pieces[25] = BlackKingKingsideCastling.getInstance();
            } else {
                 pieces[25] = BlackKingNormal.getInstance();
            }
            // en passant
        }

        @Override
        public void undo(final Piece[] pieces, final int oldPos, final int newPos,
                final UndoData data, final Piece oldPiece) {
            // en passant
            pieces[25] = data.getKing();
            pieces[oldPos] = BlackRookQueenside.getInstance();
            pieces[newPos] = oldPiece;
        }
    }

    public static final class WhiteQueensideCastlingDisablingMove extends
    AbstractQueensideCastlingDisablingMove {

        private static final WhiteQueensideCastlingDisablingMove INSTANCE;

        static {
            INSTANCE = new WhiteQueensideCastlingDisablingMove();
        }

        public static final Move getInstance() {
            return INSTANCE;
        }

        private WhiteQueensideCastlingDisablingMove() { }
        
        @Override
        public void execute(final Piece[] pieces, final int oldPos, final int newPos) {
            pieces[newPos] = WhiteRookNormal.getInstance();
            pieces[oldPos] = DefaultPiece.NULL;
            if (pieces[95] instanceof AbstractKingBothCastlings) {
                pieces[95] = WhiteKingKingsideCastling.getInstance();
            } else {
                 pieces[95] = WhiteKingNormal.getInstance();
            }
            // en passant
        }

        @Override
        public void undo(final Piece[] pieces, final int oldPos, final int newPos,
                final UndoData data, final Piece oldPiece) {
            // en passant
            pieces[95] = data.getKing();
            pieces[oldPos] = WhiteRookQueenside.getInstance();
            pieces[newPos] = oldPiece;
        }
    }

    @Override
    public int getMoveValue() {
        // TODO Auto-generated method stub
        return 0;
    }

}
