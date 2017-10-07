package Grafik;

import java.awt.BorderLayout;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * Diese Klasse stellt die GUI für TD dar und enthält alle Methoden zum
 * verändern der Grafik.
 * 
 * @author (Carsten Orth)
 * @version (1.0)
 */
public abstract class TDGUI extends JFrame {
    private GMap map;
    private GInterface i;
    public static int dim = 16;
    public static int fielddim = 40;
    public static String ppt = "Grafik/"; // picturepath Türme
    public static String ppc = "Grafik/"; // picturepath Creeps
    public static String pps = "Grafik/"; // picturepath Sonstiges

    public TDGUI(String head, int[][] spielfeld, boolean determindway) {
        super(head);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        map = new GMap(this);
        map.initMap(spielfeld, determindway);
        add(map, BorderLayout.CENTER);

        i = new GInterface();
        add(i, BorderLayout.EAST);
        pack();

        setVisible(true);

    }

    // Methoden die die Map verändern
    /**
     * malt die Map neu
     */
    public void updateMap() {
        map.updateUI();
    }

    public void setRadius(int x, int y, int r) {
        map.setRadius(x * fielddim + fielddim / 2, y * fielddim + fielddim / 2,
                r);
    }

    public void removeRadius() {
        map.removeRadius();
    }

    /**
     * setzt die RolloverIcons für die Felder auf der Karte, wo noch gebaut
     * werden kann.
     * 
     * @param name
     *            Name des zu bauenden Turmes / "nothing" verhindert ein
     *            Rollover
     */
    public void setRollover(String name) { // name des Turmes oder Nothing
        map.setRollover(name);
    }

    /**
     * setzt einen Turm auf die Karte.
     * 
     * @param t
     *            der zusetzende Turm
     */
    public void setTower(GTower t) {
        t.addActionListener(new MapListener(this));
        map.setTower(t);
    }

    /**
     * entfernt einen Turm von der Karte.
     * 
     * @param x
     *            X-Koordinate des zu entfernenden Turms y Y-Koordinate des zu
     *            entfernenden Turms
     */
    public void delTower(int x, int y) {
        map.delTower(x, y);
    }

    /**
     * Erhöht die Levelanzeige im Icon um 1.
     * 
     * @param x
     *            X-Koordinate des aufgerüsteten Turms y Y-Koordinate des
     *            aufgerüsteten Turms
     */
    public void upgradeTower(int x, int y) {
        map.upgradeTower(x, y);
    }

    /**
     * setzt eine komplett neue Karte.
     * 
     * @param spielfeld
     *            werte der einzelnen Felder: 0 = "wiese" 1 = "weg" determindway
     *            true->vorgegebener Weg false->Türme können auch in den Weg
     *            gebaut werden
     */
    public void setMap(int[][] spielfeld, boolean determindway) {
        map.initMap(spielfeld, determindway);
    }

    // Methoden die das Interface verändern
    /**
     * aktualisiert die Anzeige des Geldes im Interface.
     * 
     * @param money
     *            aktuelles Kapital
     */
    public void setMoney(int money) {
        i.setMoney(money);
    }

    /**
     * aktualisiert die Anzeige der Lebenspunkte im Interface.
     * 
     * @param lifes
     *            aktuelle Lebenspunkte
     */
    public void setLife(int lifes) {
        i.setLife(lifes);
    }

    /**
     * fügt einen Turm, der ab jetzt gebaut werden kann, dem Interface hinzu.
     * 
     * @param name
     *            name des neuen Turmes
     */
    public void addTower(String name) {
        i.addTower(name, new InterfaceListener(this));
    }

    /**
     * aktualisiert die Infobox im Interface.
     * 
     * @param img
     *            anzuzeigendes Icon infos Informationen über den turm
     */
    protected void setGeneralInformation(Icon img, String[] infos) {
        GInformation information = new GInformation();
        information.addIcon(img);
        for (int l = 0; l < infos.length; l++) {
            information.addInfo(infos[l]);
        }
        i.setInfo(information);
        i.updateUI();
    }

    /**
     * aktualisiert die Infobox im Interface.
     * 
     * @param t
     *            Turm, um den es sich handelt infos Informationen über den Turm
     *            upgrades Texte auf den Upgrade-Buttons. Werden bei Auswahl
     *            auch zurückgegeben (siehe upgrade(x,y,upgrade))
     */
    protected void setSpecificInformation(GTower t, String[] infos,
            String upgrades[], boolean hasMoreUpgrades) {
        GInformation information = new GInformation();
        information.addIcon(t.getIcon());
        // Infos hinzufügen
        for (int l = 0; l < infos.length; l++) {
            information.addInfo(infos[l]);
        }
        // upgradebutton hinzufügen
        for (int l = 0; l < upgrades.length; l++) {
            JButton b = new JButton(upgrades[l]);
            b.addActionListener(new InformationListener(this, t
                    .getXKoordinate(), t.getYKoordinate()));
            information.addButton(b);
            b.setEnabled(hasMoreUpgrades);
        }
        // abrissbutton hinzufügen
        JButton b = new JButton("abreißen");
        b.addActionListener(new InformationListener(this, t.getXKoordinate(), t
                .getYKoordinate()));
        information.addButton(b);
        i.setInfo(information);
        i.updateUI();
    }

    /**
     * Es wird nichts in der Infobox im Interface angezeigt.
     */
    public void removeInformation() {
        i.removeInfo();
    }

    // Methoden, die die Creeps betreffen
    public int getCreepX(int index) {
        return map.getCreepX(index);
    }

    public int getCreepY(int index) {
        return map.getCreepY(index);
    }

    public void setCreepLocation(int index, int x, int y) {
        map.setCreepLocation(index, x, y);
    }

    public void setCreepLP(int index, int lp) {
        map.setCreepLP(index, lp);
    }

    public void addCreep(GCreep creep) {
        creep.addActionListener(new MapListener(this));
        map.addCreep(creep);
    }

    public void removeCreep(int index) {
        map.removeCreep(index);
    }

    public void addShoot(int x1, int y1, int x2, int y2) {
        map.addShoot(x1, y1, x2, y2);
    }

    // Getter-Methoden
    /**
     * gibt den Namen das ausgewählten Turmes zurück
     * 
     * @return Name des zu bauenden Turms.
     */
    public String getTowerToBuild() {
        return map.getTowerToBuild();
    }

    public GTower getTower(int x, int y) {
        return map.getTower(x, y);
    }

    // Methoden die nachher abstract werden und mit der engine kommunizieren
    // müssen
    public abstract void buildTower(GTower t);

    public abstract void knockTowerDown(int x, int y);

    public abstract void upgrade(int x, int y, String upgrade);

    public abstract void createGeneralInfobox(String name, Icon img);

    public abstract void createSpecificInfobox(GTower t);

    public abstract void createCreepInfobox(String name, Icon img);
}
