 

import java.awt.Point;

public final class Rook extends AbstractFigure {

    private transient boolean hasMoved = false;

    /**
     * Konstruiert einen neuen Turm mit vorgegebener Farbe und Position auf dem
     * Spielfeld.
     * 
     * @param color
     *            Die Farbe der Figur (weiss/schwarz).
     * @param x
     *            Die x-Position der Figur auf dem Schachbrett.
     * @param y
     *            Die y-Position der Figur auf dem Schachbrett.
     */
    public Rook(final int color, final int x, final int y) {
        super();
        this.color = color;
        this.location.x = x;
        this.location.y = y;
    }

    @Override
    public boolean canBeatKing(final Field[][] fields) {
        return canBeatKing(fields, 1, 0) || canBeatKing(fields, -1, 0) ||
               canBeatKing(fields, 0, 1) || canBeatKing(fields, 0, -1);
    }

    private boolean canBeatKing(final Field[][] fields, final int xChange,
                    final int yChange) {
        boolean canBeatKing = false;
        for (int i = 1; location.x + i * xChange >= 0 &&
                        location.x + i * xChange < 8 &&
                        location.y + i * yChange >= 0 &&
                        location.y + i * yChange < 8; i++) {
            final AbstractFigure figure = fields[location.x + i * xChange][location.y +
                                                                           i *
                                                                           yChange]
                            .getFigure();
            if (figure != null) {
                if (figure instanceof King && figure.getColor() != color) {
                    canBeatKing = true;
                }
                break;
            }
        }
        return canBeatKing;
    }

    public boolean hasMoved() {
        return hasMoved;
    }

    public void setHasMoved(final boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    @Override
    public void markMoves(final Field[][] fields) {
        markMoves(fields, 1, 0);
        markMoves(fields, -1, 0);
        markMoves(fields, 0, 1);
        markMoves(fields, 0, -1);
        unmarkMoves(fields);
    }

    public Field[][] simulateMove(final Point end, final Field[][] fields) {
        final Field[][] fieldsCopy = Chessboard.copyFields(fields);
        fieldsCopy[end.x][end.y].setFigure(fieldsCopy[location.x][location.y]
                        .getFigure());
        fieldsCopy[location.x][location.y].setFigure(null);
        fieldsCopy[end.x][end.y].getFigure().setLocation(end.x, end.y);
        ((Rook) fieldsCopy[end.x][end.y].getFigure()).setHasMoved(true);
        return fieldsCopy;
    }

    private void markMoves(final Field[][] fields, final int xChange,
                    final int yChange) {
        for (int i = 1; location.x + i * xChange >= 0 &&
                        location.x + i * xChange < 8 &&
                        location.y + i * yChange >= 0 &&
                        location.y + i * yChange < 8; i++) {
            final Field field = fields[location.x + i * xChange][location.y +
                                                                 i * yChange];
            if (field.getFigure() == null) {
                field.setPossible(true);
            } else {
                if (field.getFigure().getColor() != color) {
                    field.setBeatable(true);
                }
                break;
            }
        }
    }
}
