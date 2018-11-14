package net.bplaced.programmierung.schach.logic;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Oberklasse aller Figuren, die sich &uuml;ber mehrere Felder auf einmal
 * bewegen k&ouml;nnen.
 * </p>
 * <p>
 * Diese Figuren sind Dame, Turm und L&auml;ufer. Es werden einige weitere
 * gemeinsame Methoden implementiert.
 * </p>
 * 
 * @version 2. November 2011
 * @author Lars Reimann
 */
public abstract class AbstractSliderPiece extends AbstractPiece {

    public AbstractSliderPiece(final ChessColor color, final int pos) {
	super(color, pos);
    }

    @Override
    public List<Move> generateMoves(final Piece[] pieces, final boolean validate) {
	final List<Move> moves = new ArrayList<Move>();
	final int[] changes = getChanges();
	for (int change : changes) {
	    moves.addAll(generateMoves(pieces, change, validate));
	    moves.addAll(generateMoves(pieces, -change, validate));
	}
	return moves;
    }

    /**
     * Hilfsmethode f&uuml;r die Zugerzeugung. Es werden alle m&ouml;glichen
     * Z&uuml;ge in Richtung change berechnet.
     * 
     * @param pieces
     *            Momentane Spielsituation
     * @param change
     *            Bewegungsrichtung
     * @return Liste der Z&uuml;ge
     */
    protected List<Move> generateMoves(final Piece[] pieces, final int change, final boolean validate) {
	final List<Move> moves = new ArrayList<Move>();
	for (int i = 1; pieces[newPosition(i, change)] != DefaultPiece.INVALID
		&& (i == 1 || pieces[newPosition(i - 1, change)] == DefaultPiece.NULL); i++) {
	    final Piece piece = pieces[newPosition(i, change)];
	    if (piece == DefaultPiece.NULL || piece.getColor() != color) {
		final Move move = newMove(pos, newPosition(i, change));
		addMove(pieces, moves, move, validate);
	    }
	}
	return moves;
    }

    /**
     * Liest die Bewegungsrichtungen der jeweiligen Figur aus und gibt diese
     * zur&uuml;ck.
     * 
     * @return Bewegungsrichtungen der Figur
     */
    protected abstract int[] getChanges();

    /**
     * Berechnet die neue Position nachdem um i Felder in Richtung change
     * gezogen worden ist.
     * 
     * @param i
     *            Anzahl der zu ziehenden Felder
     * @param change
     *            Richtung, in die zu ziehen ist
     * @return Position nach Zug
     */
    protected int newPosition(final int i, final int change) {
	return pos + i * change;
    }
}