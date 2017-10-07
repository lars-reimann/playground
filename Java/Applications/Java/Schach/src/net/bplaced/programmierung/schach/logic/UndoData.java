package src.net.bplaced.programmierung.schach.logic;

public final class UndoData {
    private final Piece enPassantPiece;
    private final int enPassantPos;
    private final Piece king;
    private final int kingPos;
    
    public UndoData(final Piece[] pieces, final ChessColor color) {
        enPassantPos = findEnPassantPiece(pieces, color);
        enPassantPiece = pieces[enPassantPos];
        kingPos = findKing(pieces, color);
        king = pieces[kingPos];
    }

    private int findEnPassantPiece(Piece[] pieces, ChessColor color) {
        if (color == ChessColor.WHITE) {
            for (int i = 51; i <= 58; i++) {
                if (pieces[i] instanceof AbstractPawnEnPassantBeatable
                        && pieces[i].getColor() == color) {
                    return i;
                }
            }
        } else {
            for (int i = 61; i <= 68; i++) {
                if (pieces[i] instanceof AbstractPawnEnPassantBeatable
                        && pieces[i].getColor() == color) {
                    return i;
                }
            }
        }
        return 0;
    }

    private int findKing(Piece[] pieces, ChessColor color) {
        if (color == ChessColor.WHITE) {
            return 95;
        } else {
            return 25;
        }
    }

    public Piece getEnPassantPiece() {
        return enPassantPiece;
    }

    public int getEnPassantPos() {
        return enPassantPos;
    }

    public Piece getKing() {
        return king;
    }

    public int getKingPos() {
        return kingPos;
    }
}
