import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public final class SierpinskiFrame extends JFrame {
    
    public SierpinskiFrame() {
        final Container cp = getContentPane();
        final SierpinskiPanel panel = new SierpinskiPanel();

        cp.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        cp.add(panel);

        addKeyListener(new SierpinskiKeyListener(panel));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sierpinski-Dreieck");
        setVisible(true);

        pack();
    }
}
