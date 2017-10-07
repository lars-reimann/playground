import javax.swing.JOptionPane;

public final class InsertionSort extends AbstractSorter {

    public void run() {
        int count = 0;
        for (int i = 1; i < array.length && !intArrayGUI.isStopped(); i++) {
            final int temp = array[i];
            int pos = i;
            for (int j = i; j > 0; j--) {
                if (temp < array[j - 1]) {
                    array[j] = array[j - 1];
                    intArrayGUI.refresh(j, j - 1);
                    count++;
                    pos--;
                } else {
                    break;
                }
            }
            array[pos] = temp;
            intArrayGUI.refresh(pos, pos);
        }
        if (!intArrayGUI.isStopped()) {
            intArrayGUI.setSorted(true);
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
