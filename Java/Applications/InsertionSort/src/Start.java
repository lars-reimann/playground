import java.applet.Applet;
import java.awt.Button;
import javax.swing.JApplet;
import javax.swing.JButton;

/**
 * Diese Klasse erbt aus {@code Applet} und dient somit als Einstiegspunkt in
 * das Programm. In ihr ist der grafische Grundaufbau des Applets festgelegt,
 * eine weitere Funktionalitaet besitzt die Klasse nicht. Insbesondere
 * existieren keine Methoden, die beim Starten, Stoppen oder Zerstoeren des
 * Applets aufgerufen werden.
 * 
 * @author Lars Reimann
 * @version 30. Mai 2011
 */
public final class Start extends JApplet {

    /**
     * Automatisch generierte Versionsnummer.
     */
    private static final long serialVersionUID = -1850192042526290579L;
    
    @Override
    public void init() {

        // Deklaration und Initialisierung
        final Entry[] entries = new Entry[10];
        final Diagram diagram = new Diagram(entries);
        final MyTextArea textArea = new MyTextArea();
        final JButton reset = new JButton("Neustart");
        final JButton next = new JButton("Naechster Schritt");
        final Sorter sorter = new Sorter(entries);
        final MyActionListener actionListener = new MyActionListener(diagram,
                                                                     textArea,
                                                                     sorter,
                                                                     entries);

        // Festlegung des LayoutManager
        setLayout(null);

        // Belegung der Speicherplaetze des Arrays mit neuen Instanzen von Entry
        for (int i = 0; i < 8; i++) {
            entries[i] = new Entry();
        }
        entries[8] = new Entry(0);
        entries[9] = new Entry(0);

        // Darstellung des Balkendiagrammes
        diagram.setBounds(0, 0, 500, 200);
        add(diagram);

        // Darstellung des Ausgabefeldes
        textArea.setBounds(0, 200, 500, 200);
        textArea.refresh(0);
        add(textArea);

        // Darstellung des Neustart-Buttons
        reset.setBounds(0, 400, 250, 100);
        reset.addActionListener(actionListener);
        add(reset);

        // Darstellung des Buttons zur Ausfuehrung des naechsten Schrittes
        next.setBounds(250, 400, 250, 100);
        next.addActionListener(actionListener);
        add(next);
    }
}
