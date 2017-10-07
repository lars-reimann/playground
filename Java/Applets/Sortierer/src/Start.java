import java.applet.Applet;
import java.awt.Button;

/**
 * Diese Klasse erbt aus {@code Applet} und dient somit als Einstiegspunkt in
 * das Programm. In ihr ist der grafische Grundaufbau des Applets festgelegt,
 * eine weitere Funktionalitaet besitzt die Klasse nicht. Insbesondere
 * existieren keine Methoden, die beim Starten, Stoppen oder Zerstoeren des
 * Applets aufgerufen werden.
 * 
 * @author Lars Reimann
 * @version 4. Juni 2011
 */
public final class Start extends Applet {

    /**
     * Automatisch generierte Versionsnummer.
     */
    private static final long serialVersionUID = -1850192042526290579L;

    /**
     * Der ActionListener, welcher auch den Thread zur automatischen Sortierung
     * beinhaltet.
     */
    private MyActionListener actionListener;

    @Override
    public void init() {

        // Deklaration und Initialisierung
        final Entry[] entries = new Entry[10];
        final Diagram diagram = new Diagram(entries);
        final MyTextArea textArea = new MyTextArea();
        final Button reset = new Button("Neustart");
        final Button next = new Button("Naechster Schritt");
        final Button startStop = new Button("Start/Stop");
        final Sorter sorter = new Sorter(entries);
        actionListener = new MyActionListener(diagram, textArea, sorter,
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
        reset.setBounds(0, 400, 167, 100);
        reset.addActionListener(actionListener);
        add(reset);

        // Darstellung des Buttons zur Ausfuehrung des naechsten Schrittes
        next.setBounds(167, 400, 166, 100);
        next.addActionListener(actionListener);
        add(next);

        // Darstellung des Start/Stop-Buttons
        startStop.setBounds(333, 400, 167, 100);
        startStop.addActionListener(actionListener);
        add(startStop);
    }

    @Override
    public void stop() {
        actionListener.interrupt();
    }
}
