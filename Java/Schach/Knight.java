 

/**
 * 
 * @author Lars Reimann
 * @version
 */
public final class Knight extends AbstractFigure {

    /**
     * Konstruiert einen neuen Springer mit vorgegebener Farbe und Position auf
     * dem Spielfeld.
     * 
     * @param color
     *            Die Farbe der Figur (weiss/schwarz).
     * @param x
     *            Die x-Position der Figur auf dem Schachbrett.
     * @param y
     *            Die y-Position der Figur auf dem Schachbrett.
     */
    public Knight(final int color, final int x, final int y) {
        super();
        this.color = color;
        this.location.x = x;
        this.location.y = y;
    }

    @Override
    public boolean canBeatKing(final Field[][] fieldsCopy) {
        return canBeatKing(fieldsCopy, location.x - 1, location.y - 2) ||
               canBeatKing(fieldsCopy, location.x + 1, location.y - 2) ||
               canBeatKing(fieldsCopy, location.x + 2, location.y - 1) ||
               canBeatKing(fieldsCopy, location.x + 2, location.y + 1) ||
               canBeatKing(fieldsCopy, location.x + 1, location.y + 2) ||
               canBeatKing(fieldsCopy, location.x - 1, location.y + 2) ||
               canBeatKing(fieldsCopy, location.x - 2, location.y + 1) ||
               canBeatKing(fieldsCopy, location.x - 2, location.y - 1);
    }

    @Override
    public void markMoves(final Field[][] fields) {
        markMove(fields, location.x - 1, location.y - 2);
        markMove(fields, location.x + 1, location.y - 2);
        markMove(fields, location.x + 2, location.y - 1);
        markMove(fields, location.x + 2, location.y + 1);
        markMove(fields, location.x + 1, location.y + 2);
        markMove(fields, location.x - 1, location.y + 2);
        markMove(fields, location.x - 2, location.y + 1);
        markMove(fields, location.x - 2, location.y - 1);
        unmarkMoves(fields);
    }

    /**
     * 
     * @param x
     * @param y
     */
    private void markMove(final Field[][] fields, final int x, final int y) {
        if (x >= 0 && x < 8 && y >= 0 && y < 8) {
            final Field field = fields[x][y];
            if (field.getFigure() == null) {
                field.setPossible(true);
            } else {
                if (field.getFigure().getColor() != color) {
                    field.setBeatable(true);
                }
            }
        }
    }

    /**
     * 
     * @param fields
     * @param x
     * @param y
     * @return
     */
    private boolean canBeatKing(final Field[][] fields, final int x, final int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8 &&
               fields[x][y].getFigure() instanceof King &&
               fields[x][y].getFigure().getColor() != color;
    }
}
