package src.net.bplaced.programmierung.schach.logic;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractPawnEnPassantBeatable extends AbstractPawn {

    @Override
    public Map<Integer, Move> generateMoves(final Piece[] pieces,
            final int pos, final boolean validate) {
        return generateNormalMoves(pieces, pos, validate);
    }

    public abstract Map<Integer, Move> generateNormalMoves(
            final Piece[] pieces, final int pos, final boolean validate);

    @Override
    public int getPosValue(final int pos) {
        // TODO Auto-generated method stub
        return 0;
    }

    public static final class BlackPawnEnPassantBeatable extends
            AbstractPawnEnPassantBeatable {

        private static final BlackPawnEnPassantBeatable INSTANCE;

        static {
            INSTANCE = new BlackPawnEnPassantBeatable();
        }

        public static final Piece getInstance() {
            return INSTANCE;
        }

        private BlackPawnEnPassantBeatable() {
        }

        @Override
        public ChessColor getColor() {
            return ChessColor.BLACK;
        }

        @Override
        public int getPosValue(final int pos) {
            return 0; // TODO
        }

        @Override
        public Map<Integer, Move> generateNormalMoves(final Piece[] pieces,
                final int pos, final boolean validate) {
            final Map<Integer, Move> moves = new HashMap<Integer, Move>(3);
            if (pieces[newPos(9, pos)].getColor() == ChessColor.WHITE) {
                final Move move = NormalMove.getInstance();
                addMove(pieces, moves, move, validate, pos, newPos(9, pos));
            }
            if (pieces[newPos(10, pos)] == DefaultPiece.NULL) {
                final Move move = NormalMove.getInstance();
                addMove(pieces, moves, move, validate, pos, newPos(10, pos));
            }
            if (pieces[newPos(11, pos)].getColor() == ChessColor.WHITE) {
                final Move move = NormalMove.getInstance();
                addMove(pieces, moves, move, validate, pos, newPos(11, pos));
            }
            return moves;
        }

        protected int newPos(final int change, final int pos) {
            return pos + change;
        }

    }

    public static final class WhitePawnEnPassantBeatable extends
            AbstractPawnEnPassantBeatable {

        private static final WhitePawnEnPassantBeatable INSTANCE;

        static {
            INSTANCE = new WhitePawnEnPassantBeatable();
        }

        public static final Piece getInstance() {
            return INSTANCE;
        }

        private WhitePawnEnPassantBeatable() {
        }

        @Override
        public ChessColor getColor() {
            return ChessColor.WHITE;
        }

        @Override
        public int getPosValue(int pos) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public Map<Integer, Move> generateNormalMoves(final Piece[] pieces,
                final int pos, final boolean validate) {
            final Map<Integer, Move> moves = new HashMap<Integer, Move>(3);
            if (pieces[newPos(9, pos)].getColor() == ChessColor.BLACK) {
                final Move move = NormalMove.getInstance();
                addMove(pieces, moves, move, validate, pos, newPos(9, pos));
            }
            if (pieces[newPos(10, pos)] == DefaultPiece.NULL) {
                final Move move = NormalMove.getInstance();
                addMove(pieces, moves, move, validate, pos, newPos(10, pos));
            }
            if (pieces[newPos(11, pos)].getColor() == ChessColor.BLACK) {
                final Move move = NormalMove.getInstance();
                addMove(pieces, moves, move, validate, pos, newPos(11, pos));
            }

            return moves;
        }

        protected int newPos(final int change, final int pos) {
            return pos - change;
        }

    }

}
