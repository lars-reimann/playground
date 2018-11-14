import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;

/**
 * <p>
 * Dies ist die Implementierung eines CommandListeners, welche dementsprechend
 * Kommandos bearbeitet.
 * </p>
 * <p>
 * Das Programm beinhaltet lediglich zwei Kommandos, eines zum L&ouml;sen der
 * Gleichung und eines zum Beenden des Programmes. Diese beiden F&auml;lle
 * werden in der Implementierung der Methode commandAction() unterschieden.
 * </p>
 * 
 * @version 23. Juni 2011
 * @author Lars Reimann
 */
public final class MyCommandListener implements CommandListener {

    /**
     * Eine Instanz von Solver, welche zum L&ouml;sen der Gleichung verwendet
     * wird, wenn das entsprechende Kommando auszuf&uuml;hren ist.
     */
    private final Solver solver;

    /**
     * Die Instanz von Start, welche bei Starten des MIDlet erzeugt worden ist.
     */
    private final Start start;

    /**
     * <p>
     * Der einzige Konstruktor dieser Klasse.
     * </p>
     * <p>
     * Die Variable solver wird mit einer neuen Instanz von Solver
     * initialisiert, welche sp&auml;ter zum L&ouml;sen der Gleichung verwendet
     * wird. Die Variable start wird mit dem &uuml;bergebenen aktuellen
     * Parameter initialisiert, so dass sie danach eine Referenz auf die beim
     * Starten des Programmes erzeugte Instanz der Start-Klasse besitzt. So ist
     * es schlie&szlig;lich m&ouml;glich, das Programm zu beenden.
     * </p>
     * 
     * @param start
     *            Die Instanz von Start,welche beim Starten des Programmes
     *            erzeugt worden ist.
     */
    public MyCommandListener(final Start start) {
        solver = new Solver();
        this.start = start;
    }

    /**
     * <p>
     * Diese Methode wird aufgerufen, wenn beim Handy eine der Kommandotasten
     * gedr&uuml;ckt wird.
     * </p>
     * <p>
     * Wenn des &uuml;bergebene Kommando den Wert Command.OK besitzt, so wird
     * die Methode zum L&ouml;sen der Gleichung aufgerufen. Sonst wird das
     * Programm beendet.
     * </p>
     * 
     * @param command
     *            Das Kommando, welches bearbeitet werden soll.
     * @param displayable
     *            Das Displayable, in welchem das Kommando aufgerufen worden
     *            ist.
     */
    public void commandAction(final Command command,
                              final Displayable displayable) {
        final int type = command.getCommandType();
        final MyForm form = (MyForm) displayable;
        if (type == Command.OK) {
            if (form.size() == 5) {
                form.delete(4);
            }
            form.append(
                solver.solveQuadratic(
                    form.getA(),
                    form.getB(),
                    form.getC()
                )
            );
        } else if (type == Command.EXIT) {
            start.notifyDestroyed();
        }
    }
}
