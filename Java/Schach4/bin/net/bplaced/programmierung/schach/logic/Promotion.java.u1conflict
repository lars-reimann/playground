package net.bplaced.programmierung.schach.logic;

public final class Promotion extends Move {

    public Promotion(final int oldPos, final int newPos) {
	super(oldPos, newPos);
    }

    @Override
    public Piece[] simulateMove(final Piece[] pieces) {
	final Piece[] piecesCopy = super.simulateMove(pieces);
	piecesCopy[newPos] = new Queen(piecesCopy[newPos].getColor(), newPos);
	return piecesCopy;
    }
}
