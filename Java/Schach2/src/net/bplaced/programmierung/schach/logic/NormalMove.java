package net.bplaced.programmierung.schach.logic;

public class NormalMove extends AbstractMove {

    private static final NormalMove INSTANCE;

    static {
        INSTANCE = new NormalMove();
    }

    public static final Move getInstance() {
        return INSTANCE;
    }

    private NormalMove() { }
    
    @Override
    public void undo(final Piece[] pieces, final int oldPos, final int newPos, final UndoData data, final Piece oldPiece) {
        // en passant
        pieces[oldPos] = pieces[newPos];
        pieces[newPos] = oldPiece;
    }

    @Override
    public void execute(final Piece[] pieces, final int oldPos, final int newPos) {
        pieces[newPos] = pieces[oldPos];
        pieces[oldPos] = DefaultPiece.NULL;
        // en passant
    }

    @Override
    public int getMoveValue() {
        // TODO Auto-generated method stub
        return 0;
    }

}