import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.swing.JPanel;

/**
 * Zeigt grafisch an, welche Buchstaben bereits verwendet wurden und welche noch
 * frei sind. Wei&szlig; werden diejenigen angezeigt, die bereits gepr&uuml;ft
 * worden sind und schwarz die restlichen. Diese verschiedenfarbigen Buchstaben
 * werden auf ein Hintergrundbild gezeichnet.
 * 
 * @author Lars Reimann
 * @version 23. September 2011
 */
public final class HangmanLettersDisplay extends JPanel {

    /**
     * Automatisch generierte Versionsnummer f&uuml;r Serialisierung.
     */
    private static final long serialVersionUID = -8481519393844879918L;

    /**
     * Das Hintergrundbild.
     */
    private final Image background;

    /**
     * Das Array von Wahrheitswerten, die angibt, welche Buchstaben schon
     * verwendet wurden (false = noch nicht verwendet; true = schon benutzt).
     */
    private boolean[] letters;

    /**
     * L&auml;dt das Hintergrundbild in die daf&uuml;r vorgesehene Variable und
     * initialisiert das Array von Wahrheitswerten, welches die Buchstabenliste
     * repr&auml;sentiert. Au&szlig;erdem werden die Grenzen zur Anzeige im
     * Panel und ein Tooltip gesetzt.
     */
    public HangmanLettersDisplay() {
        background = getToolkit().createImage(
                                              "pic" + File.separator +
                                                      "letters.png");
        letters = new boolean[26];

        setBounds(0, 400, 700, 200);
        setFocusable(false);
        setToolTipText("Diese Tabelle gibt an, welche Buchstaben schon verwendet wurden.");
    }

    /**
     * Setzt die Buchstabenliste auf den &uuml;bergebenen Wert.
     * 
     * @param letters
     *            Das Array von Wahrheitswerten, die angibt, welche Buchstaben
     *            schon verwendet wurden (false = noch nicht verwendet; true =
     *            schon benutzt).
     */
    public void setLetters(final boolean[] letters) {
        this.letters = letters;
    }

    @Override
    public void paintComponent(final Graphics graphics) {
        graphics.drawImage(background, 0, 0, this);
        graphics.setFont(new Font("Serif", Font.BOLD, 50));
        for (int i = 0; i < 26; i++) {
            if (letters[i]) {
                graphics.setColor(Color.white);
            } else {
                graphics.setColor(Color.black);
            }
            graphics.drawString(Character.toString((char) ('A' + i)),
                                20 + (i % 13) * 50, 75 * (1 + i / 13));
        }
    }
}
