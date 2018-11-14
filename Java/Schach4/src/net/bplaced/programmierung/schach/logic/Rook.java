package net.bplaced.programmierung.schach.logic;

/**
 * <p>
 * Repr&auml;sentation eines Turmes.
 * </p>
 * 
 * @version 2. November 2011
 * @author Lars Reimann
 */
public class Rook extends AbstractSliderPiece {

    /**
     * Die Bewegungsrichtungen des Turmes.
     */
    private static final int[] changes;

    /**
     * Die Abk&uuml;rzung des Turmes in der Notation.
     */
    private static final char name;

    /**
     * Der Wert des Turmes als Spielfigur.
     */
    private static final int value;

    static {
	changes = new int[2];
	changes[0] = 1;
	changes[1] = 10;

	name = 'T';

	value = 5;
    }

    /**
     * Konstruiert einen neuen Turm mit vorgegebener Farbe und Position auf dem
     * Spielfeld.
     * 
     * @param color
     *            Die Farbe der Figur (wei&szlig;/schwarz).
     * @param pos
     *            Die Position der Figur auf dem Schachbrett.
     */
    public Rook(final ChessColor color, final int pos) {
	super(color, pos);
    }

    @Override
    protected int[] getChanges() {
	return changes;
    }

    @Override
    public char getName() {
	return name;
    }

    @Override
    public int getPieceValue() {
	return value;
    }

    @Override
    public Piece copyPiece(int newPos) {
	return new Rook(color, newPos);
    }
}
