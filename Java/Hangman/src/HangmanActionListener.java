import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Dies ist die Implementierung des ActionListeners, der auf Buttonklicks
 * reagiert. So bewirkt ein Klick auf den Button "Testen" das
 * &Uuml;berpr&uuml;fen der eingegeben Buchstaben. Nach Dr&uuml;cken auf den
 * Button "N&auml;chstes Wort" eine Meldung angezeigt, die man bestätigen muss.
 * Wird dies getan, so wird das aktuelle Spiel als verloren gewertet und ein
 * neues Wort wird zuf&auml;llig gew&auml;hlt und angezeigt. Klickt man auf
 * Statistik, so werden die bisherigen Spielergebnisse (gewonnen/verloren,
 * richtige/falsche Buchstaben) angezeigt. Der Button Optionen zeigt
 * schlie&szlig;ich einen Dialog an, in dem der Benutzer das aktuelle Wortfeld
 * &auml;ndern kann.
 * 
 * @author Lars Reimann
 * @version 23. September 2011
 */
public final class HangmanActionListener implements ActionListener {

    /**
     * Die Instanz der Spiellogik.
     */
    private final HangmanLogic logic;

    /**
     * Konstruiert eine neue Instanz dieser Klasse und weist der Variablen, die
     * die Spiellogik enth&auml;lt, den &uuml;bergebenen Parameter zu.
     * 
     * @param logic
     *            Die Instanz der Spiellogik.
     */
    public HangmanActionListener(final HangmanLogic logic) {
        this.logic = logic;
    }

    @Override
    public void actionPerformed(final ActionEvent event) {
        final String actionCommand = event.getActionCommand();
        if ("Naechstes Wort".equals(actionCommand)) {
            logic.nextWord(false);
        } else if ("Optionen".equals(actionCommand)) {
            logic.showSettings();
        } else if ("Statistik".equals(actionCommand)) {
            logic.showScores();
        } else if ("Testen".equals(actionCommand)) {
            logic.check();
        }
    }
}
