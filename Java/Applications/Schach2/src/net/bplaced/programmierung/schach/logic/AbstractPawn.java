package net.bplaced.programmierung.schach.logic;

public class Pawn extends AbstractPiece {

    private static final Pawn BLACK_PAWN;
    private static final char SYMBOL;
    private static final Pawn WHITE_PAWN;

    static {
        SYMBOL = 'B';
        
        BLACK_PAWN = new Pawn(ChessColor.BLACK);
        WHITE_PAWN = new Pawn(ChessColor.WHITE);
    }

    private Pawn(ChessColor color) {
        super(color, SYMBOL);
    }

    public Piece getBlackInstance() {
        return BLACK_PAWN;
    }

    public Piece getWhiteInstance() {
        return WHITE_PAWN;
    }
}