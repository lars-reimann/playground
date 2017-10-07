import javax.swing.JButton;
import javax.swing.JTextField;

/**
 * Diese Klasse beinhaltet nur die Methode {@code void main(String[])}.
 * 
 * @author Lars Reimann
 * @version 23. September 2011
 */
public final class HangmanStart {

    /**
     * Der Einstiegspunkt in das Programm. Hier werden alle Instanzen von
     * Klassen erzeugt, die von mehreren Teilen des Programms ben&ouml;tigt
     * werden. Im Anschluss werden sie entsprechen untereinander &uuml;bergeben.
     * 
     * @param args
     *            Der Standardparameter dieser Methode.
     */
    public static void main(final String[] args) {

        // Anzeige
        final JButton nextWord = new JButton("Naechstes Wort");
        final JButton settings = new JButton("Optionen");
        final JButton scores = new JButton("Statistik");
        final JButton test = new JButton("Testen");
        final JTextField textField = new JTextField();
        final HangmanGuyDisplay guy = new HangmanGuyDisplay();
        final HangmanLettersDisplay letters = new HangmanLettersDisplay();
        final HangmanWordDisplay word = new HangmanWordDisplay();
        final HangmanFrame frame = new HangmanFrame(
            new HangmanPanel(
                nextWord,
                settings,
                scores,
                test,
                textField,
                guy,
                letters,
                word
            )
        );

        final HangmanLogic logic = new HangmanLogic(textField, guy, letters,
                                                    word, frame);

        // Kommunikation mit Benutzer
        final HangmanActionListener aL = new HangmanActionListener(logic);
        nextWord.addActionListener(aL);
        settings.addActionListener(aL);
        scores.addActionListener(aL);
        test.addActionListener(aL);

        textField.addKeyListener(new HangmanKeyListener(logic));

        frame.addWindowListener(new HangmanWindowListener(logic));

        // Spiellogik wird gestartet
        logic.setup();

        // Fenster wird sichtbar gemacht
        frame.setVisible(true);
    }
}
