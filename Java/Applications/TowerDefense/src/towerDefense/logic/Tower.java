package towerDefense.logic;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import towerDefense.main.Main;

/**
 * Die Darstellung eines Turmes.
 * 
 * @version 23.5.2012
 * @author Lars Reimann
 */
public class Tower {

    /**
     * Der Index des n&auml;chsten Upgrades in der Liste.
     */
    private int indexNextUpgrade;

    /**
     * Der Name des Turmes.
     */
    private final String name;

    /**
     * Die Position dieses Turmes.
     */
    private final Point pos;

    /**
     * Der Gesamtpreis des Turmes.
     */
    private int price;

    /**
     * Die Liste mit den m&ouml;glichen Upgrades dieses Turmes.
     */
    private final List<Upgrade> upgrades;

    /**
     * Die Waffe dieses Turmes.
     */
    private final Weapon weapon;

    /**
     * Erstellt einen neuen Prototypen eines Turmes mit den angegebenen
     * Parametern.
     * 
     * @param name
     *            Der Name der Turmes.
     * @param price
     *            Die Kosten des Turmes.
     * @param weapon
     *            Die Waffe des Turmes.
     * @param upgrades
     *            Die Liste mit den m&ouml;glichen Upgrades dieses Turmes.
     */
    public Tower(String name, int price, Weapon weapon, List<Upgrade> upgrades) {
        this.name = name;
        this.price = price;
        this.weapon = weapon;
        this.upgrades = upgrades;
        this.pos = null;
        this.indexNextUpgrade = 0;
    }

    /**
     * Kopiert den angegebenen Turm. Die so erzeugte Instanz besitzt exakt den
     * gleichen Zustand wie die zu kopierende Instanz.
     * 
     * @param tower
     *            Der zu kopierende Turm.
     */
    public Tower(Tower tower, Point pos) {
        this.name = tower.getName();
        this.price = tower.getPrice();
        this.weapon = new Weapon(tower.getWeapon());
        this.upgrades = tower.getUpgrades();
        this.pos = pos;
        this.indexNextUpgrade = 0;
    }

    /**
     * Gibt den Namen des Turmes zur&uuml;ck.
     * 
     * @return Den Namen des Turmes.
     */
    public String getName() {
        return name;
    }

    /**
     * Gibt das n&auml;chste Upgrade zur&uuml;ck oder null, wenn keines mehr
     * vorhanden ist.
     * 
     * @return Das n&auml;chste Upgrade.
     */
    public Upgrade getNextUpgrade() {
        if (hasMoreUpgrades()) {
            return upgrades.get(indexNextUpgrade);
        } else {
            return null;
        }
    }

    /**
     * Gibt die Kosten des Turmes zur&uuml;ck.
     * 
     * @return Die Kosten des Turmes.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Gibt die Liste mit den Upgrades dieses Turmes zur&uuml;ck
     * 
     * @return Die Liste mit den Upgrades dieses Turmes.
     */
    public List<Upgrade> getUpgrades() {
        return upgrades;
    }

    /**
     * Gibt die Waffe dieses Turmes zur&uuml;ck.
     * 
     * @return Die Waffe dieses Turmes.
     */
    public Weapon getWeapon() {
        return weapon;
    }

    /**
     * Gibt an, ob der Turm weiter verbessert werden kann.
     * 
     * @return Ob der Turm noch verbessert werden kann.
     */
    public boolean hasMoreUpgrades() {
        return indexNextUpgrade < upgrades.size();
    }

    /**
     * Setzt die Kosten des Turmes auf den &uuml;bergebenen Wert.
     * 
     * @param price
     *            Die neuen Kosten des Turmes.
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Aktualisiert diesen Turm.
     * 
     * @param engine
     *            Die Instanz der Hauptklasse.
     */
    public void update(Engine engine) {
        weapon.update(engine, pos);
    }

    /**
     * Verbessert diesen Turm indem die Werte des n&auml;chsten Upgrades die
     * alten Werte ersetzen. Der Preis dieser Turmes wird um den Betrag, der
     * f&uuml;r das Upgrade f&auml;llig wird erh&ouml;ht, sodass sp&auml;ter ein
     * entsprechender Betrag zur&uuml;ckerstattet werden kann.
     */
    public void upgrade(Main main) {
        if (hasMoreUpgrades()) {
            Upgrade upgrade = upgrades.get(indexNextUpgrade);
            price += upgrade.getPrice();
            main.setCapital(main.getCapital() - upgrade.getPrice());
            weapon.upgrade(upgrade);
            indexNextUpgrade++;

        }
    }
}
