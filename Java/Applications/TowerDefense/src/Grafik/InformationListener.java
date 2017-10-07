package Grafik;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * Write a description of class InformationListener here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class InformationListener implements ActionListener {
    private TDGUI g;
    private int x, y;

    public InformationListener(TDGUI g, int x, int y) {
        this.x = x;
        this.y = y;
        this.g = g;
    }

    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o instanceof JButton) {
            JButton button = (JButton) o;
            if (button.getText().equals("abreißen")) {
                g.knockTowerDown(x, y);
            } else {
                g.upgrade(x, y, button.getText());
            }
        }
    }
}
