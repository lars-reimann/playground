package net.bplaced.programmierung.schach.logic;

import java.util.List;

/**
 * <p>
 * Default-Implementierung des Interfaces {@link Piece}.
 * </p>
 * <p>
 * Einige wesentliche Methoden, die allen konkreten Schachfiguren gemein sind,
 * werden implementiert.
 * </p>
 * 
 * @version 2. November 2011
 * @author Lars Reimann
 */
public abstract class AbstractPiece implements Piece {
    
    public AbstractPiece(final ChessColor color, final int pos) {
	this.color = color;
	this.pos = pos;
    }

    @Override
    public void addMove(final Piece[] pieces, final List<Move> moves, final Move move, final boolean validate) {
	if (validate) {
	    if (move.isValid(pieces, color)) {
		moves.add(move);
	    }
	} else {
	    moves.add(move);
	}
    }
    
    @Override
    public Move newMove(final int oldPos, final int newPos) {
	return new Move(oldPos, newPos);
    }
    
    /**
     * Die Farbe der Figur (wei&szlig; oder schwarz).
     */
    protected ChessColor color;

    /**
     * Die momentane Position der Figur.
     */
    protected int pos;

    @Override
    public boolean checksEnemyKing(final Piece[] pieces) {
	final List<Move> moves = generateMoves(pieces, false);
	for (Move move : moves) {
	    if (pieces[move.getNewPos()] instanceof King
		    && pieces[move.getNewPos()].getColor() != color) {
		return true;
	    }
	}
	return false;
    }

    @Override
    public final ChessColor getColor() {
	return color;
    }

    @Override
    public final int getPos() {
	return pos;
    }
}
