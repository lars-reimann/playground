package Grafik;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 * Diese Klasse stellt die grafische map dar. Sie besteht aus einem LayeredPane.
 * Im hintergrund(Tiefe 0) befinden sich die nicht dynamischen
 * Felder(Wege,Türme,freie Felder) Im vordergrund(Tiefe 1) befinden sich die
 * dynamischen Panels(Kraturen, evtl. Schüsse)
 * 
 */
public class GMap extends JLayeredPane {
    private TDGUI gui;

    public GMap(TDGUI g) {
        this.gui = g;
        background = new JPanel();
        add(background, new Integer(0));
        setPreferredSize(new Dimension(TDGUI.dim * TDGUI.fielddim, TDGUI.dim
                * TDGUI.fielddim));
        actualTower = "nothing";
        actualRadius = null;
        creeps = new ArrayList<GCreep>();

        shoots = new GShoot(TDGUI.dim * TDGUI.fielddim, TDGUI.dim
                * TDGUI.fielddim);
        shoots.setBounds(0, 0, TDGUI.dim * TDGUI.fielddim, TDGUI.dim
                * TDGUI.fielddim);
        add(shoots, new Integer(2));

    }

    // -------------------------------------------------------------------
    // dynamische Elemente
    private ArrayList<GCreep> creeps;
    private GShoot shoots;

    public int getCreepX(int index) {
        return creeps.get(index).getX() + (TDGUI.fielddim / 2);
    }

    public int getCreepY(int index) {
        return creeps.get(index).getY() + (TDGUI.fielddim / 2);
    }

    public void setCreepLocation(int index, int x, int y) {
        creeps.get(index).setLocation(x - (TDGUI.fielddim / 2),
                y - (TDGUI.fielddim / 2));
    }

    public void setCreepLP(int index, int lp) {
        creeps.get(index).setLP(lp);
    }

    public void addCreep(GCreep creep) {
        creeps.add(creep);
        add(creep, new Integer(1));
    }

    public void removeCreep(int index) {
        remove(creeps.get(index));
        creeps.remove(index);
        repaint();
    }

    public void addShoot(int x1, int y1, int x2, int y2) {
        shoots.addShoot(x1, y1, x2, y2);
    }

    public void drawShoots() {
        shoots.drawShoots();
    }

    // ---------------------------------------------------------------------
    // statische Karte
    private GField[][] staticFields;
    private JPanel background;
    private String actualTower;
    private GRadius actualRadius;

    public void initMap(int[][] values, boolean determindway) {
        staticFields = new GField[TDGUI.dim][TDGUI.dim];
        background.removeAll();
        background.setSize(TDGUI.fielddim * TDGUI.dim, TDGUI.fielddim
                * TDGUI.dim);
        background.setLayout(new GridLayout(TDGUI.dim, TDGUI.dim));
        for (int y = 0; y < TDGUI.dim; y++) {
            for (int x = 0; x < TDGUI.dim; x++) {
                if (values[y][x] == 0) {
                    GField g = new GField("free");
                    staticFields[x][y] = g;
                    background.add(g);
                    GItem gitem = new GItem("free", x, y);
                    gitem.addActionListener(new MapListener(gui));
                    g.changeContent(gitem);
                } else {
                    GField g = new GField("way");
                    staticFields[x][y] = g;
                    background.add(g);
                    if (!determindway) {
                        GItem gitem = new GItem("free", x, y);
                        gitem.addActionListener(new MapListener(gui));
                        g.changeContent(gitem);
                    }
                }
            }
        }

        updateUI();
    }

    public void setRadius(int x, int y, int r) {
        GRadius radius = new GRadius(r);
        radius.setBounds(x - r, y - r, r * 2, r * 2);
        add(radius, new Integer(4));
        removeRadius();
        actualRadius = radius;
    }

    public void removeRadius() {
        if (actualRadius != null) {
            remove(actualRadius);
            repaint();
        }
    }

    public GTower getTower(int x, int y) {
        return staticFields[x][y].getTower();
    }

    public void upgradeTower(int x, int y) {
        staticFields[x][y].upgradeTower();
    }

    public void setTower(GTower t) {
        staticFields[t.getXKoordinate()][t.getYKoordinate()].changeContent(t);
    }

    public void delTower(int x, int y) {
        GItem gitem = new GItem("free", x, y);
        gitem.addActionListener(new MapListener(gui));
        staticFields[x][y].changeContent(gitem);
    }

    public void setRollover(String name) {
        actualTower = name;
        if (name.equals("nothing")) {
            for (int i = 0; i < TDGUI.dim; i++) {
                for (int j = 0; j < TDGUI.dim; j++) {
                    staticFields[i][j].disableRollover();
                }
            }
        } else {
            try {
                ImageIcon rol = new ImageIcon(
                        ImageCreator.bufferedImagetoImage(ImageCreator
                                .loadImage(TDGUI.ppt + name + ".png")));
                for (int i = 0; i < TDGUI.dim; i++) {
                    for (int j = 0; j < TDGUI.dim; j++) {
                        if (staticFields[i][j].getName().equals("free"))
                            staticFields[i][j].setRollover(rol);
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    public String getTowerToBuild() {
        return actualTower;
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
