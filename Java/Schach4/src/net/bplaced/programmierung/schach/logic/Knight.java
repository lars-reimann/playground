package net.bplaced.programmierung.schach.logic;

import java.util.ArrayList;
import java.util.List;

public final class Knight extends AbstractPiece {

    /**
     * Die Bewegungsrichtungen des Springers.
     */
    private static final int[] changes;

    /**
     * Die Abk&uuml;rzung des Springers in der Notation.
     */
    private static final char name;

    /**
     * Der Wert des Springers als Spielfigur.
     */
    private static final int value;

    static {
	changes = new int[4];
	changes[0] = 8;
	changes[1] = 12;
	changes[2] = 19;
	changes[3] = 21;
	
	name = 'S';
	
	value = 3;
    }

    /**
     * Konstruiert einen neuen Springer mit vorgegebener Farbe und Position auf
     * dem Spielfeld.
     * 
     * @param color
     *            Die Farbe der Figur (wei&szlig;/schwarz).
     * @param pos
     *            Die Position der Figur auf dem Schachbrett.
     */
    public Knight(final ChessColor color, final int pos) {
	super(color, pos);
    }

    @Override
    public List<Move> generateMoves(Piece[] pieces, final boolean validate) {
	final List<Move> moves = new ArrayList<Move>();
	for (int change : changes) {
	    moves.addAll(generateMoves(pieces, change, validate));
	    moves.addAll(generateMoves(pieces, -change, validate));
	}
	return moves;
    }

    private List<Move> generateMoves(final Piece[] pieces,
	    final int change, final boolean validate) {
	final List<Move> moves = new ArrayList<Move>();
	final Piece piece = pieces[newPosition(change)];
	if (piece != DefaultPiece.INVALID && (piece == DefaultPiece.NULL || piece.getColor() != color)) {
	    final Move move = new Move(pos, newPosition(change));
	    addMove(pieces, moves, move, validate);
	}
	return moves;
    }

    private int newPosition(int change) {
	return pos + change;
    }

    @Override
    public Piece copyPiece(int newPos) {
	return new Knight(color, newPos);
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
