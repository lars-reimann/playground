import javax.swing.JOptionPane;

public final class ShellSort extends AbstractSorter {

    public void run() {
        int count = 0;
        final int[] cols = {701, 301, 132, 57, 23, 10, 4, 1};
        for (int i = 0; i < cols.length; i++) {
            for (int j = cols[i]; j < array.length; j++) {
                final int temp = array[j];
                int k = j;
                for ( ; k >= cols[i] && array[k - cols[i]] > temp;
                        k -= cols[i]) {
                    array[k] = array[k - cols[i]];
                    count++;
                    intArrayGUI.refresh(k, k - cols[i]);
                }
                array[k] = temp;
                intArrayGUI.refresh(k, k);
            }
        }
        intArrayGUI.setSorted(true);
        JOptionPane.showMessageDialog(
            intArrayGUI,
            "Sortierung beendet! (Vertauschungen " + count + ")"
        );
    }
}
