package Grafik;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Write a description of class InterfacListener here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class InterfaceListener implements ActionListener {
    private TDGUI g;

    public InterfaceListener(TDGUI g) {
        this.g = g;
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() instanceof GTower) {
            GTower t = (GTower) (ae.getSource());
            g.createGeneralInfobox(t.getName(), t.getIcon());
            g.setRollover(t.getName());
        }
    }

}
