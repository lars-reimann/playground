package net.bplaced.programmierung.schach.logic;

/**
 * <p>
 * Darstellung einer Dame.
 * </p>
 * 
 * @version 2. November 2011
 * @author Lars Reimann
 */
public final class Queen extends AbstractSliderPiece {

    /**
     * Die Bewegungsrichtungen der Dame.
     */
    private static final int[] changes;

    /**
     * Die Abk&uuml;rzung der Dame in der Notation.
     */
    private static final char name;

    /**
     * Der Wert der Dame als Spielfigur.
     */
    private static final int value;

    static {
	changes = new int[4];
	changes[0] = 1;
	changes[1] = 9;
	changes[2] = 10;
	changes[3] = 11;

	name = 'D';

	value = 9;
    }

    /**
     * Konstruiert eine neue Dame mit vorgegebener Farbe und Position auf dem
     * Spielfeld.
     * 
     * @param color
     *            Die Farbe der Figur (wei&szlig;/schwarz).
     * @param pos
     *            Die Position der Figur auf dem Schachbrett.
     */
    public Queen(final ChessColor color, final int pos) {
	super(color, pos);
    }

    @Override
    public Piece copyPiece(int newPos) {
	return new Queen(color, newPos);
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
}
