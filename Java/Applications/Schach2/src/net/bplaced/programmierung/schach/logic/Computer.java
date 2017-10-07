package net.bplaced.programmierung.schach.logic;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class Computer implements Runnable {

    private ChessColor color;
    private Move move;
    private int oldPos;
    private Piece[] pieces;
    private int maxDepth = 4;

    private final Engine engine;
    private int newPos;

    public Computer(final ChessColor color, final Engine engine) {
        this.color = color;
        this.engine = engine;
    }

    private int evaluate(final Piece[] pieces) {
        int fitness = 0;
        ChessColor playerColor = color == ChessColor.WHITE ? ChessColor.BLACK
                : ChessColor.WHITE;
        // TODO Mate und Stalemate methoden nutzen
        final boolean playerIsChecked = Engine.isCheck(pieces, playerColor);
        final boolean AIIsChecked = Engine.isCheck(pieces, color);
        if (!Engine.hasMoves(pieces, playerColor)) {
            if (playerIsChecked) {
                fitness = 1000000;
            } else {
                fitness = -500000;
            }
        } else if (!Engine.hasMoves(pieces, color)) {
            if (AIIsChecked) {
                fitness = -1000000;
            } else {
                fitness = 500000;
            }
        } else {
            if (playerIsChecked) {
                fitness += 50;
            }
            if (AIIsChecked) {
                fitness -= 50;
            }
            // TODO SCHLEIFE VERBESSERN
            for (int i = 21; i <= 98; i++) {
                final Piece piece = pieces[i];
                int fitnessChange = 0;
                fitnessChange += piece.getPieceValue();
                fitnessChange += piece.getPosValue(i);
                if (piece.getColor() != color) {
                    fitnessChange *= -1;
                }
                fitness += fitnessChange;
            }
        }
        return fitness;
    }

    public void computeMove(final Piece[] pieces) {
        this.pieces = pieces;
        final Thread thread = new Thread(this);
        thread.start();
    }

    public int maxMove(final int maxDepth, final int depth, int alpha,
            int beta, final Piece[] pieces) {
        if (depth > 0) {
            // TODO Verbessern
            final UndoData data = new UndoData(pieces, ChessColor.BLACK);
            for (int i = 21; i <= 98; i++) {
                if (pieces[i].getColor() == color) {
                    final Map<Integer, Move> moves = pieces[i].generateMoves(pieces, i, true);
                    final Set<Entry<Integer, Move>> entries = moves.entrySet();
                    for (Entry<Integer, Move> entry : entries) {
                        final int newPos = entry.getKey();
                        final Move move = entry.getValue();
                        final Piece oldPiece = pieces[newPos];
                        move.execute(pieces, i, newPos);
                        final int fitness = minMove(maxDepth, depth - 1, alpha,
                                beta, pieces) + move.getMoveValue();
                        move.undo(pieces, i, newPos, data, oldPiece);
                        if (fitness >= beta) {
                            return beta;
                        }
                        if (fitness > alpha) {
                            alpha = fitness;
                            if (depth == maxDepth) {
                                this.move = move;
                                oldPos = i;
                                this.newPos = newPos;
                            }
                        }
                    }
                }
            }
            return alpha;
        } else {
            return evaluate(pieces);
        }
    }

    public int minMove(final int maxDepth, final int depth, int alpha,
            int beta, final Piece[] pieces) {
        if (depth > 0) {
            // TODO Verbessern
            final UndoData data = new UndoData(pieces, ChessColor.WHITE);
            for (int i = 21; i <= 98; i++) {
                if (pieces[i] != DefaultPiece.NULL
                        && pieces[i] != DefaultPiece.INVALID
                        && pieces[i].getColor() != color) {
                    final Map<Integer, Move> moves = pieces[i].generateMoves(pieces, i, true);
                    final Set<Entry<Integer, Move>> entries = moves.entrySet();
                    for (Entry<Integer, Move> entry : entries) {
                        final int newPos = entry.getKey();
                        final Move move = entry.getValue();
                        final Piece oldPiece = pieces[newPos];
                        move.execute(pieces, i, newPos);
                        final int fitness = maxMove(maxDepth, depth - 1, alpha,
                                beta, pieces) - move.getMoveValue();
                        move.undo(pieces, i, newPos, data, oldPiece);
                        if (fitness <= alpha) {
                            return alpha;
                        }
                        if (fitness < beta) {
                            beta = fitness;
                        }
                    }
                }
            }
            return beta;
        } else {
            return evaluate(pieces);
        }
    }

    @Override
    public void run() {
        for (int i = 1; i <= maxDepth; i++) {
            final long startI = System.nanoTime();
            final int best = maxMove(i, i, Integer.MIN_VALUE,
                    Integer.MAX_VALUE, Arrays.copyOf(pieces, 120));
            if (best == 1000000 || best == -1000000 || best == 500000
                    || best == -500000) {
                System.out.println(move + " Depth: " + i);
                break;
            }
            final long timeI = System.nanoTime() - startI;
            System.out.println(timeI / 1E9);
        }
        move.execute(pieces, oldPos, newPos);
        engine.setPieces(pieces);
    }

    public void setPiece(final Piece[] pieces) {
        this.pieces = Arrays.copyOf(pieces, 120);
    }
}
