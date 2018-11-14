package Grafik;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class GItem extends JButton {
    protected String name;
    protected int x, y;

    public GItem(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
        try {
            setIcon(new ImageIcon(
                    ImageCreator.bufferedImagetoImage(ImageCreator
                            .loadImage(TDGUI.pps + "unsichtbar.png"))));
        } catch (Exception e) {
        }
        setOpaque(false);
        setContentAreaFilled(false);
        setBorder(null);
    }

    public String getName() {
        return name;
    }

    public int getXKoordinate() {
        return x;
    }

    public int getYKoordinate() {
        return y;
    }

    public void levelUp() {

    }

}
