import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Circle extends JPanel implements Runnable {
    
    public Circle() {
        setSize(400, 400);
    }
    
    public void run() {
        int count = 0;
        repaint();
        final Graphics graphics = getGraphics();

        for (int i = 0; i < 10000000; i++) {
            double randomX = (Math.random() * 400);
            double randomY = (Math.random() * 400);
            if (randomX * randomX + randomY * randomY <= 400 * 400) {
                count++;
                graphics.setColor(Color.green);
            } else {
                graphics.setColor(Color.red);
            }
            graphics.drawLine((int)randomX, (int)randomY, (int)randomX, (int)randomY);
        }
        JOptionPane.showMessageDialog(this, "Pi: " + 4 * (double) count / 10000000d, "Ergebnis", JOptionPane.INFORMATION_MESSAGE);
    }
}
