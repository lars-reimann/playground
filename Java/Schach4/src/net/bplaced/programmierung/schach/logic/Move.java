package net.bplaced.programmierung.schach.logic;

import java.util.Arrays;

/**
 * <p>
 * Hilfsklasse die m&ouml;gliche Z&uuml;ge der Figur repr&auml;sentiert.
 * </p>
 * <p>
 * Der Zug besteht dabei aus dem Zielfeld, auf welchem sich die Figur nach dem
 * Zug befinden soll. Dieser Zug kann entsprechend simuliert werden, um zu
 * testen, ob der Zug legal ist, oder um die resultierende Spielsituation besser
 * analysieren zu k&ouml;nnen.
 * </p>
 * 
 * @version 1. November 2011
 * @author Lars Reimann
 */
public class Move {
    
    /**
     * Die Position bevor der Zug ausgef&uuml;hrt worden ist.
     */
    protected final int oldPos;
    
    /**
     * Die Position nachdem der Zug ausgef&uuml;hrt worden ist.
     */
    protected final int newPos;

    public Move(final int oldPos, final int newPos) {
	this.oldPos = oldPos;
	this.newPos = newPos;
    }

    /**
     * Kopiert den gegebenen Spielaufbau und gibt die Kopie zur&uuml;ck.
     * 
     * @param pieces
     *            Aktueller Spielaufbau
     * @return Kopie der Spielsituation
     */
    protected Piece[] copyPieces(final Piece[] pieces) {
	final Piece[] piecesCopy = Arrays.copyOf(pieces, 120);
	return piecesCopy;
    }

    public int getNewPos() {
	return newPos;
    }

    /**
     * Simuliert den Zug dieser Figur, bei der gegebenen Spielsituation und gibt
     * den neuen Spielaufbau zur&uuml;ck.
     * 
     * @param pieces
     *            Aktueller Spielplan
     * @return Spielaufbau nach Zug
     */
    protected Piece[] simulateMove(final Piece[] pieces) {
	final Piece[] piecesCopy = disableEnPassant(copyPieces(pieces));
	piecesCopy[newPos] = piecesCopy[oldPos].copyPiece(newPos);
	piecesCopy[oldPos] = DefaultPiece.NULL;
	return piecesCopy;
    }
    
    protected Piece[] disableEnPassant(final Piece[] pieces) {
	
	final ChessColor color = pieces[oldPos].getColor();
	
	// TODO Schleife verbessern
	for (int i = 0; i < 120; i++) {
	    if (pieces[i] instanceof PawnEnPassantBeatable && pieces[i].getColor() != color) {
		pieces[i] = pieces[i].copyPiece(i);
	    }
	}
	
	return pieces;
    }

    public boolean isValid(final Piece[] pieces, final ChessColor color) {
	return !isCheck(simulateMove(pieces), color);
    }
    
    // TODO Methode in Engine einpassen
    public static boolean isCheck(final Piece[] pieces, final ChessColor color) {
	// TODO Schleife ueberarbeiten
	for (int i = 0; i < 120; i++) {
	    final Piece piece = pieces[i];
	    if (piece != DefaultPiece.INVALID && piece != DefaultPiece.NULL && piece.getColor() != color) {
		if (piece.checksEnemyKing(pieces)) {
		    return true;
		}
	    }
	}
	return false;
    }
    
    public String toString() {
	return (oldPos + "--" + newPos);
    }
}