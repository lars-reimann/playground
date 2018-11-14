package net.bplaced.programmierung.schach.logic;

public class Rook extends AbstractSliderPiece {

    private static final Rook BLACK_KINGSIDE_ROOK;
    private static final Rook BLACK_QUEENSIDE_ROOK;
    private static final int[] DIRECTIONS;
    private static final char SYMBOL;
    private static final Rook WHITE_KINGSIDE_ROOK;
    private static final Rook WHITE_QUEENSIDE_ROOK;

    static {
        DIRECTIONS = new int[2];
        DIRECTIONS[0] = 9;
        DIRECTIONS[1] = 11;

        SYMBOL = 'T';

        BLACK_KINGSIDE_ROOK = new Rook(ChessColor.BLACK);
        BLACK_QUEENSIDE_ROOK = new Rook(ChessColor.BLACK);
        WHITE_KINGSIDE_ROOK = new Rook(ChessColor.WHITE);
        WHITE_QUEENSIDE_ROOK = new Rook(ChessColor.WHITE);
    }

    private Rook(ChessColor color) {
        super(color, SYMBOL, DIRECTIONS);
    }

    public Piece getBlackKingsideInstance() {
        return BLACK_KINGSIDE_ROOK;
    }

    public Piece getBlackQueensideInstance() {
        return BLACK_QUEENSIDE_ROOK;
    }

    public Piece getWhiteKingsideInstance() {
        return WHITE_KINGSIDE_ROOK;
    }

    public Piece getWhiteQueensideInstance() {
        return BLACK_QUEENSIDE_ROOK;
    }
}