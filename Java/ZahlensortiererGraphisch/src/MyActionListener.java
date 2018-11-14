import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

/**
 * Dieser ActionListener verarbeitet die ActionEvents der verschiedenen Buttons
 * des Fensters und fuehrt die entsprechenden Aktionen aus.
 *
 * @author Lars Reimann
 * @version 4. September 2010
 */
public final class MyActionListener implements ActionListener {


    // ----Instanzvariablen----------------------------------------------------

    /**
     * Die Instanz der Klasse IntArrayGUI.
     */
    private final IntArrayGUI intArrayGUI;


    // -----Konstuktoren-------------------------------------------------------

    /**
     * Der Konstruktor fuer MyActionListener-Objekte.
     *
     * @param intArrayGUI Die Instanz der Klasse IntArrayGUI.
     */
    public MyActionListener(final IntArrayGUI intArrayGUI) {
        this.intArrayGUI = intArrayGUI;
    }


    // -----Instanzmethoden----------------------------------------------------

    /**
     * Reagiere auf Events!
     *
     * @param event Das Event, das sich ereignet hat.
     */
    public void actionPerformed(final ActionEvent event) {
        final String command = event.getActionCommand();
        if ("Erzeuge Zufallszahlen".equals(command) && intArrayGUI.isStopped()) {
            intArrayGUI.createRandomNumbers();
            intArrayGUI.setCount(0);
        } else if ("Sortiere".equals(command)) {
            intArrayGUI.setStopped(false);
            final String selectedItem = intArrayGUI.getSelectedItem();
            if (!intArrayGUI.isSorted() && selectedItem != null) {
                AbstractSorter sorter = null;
                if ("BubbleSort".equals(selectedItem)) {
                    sorter = new BubbleSort();
                } else if ("ShakerSort".equals(selectedItem)) {
                    sorter = new ShakerSort();
                } else if ("InsertionSort".equals(selectedItem)) {
                    sorter = new InsertionSort();
                } else if ("ShellSort".equals(selectedItem)) {
                    sorter = new ShellSort();
                } else if ("SelectionSort".equals(selectedItem)) {
                    sorter = new SelectionSort();
                } else if ("Quicksort".equals(selectedItem)) {
                    sorter = new Quicksort();
                } else if ("Mergesort".equals(selectedItem)) {
                    sorter = new Mergesort();
                }
                Thread thread = new Thread(sorter);
                thread.start();
            } else if (intArrayGUI.isSorted()) {
                JOptionPane.showMessageDialog(
                    intArrayGUI,
                    "Die Liste ist bereits sortiert!"
                );
            } else if (selectedItem == null) {
                JOptionPane.showMessageDialog(
                    intArrayGUI,
                    "Bitte waehlen Sie ein Sortierverfahren aus!",
                    "Fehler",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        } else if("Stop".equals(command)) {
            intArrayGUI.setStopped(true);
        }
    }
}
