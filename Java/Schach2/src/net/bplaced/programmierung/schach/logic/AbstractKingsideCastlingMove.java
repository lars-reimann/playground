package net.bplaced.programmierung.schach.logic;

import net.bplaced.programmierung.schach.logic.AbstractKingNormal.BlackKingNormal;
import net.bplaced.programmierung.schach.logic.AbstractKingNormal.WhiteKingNormal;
import net.bplaced.programmierung.schach.logic.AbstractRookKingside.BlackRookKingside;
import net.bplaced.programmierung.schach.logic.AbstractRookKingside.WhiteRookKingside;
import net.bplaced.programmierung.schach.logic.AbstractRookNormal.BlackRookNormal;
import net.bplaced.programmierung.schach.logic.AbstractRookNormal.WhiteRookNormal;

public abstract class AbstractKingsideCastlingMove extends AbstractMove {
    
    public static final class BlackKingsideCastlingMove extends AbstractKingsideCastlingMove {

        private static final BlackKingsideCastlingMove INSTANCE;

        static {
            INSTANCE = new BlackKingsideCastlingMove();
        }

        public static final Move getInstance() {
            return INSTANCE;
        }

        private BlackKingsideCastlingMove() { }
        
        @Override
        public void execute(final Piece[] pieces, final int oldPos, final int newPos) {
            pieces[newPos] = BlackKingNormal.getInstance();
            pieces[oldPos] = DefaultPiece.NULL;
            pieces[newPos - 1] = BlackRookNormal.getInstance();
            pieces[newPos + 1] = DefaultPiece.NULL;
            // en passant
        }

        public void undo(final Piece[] pieces, final int oldPos, final int newPos,
                final UndoData data, final Piece oldPiece) {
            // en passant
            pieces[newPos + 1] = BlackRookKingside.getInstance();
            pieces[newPos - 1] = DefaultPiece.NULL;
            pieces[oldPos] = data.getKing();
            pieces[newPos] = DefaultPiece.NULL;
        }
    }
    
    public static final class WhiteKingsideCastlingMove extends AbstractKingsideCastlingMove {

        private static final WhiteKingsideCastlingMove INSTANCE;

        static {
            INSTANCE = new WhiteKingsideCastlingMove();
        }

        public static final Move getInstance() {
            return INSTANCE;
        }

        private WhiteKingsideCastlingMove() { }
        
        @Override
        public void execute(final Piece[] pieces, final int oldPos, final int newPos) {
            pieces[newPos] = WhiteKingNormal.getInstance();
            pieces[oldPos] = DefaultPiece.NULL;
            pieces[newPos - 1] = WhiteRookNormal.getInstance();
            pieces[newPos + 1] = DefaultPiece.NULL;
            // en passant
        }

        public void undo(final Piece[] pieces, final int oldPos, final int newPos,
                final UndoData data, final Piece oldPiece) {
            // en passant
            pieces[newPos + 1] = WhiteRookKingside.getInstance();
            pieces[newPos - 1] = DefaultPiece.NULL;
            pieces[oldPos] = data.getKing();
            pieces[newPos] = DefaultPiece.NULL;
        }
    }

    @Override
    public int getMoveValue() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean isValid(final Piece[] pieces, final int oldPos, final int newPos, final ChessColor color) {
        return !Engine.isCheck(pieces, color)
                && NormalMove.getInstance().isValid(pieces, oldPos, oldPos + 1, color) 
                && super.isValid(pieces, oldPos, newPos, color);
    }
}
