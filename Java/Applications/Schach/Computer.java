 

import java.awt.Point;

public final class Computer implements Runnable {

    private transient int              color;
    private transient Field[][]        fields;
    private transient final int        maxDepth            = 4;
    private transient Point            start;
    private transient Point            end;
    private final transient int[][]    positionValueBishop = {
                    {0, 0, 2, 4, 4, 2, 0, 0}, {0, 8, 6, 8, 8, 6, 8, 0},
                    {2, 6, 12, 10, 10, 12, 6, 2}, {4, 8, 10, 16, 16, 10, 8, 4},
                    {4, 8, 10, 16, 16, 10, 8, 4}, {2, 6, 12, 10, 10, 12, 6, 2},
                    {0, 8, 6, 8, 8, 6, 8, 0},
                    {-10, -10, -8, -6, -6, -8, -10, -10}   };

    private final transient int[][]    positionValueQueen  = {
                    {0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 4, 4, 4, 4, 0, 0},
                    {0, 4, 4, 6, 6, 4, 4, 0}, {0, 4, 6, 8, 8, 6, 4, 0},
                    {0, 4, 6, 8, 8, 6, 4, 0}, {0, 4, 4, 6, 6, 4, 4, 0},
                    {0, 0, 4, 4, 4, 4, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0}};
    
    private final transient int[][]    positionValuePawn  = {
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {66, 66, 66, 66, 66, 66, 66, 66},
                    {10, 10, 10, 30, 30, 10, 10, 10},
                    {6, 6, 6, 16, 16, 6, 6, 6},
                    {3, 3, 3, 13, 13, 3, 3, 3},
                    {1, 1, 1, 10, 10, 1, 1, 1},
                    {0, 0, 0, -12, -12, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0}};

    private final transient Chessboard chessboard;

    public Computer(final Chessboard chessboard) {
        this.chessboard = chessboard;
    }

    public void setColor(final int color) {
        this.color = color;
    }

    public void setFields(final Field[][] fields) {
        this.fields = fields;
    }

    public void moveFigure() {
        Thread thread = new Thread(this);
        thread.start();
    }

    public void run() {
        maxMove(maxDepth, Integer.MIN_VALUE, Integer.MAX_VALUE, Chessboard.copyFields(fields));
        chessboard.clickOnField(start.x, start.y);
        chessboard.clickOnField(end.x, end.y);
    }

    public int getColor() {
        return color;
    }

    public int maxMove(final int depth, int alpha, int beta, final Field[][] fields) {
        if (depth > 0) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (fields[i][j].getFigure() != null &&
                        fields[i][j].getFigure().getColor() == color) {
                        for (int k = 0; k < 8; k++) {
                            for (int l = 0; l < 8; l++) {
                                fields[k][l].resetSelections();
                            }
                        }
                        fields[i][j].getFigure().markMoves(fields);
                        for (int k = 0; k < 7; k++) {
                            for (int l = 0; l < 7; l++) {
                                if (fields[k][l].isBeatable() ||
                                    fields[k][l].isPossible()) {
                                    final Field[][] fieldsCopy = fields[i][j]
                                                    .getFigure()
                                                    .simulateMove(new Point(k,
                                                                                  l),
                                                                  fields);
                                    final int fitness = minMove(depth - 1, alpha, beta,
                                                                fieldsCopy);
                                    if (fitness >= beta) {
                                        return beta;
                                    }
                                    if (fitness > alpha) {
                                        alpha = fitness;
                                        if (depth == maxDepth) {
                                            start = new Point(i, j);
                                            end = new Point(k, l);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (alpha == Integer.MIN_VALUE) {
                return computeFitness(fields);
            } else {
                return alpha;
            }
        } else {
            return computeFitness(fields);
        }
    }

    public int minMove(final int depth, int alpha, int beta, final Field[][] fields) {
        if (depth > 0) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (fields[i][j].getFigure() != null &&
                        fields[i][j].getFigure().getColor() != color) {
                        for (int k = 0; k < 8; k++) {
                            for (int l = 0; l < 8; l++) {
                                fields[k][l].resetSelections();
                            }
                        }
                        fields[i][j].getFigure().markMoves(fields);
                        for (int k = 0; k < 7; k++) {
                            for (int l = 0; l < 7; l++) {
                                if (fields[k][l].isBeatable() ||
                                    fields[k][l].isPossible()) {
                                    final Field[][] fieldsCopy = fields[i][j]
                                                    .getFigure()
                                                    .simulateMove(new Point(k,
                                                                                  l),
                                                                  fields);
                                    final int fitness = maxMove(depth - 1, alpha, beta,
                                                                fieldsCopy);
                                    if (fitness <= alpha) {
                                        return alpha;
                                    }
                                    if (fitness < beta) {
                                        beta = fitness;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (beta == Integer.MAX_VALUE) {
                return computeFitness(fields);
            } else {
                return beta;
            }
        } else {
            return computeFitness(fields);
        }
    }

    private int computeFitness(final Field[][] fields) {
        int fitness = 0;
        int playerColor = color == ChessConstants.WHITE ? ChessConstants.BLACK
                        : ChessConstants.WHITE;
        if (!Chessboard.hasPossibleMoves(playerColor, fields)) {
            if (Chessboard.isCheck(playerColor, fields)) {
                fitness = 1000000;
            } else {
                fitness = -500000;
            }
        } else if (!Chessboard.hasPossibleMoves(color, fields)) {
            if (Chessboard.isCheck(color, fields)) {
                fitness = -1000000;
            } else {
                fitness = 500000;
            }
        } else {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    final AbstractFigure figure = fields[i][j].getFigure();
                    int fitnessChange = 0;
                    if (figure != null) {
                        if (figure instanceof Bishop) {
                            fitnessChange += 325;
                            if (figure.getColor() == ChessConstants.WHITE) {
                                fitnessChange += positionValueBishop[i][j];
                            } else {
                                fitnessChange += positionValueBishop[7 - i][7 - j];
                            }
                        } else if (figure instanceof Knight) {
                            fitnessChange += 325;
                        } else if (figure instanceof Pawn) {
                            fitnessChange += 100;
                            if (figure.getColor() == ChessConstants.WHITE) {
                                fitnessChange += positionValuePawn[i][j];
                            } else {
                                fitnessChange += positionValuePawn[7 - i][7 - j];
                            }
                        } else if (figure instanceof Queen) {
                            fitnessChange += 1050 + positionValueQueen[i][j];
                        } else if (figure instanceof Rook) {
                            fitnessChange += 500;
                        }
                        if (figure.getColor() != color) {
                            fitnessChange *= -1;
                        }
                    }

                    fitness += fitnessChange;
                }
            }
        }
        return fitness;
    }
}
