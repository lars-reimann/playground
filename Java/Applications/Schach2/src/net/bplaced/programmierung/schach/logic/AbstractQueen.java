package net.bplaced.programmierung.schach.logic;

public class Queen extends AbstractSliderPiece {

    private static final Queen BLACK_QUEEN;
    private static final int[] DIRECTIONS;
    private static final char SYMBOL;
    private static final Queen WHITE_QUEEN;

    static {
        DIRECTIONS = new int[4];
        DIRECTIONS[0] = 1;
        DIRECTIONS[1] = 9;
        DIRECTIONS[2] = 10;
        DIRECTIONS[3] = 11;

        SYMBOL = 'D';
        
        BLACK_QUEEN = new Queen(ChessColor.BLACK);
        WHITE_QUEEN = new Queen(ChessColor.WHITE);
    }

    private Queen(ChessColor color) {
        super(color, SYMBOL, DIRECTIONS);
    }

    public Piece getBlackInstance() {
        return BLACK_QUEEN;
    }

    public Piece getWhiteInstance() {
        return WHITE_QUEEN;
    }
}
