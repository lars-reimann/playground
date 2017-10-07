import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

/**
 * Diese Klasse ist fuer die grafische Darstellung der Zahlenliste und der
 * beiden Zeiger zustaendig. Die Zahlenliste wird in Form eines Balkendiagrammes
 * ausgegeben und die Zeiger werden als farbige Striche unter diesen Balken
 * repraesentiert. Der rote Strich steht dabei fuer den Elementzeiger, waehrend
 * der gruene fuer den Einsetzungszeiger steht.
 * 
 * @author Lars Reimann
 * @version 30. Mai 2011
 */
public final class Diagram extends Canvas {

    /**
     * Automatisch generierte Versionsnummer.
     */
    private static final long serialVersionUID = -2291150254176503781L;

    /**
     * Die Zahlenliste, die dargestellt werden soll.
     */
    private final Entry[] entries;

    /**
     * Konstruiert ein neues Dialogelement, welches die Form eines
     * Balkendiagrammes besitzt und so die Schluessel der uebergebenen Eintraege
     * verkoerpert.
     * 
     * @param entries
     *            Die Zahlenliste, die dargestellt werden soll.
     */
    public Diagram(final Entry[] entries) {
        this.entries = entries;
    }

    @Override
    public void paint(final Graphics graphics) {
        
        // Darstellung der Eintraege in der Liste
        for (int i = 0; i < 9; i++) {
            
            // Darstellung des Elementzeigers
            if (entries[i].isSelectedPos()) {
                graphics.setColor(Color.red);
                graphics.fillRect(i * 50 + 5, 180, 40, 5);
            }
            
            // Darstellung des Einsetzungszeigers
            if (entries[i].isInsertPos()) {
                graphics.setColor(Color.green);
                graphics.fillRect(i * 50 + 5, 190, 40, 5);
            }
            
            // Darstellung des Schluessels
            graphics.setColor(Color.black);
            graphics.fillRect(i * 50 + 5, 175 - entries[i].getKey(), 40,
                              entries[i].getKey());
        }
        
        // Darstellung des gemerkten Schluessels
        graphics.setColor(Color.blue);
        graphics.fillRect(9 * 50 + 5, 175 - entries[9].getKey(), 40,
                          entries[9].getKey());
    }
}
