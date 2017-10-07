package src.net.bplaced.programmierung.schach.logic;

import java.util.HashMap;
import java.util.Map;

import net.bplaced.programmierung.schach.logic.AbstractEnPassantMove.BlackEnPassantMove;
import net.bplaced.programmierung.schach.logic.AbstractEnPassantMove.WhiteEnPassantMove;
import net.bplaced.programmierung.schach.logic.AbstractPromotionMove.BlackPromotionMove;
import net.bplaced.programmierung.schach.logic.AbstractPromotionMove.WhitePromotionMove;

public abstract class AbstractPawnNormal extends AbstractPawn {
    
    public static final class BlackPawnNormal extends AbstractPawnNormal {

        private static final BlackPawnNormal INSTANCE;

        static {
            INSTANCE = new BlackPawnNormal();
        }

        public static final Piece getInstance() {
            return INSTANCE;
        }

        private BlackPawnNormal() {
        }

        @Override
        public Map<Integer, Move> generateEnPassant(final Piece[] pieces,
                final int pos, final boolean validate) {
            final Map<Integer, Move> moves = new HashMap<Integer, Move>(
                    1);
            if (pieces[pos - 1] instanceof AbstractPawnEnPassantBeatable
                    && pieces[pos - 1].getColor() == ChessColor.WHITE) {
                final Move move = BlackEnPassantMove.getInstance();
                addMove(pieces, moves, move, validate, pos, pos + 9);
            } else if (pieces[pos + 1] instanceof AbstractPawnEnPassantBeatable
                    && pieces[pos + 1].getColor() == ChessColor.WHITE) {
                final Move move = BlackEnPassantMove.getInstance();
                addMove(pieces, moves, move, validate, pos, pos + 11);
            }
            return moves;
        }

        @Override
        public Map<Integer, Move> generateNormalMoves(final Piece[] pieces,
                final int pos, final boolean validate) {
            final Map<Integer, Move> moves = new HashMap<Integer, Move>(
                    3);
            if (pos < 81 || pos > 88) {
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
            }
            return moves;
        }

        @Override
        public Map<Integer, Move> generatePromotion(final Piece[] pieces,
                final int pos, final boolean validate) {
            final Map<Integer, Move> moves = new HashMap<Integer, Move>(
                    3);
            if (pos >= 81 && pos <= 88) {
                if (pieces[newPos(9, pos)].getColor() == ChessColor.WHITE) {
                    final Move move = BlackPromotionMove.getInstance();
                    addMove(pieces, moves, move, validate, pos, newPos(9, pos));
                }
                if (pieces[newPos(10, pos)] == DefaultPiece.NULL) {
                    final Move move = BlackPromotionMove.getInstance();
                    addMove(pieces, moves, move, validate, pos, newPos(10, pos));
                }
                if (pieces[newPos(11, pos)].getColor() == ChessColor.WHITE) {
                    final Move move = BlackPromotionMove.getInstance();
                    addMove(pieces, moves, move, validate, pos, newPos(11, pos));
                }
            }
            return moves;
        }

        @Override
        public ChessColor getColor() {
            return ChessColor.BLACK;
        }

        @Override
        public int getPosValue(int pos) {
            // TODO Auto-generated method stub
            return 0;
        }

        protected int newPos(final int change, final int pos) {
            return pos + change;
        }

    }


    public static final class WhitePawnNormal extends AbstractPawnNormal {

        private static final WhitePawnNormal INSTANCE;

        static {
            INSTANCE = new WhitePawnNormal();
        }

        public static final Piece getInstance() {
            return INSTANCE;
        }

        private WhitePawnNormal() {
        }

        @Override
        public Map<Integer, Move> generateEnPassant(final Piece[] pieces,
                final int pos, final boolean validate) {
            final Map<Integer, Move> moves = new HashMap<Integer, Move>(
                    1);
            if (pieces[pos - 1] instanceof AbstractPawnEnPassantBeatable
                    && pieces[pos - 1].getColor() != getColor()) {
                final Move move = WhiteEnPassantMove.getInstance();
                addMove(pieces, moves, move, validate, pos, pos - 11);
            } else if (pieces[pos + 1] instanceof AbstractPawnEnPassantBeatable
                    && pieces[pos + 1].getColor() != getColor()) {
                final Move move = WhiteEnPassantMove.getInstance();
                addMove(pieces, moves, move, validate, pos, pos - 9);
            }
            return moves;
        }

        @Override
        public Map<Integer, Move> generateNormalMoves(final Piece[] pieces,
                final int pos, final boolean validate) {
            final Map<Integer, Move> moves = new HashMap<Integer, Move>(
                    3);
            if (pos < 31 || pos > 38) {
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
            }
            return moves;
        }

        @Override
        public Map<Integer, Move> generatePromotion(final Piece[] pieces,
                final int pos, final boolean validate) {
            final Map<Integer, Move> moves = new HashMap<Integer, Move>(
                    3);
            if (pos >= 31 && pos <= 39) {
                if (pieces[newPos(9, pos)].getColor() == ChessColor.BLACK) {
                    final Move move = WhitePromotionMove.getInstance();
                    addMove(pieces, moves, move, validate, pos, newPos(9, pos));
                }
                if (pieces[newPos(10, pos)] == DefaultPiece.NULL) {
                    final Move move = WhitePromotionMove.getInstance();
                    addMove(pieces, moves, move, validate, pos, newPos(10, pos));
                }
                if (pieces[newPos(11, pos)].getColor() == ChessColor.BLACK) {
                    final Move move = WhitePromotionMove.getInstance();
                    addMove(pieces, moves, move, validate, pos, newPos(11, pos));
                }
            }
            return moves;
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

        protected int newPos(final int change, final int pos) {
            return pos - change;
        }

    }

    public abstract Map<Integer, Move> generateEnPassant(
            final Piece[] pieces, final int pos, final boolean validate);

    @Override
    public Map<Integer, Move> generateMoves(final Piece[] pieces,
            final int pos, final boolean validate) {
        final Map<Integer, Move> moves = new HashMap<Integer, Move>();
        moves.putAll(generateNormalMoves(pieces, pos, validate));
        moves.putAll(generateEnPassant(pieces, pos, validate));
        moves.putAll(generatePromotion(pieces, pos, validate));
        return moves;
    }

    public abstract Map<Integer, Move> generateNormalMoves(
            Piece[] pieces, int pos, boolean validate);

    public abstract Map<Integer, Move> generatePromotion(
            final Piece[] pieces, final int pos, final boolean validate);
    
    @Override
    public int getPosValue(final int pos) {
        // TODO Auto-generated method stub
        return 0;
    }

}
