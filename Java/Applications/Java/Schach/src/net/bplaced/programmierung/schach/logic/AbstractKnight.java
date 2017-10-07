package src.net.bplaced.programmierung.schach.logic;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractKnight extends AbstractPiece {

    public static final class BlackKnight extends AbstractKnight {

        private static final BlackKnight INSTANCE;

        static {
            INSTANCE = new BlackKnight();
        }

        public static final Piece getInstance() {
            return INSTANCE;
        }

        private BlackKnight() {
        }

        @Override
        public ChessColor getColor() {
            return ChessColor.BLACK;
        }
    }

    public static final class WhiteKnight extends AbstractKnight {

        private static final WhiteKnight INSTANCE;

        static {
            INSTANCE = new WhiteKnight();
        }

        public static final Piece getInstance() {
            return INSTANCE;
        }

        private WhiteKnight() {
        }

        @Override
        public ChessColor getColor() {
            return ChessColor.WHITE;
        }
    }

    /**
     * Die Bewegungsrichtungen des Springers.
     */
    private static final int[] CHANGES;

    /**
     * Die Abk&uuml;rzung des Springers in der Notation.
     */
    private static final char NAME;

    /**
     * Der Wert des Springers als Spielfigur.
     */
    private static final int VALUE;

    static {
        CHANGES = new int[4];
        CHANGES[0] = 8;
        CHANGES[1] = 12;
        CHANGES[2] = 19;
        CHANGES[3] = 21;

        NAME = 'S';

        VALUE = 325;
    }

    @Override
    public Map<Integer, Move> generateMoves(Piece[] pieces,
            final int pos, final boolean validate) {
        return generateNormalMoves(pieces, pos, validate);
    }

    private Map<Integer, Move> generateNormalMoves(Piece[] pieces,
            final int pos, final boolean validate) {
        final Map<Integer, Move> normalMoves = new HashMap<Integer, Move>(
                8);
        for (int change : CHANGES) {
            normalMoves
                    .putAll(generateNormalMove(pieces, pos, change, validate));
            normalMoves.putAll(generateNormalMove(pieces, pos, -change,
                    validate));
        }
        return normalMoves;
    }

    private Map<Integer, Move> generateNormalMove(final Piece[] pieces,
            final int pos, final int change, final boolean validate) {
        final Map<Integer, Move> moves = new HashMap<Integer, Move>(
                1);
        final Piece piece = pieces[newPosition(change, pos)];
        if (piece != DefaultPiece.INVALID
                && (piece == DefaultPiece.NULL || piece.getColor() != getColor())) {
            final Move move = NormalMove.getInstance();
            addMove(pieces, moves, move, validate, pos, newPosition(change, pos));
        }
        return moves;
    }

    private int newPosition(int change, final int pos) {
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

    @Override
    public int getPosValue(final int pos) {
        return 0; // TODO
    }
}
