package net.bplaced.programmierung.schach.logic;

public interface Move {
    void undo(final Piece[] pieces, final int oldPos, final int newPos,
            final UndoData data, final Piece oldPiece);

    void execute(final Piece[] pieces, final int oldPos, final int newPos);

    int getMoveValue();
    
    boolean isValid(final Piece[] pieces, final int oldPos, final int newPos, final ChessColor color);
}