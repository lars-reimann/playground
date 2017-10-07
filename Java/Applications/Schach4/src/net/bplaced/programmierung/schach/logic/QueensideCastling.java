package net.bplaced.programmierung.schach.logic;


public final class QueensideCastling extends Move {

    public QueensideCastling(final int oldPos, final int newPos) {
	super(oldPos, newPos);
    }

    @Override
    public Piece[] simulateMove(final Piece[] pieces) {
	final Piece[] piecesCopy = super.simulateMove(pieces);

	piecesCopy[newPos + 1] = new Rook(piecesCopy[newPos].getColor(), newPos + 1);
	piecesCopy[newPos - 2] = DefaultPiece.NULL;

	return piecesCopy;
    }

    @Override
    public boolean isValid(final Piece[] pieces, final ChessColor color) {
	return !isCheck(pieces, color) &&
		new Move(oldPos, oldPos - 1).isValid(pieces,color) &&
		new Move(oldPos, oldPos - 2).isValid(pieces,color) &&
		super.isValid(pieces, color);
    }
}