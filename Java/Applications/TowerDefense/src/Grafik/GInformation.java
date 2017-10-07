package Grafik;

import java.awt.Dimension;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GInformation extends JPanel {
    private int countInfo;

    public GInformation() {
        setLayout(null);
        setOpaque(false);
        setPreferredSize(new Dimension(TDGUI.fielddim * 6, TDGUI.fielddim * 7));
        countInfo = 0;
    }

    public void addIcon(Icon img) {
        JLabel l = new JLabel();
        l.setHorizontalAlignment(JLabel.LEFT);
        l.setBounds(0, 0, TDGUI.fielddim, TDGUI.fielddim);
        l.setIcon(img);
        add(l);
    }

    public void addInfo(String info) {
        JLabel l = new JLabel(info);
        l.setHorizontalAlignment(JLabel.LEFT);
        l.setBounds(0, 20 * countInfo + TDGUI.fielddim, TDGUI.fielddim * 6, 20);
        countInfo++;
        add(l);
    }

    public void addButton(JButton b) {
        b.setBounds(0, 20 * countInfo + TDGUI.fielddim, TDGUI.fielddim * 6, 20);
        countInfo++;
        add(b);
    }

}
