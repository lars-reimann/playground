import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public final class JuliaFrame extends JFrame {

    public JuliaFrame() {
        final Container cp = getContentPane();
        final JuliaPanel panel = new JuliaPanel();

        cp.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        cp.add(panel);

        addKeyListener(new JuliaKeyListener(this, panel));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Julia-Menge");
        setVisible(true);

        pack();
    }
}
