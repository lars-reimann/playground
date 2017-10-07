package net.bplaced.programmierung.schach.logic;

public class Bishop extends AbstractSliderPiece {

    private static final Bishop BLACK_BISHOP;
    private static final int[] DIRECTIONS;
    private static final char SYMBOL;
    private static final Bishop WHITE_BISHOP;

    static {
        DIRECTIONS = new int[2];
        DIRECTIONS[0] = 9;
        DIRECTIONS[1] = 11;

        SYMBOL = 'L';
        
        BLACK_BISHOP = new Bishop(ChessColor.BLACK);
        WHITE_BISHOP = new Bishop(ChessColor.WHITE);
    }

    private Bishop(ChessColor color) {
        super(color, SYMBOL, DIRECTIONS);
    }

    public Piece getBlackInstance() {
        return BLACK_BISHOP;
    }

    public Piece getWhiteInstance() {
        return WHITE_BISHOP;
    }
}
