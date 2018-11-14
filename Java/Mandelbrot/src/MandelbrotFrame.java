import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public final class MandelbrotFrame extends JFrame {
    
    public MandelbrotFrame() {
        final Container cp = getContentPane();
        final MandelbrotPanel panel = new MandelbrotPanel();

        cp.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        cp.add(panel);

        addKeyListener(new MandelbrotKeyListener(panel));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Mandelbrot-Menge");
        setVisible(true);

        pack();
    }
}
