import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Die einzige Aufgabe dieses KeyListeners ist es, auf die Bet&auml;tigung der
 * Entertaste zu reagieren und daraufhin der Spiellogik mitzuteilen, die Eingabe
 * zu testen. Dies beschleunigt den Spielablauf erheblich, da man nicht
 * st&auml;ndig zwischen Maus und Tastatur wechseln muss.
 * 
 * @author lars
 */
public final class HangmanKeyListener extends KeyAdapter {

    /**
     * Die Instanz der Spiellogik.
     */
    private final HangmanLogic logic;

    /**
     * Setzt die Variable f&uuml;r die Spiellogik auf den &uuml;bergebenen Wert.
     * 
     * @param logic
     *            Die Instanz der Spiellogik.
     */
    public HangmanKeyListener(final HangmanLogic logic) {
        this.logic = logic;
    }

    @Override
    public void keyPressed(final KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_ENTER) {
            logic.check();
        }
    }
}