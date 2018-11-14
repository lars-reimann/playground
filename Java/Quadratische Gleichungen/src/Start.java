import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

/**
 * <p>
 * Dies ist der Einstiegspunkt in das Programm.
 * </p>
 * <p>
 * Die Klasse erbt aus MIDlet und &uuml;berschreibt alle n&ouml;tigen Methoden
 * zum Ausf&uuml;hren des Programmes.
 * </p>
 * 
 * @version 23. Juni 2011
 * @author Lars Reimann
 */
public final class Start extends MIDlet {

    /**
     * <p>
     * Wird beim Beenden des Programmes aufgerufen.
     * </p>
     * <p>
     * Da dann jedoch keine Aufgaben zu erledigen sind, ist die Methode leer.
     * </p>
     * 
     * @param unconditional
     *            Wenn hier true &uuml;bergeben wird, muss das Programm beendet
     *            werden. Sonst hat es die M&ouml;glichkeit mit der
     *            Ausf&uuml;hrung fortzufahren und eine
     *            MIDletStateChangeException zu werfen.
     * @throws MIDletStateChangeException
     */
    protected void destroyApp(final boolean unconditional)
            throws MIDletStateChangeException {
    }

    /**
     * <p>
     * Wird beim Pausieren des Programmes aufgerufen.
     * </p>
     * <p>
     * Da dann jedoch keine Aufgaben zu erledigen sin, ist die Methode leer.
     * </p>
     */
    protected void pauseApp() {
    }

    /**
     * <p>
     * Wird beim Starten des Programmes aufgerufen.
     * </p>
     * <p>
     * Es wird eine neue Instanz der Klasse MyForm erzeugt, welcher ein Objekt
     * des Types MyCommandListener als CommandListener zugewiesen wird, und
     * welche anschlie&szlig;end auf dem Bildschirm angezeigt wird.
     * </p>
     * 
     * @throws MIDletStateChangeException
     */
    protected void startApp() throws MIDletStateChangeException {
        final MyForm form = new MyForm();
        form.setCommandListener(new MyCommandListener(this));
        Display.getDisplay(this).setCurrent(form);
    }
}
