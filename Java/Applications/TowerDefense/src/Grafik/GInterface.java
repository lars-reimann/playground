package Grafik;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GInterface extends JPanel {
    private final Color bg = Color.orange;
    private int countTowers;
    private JLabel money, life;
    private JPanel towers, information;

    public GInterface() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(6 * TDGUI.fielddim, TDGUI.dim
                * TDGUI.fielddim));
        countTowers = 0;
        // -------North
        JPanel north = new JPanel();
        north.setPreferredSize(new Dimension(6 * TDGUI.fielddim, TDGUI.fielddim));
        north.setBackground(bg);
        JLabel picMoney = new JLabel();
        try {
            Image coin = ImageCreator.bufferedImagetoImage(ImageCreator
                    .loadImage(TDGUI.pps + "coins.png"));
            picMoney.setIcon(new ImageIcon(coin));
        } catch (Exception e) {
        }
        picMoney.setPreferredSize(new Dimension(30, 30));
        north.add(picMoney);
        money = new JLabel("0");
        money.setPreferredSize(new Dimension(TDGUI.fielddim, TDGUI.fielddim));
        north.add(money);
        JLabel picLife = new JLabel();
        try {
            Image heart = ImageCreator.bufferedImagetoImage(ImageCreator
                    .loadImage(TDGUI.pps + "heart.png"));
            picLife.setIcon(new ImageIcon(heart));
        } catch (Exception e) {
        }
        picMoney.setPreferredSize(new Dimension(30, 30));
        north.add(picLife);
        life = new JLabel("0");
        life.setPreferredSize(new Dimension(TDGUI.fielddim, TDGUI.fielddim));
        north.add(life);
        add(north, BorderLayout.NORTH);
        // Towers
        towers = new JPanel();
        towers.setBackground(bg);
        towers.setPreferredSize(new Dimension(6 * TDGUI.fielddim,
                8 * TDGUI.fielddim));
        towers.setLayout(null);

        add(towers, BorderLayout.WEST);
        // South
        information = new JPanel();
        information.setBackground(bg);
        information.setPreferredSize(new Dimension(6 * TDGUI.fielddim,
                7 * TDGUI.fielddim));
        add(information, BorderLayout.SOUTH);

    }

    public void addTower(String name, ActionListener al) {
        GTower t = new GTower(name, 0, 0);
        t.addActionListener(al);
        t.setBounds(countTowers % 6 * TDGUI.fielddim, countTowers / 6
                * TDGUI.fielddim, TDGUI.fielddim, TDGUI.fielddim);
        countTowers++;
        towers.add(t);
        towers.repaint();
    }

    public void setMoney(int capital) {
        money.setText("" + capital);
    }

    public void setLife(int lifes) {
        life.setText("" + lifes);
    }

    public void setInfo(GInformation info) {
        information.removeAll();
        information.add(info);
    }

    public void removeInfo() {
        information.removeAll();
        information.updateUI();
    }

    public ImageIcon createImageIcon(String path, String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

}
