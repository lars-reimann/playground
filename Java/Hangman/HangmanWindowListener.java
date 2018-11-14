import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Diese Implementierung eines WindowListeners sorgt beim Schlie&szlig;en des
 * Fensters f&uuml;r das Abspeichern des Spielstandes in einer Datei und
 * f&uuml;r das Beenden des Programmes.
 * 
 * @author Lars Reimann
 * @version 23. September 2011
 */
public final class HangmanWindowListener extends WindowAdapter {

    /**
     * Die Instanz der Spiellogik.
     */
    private final HangmanLogic logic;

    /**
     * Setzt die Variable, die die Spiellogik beinhaltet auf den
     * &uuml;bergebenen Wert.
     * 
     * @param logic
     *            Die Instanz der Spiellogik.
     */
    public HangmanWindowListener(final HangmanLogic logic) {
        this.logic = logic;
    }

    @Override
    public void windowClosing(final WindowEvent event) {
        logic.close();
    }
}