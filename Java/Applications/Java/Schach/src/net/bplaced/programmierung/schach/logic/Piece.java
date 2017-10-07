package src.net.bplaced.programmierung.schach.logic;

import java.util.Map;

public interface Piece {

    boolean checksEnemyKing(final Piece[] pieces, final int pos,
            final int kingPos);

    ChessColor getColor();

    Map<Integer, Move> generateMoves(final Piece[] pieces, final int pos,
            final boolean validate);

    char getName();

    int getPieceValue();

    int getPosValue(final int pos);
}