import javax.swing.JOptionPane;

public final class BubbleSort extends AbstractSorter {

    public void run() {
        int count = 0;
        boolean hasChanged = true;
        while (hasChanged && !intArrayGUI.isStopped()) {
            hasChanged = false;
            for (int i = 1; i < zahlenliste.length; i++) {
                if (zahlenliste[i] < zahlenliste[i - 1]) {
                    swap(i, i - 1);
                    count++;
                    hasChanged = true;
                }
            }
        }
        intArrayGUI.setSorted(true);
        JOptionPane.showMessageDialog(
            intArrayGUI,
            "Sortierung beendet! (Vertauschungen " + count + ")"
        );
    }
}
