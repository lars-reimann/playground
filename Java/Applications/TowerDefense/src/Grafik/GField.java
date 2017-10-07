package Grafik;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * eine nicht dynamische Komponente auf dem spielfeld
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class GField extends JPanel {
    private GItem actualComponent;
    private Image bg;

    public GField(String type) {
        setPreferredSize(new Dimension(TDGUI.dim, TDGUI.dim));
        setLayout(new BorderLayout());
        actualComponent = null;
        try {
            bg = ImageCreator.bufferedImagetoImage(ImageCreator
                    .loadImage(TDGUI.pps + type + ".png"));
        } catch (Exception e) {
        }
    }

    public void paintComponent(Graphics g) {
        g.drawImage(bg, 0, 0, this);
    }

    public void changeContent(GItem c) {
        if (actualComponent != null) {
            remove(actualComponent);
        }
        actualComponent = c;
        add(c, BorderLayout.CENTER);
        updateUI();
    }

    public void setRollover(ImageIcon img) {
        if (actualComponent != null) {
            actualComponent.setRolloverEnabled(true);
            actualComponent.setRolloverIcon(img);
        }
    }

    public void disableRollover() {
        if (actualComponent != null) {
            actualComponent.setRolloverEnabled(false);
        }
    }

    public void upgradeTower() {
        actualComponent.levelUp();
    }

    public GTower getTower() {
        if (actualComponent instanceof GTower) {
            return (GTower) actualComponent;
        }
        return null;
    }

    public String getName() {
        if (actualComponent != null) {
            return actualComponent.getName();
        }
        return "unoccupyable";
    }
}
