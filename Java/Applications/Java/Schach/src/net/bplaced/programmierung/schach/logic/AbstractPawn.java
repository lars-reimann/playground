package src.net.bplaced.programmierung.schach.logic;

public abstract class AbstractPawn extends AbstractPiece {

    private static final int VALUE;

    static {
        VALUE = 100;
    }

    protected static final char NAME;

    static {
        NAME = 'B';
    }

    public AbstractPawn() {
        super();
    }

    @Override
    public int getPieceValue() {
        return VALUE;
    }

    @Override
    public char getName() {
        return NAME;
    }

}