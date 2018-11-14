package Grafik;

import javax.swing.JLabel;

/**
 * Write a description of class GRadius here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class GRadius extends JLabel {

    public GRadius(int r) {
        setText("hallo");
        setIcon(ImageCreator.createRadius(r));
    }

}
