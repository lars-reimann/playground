package net.bplaced.programmierung.schach.logic;

public final class KingsideRook extends Rook {
    
    public KingsideRook(final ChessColor color, final int pos) {
	super(color, pos);
    }
    
    @Override
    public Move newMove(final int oldPos, final int newPos) {
	return new KingsideCastlingDisablingMove(oldPos, newPos);
    }
}
