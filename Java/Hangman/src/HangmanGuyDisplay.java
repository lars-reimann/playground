import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.swing.JPanel;

/**
 * Diese Klasse dient als Anzeiger des aktuellen Spielstandes, indem
 * entsprechend der Fehlerzahl Bilder angezeigt werden. Je voller die Batterie
 * ist, desto mehr Fehler hat man bereits gemacht. Ist die Batterie
 * vollst&auml;ndig gef&uuml;llt, so erleidet das arme Männchen einen
 * schmerzhaften Stromschlag und das Spiel ist verloren. Wenn dies geschieht,
 * wird eine kurze Filmsequenz abgespielt. Wenn man das Wort erraten sollte,
 * wird ebenfalls ein kurze Filmsequenz abgespielt, in der sich die Batterie
 * wieder entl&auml;dt.
 * 
 * @author Lars Reimann
 * @version 23. September 2011
 */
public final class HangmanGuyDisplay extends JPanel implements Runnable {

    /**
     * Automatisch generierte Versionsnummer f&uuml;r Serialisierung.
     */
    private static final long serialVersionUID = 5052804448164100932L;

    /**
     * Die Bilder, die als Information &uuml;ber den aktuellen Spielstand
     * dienen.
     */
    private final Image[] backgrounds;

    /**
     * Die Anzahl der bisher falschen Buchstaben.
     */
    private int errorCount;

    /**
     * Gibt an, ob das Spiel gewonnen ist (true) oder verloren (false).
     */
    private boolean isWon;

    /**
     * Konstruiert eine neue Instanz dieser Klasse. Das Array der
     * Hintergrundbilder wird bef&uuml;llt, indem die betreffenden Bilddateien
     * ausgelesen werden. Anschlie&szlig;end wird die gew&uuml;nschte
     * Gr&ouml;&szlig;e des Panels auf 300x200 Pixel festgelegt.
     */
    public HangmanGuyDisplay() {
        super();

        backgrounds = new Image[35];
        for (int i = 0; i <= 34; i++) {
            backgrounds[i] = getToolkit().createImage(
                                                      "pic" + File.separator +
                                                              "guy" + i +
                                                              ".png");
        }

        setBounds(200, 100, 300, 200);
        setFocusable(false);

    }

    @Override
    public void paint(final Graphics graphics) {
        graphics.drawImage(backgrounds[errorCount], 0, 0, this);
    }

    @Override
    public void run() {
        if (isWon) {
            for (; errorCount >= 0; errorCount--) {
                repaint();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }
        } else {
            for (; errorCount <= 34; errorCount++) {
                repaint();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }
            for (errorCount = 34; errorCount >= 0; errorCount--) {
                repaint();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    /**
     * Setzt die Anzahl der bisher gemachten Fehler, also der falschen
     * Buchstaben auf den &uuml;bergebenen Wert. Anhand dieses Wertes wird dann
     * das passende Bild gezeichnet, welches den Spieler &uuml;ber den aktuellen
     * Spielstand informiert.
     * 
     * @param errorCount
     *            Die neue Anzahl der bisher gemachten Fehler.
     */
    public void setErrorCount(final int errorCount) {
        this.errorCount = errorCount;
    }

    /**
     * Setzt die Flag, die zeigt ob das Spiel gewonnen oder verloren wurde, auf
     * den &uuml;bergebenen Wert.
     * 
     * @param isWon
     *            Gibt an, ob das Spiel gewonnen ist (true) oder verloren
     *            (false).
     */
    public void setWon(final boolean isWon) {
        this.isWon = isWon;
    }
}
