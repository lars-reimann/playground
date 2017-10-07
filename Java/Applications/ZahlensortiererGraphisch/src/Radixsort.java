import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Radixsort extends AbstractSorter {

    private ArrayList<Integer>[] digits = new ArrayList[2];
    
    public Radixsort() {
        for (int i = 0; i < digits.length; i++) {
            digits[i] = new ArrayList<Integer>();
        }
    }
    
    public void run() {
        int count = 0;
        for (int i = 0; i < 8; i++) {
            for (int value : array) {
                int value2 = value >> i;
                String string = Integer.toBinaryString(value2);
                int index = string.charAt(string.length() - 1) - 48;
                digits[index].add(value);
            }
            int index = 0;
            for (ArrayList<Integer> values : digits) {
                for (int value : values) {
                    array[index++] = value;
                    intArrayGUI.refresh(index - 1, index - 1);
                    count++;
                }
                values.clear();
            }
        }
        intArrayGUI.setSorted(true);
        JOptionPane.showMessageDialog(
            intArrayGUI,
            "Sortierung beendet! (Vertauschungen " + count + ")"
        );
    }
}
