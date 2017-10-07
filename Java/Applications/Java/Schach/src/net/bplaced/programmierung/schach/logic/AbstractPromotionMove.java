package src.net.bplaced.programmierung.schach.logic;

import net.bplaced.programmierung.schach.logic.AbstractPawnNormal.BlackPawnNormal;
import net.bplaced.programmierung.schach.logic.AbstractPawnNormal.WhitePawnNormal;
import net.bplaced.programmierung.schach.logic.AbstractQueen.BlackQueen;
import net.bplaced.programmierung.schach.logic.AbstractQueen.WhiteQueen;

public abstract class AbstractPromotionMove extends AbstractMove {

    public static final class BlackPromotionMove extends AbstractPromotionMove {

        private static final BlackPromotionMove INSTANCE;

        static {
            INSTANCE = new BlackPromotionMove();
        }

        public static final Move getInstance() {
            return INSTANCE;
        }

        private BlackPromotionMove() { }
        
        @Override
        public void execute(final Piece[] pieces, final int oldPos, final int newPos) {
            pieces[newPos] = BlackQueen.getInstance();
            pieces[oldPos] = DefaultPiece.NULL;
            // en passant
        }

        @Override
        public void undo(final Piece[] pieces, final int oldPos, final int newPos,
                final UndoData data, final Piece oldPiece) {
            // en passant
            pieces[oldPos] = BlackPawnNormal.getInstance();
            pieces[newPos] = oldPiece;
        }
    }
    
    public static final class WhitePromotionMove extends AbstractPromotionMove {

        private static final WhitePromotionMove INSTANCE;

        static {
            INSTANCE = new WhitePromotionMove();
        }

        public static final Move getInstance() {
            return INSTANCE;
        }

        private WhitePromotionMove() { }
        
        @Override
        public void execute(final Piece[] pieces, final int oldPos, final int newPos) {
            pieces[newPos] = WhiteQueen.getInstance();
            pieces[oldPos] = DefaultPiece.NULL;
            // en passant
        }

        @Override
        public void undo(final Piece[] pieces, final int oldPos, final int newPos,
                final UndoData data, final Piece oldPiece) {
            // en passant
            pieces[oldPos] = WhitePawnNormal.getInstance();
            pieces[newPos] = oldPiece;
        }
    }
    
    @Override
    public int getMoveValue() {
        // TODO Auto-generated method stub
        return 0;
    }
}
