package Grafik;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Write a description of class GCreepButton here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class GCreepButton extends JButton {
    private String name;

    public GCreepButton(String name) {
        this.name = name;
        try {
            Image pic = ImageCreator.bufferedImagetoImage(ImageCreator
                    .loadImage(TDGUI.ppc + name + ".png"));
            setIcon(new ImageIcon(pic));
        } catch (Exception e) {
        }
    }

    public String getName() {
        return name;
    }

}
