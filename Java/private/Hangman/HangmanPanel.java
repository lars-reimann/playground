import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Dies ist der Inhalt des Frames und stellt somit das Herzst&uuml;ck der
 * Kommunikation mit dem Benutzer dar. Es umfasst ein Hintergrundbild, &uuml;ber
 * welches die anderen Komponenten gezeichnet werden. Diese anderen Komponenten
 * bestehen aus den vier Buttons zum Testen des Buchstaben, zum Voranschreiten
 * zum n&auml;chsten Wort, zum Aufrufen der Statistik und zum Anzeigen eines
 * Dialogs zur Auswahl des Wortfeldes. Dann gibt es drei weitere Panels, nämlich
 * solche f&uuml;r die Anzeige des Wortes, des M&auml;nnchens und der
 * Buchstabenliste. Die letzte Komponente ist das Textfeld zur Eingabe der zu
 * testenden Buchstaben. All diese Komponenten werden manuell angeordnet und
 * ausgerichtet, was durch die Verwendung des null-Layouts errreicht wird.
 * 
 * @author Lars Reimann
 * @version 23. September 2011
 */
public final class HangmanPanel extends JPanel {

    /**
     * Automatisch generierte Versionsnummer f&uuml; Serialisierung.
     */
    private static final long serialVersionUID = -4322809536719837089L;

    /**
     * Das Hintergrundbild.
     */
    private final Image background;

    /**
     * Konstruiert eine neue Instanz dieser Klasse indem die &uuml;bergebenen
     * Dialogelemente im Panel platziert werden. Den Buttons und dem Textfeld
     * werden die passende Gr&ouml;&szlig; und Position sowie einige weitere
     * Dinge zugewiesen, w&auml;hrend sich die selbstdefinierten Dialogelemente
     * selbst um ihrer Ausrichtung und Einstellung k&uuml;mmern.
     * 
     * @param nextWord
     *            Der Button f&uuml;r das n&auml;chste Wort.
     * @param settings
     *            Der Button f&uuml;r die Einstellungen.
     * @param stat
     *            Der Button f&uuml;r die Statistik.
     * @param test
     *            Der Button f&uuml;r das Testen des/der Buchstaben.
     * @param textField
     *            Das Textfeld zur Eingabe.
     * @param guy
     *            Die Spielstandsanzeige.
     * @param letters
     *            Die Buchstabenliste.
     * @param word
     *            Die Wortanzeige.
     */
    public HangmanPanel(JButton nextWord, JButton settings, JButton stat,
                        JButton test, JTextField textField,
                        HangmanGuyDisplay guy, HangmanLettersDisplay letters,
                        HangmanWordDisplay word) {

        // Aufruf des Konstruktors der Vaterklasse
        super();

        // Hintergrundbild
        background = getToolkit().createImage(
                                              "pic" + File.separator +
                                                      "background.png");

        // Einstellungen des Panels
        setLayout(null);
        setLocation(0, 0);
        setPreferredSize(new Dimension(700, 600));

        // Die Anzeige des Wortes
        add(word);

        // Der Button fuer das Testen des Buchstabens
        test.setBorderPainted(false);
        test.setBounds(0, 100, 200, 100);
        test.setContentAreaFilled(false);
        test.setFocusable(false);
        test.setIcon(new ImageIcon("pic" + File.separator + "test.png"));
        test.setPressedIcon(new ImageIcon("pic" + File.separator +
                                          "testSel.png"));
        test.setToolTipText("Teste den aktuellen Buchstaben.");
        add(test);

        // Der Button fuer die Statistik
        stat.setBorderPainted(false);
        stat.setBounds(500, 100, 200, 100);
        stat.setContentAreaFilled(false);
        stat.setFocusable(false);
        stat.setIcon(new ImageIcon("pic" + File.separator + "stat.png"));
        stat.setPressedIcon(new ImageIcon("pic" + File.separator +
                                          "statSel.png"));
        stat.setToolTipText("Zeigt die Statistik an.");
        add(stat);

        // Die Anzeige der Figur
        add(guy);

        // Der Button fuer das naechste Wort
        nextWord.setBorderPainted(false);
        nextWord.setBounds(0, 200, 200, 100);
        nextWord.setContentAreaFilled(false);
        nextWord.setFocusable(false);
        nextWord.setIcon(new ImageIcon("pic" + File.separator + "nextWord.png"));
        nextWord.setPressedIcon(new ImageIcon("pic" + File.separator +
                                              "nextWordPressed.png"));
        nextWord.setToolTipText("Lasse das aktuelle Wort aus und springe zum folgenden. Das begonnene Spiel gilt als verloren!");
        add(nextWord);

        // Der Button fuer Einstellungen
        settings.setBorderPainted(false);
        settings.setBounds(500, 200, 200, 100);
        settings.setContentAreaFilled(false);
        settings.setFocusable(false);
        settings.setIcon(new ImageIcon("pic" + File.separator + "settings.png"));
        settings.setPressedIcon(new ImageIcon("pic" + File.separator +
                                              "settingsSel.png"));
        settings.setToolTipText("Hier kannst du Einstellungen am Spiel vornehmen.");
        add(settings);

        // Das Textfeld zur Eingabe
        textField.setBounds(25, 315, 650, 70);
        textField.setCaretColor(Color.white);
        textField.setFont(new Font("Serif", Font.BOLD, 50));
        textField.setForeground(Color.white);
        textField.setOpaque(false);
        textField.setSelectionColor(Color.white);
        textField.setToolTipText("Gebe hier die Buchstaben ein, die du testen willst.");
        add(textField);

        // Die Buchstabenliste
        add(letters);
    }

    @Override
    public void paintComponent(final Graphics graphics) {
        graphics.drawImage(background, 0, 0, this);
    }
}