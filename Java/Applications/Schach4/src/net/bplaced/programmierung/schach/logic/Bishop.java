package net.bplaced.programmierung.schach.logic;

/**
 * <p>
 * Darstellung eines L&auml;ufers.
 * </p>
 * 
 * @version 2. November 2011
 * @author Lars Reimann
 */
public final class Bishop extends AbstractSliderPiece {

    /**
     * Die Bewegungsrichtungen des L&auml;ufers.
     */
    private static final int[] changes;

    /**
     * Die Abk&uuml;rzung des L&auml;ufers in der Notation.
     */
    private static final char name;

    /**
     * Der Wert des L&auml;ufers als Spielfigur.
     */
    private static final int value;

    static {
	changes = new int[2];
	changes[0] = 9;
	changes[1] = 11;

	name = 'L';
	
	value = 3;
    }

    /**
     * Konstruiert einen neuen L&auml;ufer mit vorgegebener Farbe und Position
     * auf dem Spielfeld.
     * 
     * @param color
     *            Die Farbe der Figur (wei&szlig;/schwarz).
     * @param pos
     *            Die Position der Figur auf dem Schachbrett.
     */
    public Bishop(final ChessColor color, final int pos) {
	super(color, pos);
    }

    @Override
    public Piece copyPiece(int newPos) {
	return new Bishop(color, newPos);
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
