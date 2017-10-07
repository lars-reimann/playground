package src.net.bplaced.programmierung.schach.logic;

public abstract class AbstractQueen extends AbstractSliderPiece {

    public static class WhiteQueen extends AbstractQueen {

        private static final WhiteQueen INSTANCE;

        static {
            INSTANCE = new WhiteQueen();
        }

        public static final Piece getInstance() {
            return INSTANCE;
        }

        private WhiteQueen() {

        }

        @Override
        public ChessColor getColor() {
            return ChessColor.WHITE;
        }
    }

    public static class BlackQueen extends AbstractQueen {

        private static final BlackQueen INSTANCE;

        static {
            INSTANCE = new BlackQueen();
        }

        public static final Piece getInstance() {
            return INSTANCE;
        }

        private BlackQueen() {

        }

        @Override
        public ChessColor getColor() {
            return ChessColor.BLACK;
        }
    }

    private static final int[] CHANGES;

    private static final char NAME;

    private static final int VALUE;

    static {
        CHANGES = new int[4];
        CHANGES[0] = 1;
        CHANGES[1] = 9;
        CHANGES[2] = 10;
        CHANGES[3] = 11;

        NAME = 'D';

        VALUE = 1050;
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

    @Override
    public int getPosValue(final int pos) {
        return 0; // TODO
    }
}
