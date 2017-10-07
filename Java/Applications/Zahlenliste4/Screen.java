import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author Lars Reimann
 * @version 11. September 2010
 */
public final class Screen extends Canvas {


    // -----Instanzvariablen---------------------------------------------------

    private Graphics          dbGraphics;

    private Image             dbImage;

    /**
     * Die Liste mit den Zufallszahlen.
     */
    private final int[]       zahlenliste;


    // -----Konstruktoren------------------------------------------------------

    /**
     * Konstruktor fuer Objekte der Klasse MyCanvas. Die Zeichnung erscheint in
     * einer Flaeche von 450 x 220!
     *
     * @param zahlenliste Die Zahlenliste, die angezeigt werden soll!
     */
    public Screen(final int[] zahlenliste) {
        super();
        this.zahlenliste = zahlenliste;
    }


    // ----Instanzmethoden-----------------------------------------------------

    /**
     * Das Balkendiagramm wird gezeichnet und beschriftet.
     *
     * @param graphics Der Grafikkontext dieses Elements.
     */
    public void paint(final Graphics graphics) {
        for (int i = 0; i < zahlenliste.length; i++) {
            graphics.fillOval(i, 250 - zahlenliste[i], 3, 3);
        }
    }

    public void paint(final int index1, final int index2) {
        final Graphics graphics = getGraphics();
        graphics.clearRect(index1, 0, 3, getHeight());
        graphics.clearRect(index2, 0, 3, getHeight());
        for (int i = -1; i < 3; i++) {
            try {
                graphics.fillOval(index1 - i, 250 - zahlenliste[index1 - i],
                                  3, 3);
                graphics.fillOval(index2 - i, 250 - zahlenliste[index2 - i],
                                  3, 3);
            } catch (ArrayIndexOutOfBoundsException exception) {
                // ignoriere Ausnahme
            }
        }

    }

    /**
     * Zeichnet die Balken erneut.
     *
     * @param graphics Der Grafikkontext dieses Elements.
     */
    public void update(final Graphics graphics) {
        if (dbImage == null) {
            dbImage = createImage(getWidth(), getHeight());
            dbGraphics = dbImage.getGraphics();
        }
        dbGraphics.clearRect(0, 0, 753, 253);
        paint(dbGraphics);
        graphics.drawImage(dbImage, 0, 0, this);
    }
}