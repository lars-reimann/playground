package net.bplaced.programmierung.schach.logic;

import net.bplaced.programmierung.schach.logic.AbstractPawnDouble.BlackPawnDouble;
import net.bplaced.programmierung.schach.logic.AbstractPawnDouble.WhitePawnDouble;
import net.bplaced.programmierung.schach.logic.AbstractPawnNormal.BlackPawnNormal;
import net.bplaced.programmierung.schach.logic.AbstractPawnNormal.WhitePawnNormal;

public abstract class AbstractDoubleMoveDisablingMove extends AbstractMove {

    public static final class BlackDoubleMoveDisablingMove extends
            AbstractDoubleMoveDisablingMove {
        
        private static final BlackDoubleMoveDisablingMove INSTANCE;

        static {
            INSTANCE = new BlackDoubleMoveDisablingMove();
        }

        public static final Move getInstance() {
            return INSTANCE;
        }

        private BlackDoubleMoveDisablingMove() {
        }

        @Override
        public void execute(final Piece[] pieces, final int oldPos, final int newPos) {
            pieces[newPos] = BlackPawnNormal.getInstance();
            pieces[oldPos] = DefaultPiece.NULL;
            // en passant
        }

        @Override
        public void undo(final Piece[] pieces, final int oldPos, final int newPos,
                final UndoData data, final Piece oldPiece) {
            // en passant
            pieces[oldPos] = BlackPawnDouble.getInstance();
            pieces[newPos] = oldPiece;
        }
    }

    public static final class WhiteDoubleMoveDisablingMove extends
            AbstractDoubleMoveDisablingMove {
        
        private static final WhiteDoubleMoveDisablingMove INSTANCE;

        static {
            INSTANCE = new WhiteDoubleMoveDisablingMove();
        }

        public static final Move getInstance() {
            return INSTANCE;
        }

        private WhiteDoubleMoveDisablingMove() {
        }

        @Override
        public void execute(final Piece[] pieces, final int oldPos, final int newPos) {
            pieces[newPos] = WhitePawnNormal.getInstance();
            pieces[oldPos] = DefaultPiece.NULL;
            // en passant
        }

        @Override
        public void undo(final Piece[] pieces, final int oldPos, final int newPos,
                final UndoData data, final Piece oldPiece) {
            // en passant
            pieces[oldPos] = WhitePawnDouble.getInstance();
            pieces[newPos] = oldPiece;
        }
    }

    @Override
    public int getMoveValue() {
        // TODO Auto-generated method stub
        return 0;
    }

}
