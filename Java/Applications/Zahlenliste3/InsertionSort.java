import javax.swing.JOptionPane;

public final class InsertionSort extends AbstractSorter {

    public void run() {
        int count = 0;
        for (int i = 1; i < zahlenliste.length; i++) {
            final int temp = zahlenliste[i];
            int pos = i;
            for (int j = i; j > 0; j--) {
                if (temp < zahlenliste[j - 1]) {
                    zahlenliste[j] = zahlenliste[j - 1];
                    intArrayGUI.refresh(j, j - 1);
                    count++;
                    pos--;
                } else {
                    break;
                }
            }
            zahlenliste[pos] = temp;
            intArrayGUI.refresh(pos, pos);
        }
        intArrayGUI.setSorted(true);
        JOptionPane.showMessageDialog(
            intArrayGUI,
            "Sortierung beendet! (Vertauschungen " + count + ")"
        );
    }
}
