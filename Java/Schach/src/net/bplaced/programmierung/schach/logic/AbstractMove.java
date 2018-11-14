package src.net.bplaced.programmierung.schach.logic;

import net.bplaced.programmierung.schach.logic.AbstractPawnNormal.BlackPawnNormal;
import net.bplaced.programmierung.schach.logic.AbstractPawnNormal.WhitePawnNormal;

public abstract class AbstractMove implements Move {

    public void disableEnPassant(final Piece[] pieces, final ChessColor color) {
        if (color == ChessColor.WHITE) {
            for (int i = 51; i <= 58; i++) {
                if (pieces[i] instanceof AbstractPawnEnPassantBeatable
                        && pieces[i].getColor() != color) {
                    pieces[i] = BlackPawnNormal.getInstance();
                    break;
                }
            }
        } else {
            for (int i = 61; i <= 68; i++) {
                if (pieces[i] instanceof AbstractPawnEnPassantBeatable
                        && pieces[i].getColor() != color) {
                    pieces[i] = WhitePawnNormal.getInstance();
                    break;
                }
            }
        }
    }

    public void enableEnPassant(final Piece[] pieces, final Piece oldEnPassant,
            final int oldEnPassantPos) {
        if (oldEnPassantPos != 0 && oldEnPassant != null) {
            pieces[oldEnPassantPos] = oldEnPassant;
        }
    }

    public boolean isValid(final Piece[] pieces, final int oldPos,
            final int newPos, final ChessColor color) {
        final UndoData data = new UndoData(pieces, color);
        final Piece oldPiece = pieces[newPos];
        execute(pieces, oldPos, newPos);
        final boolean isValid = !Engine.isCheck(pieces, color);
        undo(pieces, oldPos, newPos, data, oldPiece);
        return isValid;
    }

}