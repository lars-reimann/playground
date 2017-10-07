package net.bplaced.programmierung.schach.logic;

public final class EnPassant extends Move {

    public EnPassant(final int oldPos, final int newPos) {
	super(oldPos, newPos);
    }

    @Override
    public Piece[] simulateMove(final Piece[] pieces) {
	final Piece[] piecesCopy = super.simulateMove(pieces);
	piecesCopy[newPos - piecesCopy[newPos].getColor().getDirection() * 10] = DefaultPiece.NULL;
	return piecesCopy;
    }
}
