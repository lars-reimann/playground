package src.net.bplaced.programmierung.schach.logic;

public abstract class AbstractRookNormal extends AbstractRook {
    public static class WhiteRookNormal extends AbstractRookNormal {

        private static final WhiteRookNormal INSTANCE;

        static {
            INSTANCE = new WhiteRookNormal();
        }

        public static final Piece getInstance() {
            return INSTANCE;
        }

        private WhiteRookNormal() {

        }

        @Override
        public ChessColor getColor() {
            return ChessColor.WHITE;
        }

        @Override
        public int getPosValue(final int pos) {
            // TODO Auto-generated method stub
            return 0;
        }
    }

    public static class BlackRookNormal extends AbstractRookNormal {

        private static final BlackRookNormal INSTANCE;

        static {
            INSTANCE = new BlackRookNormal();
        }

        public static final Piece getInstance() {
            return INSTANCE;
        }

        private BlackRookNormal() {

        }

        @Override
        public ChessColor getColor() {
            return ChessColor.BLACK;
        }

        @Override
        public int getPosValue(final int pos) {
            // TODO Auto-generated method stub
            return 0;
        }
    }
}
