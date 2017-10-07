package src.net.bplaced.programmierung.schach.logic;

import net.bplaced.programmierung.schach.logic.AbstractKingNormal.BlackKingNormal;
import net.bplaced.programmierung.schach.logic.AbstractKingNormal.WhiteKingNormal;
import net.bplaced.programmierung.schach.logic.AbstractRookKingside.BlackRookKingside;
import net.bplaced.programmierung.schach.logic.AbstractRookKingside.WhiteRookKingside;
import net.bplaced.programmierung.schach.logic.AbstractRookNormal.BlackRookNormal;
import net.bplaced.programmierung.schach.logic.AbstractRookNormal.WhiteRookNormal;

public abstract class AbstractQueensideCastlingMove extends AbstractMove {
    
    public static final class BlackQueensideCastlingMove extends AbstractQueensideCastlingMove {

        private static final BlackQueensideCastlingMove INSTANCE;

        static {
            INSTANCE = new BlackQueensideCastlingMove();
        }

        public static final Move getInstance() {
            return INSTANCE;
        }

        private BlackQueensideCastlingMove() {
        }
        
        @Override
        public void execute(final Piece[] pieces, final int oldPos, final int newPos) {
            pieces[newPos] = BlackKingNormal.getInstance();
            pieces[oldPos] = DefaultPiece.NULL;
            pieces[newPos + 1] = BlackRookNormal.getInstance();
            pieces[newPos - 2] = DefaultPiece.NULL;
            // en passant
        }

        public void undo(final Piece[] pieces, final int oldPos, final int newPos,
                final UndoData data, final Piece oldPiece) {
            // en passant
            pieces[newPos - 2] = BlackRookKingside.getInstance();
            pieces[newPos + 1] = DefaultPiece.NULL;
            pieces[oldPos] = data.getKing();
            pieces[newPos] = DefaultPiece.NULL;
        }
    }
    
    public static final class WhiteQueensideCastlingMove extends AbstractQueensideCastlingMove {

        private static final WhiteQueensideCastlingMove INSTANCE;

        static {
            INSTANCE = new WhiteQueensideCastlingMove();
        }

        public static final Move getInstance() {
            return INSTANCE;
        }

        private WhiteQueensideCastlingMove() {
        }
        
        @Override
        public void execute(final Piece[] pieces, final int oldPos, final int newPos) {
            pieces[newPos] = WhiteKingNormal.getInstance();
            pieces[oldPos] = DefaultPiece.NULL;
            pieces[newPos + 1] = WhiteRookNormal.getInstance();
            pieces[newPos - 2] = DefaultPiece.NULL;
            // en passant
        }

        public void undo(final Piece[] pieces, final int oldPos, final int newPos,
                final UndoData data, final Piece oldPiece) {
            // en passant
            pieces[newPos - 2] = WhiteRookKingside.getInstance();
            pieces[newPos + 1] = DefaultPiece.NULL;
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
                && NormalMove.getInstance().isValid(pieces, oldPos, oldPos - 1, color) 
                && super.isValid(pieces, oldPos, newPos, color);
    }
}
