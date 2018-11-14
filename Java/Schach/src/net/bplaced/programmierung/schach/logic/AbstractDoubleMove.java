package src.net.bplaced.programmierung.schach.logic;

import net.bplaced.programmierung.schach.logic.AbstractPawnDouble.BlackPawnDouble;
import net.bplaced.programmierung.schach.logic.AbstractPawnDouble.WhitePawnDouble;
import net.bplaced.programmierung.schach.logic.AbstractPawnEnPassantBeatable.BlackPawnEnPassantBeatable;
import net.bplaced.programmierung.schach.logic.AbstractPawnEnPassantBeatable.WhitePawnEnPassantBeatable;

public abstract class AbstractDoubleMove extends AbstractMove {

    public static final class BlackDoubleMove extends AbstractDoubleMove {
        
        private static final BlackDoubleMove INSTANCE;

        static {
            INSTANCE = new BlackDoubleMove();
        }

        public static final Move getInstance() {
            return INSTANCE;
        }

        private BlackDoubleMove() {
        }

        @Override
        public void execute(final Piece[] pieces, final int oldPos,
                final int newPos) {
            pieces[newPos] = BlackPawnEnPassantBeatable.getInstance();
            pieces[oldPos] = DefaultPiece.NULL;
            // en Passant
        }

        @Override
        public void undo(final Piece[] pieces, final int oldPos,
                final int newPos, final UndoData data, final Piece oldPiece) {
            // enPassant
            pieces[oldPos] = BlackPawnDouble.getInstance();
            pieces[newPos] = DefaultPiece.NULL;
        }
    }

    public static final class WhiteDoubleMove extends AbstractDoubleMove {
        
        private static final WhiteDoubleMove INSTANCE;

        static {
            INSTANCE = new WhiteDoubleMove();
        }

        public static final Move getInstance() {
            return INSTANCE;
        }

        private WhiteDoubleMove() {
        }

        @Override
        public void execute(final Piece[] pieces, final int oldPos, final int newPos) {
            pieces[newPos] = WhitePawnEnPassantBeatable.getInstance();
            pieces[oldPos] = DefaultPiece.NULL;
            // en Passant
        }

        @Override
        public void undo(final Piece[] pieces, final int oldPos, final int newPos,
                final UndoData data, final Piece oldPiece) {
            // enPassant
            pieces[oldPos] = WhitePawnDouble.getInstance();
            pieces[newPos] = DefaultPiece.NULL;
        }
    }
    
    @Override
    public int getMoveValue() {
        // TODO Auto-generated method stub
        return 0;
    }
}
