import javax.swing.JOptionPane;

public final class ShellSort extends AbstractSorter {

    public void run() {
        int count = 0;
        final int[] cols = {701, 301, 132, 57, 23, 10, 4, 1};
        for (int i = 0; i < cols.length; i++) {
            for (int j = cols[i]; j < zahlenliste.length; j++) {
                final int temp = zahlenliste[j];
                int k = j;
                for ( ; k >= cols[i] && zahlenliste[k - cols[i]] > temp;
                        k -= cols[i]) {
                    zahlenliste[k] = zahlenliste[k - cols[i]];
                    count++;
                    intArrayGUI.refresh(k, k - cols[i]);
                }
                zahlenliste[k] = temp;
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
