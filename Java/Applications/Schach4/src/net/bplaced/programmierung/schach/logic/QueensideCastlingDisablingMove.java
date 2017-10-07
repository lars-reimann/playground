package net.bplaced.programmierung.schach.logic;

public class QueensideCastlingDisablingMove extends Move {

    public QueensideCastlingDisablingMove(final int oldPos, final int newPos) {
	super(oldPos, newPos);
    }

    @Override
    public Piece[] simulateMove(final Piece[] pieces) {
	final Piece[] piecesCopy = super.simulateMove(pieces);
	
	//TODO Schleife verbessern
	for (int i = 0; i < 120; i++) {
	    if (piecesCopy[i] instanceof King && piecesCopy[i].getColor() == piecesCopy[newPos].getColor()) {
		if (piecesCopy[i] instanceof KingBothCastlings) {
		    piecesCopy[i] = new KingKingsideCastling(piecesCopy[i].getColor(), i);
		} else {
		    piecesCopy[i] = piecesCopy[i].copyPiece(i);
		}
	    }
	}

	return piecesCopy;
    }
}
