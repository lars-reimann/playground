package src.net.bplaced.programmierung.schach.logic;

import java.util.HashMap;
import java.util.Map;

import net.bplaced.programmierung.schach.logic.AbstractDoubleMove.BlackDoubleMove;
import net.bplaced.programmierung.schach.logic.AbstractDoubleMove.WhiteDoubleMove;
import net.bplaced.programmierung.schach.logic.AbstractDoubleMoveDisablingMove.BlackDoubleMoveDisablingMove;
import net.bplaced.programmierung.schach.logic.AbstractDoubleMoveDisablingMove.WhiteDoubleMoveDisablingMove;

public abstract class AbstractPawnDouble extends AbstractPawn {

    public static final class WhitePawnDouble extends AbstractPawnDouble {

        private static final WhitePawnDouble INSTANCE;

        static {
            INSTANCE = new WhitePawnDouble();
        }

        public static final Piece getInstance() {
            return INSTANCE;
        }

        private WhitePawnDouble() {
        }

        @Override
        public ChessColor getColor() {
            return ChessColor.WHITE;
        }

        @Override
        public Map<Integer, Move> generateDoubleMove(final Piece[] pieces,
                final int pos, final boolean validate) {
            // TODO Single Element List?
            final Map<Integer, Move> moves = new HashMap<Integer, Move>(1);
            if (pieces[newPos(10, pos)] == DefaultPiece.NULL
                    && pieces[newPos(20, pos)] == DefaultPiece.NULL) {
                final Move move = WhiteDoubleMove.getInstance();
                addMove(pieces, moves, move, validate, pos, newPos(20, pos));
            }
            return moves;
        }

        @Override
        public Map<Integer, Move> generateNormalMoves(final Piece[] pieces,
                final int pos, final boolean validate) {
            final Map<Integer, Move> moves = new HashMap<Integer, Move>(3);
            if (pieces[newPos(9, pos)].getColor() == ChessColor.BLACK) {
                final Move move = WhiteDoubleMoveDisablingMove.getInstance();
                addMove(pieces, moves, move, validate, pos, newPos(9, pos));
            }
            if (pieces[newPos(10, pos)] == DefaultPiece.NULL) {
                final Move move = WhiteDoubleMoveDisablingMove.getInstance();
                addMove(pieces, moves, move, validate, pos, newPos(10, pos));
            }
            if (pieces[newPos(11, pos)].getColor() == ChessColor.BLACK) {
                final Move move = WhiteDoubleMoveDisablingMove.getInstance();
                addMove(pieces, moves, move, validate, pos, newPos(11, pos));
            }
            return moves;
        }

        protected int newPos(final int change, final int pos) {
            return pos - change;
        }

    }

    public static final class BlackPawnDouble extends AbstractPawnDouble {

        private static final BlackPawnDouble INSTANCE;

        static {
            INSTANCE = new BlackPawnDouble();
        }

        public static final Piece getInstance() {
            return INSTANCE;
        }

        private BlackPawnDouble() {
        }

        @Override
        public ChessColor getColor() {
            return ChessColor.BLACK;
        }

        @Override
        public Map<Integer, Move> generateDoubleMove(final Piece[] pieces,
                final int pos, final boolean validate) {
            // TODO Single Element List?
            final Map<Integer, Move> moves = new HashMap<Integer, Move>(1);
            if (pieces[newPos(10, pos)] == DefaultPiece.NULL
                    && pieces[newPos(20, pos)] == DefaultPiece.NULL) {
                final Move move = BlackDoubleMove.getInstance();
                addMove(pieces, moves, move, validate, pos, newPos(20, pos));
            }
            return moves;
        }

        @Override
        public Map<Integer, Move> generateNormalMoves(final Piece[] pieces,
                final int pos, final boolean validate) {
            final Map<Integer, Move> moves = new HashMap<Integer, Move>(3);
            if (pieces[newPos(9, pos)].getColor() == ChessColor.WHITE) {
                final Move move = BlackDoubleMoveDisablingMove.getInstance();
                addMove(pieces, moves, move, validate, pos, newPos(9, pos));
            }
            if (pieces[newPos(10, pos)] == DefaultPiece.NULL) {
                final Move move = BlackDoubleMoveDisablingMove.getInstance();
                addMove(pieces, moves, move, validate, pos, newPos(10, pos));
            }
            if (pieces[newPos(11, pos)].getColor() == ChessColor.WHITE) {
                final Move move = BlackDoubleMoveDisablingMove.getInstance();
                addMove(pieces, moves, move, validate, pos, newPos(11, pos));
            }
            return moves;
        }

        protected int newPos(final int change, final int pos) {
            return pos + change;
        }

    }

    static final int[] POS_VALUES;

    static {
        POS_VALUES = new int[120];
        for (int i = 0; i < 120; i++) {
            final int rank = (i - 1) % 10; // TODO
            if (rank >= 0 && rank <= 2) {
                POS_VALUES[i] = 5;
            } else if (rank >= 5 && rank <= 7) {
                POS_VALUES[i] = 10;
            } else {
                POS_VALUES[i] = -15;
            }
        }

    }

    @Override
    public Map<Integer, Move> generateMoves(final Piece[] pieces,
            final int pos, final boolean validate) {
        final Map<Integer, Move> moves = new HashMap<Integer, Move>(4);
        moves.putAll(generateNormalMoves(pieces, pos, validate));
        moves.putAll(generateDoubleMove(pieces, pos, validate));
        return moves;
    }

    public abstract Map<Integer, Move> generateNormalMoves(
            final Piece[] pieces, final int pos, final boolean validate);

    public abstract Map<Integer, Move> generateDoubleMove(final Piece[] pieces,
            final int pos, final boolean validate);

    @Override
    public int getPosValue(final int pos) {
        return POS_VALUES[pos];
    }
}
