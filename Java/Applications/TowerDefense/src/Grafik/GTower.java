package Grafik;

/**
 * Abstract class ClickableIcon - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public class GTower extends GItem {
    private int level;

    public GTower(String name, int x, int y) {
        super(name, x, y);
        level = 1;
        actIcon();
    }

    public void setLevel(int level) {
        this.level = level;
        actIcon();
    }

    public void levelUp() {
        level++;
        actIcon();
    }

    public void actIcon() {
        try {
            setIcon(ImageCreator.createTower(name, level));
            repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
