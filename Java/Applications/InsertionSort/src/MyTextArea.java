import java.awt.TextArea;

/**
 * Eine aus {@code TextArea} abgeleitete Klasse, die zusaetzlich noch eine
 * Methode enthaelt, die abhaengig vom Status des Sortierers einen bestimmten
 * Text zum bestehenden hinzufuegt. Auch kann der angezeigte Text nicht von Hand
 * geaendert werden.
 * 
 * @author Lars Reimann
 * @version 30. Mai 2011
 */
public class MyTextArea extends TextArea {

    /**
     * Automatisch generierte Versionsnummer.
     */
    private static final long serialVersionUID = -3284882425391047128L;

    /**
     * Konstruiert ein neues mehrzeiliges Textfeld, welches nicht von Hand
     * bearbeitet werden kann und lediglich zur Ausgabe von Text genutzt wird.
     */
    public MyTextArea() {
        setEditable(false);
    }

    /**
     * Fuegt abhaengig vom uebergebenen Status des Sortierers einen Text zum
     * bestehenden hinzu.
     * 
     * @param step
     *            Der naechste Schritt, der vom Sortierer ausgefuehrt wird.
     */
    public void refresh(final int step) throws IllegalArgumentException {
        switch (step) {
            case 0:
                append("0: Setze den Elementzeiger auf das zweite Element\n");
                break;
            case 1:
                append("1: Schaue ob der Elementzeiger auf ein existierendes Element deutet\n");
                break;
            case 2:
                append("2: Setze den Einsetzungszeiger auf die Position des Elementzeigers\n");
                break;
            case 3:
                append("3: Merke dir das Element, auf das der Elementzeiger deutet\n");
                break;
            case 4:
                append("4: Schaue ob ein Feld links des Einsetzungszeigers existiert und ob es groesser als das gemerkte Element ist\n");
                break;
            case 5:
                append("5: Schiebe das Element links des Einsetzungszeigers eine Position nach rechts\n");
                break;
            case 6:
                append("6: Schiebe den Einsetzungszeiger eine Position nach links\n");
                break;
            case 7:
                append("7: Setze das gemerkte Element an die Stelle des Einsetzungszeigers\n");
                break;
            case 8:
                append("8: Schiebe den Elementzeiger eine Position nach rechts\n");
                break;
            case -1:
                append("-1: Die Liste ist fertig sortiert\n");
                break;
            default:
                throw new IllegalArgumentException();
        }
    }
}
