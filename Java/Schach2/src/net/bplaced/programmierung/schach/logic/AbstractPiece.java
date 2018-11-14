package net.bplaced.programmierung.schach.logic;

public abstract class AbstractPiece implements Piece {
    
    private ChessColor color;
    private char symbol;
    
    public ChessColor getColor() {
        return color;
    }
    
    public char getSymbol() {
        return symbol;
    }
    
    public AbstractPiece(ChessColor color, char symbol) {
        this.color = color;
        this.symbol = symbol;
    }

// TODO
//    public void addMove(final Piece[] pieces,
//            final Map<Integer, Move> moves, final Move move,
//            final boolean validate, final int oldPos, final int newPos) {
//        if (!validate || move.isValid(pieces, oldPos, newPos, getColor())) {
//            moves.put(newPos, move);
//        }
//    }
//
//    @Override
//    public boolean checksEnemyKing(final Piece[] pieces, final int pos,
//            final int kingPos) {
//        return generateMoves(pieces, pos, false).containsKey(kingPos);
//    }
}
