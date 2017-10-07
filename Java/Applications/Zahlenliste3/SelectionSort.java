import javax.swing.JOptionPane;

public final class SelectionSort extends AbstractSorter {

    public void run() {
        int count = 0;
        for (int i = 0; i < zahlenliste.length; i++) {
            final int indexMin = findMinimum(0 + i, zahlenliste.length);
            swap(0 + i, indexMin);
            count++;
        }
        intArrayGUI.setSorted(true);
        JOptionPane.showMessageDialog(
            intArrayGUI,
            "Sortierung beendet! (Vertauschungen " + count + ")"
        );
    }
    
    /**
     * Sucht den Index eines minimalen Elementes.
     *
     * @param start Bei diesem Index wird die Suche begonnen.
     * @param end   Vor diesem Index endet die Suche.
     * @return Index
     */
    private int findMinimum(final int start, final int end) {
        int minimum = Integer.MAX_VALUE;
        int indexMin = 0;
        for (int i = start; i < end; i++) {
            if (zahlenliste[i] < minimum) {
                minimum = zahlenliste[i];
                indexMin = i;
            }
        }
        return indexMin;
    }
}
