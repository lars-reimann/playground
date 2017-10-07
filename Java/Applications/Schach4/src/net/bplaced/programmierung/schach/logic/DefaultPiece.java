package net.bplaced.programmierung.schach.logic;

import java.util.List;

/**
 * <p>
 * Einige Konstanten f&uuml;r Schachfiguren.
 * </p>
 * 
 * @version 2. November 2011
 * @author Lars Reimann
 */
public enum DefaultPiece implements Piece {

    /**
     * Eine ung&uuml;ltige Figur, die sich au&szlig;erhalb des Feldes befindet.
     */
    INVALID,

    /**
     * Eine leere Figur, die anzeigt, dass sich an dieser Stelle keine FIgur
     * befindet. Diese Konstante ist statt des Literales null zu verwenden, um
     * polymorphistisches Verhalten der Figuren zu gew&auml;hrleisten. Dies
     * fungiert als null-Objekt.
     */
    NULL;

    @Override
    public boolean checksEnemyKing(final Piece[] pieces) {
	return false;
    }

    @Override
    public List<Move> generateMoves(final Piece[] pieces, final boolean validate) {
	throw new AssertionError();
    }

    @Override
    public Piece copyPiece(final int newPos) {
	throw new AssertionError();
    }

    @Override
    public ChessColor getColor() {
	throw new AssertionError();
    }

    @Override
    public char getName() {
	throw new AssertionError();
    }

    @Override
    public int getPieceValue() {
	throw new AssertionError();
    }

    @Override
    public int getPos() {
	throw new AssertionError();
    }

    @Override
    public void addMove(Piece[] pieces, List<Move> moves, Move move, boolean validate) {
	throw new AssertionError();
    }

    @Override
    public Move newMove(int oldPos, int newPos) {
	throw new AssertionError();
    }
}
