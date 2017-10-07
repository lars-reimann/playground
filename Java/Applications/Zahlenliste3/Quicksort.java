import javax.swing.JOptionPane;

public final class Quicksort extends AbstractSorter {

    /**
     * Der Zaehler der Vertauschungen.
     */
    private int count = 0;
    
    public void run() {
        count = 0;
        quicksort(0, zahlenliste.length - 1);
        JOptionPane.showMessageDialog(
            intArrayGUI,
            "Sortierung beendet! (Vertauschungen " + count + ")"
        );
    }

    /**
     * Eine Hilfsmethode fuer den QuickSort-Algorithmus. Sie sortiert das Array
     * in der Weise vor, dass alle Elemente kleiner dem Pivot-Element auf die
     * linke Seite und alle groessen auf die rechte Seite desselben verschoben
     * werden.
     *
     * @param start Hier beginnt die Sortierung.
     * @param end   Hier endet die Sortierung.
     * @return Den neuen Index des Pivot-Elements.
     */
    private int divide(final int start, final int end) {
        final int pivot = zahlenliste[end];
        int i = start;
        int j = end;
        while (i < j) {
            for ( ; i < end && zahlenliste[i] <= pivot; i++);
            for ( ; j > start && zahlenliste[j] >= pivot; j--);
            if (i < j) {
                swap(i, j);
                count++;
            }
        }
        if (zahlenliste[i] > pivot) {
            swap(i, end);
            count++;
        }
        return i;
    }

    /**
     * Der Sortieralgorithmus "QuickSort". Die Methode ruft sich rekursiv
     * erneut mit veraenderten Grenzen auf, um das Array nach und nach zu
     * sortieren.
     *
     * @param start Hier beginnt die Sortierung.
     * @param end   Hier endet die Sortierung.
     */
    private void quicksort(final int start, final int end) {
        if (start < end) {
            final int pos = divide(start, end);
            quicksort(start, pos - 1);
            quicksort(pos + 1, end);
        }
        intArrayGUI.setSorted(true);
    }
}
