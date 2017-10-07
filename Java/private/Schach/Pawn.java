 

import java.awt.Point;

public final class Pawn extends AbstractFigure {

    private transient boolean hasMovedTwice = false;

    /**
     * Konstruiert einen neuen Bauer mit vorgegebener Farbe und Position auf dem
     * Spielfeld.
     * 
     * @param color
     *            Die Farbe der Figur (weiss/schwarz).
     * @param x
     *            Die x-Position der Figur auf dem Schachbrett.
     * @param y
     *            Die y-Position der Figur auf dem Schachbrett.
     */
    public Pawn(final int color, final int x, final int y) {
        super();
        this.color = color;
        this.location.x = x;
        this.location.y = y;
    }

    @Override
    public boolean canBeatKing(final Field[][] fieldsCopy) {
        if (location.x + 1 < 8) {
            final Field field = fieldsCopy[location.x + 1][location.y + color];
            if (field.getFigure() != null) {
                final AbstractFigure figure = field.getFigure();
                if (figure instanceof King && figure.getColor() != color) {
                    return true;
                }
            }
        }
        if (location.x - 1 >= 0) {
            final Field field = fieldsCopy[location.x - 1][location.y + color];
            if (field.getFigure() != null) {
                final AbstractFigure figure = field.getFigure();
                if (figure instanceof King && figure.getColor() != color) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasMovedTwice() {
        return hasMovedTwice;
    }

    @Override
    public void markMoves(final Field[][] fields) {
        markPossibleMoves(fields);
        markBeatableFigures(fields);
        unmarkMoves(fields);
    }

    public void setHasMovedTwice(final boolean hasMovedTwice) {
        this.hasMovedTwice = hasMovedTwice;
    }

    @Override
    public void unmarkMoves(final Field[][] fields) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (fields[i][j].isPossible() || fields[i][j].isBeatable()) {
                    final Field[][] fieldsCopy = Chessboard.copyFields(fields);
                    if (Math.abs(location.x - i) == 1 &&
                        fieldsCopy[i][j] == null) {
                        fieldsCopy[location.x][location.y -
                                               fieldsCopy[location.x][location.y]
                                                               .getFigure()
                                                               .getColor()]
                                        .setFigure(null);
                    }
                    fieldsCopy[i][j].setFigure(fieldsCopy[location.x][location.y]
                                    .getFigure());
                    fieldsCopy[location.x][location.y].setFigure(null);
                    fieldsCopy[i][j].getFigure().setLocation(i, j);
                    if (Chessboard.isCheck(color, fieldsCopy)) {
                        fields[i][j].setBeatable(false);
                        fields[i][j].setPossible(false);
                    }
                }
            }
        }
    }

    public Field[][] simulateMove(final Point end, final Field[][] fields) {
        final Field[][] fieldsCopy = Chessboard.copyFields(fields);
        if (Math.abs(location.x - end.x) == 1 &&
            fieldsCopy[end.x][end.y] == null) {
            fieldsCopy[location.x][location.y -
                                   fieldsCopy[location.x][location.y]
                                                   .getFigure().getColor()]
                            .setFigure(null);
        }
        fieldsCopy[end.x][end.y].setFigure(fieldsCopy[location.x][location.y]
                        .getFigure());
        fieldsCopy[location.x][location.y].setFigure(null);
        fieldsCopy[end.x][end.y].getFigure().setLocation(end.x, end.y);
        if (Math.abs(location.y - end.y) == 2) {
            hasMovedTwice = true;
        }
        if (end.y == 0 || end.y == 7) {
            fieldsCopy[end.x][end.y].setFigure(new Queen(color,
                            end.x, end.y));
        }
        return fieldsCopy;
    }

    private void markBeatableFigures(final Field[][] fields) {

        // Figur schraeg rechts
        if (location.x + 1 < 8) {
            final Field field = fields[location.x + 1][location.y + color];
            if (field.getFigure() != null) {
                final AbstractFigure figure = field.getFigure();
                if (figure.getColor() != color) {
                    field.setBeatable(true);
                }
            }
        }

        // Figur schraeg links
        if (location.x - 1 >= 0) {
            final Field field = fields[location.x - 1][location.y + color];
            if (field.getFigure() != null) {
                final AbstractFigure figure = field.getFigure();
                if (figure.getColor() != color) {
                    field.setBeatable(true);
                }
            }
        }

        // En passant schraeg rechts
        if (location.x + 1 < 8 && location.y == (7 - 3 * color) % 7) {
            final Field field = fields[location.x + 1][location.y];
            if (field.getFigure() instanceof Pawn) {
                final Pawn pawn = (Pawn) field.getFigure();
                if (pawn.hasMovedTwice && pawn.getColor() != color) {
                    fields[location.x + 1][(7 - 2 * color) % 7]
                                    .setBeatable(true);
                }
            }
        }

        // En passant schraeg links
        if (location.x - 1 >= 0 && location.y == (7 - 3 * color) % 7) {
            final Field field = fields[location.x - 1][location.y];
            if (field.getFigure() instanceof Pawn) {
                final Pawn pawn = (Pawn) field.getFigure();
                if (pawn.hasMovedTwice && pawn.getColor() != color) {
                    fields[location.x - 1][(7 - 2 * color) % 7]
                                    .setBeatable(true);
                }
            }
        }
    }

    private void markPossibleMoves(final Field[][] fields) {
        final Field field1 = fields[location.x][location.y + color];
        if (field1.getFigure() == null) {
            field1.setPossible(true);
            if (location.y == (7 + color) % 7) {
                final Field field2 = fields[location.x][location.y + 2 * color];
                if (field2.getFigure() == null) {
                    field2.setPossible(true);
                }
            }
        }
    }
}
