package net.bplaced.programmierung.schach.logic;

public final class QueensideRook extends Rook {
    
    public QueensideRook(final ChessColor color, final int pos) {
	super(color, pos);
    }

    @Override
    public Move newMove(final int oldPos, final int newPos) {
	return new QueensideCastlingDisablingMove(oldPos, newPos);
    }
}
