package net.bplaced.programmierung.schach.logic;

import java.util.HashMap;
import java.util.Map;

public class Knight extends AbstractPiece {

    private static final Knight BLACK_KNIGHT;
    private static final int[] DIRECTIONS;
    private static final char SYMBOL;
    private static final Knight WHITE_KNIGHT;

    static {
        DIRECTIONS = new int[4];
        DIRECTIONS[0] = 8;
        DIRECTIONS[1] = 12;
        DIRECTIONS[2] = 19;
        DIRECTIONS[3] = 21;

        SYMBOL = 'S';

        BLACK_KNIGHT = new Knight(ChessColor.BLACK);
        WHITE_KNIGHT = new Knight(ChessColor.WHITE);
    }

    private Knight(ChessColor color) {
        super(color, SYMBOL);
    }

    public Piece getBlackInstance() {
        return BLACK_KNIGHT;
    }

    public Piece getWhiteInstance() {
        return WHITE_KNIGHT;
    }

    // TODO in Strategie ueberfuehren
    // @Override
    // public Map<Integer, Move> generateMoves(Piece[] pieces,
    // final int pos, final boolean validate) {
    // return generateNormalMoves(pieces, pos, validate);
    // }
    //
    // private Map<Integer, Move> generateNormalMoves(Piece[] pieces,
    // final int pos, final boolean validate) {
    // final Map<Integer, Move> normalMoves = new HashMap<Integer, Move>(
    // 8);
    // for (int change : CHANGES) {
    // normalMoves
    // .putAll(generateNormalMove(pieces, pos, change, validate));
    // normalMoves.putAll(generateNormalMove(pieces, pos, -change,
    // validate));
    // }
    // return normalMoves;
    // }
    //
    // private Map<Integer, Move> generateNormalMove(final Piece[] pieces,
    // final int pos, final int change, final boolean validate) {
    // final Map<Integer, Move> moves = new HashMap<Integer, Move>(
    // 1);
    // final Piece piece = pieces[newPosition(change, pos)];
    // if (piece != DefaultPiece.INVALID
    // && (piece == DefaultPiece.NULL || piece.getColor() != getColor())) {
    // final Move move = NormalMove.getInstance();
    // addMove(pieces, moves, move, validate, pos, newPosition(change, pos));
    // }
    // return moves;
    // }
}
