 

import java.awt.Point;

public final class King extends AbstractFigure {

    private transient boolean hasMoved = false;

    /**
     * Konstruiert einen neuen Koenig mit vorgegebener Farbe und Position auf
     * dem Spielfeld.
     * 
     * @param color
     *            Die Farbe der Figur (weiss/schwarz).
     * @param x
     *            Die x-Position der Figur auf dem Schachbrett.
     * @param y
     *            Die y-Position der Figur auf dem Schachbrett.
     */
    public King(final int color, final int x, final int y) {
        super();
        this.color = color;
        this.location.x = x;
        this.location.y = y;
    }

    @Override
    public boolean canBeatKing(final Field[][] fieldsCopy) {
        return canBeatKing(fieldsCopy, location.x, location.y - 1) ||
               canBeatKing(fieldsCopy, location.x + 1, location.y - 1) ||
               canBeatKing(fieldsCopy, location.x + 1, location.y) ||
               canBeatKing(fieldsCopy, location.x + 1, location.y + 1) ||
               canBeatKing(fieldsCopy, location.x, location.y + 1) ||
               canBeatKing(fieldsCopy, location.x - 1, location.y + 1) ||
               canBeatKing(fieldsCopy, location.x - 1, location.y) ||
               canBeatKing(fieldsCopy, location.x - 1, location.y - 1);
    }

    public boolean hasMoved() {
        return hasMoved;
    }

    @Override
    public void markMoves(final Field[][] fields) {
        markMove(fields, location.x, location.y - 1);
        markMove(fields, location.x + 1, location.y - 1);
        markMove(fields, location.x + 1, location.y);
        markMove(fields, location.x + 1, location.y + 1);
        markMove(fields, location.x, location.y + 1);
        markMove(fields, location.x - 1, location.y + 1);
        markMove(fields, location.x - 1, location.y);
        markMove(fields, location.x - 1, location.y - 1);
        unmarkMoves(fields);

        if (!hasMoved && fields[0][location.y].getFigure() instanceof Rook &&
            !((Rook) fields[0][location.y].getFigure()).hasMoved() &&
            fields[1][location.y].getFigure() == null &&
            fields[2][location.y].getFigure() == null &&
            fields[3][location.y].getFigure() == null) {
            fields[2][location.y].setPossible(true);
        }
        if (!hasMoved && fields[7][location.y].getFigure() instanceof Rook &&
            !((Rook) fields[7][location.y].getFigure()).hasMoved() &&
            fields[5][location.y].getFigure() == null &&
            fields[6][location.y].getFigure() == null) {
            fields[6][location.y].setPossible(true);
        }
        unmarkCastling(fields);
    }

    public void setHasMoved(final boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    public Field[][] simulateMove(final Point end, final Field[][] fields) {
        final Field[][] fieldsCopy = Chessboard.copyFields(fields);
        if (Math.abs(end.x - location.x) == 2) {
            if (end.x == 2) {
                fields[3][end.y].setFigure(fields[0][end.y].getFigure());
                fields[0][end.y].setFigure(null);
                fields[3][end.y].getFigure().setLocation(3, end.y);
            } else {
                fields[5][end.y].setFigure(fields[7][end.y].getFigure());
                fields[7][end.y].setFigure(null);
                fields[5][end.y].getFigure().setLocation(5, end.y);
            }
        }
        fieldsCopy[end.x][end.y].setFigure(fieldsCopy[location.x][location.y]
                        .getFigure());
        fieldsCopy[location.x][location.y].setFigure(null);
        fieldsCopy[end.x][end.y].getFigure().setLocation(end.x, end.y);
        ((King) fieldsCopy[end.x][end.y].getFigure()).setHasMoved(true); 
        return fieldsCopy;
    }

    private void markMove(final Field[][] fields, final int x, final int y) {
        if (x >= 0 && x < 8 && y >= 0 && y < 8) {
            if (fields[x][y].getFigure() == null) {
                fields[x][y].setPossible(true);
            } else {
                if (fields[x][y].getFigure().getColor() != color) {
                    fields[x][y].setBeatable(true);
                }
            }
        }
    }

    private boolean canBeatKing(final Field[][] fieldsCopy, final int x,
                    final int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8 &&
               fieldsCopy[x][y].getFigure() instanceof King &&
               fieldsCopy[x][y].getFigure().getColor() != color;
    }

    private void unmarkCastling(final Field[][] fields) {
        if (!hasMoved) {
            Field[][] fieldsCopy = Chessboard.copyFields(fields);
            if (Chessboard.isCheck(color, fieldsCopy)) {
                fields[2][location.y].setPossible(false);
                fields[6][location.y].setPossible(false);
            } else if (fields[2][location.y].isPossible()) {
                for (int i = 1; i <= 2; i++) {
                    fieldsCopy[4 - i][location.y]
                                    .setFigure(fieldsCopy[4][location.y]
                                                    .getFigure());
                    fieldsCopy[4][location.y].setFigure(null);
                    fieldsCopy[4 - i][location.y].getFigure()
                                    .setLocation(4 - i, location.y);
                    if (Chessboard.isCheck(color, fieldsCopy)) {
                        fields[2][location.y].setPossible(false);
                        break;
                    } else {
                        fieldsCopy = Chessboard.copyFields(fields);
                    }
                }
            } else if (fields[6][location.y].isPossible()) {
                for (int i = 1; i <= 2; i++) {
                    fieldsCopy[4 + i][location.y]
                                    .setFigure(fieldsCopy[4][location.y]
                                                    .getFigure());
                    fieldsCopy[4][location.y].setFigure(null);
                    fieldsCopy[4 + i][location.y].getFigure()
                                    .setLocation(4 + i, location.y);
                    if (Chessboard.isCheck(color, fieldsCopy)) {
                        fields[6][location.y].setPossible(false);
                        break;
                    } else {
                        fieldsCopy = Chessboard.copyFields(fields);
                    }
                }
            }
        }
    }
}
