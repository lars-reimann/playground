package Grafik;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class GShoot extends JLabel {
    private ArrayList<int[]> shoots;
    private BufferedImage bimg;
    private Graphics2D g2;

    public GShoot(int swidth, int sheight) {
        bimg = new BufferedImage(swidth, sheight, 6);
        g2 = bimg.createGraphics();
        shoots = new ArrayList<int[]>();
    }

    public void drawShoots() {
        g2.setColor(Color.white);
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setColor(Color.black);
        for (int[] s : shoots) {
            g2.drawLine(s[0], s[1], s[2], s[3]);
        }
        g2.dispose();
        setIcon(new ImageIcon(ImageCreator.makeColorTransparent(bimg,
                Color.white)));
        shoots.clear();
    }

    public void addShoot(int x1, int y1, int x2, int y2) {
        int[] i = { x1, y1, x2, y2 };
        shoots.add(i);
    }
}
