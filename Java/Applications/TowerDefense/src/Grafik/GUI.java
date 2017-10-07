package Grafik;

import java.awt.Point;

import javax.swing.Icon;
import javax.swing.JOptionPane;

import towerDefense.logic.Creep;
import towerDefense.logic.Tower;
import towerDefense.logic.Upgrade;
import towerDefense.logic.Weapon;
import towerDefense.main.Main;

/**
 * Beschreiben Sie hier die Klasse GUI.
 * 
 * @author (Ihr Name)
 * @version (eine Versionsnummer oder ein Datum)
 */
public class GUI extends TDGUI {
    private Main m;

    public GUI(int[][] feld, Main m) {
        super("TD", feld, true);
        this.m = m;
    }

    public void buildTower(GTower t) {
        if (m.canBuyTower(t.getName())) {
            m.buildTower(t.getName(), new Point(t.x, t.y));
            setTower(t);
            setMoney(m.getCapital());
        }
    }

    public void knockTowerDown(int x, int y) {
        m.delTower(new Point(x, y));
        delTower(x, y);
        removeInformation();
        removeRadius();
    }

    public void upgrade(int x, int y, String upgrade) {
        if (m.isUpgradable(new Point(x, y))) {
            upgradeTower(x, y);
            m.upgradeTower(new Point(x, y));
            setMoney(m.getCapital());
            createSpecificInfobox(getTower(x, y));
        }
    }

    public void createGeneralInfobox(String name, Icon img) {
        removeRadius();
        Tower tower = m.getTower(name);
        Weapon w = tower.getWeapon();
        String[] info = { "Name: " + tower.getName(),
                "Kosten: " + tower.getPrice(), "Schaden: " + w.getDamage(),
                "Reichweite: " + w.getRange(),
                "Schusszeit: " + w.getTimePerShoot() };
        setGeneralInformation(img, info);
    }

    public void createSpecificInfobox(GTower t) {
        Tower tower = m.getTower(new Point(t.getXKoordinate(), t
                .getYKoordinate()));
        Weapon w = tower.getWeapon();
        String[] info = { "Name: " + tower.getName(),
                "Schaden: " + w.getDamage(), "Reichweite: " + w.getRange(),
                "Schusszeit: " + w.getTimePerShoot() };
        boolean hasMoreUpgrades = tower.hasMoreUpgrades();
        String[] upgrade;
        if (hasMoreUpgrades) {
            Upgrade u = tower.getNextUpgrade();
            upgrade = new String[] { "Upgrade(" + u.getPrice() + ")" };
        } else {
            upgrade = new String[] { "Upgrade" };
        }
        setSpecificInformation(t, info, upgrade, hasMoreUpgrades);
        setRadius(t.getXKoordinate(), t.getYKoordinate(), w.getRange());
    }

    public void createCreepInfobox(String name, Icon img) {
        Creep creep = m.getCreep(name);
        String[] info = { "Name: " + creep.getName(),
                "Trefferpunkte: " + creep.getHitpoints(),
                "Gewinn: " + creep.getGain() };
        setGeneralInformation(img, info);
        removeRadius();
    }

    public void spawnCreep(String name, Point pos) {
        this.addCreep(new GCreep(name, pos.x, pos.y));
    }

    public void showEnd(int deathCount) {
        JOptionPane.showMessageDialog(this, "Spielende. Sie haben " + deathCount + " Gegner eliminiert.\r\nGut gemacht!");
    }
}
