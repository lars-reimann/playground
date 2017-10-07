package net.bplaced.programmierung.schach.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PawnCanDoubleMove extends Pawn {

    public PawnCanDoubleMove(final ChessColor color, final int pos) {
	super(color, pos);
    }


    @Override
    public List<Move> generateDoubleMoves(final Piece[] pieces, final boolean validate) {
	// TODO Single Element List?
	final List<Move> moves = new ArrayList<Move>();
	if (pieces[newPos(10)] == DefaultPiece.NULL && pieces[newPos(20)] == DefaultPiece.NULL) {
	    final Move move = new PawnDoubleMove(pos, newPos(20));
	    addMove(pieces, moves, move, validate);
	}
	return moves;
    }

    @Override
    public List<Move> generatePromotion(final Piece[] pieces, final boolean validate) {
	return Collections.emptyList();
    }
}
