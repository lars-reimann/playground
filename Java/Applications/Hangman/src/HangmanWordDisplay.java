import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.swing.JPanel;

/**
 * Die Instanz dieser Klasse ist f&uuml; das Anzeigen des zu findenden Wortes
 * zust&auml;ndig. Das Panel besteht aus einem Hintergrundbild, auf welches dann
 * das Wort oder Teile des Wortes gemalt werden.
 * 
 * @author Lars Reimann
 * @version 23. September 2011
 */
public final class HangmanWordDisplay extends JPanel {

    /**
     * Automatisch generierte Versionsnummer f&uuml;r Serialisierung.
     */
    private static final long serialVersionUID = 2075659382642215895L;

    /**
     * Der Hintergrund.
     */
    private final Image background;

    /**
     * Das angezeigte Wort.
     */
    private String shownWord;

    /**
     * L&auml;dt das Hintergrundbild und initialisiert das angezeigte Wort mit
     * einem leeren String. Des Weiteren werden die Grenzen f&uuml;r die Anzeige
     * im Panel gesetzt und ein ToolTip hinzugef&uuml;gt.
     */
    public HangmanWordDisplay() {
        background = getToolkit().createImage(
                                              "pic" + File.separator +
                                                      "input.png");
        shownWord = "";

        setBounds(0, 0, 700, 100);
        setFocusable(false);
        setToolTipText("Hier steht das zu erratende Wort.");
    }

    @Override
    public void paintComponent(final Graphics graphics) {
        graphics.drawImage(background, 0, 0, this);
        graphics.setFont(new Font("Serif", Font.BOLD, 30));
        graphics.drawString(shownWord, 350 - shownWord.length() * 12, 60);
    }

    /**
     * Setzt das angezeigte Wort auf den &uuml;bergebenen Wert.
     * 
     * @param shownWord
     *            Das anzuzeigende Wort.
     */
    public void setShownWord(final String shownWord) {
        this.shownWord = shownWord;
    }
}
