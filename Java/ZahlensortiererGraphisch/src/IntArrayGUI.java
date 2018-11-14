import java.awt.Color;
import java.awt.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public final class IntArrayGUI extends JFrame {

    // ----Klassenvariablen----------------------------------------------------

    /**
     * Die einzige Instanze dieser Klasse.
     */
    private static final IntArrayGUI INSTANCE  = new IntArrayGUI();

    // -----Instanzvariablen---------------------------------------------------

    /**
     * Wahrheitswert, ob die Zahlenliste sortiert ist oder nicht.
     */
    private boolean                  isSorted  = false;

    private boolean                  isStopped = false;

    private int                      count     = 0;

    /**
     * Die Leinwand, auf die die einzelnen Elemente gezeichnet werden.
     */
    private final Screen             leinwand;

    /**
     * Die Liste der verschiedenen Sortierverfahren.
     */
    private final List               lSortiere;

    /**
     * In diesem Array werden die Zufallszahlen gespeichert.
     */
    private final int[]              zahlenliste;

    // ----Konstruktoren-------------------------------------------------------

    /**
     * Konstruktor fuer Objekte der Klasse IntArrayGUI. Der Konstruktor ist als
     * private markiert, um zu verhindern, das weitere Instanzen dieser Klasse
     * ausser INSTANCE angelegt werden koennen.
     */
    private IntArrayGUI() {
        super("Zahlenliste");

        // Zahlenliste
        zahlenliste = new int[750];
        final Random random = new Random();
        for (int i = 0; i < zahlenliste.length; i++) {
            zahlenliste[i] = random.nextInt(250);
        }

        // ActionListener
        final MyActionListener actionListener = new MyActionListener(this);

        // Fenster
        setSize(795, 365);
        setLocation(100, 100);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.lightGray);

        // Zufallszahlen
        final JButton bErzeuge = new JButton("Erzeuge Zufallszahlen");
        bErzeuge.setBounds(470, 272, 200, 50);
        bErzeuge.addActionListener(actionListener);
        getContentPane().add(bErzeuge);

        // Sortieren
        lSortiere = new List();
        lSortiere.add("BubbleSort");
        lSortiere.add("ShakerSort");
        lSortiere.add("InsertionSort");
        lSortiere.add("ShellSort");
        lSortiere.add("SelectionSort");
        lSortiere.add("Quicksort");
        lSortiere.add("Mergesort");
        lSortiere.setBounds(100, 272, 100, 50);
        getContentPane().add(lSortiere);

        final JButton bSortiere = new JButton("Sortiere");
        bSortiere.addActionListener(actionListener);
        bSortiere.setBounds(200, 272, 100, 50);
        getContentPane().add(bSortiere);

        final JButton bStoppe = new JButton("Stop");
        bStoppe.addActionListener(actionListener);
        bStoppe.setBounds(300, 272, 100, 50);
        getContentPane().add(bStoppe);

        // Leinwand
        leinwand = new Screen(zahlenliste);
        leinwand.setBounds(14, 10, 753, 253);
        leinwand.setBackground(Color.white);
        getContentPane().add(leinwand);

        // Anzeigen
        setVisible(true);
        refreshAll();
    }

    // -----Klassenmethoden----------------------------------------------------

    /**
     * Gibt die einzige Instanz dieser Klasse zurueck.
     * 
     * @return Die einzige Instanz der Klasse {@code IntArrayGUI}.
     */
    public static IntArrayGUI getInstance() {
        return INSTANCE;
    }

    /**
     * Die main()-Methode, die lediglich das Fenster anzeigt.
     * 
     * @param args
     *            Standardparameter
     */
    public static void main(final String[] args) {
        getInstance();
    }

    // -----Instanzmethoden----------------------------------------------------

    /**
     * Erzeuge neue Zufallszahlen!
     * 
     * Die Leinwand und das Listenfeld werden angepasst. Das Label lAusgabe wird
     * auf "" gesetzt.
     */
    public void createRandomNumbers() {
        final Random random = new Random();
        for (int i = 0; i < zahlenliste.length; i++) {
            zahlenliste[i] = random.nextInt(250);
        }
        refreshAll();
        isSorted = false;
    }

    /**
     * Gibt das ausgewaehlte Suchverfahren aus der Liste zurueck.
     * 
     * @return Das ausgewaehlte Element der Liste.
     */
    public String getSelectedItem() {
        return lSortiere.getSelectedItem();
    }

    /**
     * Gibt das Array mit der Zahlenliste zurueck.
     * 
     * @return Die Zahlenliste.
     */
    public int[] getZahlenliste() {
        return zahlenliste;
    }

    /**
     * Gibt zurueck, ob die Zahlenliste bereits sortiert ist. Diese Information
     * ist nuetzlich, um Maximum und Minimum der Liste zu finden, denn wenn die
     * Liste sortiert ist, befinden sich beide am Rand der Liste und koennen
     * ohne aufwendige Suche gefunden werden.
     * 
     * @return Ob die Zahlenliste sortiert ist.
     */
    public boolean isSorted() {
        return isSorted;
    }

    public void refresh(final int index1, final int index2) {
        leinwand.paint(index1, index2);
    }

    /**
     * Zeichne neu und stelle die Liste neu dar! Das Label lAusgabe wird ""
     * gesetzt.
     */
    public void refreshAll() {
        leinwand.update(leinwand.getGraphics());
    }

    /**
     * Setzt den Wahheitswert, der angibt, ob die Liste sortiert ist auf den
     * uebergebenen Parameter.
     * 
     * @param isSorted
     *            Gibt an, ob die Liste sortiert ist.
     */
    public void setSorted(final boolean isSorted) {
        this.isSorted = isSorted;
    }

    public void setStopped(final boolean isStopped) {
        this.isStopped = isStopped;
    }

    public boolean isStopped() {
        return isStopped;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
