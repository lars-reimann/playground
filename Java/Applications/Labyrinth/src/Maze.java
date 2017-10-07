import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class Maze extends JPanel {


    // ----Klassenvariablen----------------------------------------------------
    private static final int CHECKPOINT = 4;

    private static final int EMPTY = 0;

    private static final int RIGHT = 2;

    private static final int WALL = 1;

    private static final int WRONG = 3;


    private Graphics dbGraphics;


    private Image dbImage;

    private int dimension;

    /**
     * In diesem Array werden die Zustaende der einzelnen Feldergespeichert:
     * <br>
     * 0: freies Feld<br>
     * 1: Wand<br>
     * 2: besuchtes Feld, das zur Verbindung gehoert<br>
     * 3: besuchtes Feld, das nicht zur Verbindung gehoert<br>
     * 4: Startfeld / Zielfeld<br>
     */
    private byte[][] field;

    public Maze(final int dimension) {
        this.dimension = dimension;
        field = new byte[dimension][dimension];
        setSize(500, 500);
        createRandomPoints();
    }

    /**
     * Erzeugt ein neues Labyrinth.
     */
    public void createRandomPoints() {
       for (int i = 0; i < dimension; i++) {
           for (int j = 0; j < dimension; j++) {
               field[i][j] = EMPTY;
           }
       }
       for (int i = 0; i < dimension; i++) {
           for (int j = 0; j < dimension; j++) {
               if (Math.random() < 0.25) {
                   field[i][j] = WALL;
               }
           }
       }
       field[0][0] = CHECKPOINT;
       field[dimension - 1][dimension - 1] = CHECKPOINT;
    }

    /**
     * Findet die Verbindung zwischen den beiden Startpunkten durch ein
     * rekursives Verfahren.
     *
     * @param xPos x-Position des ersten Punktes.
     * @param yPos y-Position des zweiten Punktes.
     */
    public boolean findConnection(final int xPos, final int yPos) {
        if (xPos < 0 || xPos > dimension - 1 || yPos < 0 || yPos > dimension - 1) {
            return false;
        } else if (field[xPos][yPos] != EMPTY && field[xPos][yPos] != CHECKPOINT) {
            return false;
        }
        field[xPos][yPos] = RIGHT;
        update(getGraphics());
        sleep(1000 / dimension / dimension);
        if (xPos == dimension - 1 && yPos == dimension - 1) {
            return true;
        } else if (findConnection(xPos + 1, yPos)) {
            return true;
        } else if (findConnection(xPos, yPos + 1)) {
            return true;
        } else if (findConnection(xPos - 1, yPos)) {
            return true;
        } else if (findConnection(xPos, yPos - 1)) {
            return true;
        }
        field[xPos][yPos] = WRONG;
        update(getGraphics());
        sleep(1000 / dimension / dimension);
        return false;
    }

    private void sleep(final int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException exception) {
            // ignoriere
        }
    }

    /**
     * {@inheritDoc}
     */
    public void paint(final Graphics graphics) {

        // Felder
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (field[i][j] == EMPTY) {
                    graphics.setColor(Color.white);
                } else if (field[i][j] == WALL) {
                    graphics.setColor(Color.black);
                } else if (field[i][j] == RIGHT) {
                    graphics.setColor(Color.green);
                } else if (field[i][j] == WRONG) {
                    graphics.setColor(Color.red);
                } else if (field[i][j] == CHECKPOINT) {
                    graphics.setColor(Color.blue);
                }
                graphics.fillRect(
                    i * 400 / dimension,
                    j * 400 / dimension,
                    400 / dimension + 1,
                    400 / dimension + 1
                );
            }
        }
    }

    public void setDimension(final int dimension) {
        this.dimension = dimension;
        field = new byte[dimension][dimension];
        createRandomPoints();
        update(getGraphics());
    }

    /**
     * {@inheritDoc}
     */
    public void update(final Graphics graphics) {
        if (dbImage == null) {
            dbImage = createImage(getSize().width, getSize().height);
        }
        dbGraphics = dbImage.getGraphics();
        dbGraphics.clearRect(0, 0, getSize().width, getSize().height);
        paint(dbGraphics);
        graphics.drawImage(dbImage, 0, 0, this);
    }
}
