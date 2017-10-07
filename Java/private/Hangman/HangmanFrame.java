import java.awt.FlowLayout;
import java.io.File;

import javax.swing.JFrame;

/**
 * Dies ist das Fenster, in welchem die Elemente des Programmes untergebracht
 * werden. Das Fenster tr&auml;gt den Titel "Hangman" und besitzt ein eigenes
 * Icon. Es kann nicht gr&ouml;&szlig;enm&auml;&szlig;ig ver&auml;ndert werden,
 * sondern richtet sich nach dem beinhalteten Panel.
 * 
 * @author Lars Reimann
 * @version 23. September 2011
 */
public final class HangmanFrame extends JFrame {

    /**
     * Automatisch generierte Versionsnummer f&uuml;r Serialisierung.
     */
    private static final long serialVersionUID = -9214827893191863387L;

    /**
     * Konstruiert eine neue Instanz dieser Klasse und zeigt das &uuml;bergebene
     * Panel im Frame an. Die Gr&ouml;&szlig;e des Frames richtet sich nach der
     * gew&uuml;nschten Gr&ouml;&szlig;e des Panel.
     */
    public HangmanFrame(final HangmanPanel panel) {

        // Aufruf des Konstruktors der Vaterklasse
        super("Hangman");

        // Einstellungen des Frames
        setIconImage(getToolkit().createImage("pic" + File.separator +
                                              "logo.png"));
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        setResizable(false);

        // Inhalt des Frames
        getContentPane().add(panel);
        pack();
    }
}