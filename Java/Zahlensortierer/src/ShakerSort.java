import javax.swing.JOptionPane;

public final class ShakerSort extends AbstractSorter {

    public void run() {
        int count = 0;
        boolean hasChanged = true;
        while (hasChanged && !intArrayGUI.isStopped()) {
            hasChanged = false;
            for (int i = 1; i < array.length; i++) {
                if (array[i] < array[i - 1]) {
                    swap(i, i - 1);
                    count++;
                    hasChanged = true;
                }
            }
            for (int i = array.length - 1; i > 0; i--) {
                if (array[i] < array[i - 1]) {
                    swap(i, i - 1);
                    count++;
                    hasChanged = true;
                }
            }
        }
        if (!intArrayGUI.isStopped()) {
            intArrayGUI.setSorted(true);
            intArrayGUI.setStopped(true);
            JOptionPane.showMessageDialog(
                intArrayGUI,
                "Sortierung beendet! (Vertauschungen " + 
                                      (intArrayGUI.getCount() + count) + ")"
            );
        } else {
            intArrayGUI.setCount(intArrayGUI.getCount() + count);
        }
    }
}
