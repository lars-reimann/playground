package src.net.bplaced.programmierung.schach.logic;

public abstract class AbstractRook extends AbstractSliderPiece {

    protected static final int[] CHANGES;

    protected static final char NAME;

    protected static final int VALUE;

    static {
        CHANGES = new int[2];
        CHANGES[0] = 1;
        CHANGES[1] = 10;

        NAME = 'T';

        VALUE = 500;
    }

    public AbstractRook() {
        super();
    }

    @Override
    protected int[] getChanges() {
        return CHANGES;
    }

    @Override
    public char getName() {
        return NAME;
    }

    @Override
    public int getPieceValue() {
        return VALUE;
    }

}