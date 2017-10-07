import java.util.Arrays;
import java.util.Vector;

import javax.swing.JOptionPane;

public class Mergesort extends AbstractSorter {

    private int count = 0;

    public void run() {
        count = 0;
        mergesort(0, zahlenliste.length - 1);
        JOptionPane.showMessageDialog(
            intArrayGUI,
            "Sortierung beendet! (Vertauschungen " + count + ")"
        );
    }

    private void mergesort(final int start, final int end) {
        if (start < end) {
            final int middle = (start + end) / 2;
            mergesort(start, middle);
            mergesort(middle + 1, end);
            merge(start, middle, end);
        }
    }

    private void merge(final int start, final int middle, final int end) {
        int i = 0;
        int j = start;
        int[] zahlenliste2 = new int[middle - start + 1];
        while (j <= middle) {
            zahlenliste2[i++] = zahlenliste[j++];
        }
        i = 0;
        int k = start;
        while (k < j && j <= end) {
            if (zahlenliste2[i] <= zahlenliste[j]) {
                zahlenliste[k++] = zahlenliste2[i++];
                intArrayGUI.refresh(k - 1, i - 1);
                count++;
            } else {
                zahlenliste[k++] = zahlenliste[j++];
                intArrayGUI.refresh(k - 1, j - 1);
                count++;
            }
        }
        while (k < j) {
            zahlenliste[k++] = zahlenliste2[i++];
            intArrayGUI.refresh(k - 1, i - 1);
            count++;
        }
    }
}
