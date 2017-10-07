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
        if ("Erzeuge Zufallszahlen".equals(command)) {
            intArrayGUI.createRandomNumbers();
        } else if ("Sortiere".equals(command)) {
            final String selectedItem = intArrayGUI.getSelectedItem();
            if (!intArrayGUI.isSorted() && selectedItem != null) {           
                if ("BubbleSort".equals(selectedItem)) {
                    // Verfahren für Bubblesort hier einfügen    
                } else if ("SwapSort".equals(selectedItem)) {
                    // Verfahren für SwapSort hier einfügen
                } else if ("GnomeSort".equals(selectedItem)) {
                    // Verfahren für Gnomesort hier einfügen
                } else if ("SelectionSort".equals(selectedItem)) {
                    // Verfahren für SelectionSort hier einfügen
                } else if ("Quicksort".equals(selectedItem)) {
                    // Verfahren für Quicksort hier einfügen
                } else if ("Radixsort".equals(selectedItem)) {
                    // Verfahren für Radixsort hier einfügen
                }
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
        }
    }
}
