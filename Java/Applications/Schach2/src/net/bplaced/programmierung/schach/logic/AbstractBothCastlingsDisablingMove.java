package net.bplaced.programmierung.schach.logic;

import net.bplaced.programmierung.schach.logic.AbstractKingBothCastlings.BlackKingBothCastlings;
import net.bplaced.programmierung.schach.logic.AbstractKingBothCastlings.WhiteKingBothCastlings;
import net.bplaced.programmierung.schach.logic.AbstractKingNormal.BlackKingNormal;
import net.bplaced.programmierung.schach.logic.AbstractKingNormal.WhiteKingNormal;
import net.bplaced.programmierung.schach.logic.AbstractRookKingside.BlackRookKingside;
import net.bplaced.programmierung.schach.logic.AbstractRookKingside.WhiteRookKingside;
import net.bplaced.programmierung.schach.logic.AbstractRookNormal.BlackRookNormal;
import net.bplaced.programmierung.schach.logic.AbstractRookNormal.WhiteRookNormal;
import net.bplaced.programmierung.schach.logic.AbstractRookQueenside.BlackRookQueenside;
import net.bplaced.programmierung.schach.logic.AbstractRookQueenside.WhiteRookQueenside;

public abstract class AbstractBothCastlingsDisablingMove extends AbstractMove  {
    
    public static final class BlackBothCastlingsDisablingMove extends AbstractBothCastlingsDisablingMove{

        private static final BlackBothCastlingsDisablingMove INSTANCE;

        static {
            INSTANCE = new BlackBothCastlingsDisablingMove();
        }

        public static final Move getInstance() {
            return INSTANCE;
        }

        private BlackBothCastlingsDisablingMove() {
        }
        
        @Override
        public void execute(final Piece[] pieces, final int oldPos, final int newPos) {
            pieces[newPos] = BlackKingNormal.getInstance();
            pieces[oldPos] = DefaultPiece.NULL;
            pieces[21] = BlackRookNormal.getInstance();
            pieces[28] = BlackRookNormal.getInstance();
            // enPassant
        }

        @Override
        public void undo(final Piece[] pieces, final int oldPos, final int newPos,
                final UndoData data, final Piece oldPiece) {
            // enPassant
            pieces[28] = BlackRookKingside.getInstance();
            pieces[21] = BlackRookQueenside.getInstance();
            pieces[oldPos] = BlackKingBothCastlings.getInstance();
            pieces[newPos] = oldPiece;
        }
    }
    
    public static final class WhiteBothCastlingsDisablingMove extends AbstractBothCastlingsDisablingMove {

        private static final WhiteBothCastlingsDisablingMove INSTANCE;

        static {
            INSTANCE = new WhiteBothCastlingsDisablingMove();
        }

        public static final Move getInstance() {
            return INSTANCE;
        }

        private WhiteBothCastlingsDisablingMove() {
        }
        
        @Override
        public void execute(final Piece[] pieces, final int oldPos, final int newPos) {
            pieces[newPos] = WhiteKingNormal.getInstance();
            pieces[oldPos] = DefaultPiece.NULL;
            pieces[91] = WhiteRookNormal.getInstance();
            pieces[98] = WhiteRookNormal.getInstance();
            // enPassant
        }

        @Override
        public void undo(final Piece[] pieces, final int oldPos, final int newPos,
                final UndoData data, final Piece oldPiece) {
            // enPassant
            pieces[98] = WhiteRookKingside.getInstance();
            pieces[91] = WhiteRookQueenside.getInstance();
            pieces[oldPos] = WhiteKingBothCastlings.getInstance();
            pieces[newPos] = oldPiece;
        }
    }

    @Override
    public int getMoveValue() {
        // TODO Auto-generated method stub
        return 0;
    }

}
