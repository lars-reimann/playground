package net.bplaced.programmierung.schach.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pawn extends AbstractPiece {

    /**
     * Die Abk&uuml;rzung des Bauern in der Notation.
     */
    private static final char name;

    /**
     * Der Wert des Bauern als Spielfigur.
     */
    private static final int value;

    static {
	name = 'B';

	value = 1;
    }

    /**
     * Konstruiert einen neuen Bauer mit vorgegebener Farbe und Position auf dem
     * Spielfeld.
     * 
     * @param color
     *            Die Farbe der Figur (weiss/schwarz).
     * @param pos
     *            Die Position der Figur auf dem Schachbrett.
     */
    public Pawn(final ChessColor color, final int pos) {
	super(color, pos);
    }

    @Override
    public Piece copyPiece(final int newPos) {
	return new Pawn(color, newPos);
    }

    public List<Move> generateDoubleMoves(final Piece[] pieces,
	    final boolean validate) {
	return Collections.emptyList();
    }

    public List<Move> generateEnPassant(final Piece[] pieces,
	    final boolean validate) {
	final List<Move> moves = new ArrayList<Move>();
	if (pieces[pos - 1] instanceof PawnEnPassantBeatable
		&& pieces[pos - 1 + color.getDirection() * 10] == DefaultPiece.NULL
		&& pieces[pos - 1].getColor() != color) {
	    final Move move = new EnPassant(pos, pos - 1 + color.getDirection()
		    * 10);
	    addMove(pieces, moves, move, validate);
	}
	if (pieces[pos + 1] instanceof PawnEnPassantBeatable
		&& pieces[pos + 1 + color.getDirection() * 10] == DefaultPiece.NULL
		&& pieces[pos + 1].getColor() != color) {
	    final Move move = new EnPassant(pos, pos + 1 + color.getDirection()
		    * 10);
	    addMove(pieces, moves, move, validate);
	}
	return moves; // TODO

    }

    @Override
    public List<Move> generateMoves(final Piece[] pieces, final boolean validate) {
	final List<Move> moves = new ArrayList<Move>();

	moves.addAll(generateNormalMoves(pieces, validate));
	moves.addAll(generateDoubleMoves(pieces, validate));
	moves.addAll(generateEnPassant(pieces, validate));
	moves.addAll(generatePromotion(pieces, validate));

	return moves;
    }

    public List<Move> generateNormalMoves(final Piece[] pieces,
	    final boolean validate) {
	final List<Move> moves = new ArrayList<Move>();
	if (pos < color.getPromoStart() || pos > color.getPromoEnd()) {
	    if (pieces[newPos(9)] != DefaultPiece.NULL
		    && pieces[newPos(9)] != DefaultPiece.INVALID
		    && pieces[newPos(9)].getColor() != color) {
		final Move move = new Move(pos, newPos(9));
		addMove(pieces, moves, move, validate);
	    }
	    if (pieces[newPos(10)] == DefaultPiece.NULL) {
		final Move move = new Move(pos, newPos(10));
		addMove(pieces, moves, move, validate);
	    }
	    if (pieces[newPos(11)] != DefaultPiece.NULL
		    && pieces[newPos(11)] != DefaultPiece.INVALID
		    && pieces[newPos(11)].getColor() != color) {
		final Move move = new Move(pos, newPos(11));
		addMove(pieces, moves, move, validate);
	    }
	}
	return moves;
    }

    public List<Move> generatePromotion(final Piece[] pieces,
	    final boolean validate) {
	final List<Move> moves = new ArrayList<Move>();
	if (pos >= color.getPromoStart() && pos <= color.getPromoEnd()) {
	    if (pieces[newPos(9)] != DefaultPiece.NULL
		    && pieces[newPos(9)] != DefaultPiece.INVALID
		    && pieces[newPos(9)].getColor() != color) {
		final Move move = new Promotion(pos, newPos(9));
		addMove(pieces, moves, move, validate);
	    }
	    if (pieces[newPos(10)] == DefaultPiece.NULL) {
		final Move move = new Promotion(pos, newPos(10));
		addMove(pieces, moves, move, validate);
	    }
	    if (pieces[newPos(11)] != DefaultPiece.NULL
		    && pieces[newPos(11)] != DefaultPiece.INVALID
		    && pieces[newPos(11)].getColor() != color) {
		final Move move = new Promotion(pos, newPos(11));
		addMove(pieces, moves, move, validate);
	    }
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

    protected int newPos(final int change) {
	return pos + change * color.getDirection();
    }
}
