package Grafik;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class GCreep extends JPanel {
    private JPanel lebenspunkte;
    private JButton pic;
    private final String name;

    public GCreep(String name, int x, int y) {
        this.name = name;
        setBounds(x - TDGUI.fielddim / 2, y - TDGUI.fielddim / 2,
                TDGUI.fielddim, TDGUI.fielddim);
        setOpaque(false);
        setLayout(new BorderLayout());
        // Bild in der Mitte
        pic = new GCreepButton(name);
        pic.setOpaque(false);
        pic.setContentAreaFilled(false);
        pic.setBorder(null);
        add(pic, BorderLayout.CENTER);

        // Lebenspunktebalken
        JPanel rot = new JPanel();
        rot.setPreferredSize(new Dimension(TDGUI.fielddim, 2));
        rot.setBackground(Color.red);
        rot.setLayout(new BoxLayout(rot, BoxLayout.LINE_AXIS));
        lebenspunkte = new JPanel();
        lebenspunkte.setMaximumSize(new Dimension(TDGUI.fielddim, 2));
        lebenspunkte.setBackground(Color.green);
        rot.add(lebenspunkte);
        add(rot, BorderLayout.NORTH);
    }

    public void setLP(int prozent) {
        int laenge = prozent * TDGUI.fielddim / 100;
        lebenspunkte.setMaximumSize(new Dimension(laenge, 2));
        lebenspunkte.setSize(new Dimension(laenge, 2));
        lebenspunkte.setMaximumSize(new Dimension(laenge, 2));
        updateUI();
    }

    public String getName() {
        return name;
    }

    public void addActionListener(ActionListener al) {
        pic.addActionListener(al);
    }

}
