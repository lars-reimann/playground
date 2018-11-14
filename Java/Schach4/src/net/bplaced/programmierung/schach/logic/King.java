package net.bplaced.programmierung.schach.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class King extends AbstractPiece {

    /**
     * Die Bewegungsrichtungen des K&ouml;nigs.
     */
    private static final int[] changes;

    /**
     * Die Abk&uuml;rzung des K&ouml;nigs in der Notation.
     */
    private static final char name;

    /**
     * Der Wert des K&ouml;nigs als Spielfigur.
     */
    private static final int value;

    static {
	changes = new int[4];
	changes[0] = 1;
	changes[1] = 9;
	changes[2] = 10;
	changes[3] = 11;

	name = 'K';

	value = 0;
    }

    /**
     * Konstruiert einen neuen Koenig mit vorgegebener Farbe und Position auf
     * dem Spielfeld.
     * 
     * @param color
     *            Die Farbe der Figur (wei&szlig;/schwarz).
     * @param pos
     *            Die Position der Figur auf dem Schachbrett.
     */
    public King(final ChessColor color, final int pos) {
	super(color, pos);
    }

    @Override
    public Piece copyPiece(int newPos) {
	return new King(color, newPos);
    }

    protected List<Move> generateCastlings(Piece[] pieces, final boolean validate) {
	return Collections.emptyList();
    }

    @Override
    public List<Move> generateMoves(Piece[] pieces, final boolean validate) {
	final List<Move> moves = new ArrayList<Move>();
	for (int change : changes) {
	    moves.addAll(generateMoves(pieces, change, validate));
	    moves.addAll(generateMoves(pieces, -change, validate));
	}
	moves.addAll(generateCastlings(pieces, validate));
	return moves;
    }

    private List<Move> generateMoves(final Piece[] pieces, final int change, final boolean validate) {
	final List<Move> moves = new ArrayList<Move>();
	final Piece piece = pieces[newPosition(change)];
	if (piece != DefaultPiece.INVALID && (piece == DefaultPiece.NULL || piece.getColor() != color)) {
	    final Move move = newMove(pos, newPosition(change));
	    addMove(pieces, moves, move, validate);
	}
	return moves;
    }

    @Override
    public char getName() {
	return name;
    }

    @Override
    public int getPieceValue() {
	return value;
    }

    private int newPosition(int change) {
	return pos + change;
    }
}
