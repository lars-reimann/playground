package net.bplaced.programmierung.schach.logic;

import java.util.ArrayList;
import java.util.List;

public class KingQueensideCastling extends King {

    public KingQueensideCastling(final ChessColor color, final int pos) {
	super(color, pos);
    }
    
    @Override
    protected List<Move> generateCastlings(Piece[] pieces, final boolean validate) {
	final List<Move> moves = new ArrayList<Move>();
	if (pieces[pos - 1] == DefaultPiece.NULL && pieces[pos - 2] == DefaultPiece.NULL) {
	    final Move move = new QueensideCastling(pos, pos - 2);
	    addMove(pieces, moves, move, validate);
	}
	return moves;
    }
}
