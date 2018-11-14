 

import java.awt.Point;

public abstract class AbstractFigure implements Cloneable {

    public static Field[][]            fields;

    public transient int               color;
    public transient Point             location = new Point();
    public static transient Computer   computer;
    public transient static Chessboard chessboard;

    public static final void setFields(final Field[][] fields) {
        AbstractFigure.fields = fields;
    }

    public static void setComputer(final Computer computer) {
        AbstractFigure.computer = computer;
    }

    /**
     * 
     * @param fieldsCopy
     * @return
     */
    public abstract boolean canBeatKing(final Field[][] fieldsCopy);

    @Override
    public final Object clone() throws CloneNotSupportedException {
        final AbstractFigure figureCopy = (AbstractFigure) super.clone();
        figureCopy.location = (Point) location.clone();
        return figureCopy;
    }

    /**
     * Gibt die Farbe der Figur (schwarz/weiss) zurueck.
     * 
     * @return Die Farbe der Figur.
     */
    public final int getColor() {
        return color;
    }

    /**
     * Gibt die Position der Figur auf dem Feld zurueck. Die Position besteht
     * aus einer x- und einer y-Komponenten, die beide einen Wert zwischen 0 und
     * 7 haben.
     * 
     * @return Die Position der Figur.
     */
    public final Point getLocation() {
        return location;
    }

    /**
     * Markiert alle moeglichen Zuege, das heisst alle Felder, auf die die Figur
     * ziehen kann und alle Figuren, die die Figur schlagen kann. Hier wird noch
     * nicht getestet, ob der Zug illegal ist, da der Koenig anschliessend im
     * Schach steht; dies geschieht erst spaeter in der Methode unmarkMoves.
     */
    public abstract void markMoves(final Field[][] fields);

    public final void setLocation(final int x, final int y) {
        if (x >= 0 && x < 8 && y >= 0 && y < 8) {
            location.x = x;
            location.y = y;
        } else {
            throw new IllegalArgumentException(
                            "Die neue Position ist nicht innerhalb der definierten Grenzen.");
        }
    }

    /**
     * Hier wird schliesslich getestet, ob der Koenig nach dem Zug im Schach
     * stehen wuerde, was den Zug regelwidrig machen wuerde. Falls dies so ist,
     * wird die Markierung wieder entfernt.
     */
    public void unmarkMoves(final Field[][] fields) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (fields[i][j].isPossible() || fields[i][j].isBeatable()) {
                    final Field[][] fieldsCopy = Chessboard.copyFields(fields);
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

    public static void setChessboard(final Chessboard chessboard) {
        AbstractFigure.chessboard = chessboard;
    }

    public Field[][] simulateMove(final Point end, final Field[][] fields) {
        final Field[][] fieldsCopy = Chessboard.copyFields(fields);
        fieldsCopy[end.x][end.y].setFigure(fieldsCopy[location.x][location.y]
                        .getFigure());
        fieldsCopy[location.x][location.y].setFigure(null);
        fieldsCopy[end.x][end.y].getFigure().setLocation(end.x, end.y);
        if (fieldsCopy[end.x][end.y].getFigure() instanceof King) {
            ((King) fieldsCopy[end.x][end.y].getFigure()).setHasMoved(true);
        } else if (fieldsCopy[end.x][end.y].getFigure() instanceof Rook) {
            ((Rook) fieldsCopy[end.x][end.y].getFigure()).setHasMoved(true);
        }
        return fieldsCopy;
    }
}
