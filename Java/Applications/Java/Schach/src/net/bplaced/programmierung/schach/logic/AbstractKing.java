package src.net.bplaced.programmierung.schach.logic;

public abstract class AbstractKing extends AbstractPiece {

    protected static final int[] CHANGES;

    protected static final char NAME;

    protected static final int VALUE;

    static {
        CHANGES = new int[4];
        CHANGES[0] = 1;
        CHANGES[1] = 9;
        CHANGES[2] = 10;
        CHANGES[3] = 11;

        NAME = 'K';

        VALUE = 0;
    }

    public AbstractKing() {
        super();
    }

    protected int newPosition(int change, final int pos) {
        return pos + change;
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