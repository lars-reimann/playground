import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;

/**
 * <p>
 * Eine Instanz dieser Klasse ist w&auml;hrend der Ausf&uuml;hrung des MIDlet
 * auf dem Bildschirm zu sehen.
 * </p>
 * <p>
 * So werden alle Ein- und Ausgaben zwischen Benutzer und Programm
 * &uuml;bermittelt. Die Anzeige besteht aus einem String, der die Eingabe
 * erkl&auml;rt und drei Textfeldern, die schlie&szlig;lich zur Eingabe der
 * Werte der Variablen dienen. Nach erfolgreichem L&ouml;sen der Gleichung wird
 * die L&ouml;sungsmenge unterhalb dieser Textfelder angezeigt.
 * </p>
 * 
 * @version 23. Juni 2011
 * @author Lars Reimann
 */
public final class MyForm extends Form {

    /**
     * Das Textfeld zur Eingabe der Variablen a.
     */
    private final TextField a;

    /**
     * Das Textfeld zur Eingabe der Variablen b.
     */
    private final TextField b;

    /**
     * Das Textfeld zur Eingabe der Variablen c.
     */
    private final TextField c;

    /**
     * <p>
     * Der einzige Konstruktor dieser Klasse.
     * </p>
     * <p>
     * Ruft den Konstruktor der Vaterklasse auf, um den Titel dieses Form auf
     * "Quadratische Gleichungen" zu setzen. Au&szlig;erdem werden die
     * Textfelder initialisiert, so dass dort lediglich noch Zahlen und optional
     * ein Dezimalpunkt oder ein Vorzeichen eingegeben werden k&ouml;nnen. Des
     * Weiteren wird der String "ax^2 + bx + c = 0", sowie alle Textfelder an
     * dieses Form angeh&auml;ngt. Ebenfalls werden zwei Kommandos
     * angef&uuml;gt, wobei einer f&uuml;r das Beenden des Programmes und der
     * andere f&uuml;r das L&ouml;sen der Gleichung zust&auml;ndig ist.
     * </p>
     */
    public MyForm() {
        super("Quadratische Gleichungen");

        a = new TextField("a:", "", 10, TextField.DECIMAL);
        b = new TextField("b:", "", 10, TextField.DECIMAL);
        c = new TextField("c:", "", 10, TextField.DECIMAL);

        append("ax² + bx + c = 0\n");
        append(a);
        append(b);
        append(c);

        addCommand(new Command("L\u00F6sen", Command.OK, 0));
        addCommand(new Command("Beenden", Command.EXIT, 0));
    }

    /**
     * Gibt den Inhalt des Textfeldes a zurueck.
     * 
     * @return Der Inhalt des Textfeldes a.
     */
    public int getA() {
        final String text = a.getString();
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException excpetion) {
            return 0;
        }
    }

    /**
     * Gibt den Inhalt des Textfeldes b zurueck.
     * 
     * @return Der Inhalt des Textfeldes b.
     */
    public int getB() {
        final String text = b.getString();
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException excpetion) {
            return 0;
        }
    }

    /**
     * Gibt den Inhalt des Textfeldes c zurueck.
     * 
     * @return Der Inhalt des Textfeldes c.
     */
    public int getC() {
        final String text = c.getString();
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException excpetion) {
            return 0;
        }
    }
}
