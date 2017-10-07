package net.bplaced.programmierung.schach.logic;

public final class PawnDoubleMove extends Move {

    public PawnDoubleMove(final int oldPos, final int newPos) {
	super(oldPos, newPos);
    }

    @Override
    public Piece[] simulateMove(final Piece[] pieces) {
	final Piece[] piecesCopy = disableEnPassant(copyPieces(pieces));
	piecesCopy[newPos] = new PawnEnPassantBeatable(piecesCopy[oldPos].getColor(), newPos);
	piecesCopy[oldPos] = DefaultPiece.NULL;
	return piecesCopy;
    }
}
