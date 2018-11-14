package Grafik;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MapListener implements ActionListener {
    private TDGUI g;

    public MapListener(TDGUI g) {
        this.g = g;
    }

    public void actionPerformed(ActionEvent ae) {
        Object o = ae.getSource();
        if (o instanceof GTower) {
            g.setRollover("nothing");
            GTower t = (GTower) o;
            g.createSpecificInfobox(t);
        } else if (o instanceof GItem) {
            GItem gitem = (GItem) o;
            if (!g.getTowerToBuild().equals("nothing")) {
                g.buildTower(new GTower(g.getTowerToBuild(), gitem
                        .getXKoordinate(), gitem.getYKoordinate()));
            }
        } else if (o instanceof GCreepButton) {
            g.setRollover("nothing");
            GCreepButton b = (GCreepButton) o;
            g.createCreepInfobox(b.getName(), b.getIcon());
        }
    }

}
