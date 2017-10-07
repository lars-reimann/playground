package net.bplaced.programmierung.schach.logic;

import net.bplaced.programmierung.schach.logic.AbstractKingNormal.BlackKingNormal;
import net.bplaced.programmierung.schach.logic.AbstractKingNormal.WhiteKingNormal;
import net.bplaced.programmierung.schach.logic.AbstractKingQueensideCastling.BlackKingQueensideCastling;
import net.bplaced.programmierung.schach.logic.AbstractKingQueensideCastling.WhiteKingQueensideCastling;
import net.bplaced.programmierung.schach.logic.AbstractRookKingside.BlackRookKingside;
import net.bplaced.programmierung.schach.logic.AbstractRookKingside.WhiteRookKingside;
import net.bplaced.programmierung.schach.logic.AbstractRookNormal.BlackRookNormal;
import net.bplaced.programmierung.schach.logic.AbstractRookNormal.WhiteRookNormal;

public abstract class AbstractKingsideCastlingDisablingMove extends
        AbstractMove {

    public static final class BlackKingsideCastlingDisablingMove extends
            AbstractKingsideCastlingDisablingMove {
        
        private static final BlackKingsideCastlingDisablingMove INSTANCE;

        static {
            INSTANCE = new BlackKingsideCastlingDisablingMove();
        }

        public static final Move getInstance() {
            return INSTANCE;
        }

        private BlackKingsideCastlingDisablingMove() { }

        @Override
        public void execute(final Piece[] pieces, final int oldPos, final int newPos) { 
            pieces[newPos] = BlackRookNormal.getInstance();
            pieces[oldPos] = DefaultPiece.NULL;
            if (pieces[25] instanceof AbstractKingBothCastlings) {
                pieces[25] = BlackKingQueensideCastling.getInstance();
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
            pieces[oldPos] = BlackRookKingside.getInstance();
            pieces[newPos] = oldPiece;
        }
    }

    public static final class WhiteKingsideCastlingDisablingMove extends
            AbstractKingsideCastlingDisablingMove {

        private static final WhiteKingsideCastlingDisablingMove INSTANCE;

        static {
            INSTANCE = new WhiteKingsideCastlingDisablingMove();
        }

        public static final Move getInstance() {
            return INSTANCE;
        }

        private WhiteKingsideCastlingDisablingMove() {
        }

        @Override
        public void execute(final Piece[] pieces, final int oldPos, final int newPos) {
            pieces[newPos] = WhiteRookNormal.getInstance();
            pieces[oldPos] = DefaultPiece.NULL;
            if (pieces[95] instanceof AbstractKingBothCastlings) {
                pieces[95] = WhiteKingQueensideCastling.getInstance();
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
            pieces[oldPos] = WhiteRookKingside.getInstance();
            pieces[newPos] = oldPiece;
        }
    }

    @Override
    public int getMoveValue() {
        // TODO Auto-generated method stub
        return 0;
    }
}
