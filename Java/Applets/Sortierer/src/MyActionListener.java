import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Dieser {@code ActionListener} fuehrt nach Betaetigen der Buttons die ihnen
 * zugewiesenen Funktionen aus. Bei Druecken von "Neustart" werden neue
 * Schluessel erzeugt und der Sortierer wird wieder auf seine Ursprungsposition
 * gesetzt. Bei Druecken von "Naechster Schritt" wird dann logischerweise der
 * naechste Schritt im Sortierverfahren ausgefuehrt.
 * 
 * @author Lars Reimann
 * @version 4. Juni 2011
 */
public final class MyActionListener implements ActionListener, Runnable {

    /**
     * Das Balkendiagramm, das fuer die grafische Ausgabe der Schluessel
     * zustaendig ist.
     */
    private final Diagram diagram;

    /**
     * Die Zahlenliste, die bei Betaetigen von "Neustart" neue Schluessel
     * bekommen soll.
     */
    private final Entry[] entries;

    /**
     * Der Sortierer, der die Zahlenliste sortieren soll.
     */
    private final Sorter sorter;

    /**
     * Das Ausgabetextfeld, in dem der als naechstes auszufuehrende Schritt in
     * Textform ausgegeben wird.
     */
    private final MyTextArea textArea;

    /**
     * Der Thread zur automatischen Sortierung.
     */
    private Thread thread;

    /**
     * Konstruiert einen neuen {@code ActionListener} und initialisiert die
     * Felder der erzeugten Instanz mit den uebergebenen Werten.
     * 
     * @param diagram
     *            Das Balkendiagramm, das fuer die grafische Ausgabe der
     *            Schluessel zustaendig ist.
     * @param textArea
     *            Das Ausgabetextfeld, in dem der als naechstes auszufuehrende
     *            Schritt in Textform ausgegeben wird.
     * @param sorter
     *            Der Sortierer, der die Zahlenliste sortieren.
     * @param entries
     *            Die Zahlenliste, die bei Betaetigen von "Neustart" neue
     *            Schluessel bekommen soll.
     */
    public MyActionListener(final Diagram diagram, final MyTextArea textArea,
                            final Sorter sorter, final Entry[] entries) {
        this.diagram = diagram;
        this.textArea = textArea;
        this.sorter = sorter;
        this.entries = entries;
        thread = new Thread(this);
    }

    /**
     * Haelt den momentan laufenden Thread zur automatischen Sortierung an.
     */
    public void interrupt() {
        thread.interrupt();
    }

    @Override
    public void actionPerformed(final ActionEvent event) {
        final String actionCommand = event.getActionCommand();
        if ("Neustart".equals(actionCommand)) {
            reset();
        } else if ("Naechster Schritt".equals(actionCommand)) {
            next();
        } else if ("Start/Stop".equals(actionCommand)) {
            startStop();
        }
    }

    /**
     * Wird bei Betaetigung des Buttons "Naechster Schritt" aufgerufen und sorgt
     * fuer die Ausfuehrung des naechsten Schrittes durch den Sortierer.
     */
    private void next() {
        sorter.sort();
        textArea.refresh(sorter.getStep());
        diagram.repaint();
    }

    /**
     * Wird bei der Betaetigung des "Start/Stop"-Buttons ausgefuehrt und bewirkt
     * die automatische Ausfuehrung der Einzelschritte des Sortierers.
     */
    private void startStop() {
        if (thread.isInterrupted() || !thread.isAlive()) {
            thread = new Thread(this);
            thread.start();
        } else {
            thread.interrupt();
        }
    }

    /**
     * Setzt den Sortierer und das Textfeld in den Ursprungszustand zurueck,
     * weist den Eintraegen im Array neue Schluessel zu und zeichnet das
     * Balkendiagramm entsprechend neu.
     */
    private void reset() {
        for (int i = 0; i < 8; i++) {
            entries[i].setIsSelectedPos(false);
            entries[i].setIsInsertPos(false);
            entries[i].newKey();
        }
        entries[8].setIsSelectedPos(false);
        entries[9].setKey(0);
        sorter.setStep(0);
        textArea.setText("");
        textArea.refresh(0);
        diagram.repaint();
    }

    @Override
    public void run() {
        while (!thread.isInterrupted() && sorter.getStep() != -1) {
            sorter.sort();
            diagram.repaint();
            textArea.refresh(sorter.getStep());
            try {
                Thread.sleep(250);
            } catch (InterruptedException exception) {
                thread.interrupt();
            }
        }
    }
}
