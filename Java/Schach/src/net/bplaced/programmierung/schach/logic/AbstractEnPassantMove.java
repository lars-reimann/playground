package src.net.bplaced.programmierung.schach.logic;

import net.bplaced.programmierung.schach.logic.AbstractPawnEnPassantBeatable.BlackPawnEnPassantBeatable;
import net.bplaced.programmierung.schach.logic.AbstractPawnEnPassantBeatable.WhitePawnEnPassantBeatable;
import net.bplaced.programmierung.schach.logic.AbstractPawnNormal.BlackPawnNormal;
import net.bplaced.programmierung.schach.logic.AbstractPawnNormal.WhitePawnNormal;

public abstract class AbstractEnPassantMove extends AbstractMove {
    
    public static final class BlackEnPassantMove extends AbstractEnPassantMove {
        
        private static final BlackEnPassantMove INSTANCE;

        static {
            INSTANCE = new BlackEnPassantMove();
        }

        public static final Move getInstance() {
            return INSTANCE;
        }

        private BlackEnPassantMove() { }

        @Override
        public void execute(final Piece[] pieces, final int oldPos, final int newPos) {
            pieces[newPos] = BlackPawnNormal.getInstance();
            pieces[oldPos] = DefaultPiece.NULL;
            pieces[newPos - 10] = DefaultPiece.NULL;
        }

        @Override
        public void undo(final Piece[] pieces, final int oldPos, final int newPos,
                final UndoData data, final Piece oldPiece) {
            pieces[newPos - 10] = WhitePawnEnPassantBeatable.getInstance();
            pieces[oldPos] = BlackPawnNormal.getInstance();
            pieces[newPos] = DefaultPiece.NULL;
        }
    }
    
    public static final class WhiteEnPassantMove extends AbstractEnPassantMove {
        
        private static final WhiteEnPassantMove INSTANCE;

        static {
            INSTANCE = new WhiteEnPassantMove();
        }

        public static final Move getInstance() {
            return INSTANCE;
        }

        private WhiteEnPassantMove() { }

        @Override
        public void execute(final Piece[] pieces, final int oldPos, final int newPos) {
            pieces[newPos] = WhitePawnNormal.getInstance();
            pieces[oldPos] = DefaultPiece.NULL;
            pieces[newPos - 10] = DefaultPiece.NULL;
        }

        @Override
        public void undo(final Piece[] pieces, final int oldPos, final int newPos,
                final UndoData data, final Piece oldPiece) {
            pieces[newPos - 10] = BlackPawnEnPassantBeatable.getInstance();
            pieces[oldPos] = WhitePawnNormal.getInstance();
            pieces[newPos] = DefaultPiece.NULL;
        }
    }

    @Override
    public int getMoveValue() {
        // TODO Auto-generated method stub
        return 0;
    }

}
