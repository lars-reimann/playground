package net.bplaced.programmierung.schach.logic;

public class King extends AbstractPiece {

    private static final King BLACK_KING;
    private static final int[] DIRECTIONS;
    private static final char SYMBOL;
    private static final King WHITE_KING;

    static {
        DIRECTIONS = new int[4];
        DIRECTIONS[0] = 1;
        DIRECTIONS[1] = 9;
        DIRECTIONS[2] = 10;
        DIRECTIONS[3] = 11;

        SYMBOL = 'K';
        
        BLACK_KING = new King(ChessColor.BLACK);
        WHITE_KING = new King(ChessColor.WHITE);
    }

    private King(ChessColor color) {
        super(color, SYMBOL);
    }

    public Piece getBlackInstance() {
        return BLACK_KING;
    }

    public Piece getWhiteInstance() {
        return WHITE_KING;
    }
}