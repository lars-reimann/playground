package src.net.bplaced.programmierung.schach.logic;

public abstract class AbstractBishop extends AbstractSliderPiece {

    public static final class BlackBishop extends AbstractBishop {

        private static final BlackBishop INSTANCE;

        static {
            INSTANCE = new BlackBishop();
        }

        public static final Piece getInstance() {
            return INSTANCE;
        }

        private BlackBishop() {
        }

        @Override
        public ChessColor getColor() {
            return ChessColor.BLACK;
        }

        @Override
        public int getPosValue(final int pos) {
            return 0; // TODO
        }
    }

    public static final class WhiteBishop extends AbstractBishop {

        private static final WhiteBishop INSTANCE;

        static {
            INSTANCE = new WhiteBishop();
        }

        public static final Piece getInstance() {
            return INSTANCE;
        }

        private WhiteBishop() {
        }

        @Override
        public ChessColor getColor() {
            return ChessColor.WHITE;
        }

        @Override
        public int getPosValue(final int pos) {
            return 0; // TODO
        }
    }

    private static final int[] CHANGES;

    private static final char NAME;

    private static final int VALUE;

    static {
        CHANGES = new int[2];
        CHANGES[0] = 9;
        CHANGES[1] = 11;

        NAME = 'L';

        VALUE = 325;
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
