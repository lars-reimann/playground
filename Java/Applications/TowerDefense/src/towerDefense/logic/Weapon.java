package towerDefense.logic;

import java.awt.Point;
import java.util.List;

import towerDefense.main.Main;

/**
 * Die Darstellung einer Waffe eines Turmes. Die Werte dieser Waffe k&ouml;nnen
 * durch Upgrades verbessert beziehungsweise generell ver&auml;ndert werden.
 * 
 * @version 23.3.2012
 * @author Lars Reimann
 */
public class Weapon {

    /**
     * Die Zeiteinheiten bis zum n&auml;chsten Schuss.
     */
    private int cooldown;

    /**
     * Der Schaden, der von dieser Waffe angerichtet wird.
     */
    private int damage;

    /**
     * Die Reichweite dieser Waffe.
     */
    private int range;

    /**
     * Die Zeit pro Schuss dieser Waffe.
     */
    private int timePerShoot;

    /**
     * Erstellt einen neuen Prototypen einer Waffe eines Turmes mit den
     * entsprechenden Angaben.
     * 
     * @param damage
     *            Der Schaden, den diese Waffe anrichtet.
     * @param range
     *            Die Reichweite dieser Waffe.
     * @param timePerShoot
     *            Die Zeit pro Schuss dieser Waffe.
     */
    public Weapon(int damage, int range, int timePerShoot) {
        this.damage = damage;
        this.range = range;
        this.timePerShoot = timePerShoot;
        this.cooldown = 0;
    }

    /**
     * Kopiert die bereits bestehende Waffe und liefert somit eine neue Instanz
     * mit exakt dem exakt gleichen Zustand.
     * 
     * @param weapon
     *            Die zu kopierende Waffe.
     */
    public Weapon(Weapon weapon) {
        this.damage = weapon.getDamage();
        this.range = weapon.getRange();
        this.timePerShoot = weapon.getTimePerShoot();
        this.cooldown = 0;
    }

    /**
     * Greift einen Gegner an, der sich im Angriffsfeld dieser Waffe befindet.
     * 
     * @param engine
     *            Die Instanz der Hauptklasse.
     * @param pos
     *            Die Position dieser Waffe.
     */
    private void attack(Engine engine, Point pos) {
        List<Creep> creeps = engine.getActiveCreeps();
        for (int i = 0; i < creeps.size(); i++) {
            Creep creep = creeps.get(i);
            if (isInRange(creep.getPixelPos(), pos)) {
                creep.damage(damage);
                return;
            }
        }
    }

    /**
     * Berechnet den Abstand der beiden Punkte in x-Richtung.
     * 
     * @param creepPos
     *            Die Position des Gegners.
     * @param towerPos
     *            Die Position des Turmes.
     * @return Den Abstand in x-Richtung.
     */
    private double computeXDistance(Point creepPos, Point towerPos) {
        return creepPos.getX() - towerPos.getX();
    }

    /**
     * Gibt den Schaden dieser Waffe zur&uuml;ck.
     * 
     * @return Den Schaden dieser Waffe.
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Gibt die Reichweite dieser Waffe zur&uuml;ck.
     * 
     * @return Die Reichweite dieser Waffe.
     */
    public int getRange() {
        return range;
    }

    /**
     * Gibt die Zeit pro Schuss dieser Waffe zur&uuml;ck.
     * 
     * @return Die Zeit pro Schuss dieses Waffe.
     */
    public int getTimePerShoot() {
        return timePerShoot;
    }

    /**
     * Gibt an, ob sich der Gegner oberhalb der Kreislinie des unteren
     * Halbkreises des Angriffsfeldes befindet.
     * 
     * @param creepPos
     *            Die Position des Gegners.
     * @param towerPos
     *            Die Position des Turmes.
     * @return Ob die obrige Bedingung erf&uuml;llt ist.
     */
    private boolean isInLowerSemicircle(Point creepPos, Point towerPos) {
        double lowerBorder = towerPos.getY()
                - Math.sqrt(range * range
                        - Math.pow(computeXDistance(creepPos, towerPos), 2));
        return creepPos.getY() >= lowerBorder;
    }

    /**
     * Gibt an, ob sich ein Gegner im Angriffsfeld dieses Waffe befindet.
     * 
     * @param creepPos
     *            Die Position des Gegners.
     * @param towerPos
     *            Die Position des Turmes.
     * @return Ob der Gegner angegriffen werden kann.
     */
    private boolean isInRange(Point creepPos, Point towerPos) {
        return isInXRange(creepPos, towerPos)
                && isInUpperSemicircle(creepPos, towerPos)
                && isInLowerSemicircle(creepPos, towerPos);
    }

    /**
     * Zeigt an, ob sich der Gegner unterhalb der Kreislinie des ooberen
     * Halbkreises des Angriffsbereiches befindet.
     * 
     * @param creepPos
     *            Die Position des Gegners.
     * @param towerPos
     *            Die Position des Turmes.
     * @return Ob die obrige Bedingung erf&uuml;llt ist.
     */
    private boolean isInUpperSemicircle(Point creepPos, Point towerPos) {
        double upperBorder = towerPos.getY()
                + Math.sqrt(range * range
                        - Math.pow(computeXDistance(creepPos, towerPos), 2));
        return creepPos.getY() <= upperBorder;
    }

    /**
     * Gibt an, ob sich der Gegner im Angriffskreis befinden kann, wenn man nur
     * die x-Komponente betrachtet.
     * 
     * @param creepPos
     *            Die Position des Gegners.
     * @param towerPos
     *            Die Position des Turmes, also dieser Waffe.
     * @return Ob die obrige Bedingung erf&uuml;llt ist.
     */
    private boolean isInXRange(Point creepPos, Point towerPos) {
        return Math.abs(computeXDistance(creepPos, towerPos)) <= range;
    }

    /**
     * Aktualisiert diese Waffe. Wenn die Waffe abgek&uuml;hlt ist, wird die
     * Angriffsmethode aufgerufen und die Abk&uuml;hlzeit aktualisiert. Sonst
     * wird die Abk&uuml;hlzeit lediglich heruntergez&auml;hlt.
     * 
     * @param engine
     *            Die Instanz der Hauptklasse.
     * @param pos
     *            Die Position dieser Waffe.
     */
    public void update(Engine engine, Point pos) {
        if (cooldown <= 0) {
            cooldown = timePerShoot;
            attack(engine, pos);
        } else {
            cooldown--;
        }
    }

    /**
     * Verbessert diese Waffe. Sie besitzt nach dem Verbessern die Werte, die
     * durch das &uuml;bergebe Upgrade angegeben worden sind.
     * 
     * @param upgrade
     *            Das auszuf&uuml;hrende Upgrade.
     */
    public void upgrade(Upgrade upgrade) {
        damage = upgrade.getNewDamage();
        range = upgrade.getNewRange();
        timePerShoot = upgrade.getNewRate();
    }
}
