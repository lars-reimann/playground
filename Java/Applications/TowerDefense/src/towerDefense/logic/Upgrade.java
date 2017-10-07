package towerDefense.logic;

/**
 * Die Verbesserung eines Turmes. Es k&ouml;nnen Schadenswert, Reichweite und
 * Feuerrate verbessert werden.
 * 
 * @version 16.3.2012
 * @author Lars Reimann
 */
public class Upgrade {

    /**
     * Der neue Schaden, den der verbesserte Turm nach dem Upgrade anrichtet.
     */
    private final int newDamage;

    /**
     * Die neue Reichweite des verbesserten Turmes.
     */
    private final int newRange;

    /**
     * Die neue Feuerrate des verbesserten Turmes.
     */
    private final int newRate;

    /**
     * Die Kosten dieses Upgrades.
     */
    private final int price;

    /**
     * Erstellt eine neue Verbesserung, die den Schadenswert, die Reichweite und
     * die Feuerrate eines Turmes ver&auml;ndern kann.
     * 
     * @param price
     *            Die Kosten dieses Upgrades.
     * @param newDamage
     *            Der verbesserte Waffenschaden.
     * @param newRange
     *            Die verbesserte Reichweite.
     * @param newRate
     *            Die neue Feuerrate.
     */
    public Upgrade(int price, int newDamage, int newRange, int newRate) {
        this.price = price;
        this.newDamage = newDamage;
        this.newRange = newRange;
        this.newRate = newRate;
    }

    /**
     * Gibt den neuen Schaden, welchen der verbesserte Turm nach diesem Upgrade
     * anrichtet, zur&uuml;ck.
     * 
     * @return Den neuen Schaden des verbesserten Turmes.
     */
    public int getNewDamage() {
        return newDamage;
    }

    /**
     * Gibt die neue Reichweite des verbesserten Turmes zur&uuml;ck.
     * 
     * @return Die neue Reichweite des verbesserten Turmes.
     */
    public int getNewRange() {
        return newRange;
    }

    /**
     * Gibt die neue Feuerrate des verbesserten Turmes zur&uuml;ck.
     * 
     * @return Die neue Feuerrate des verbesserten Turmes.
     */
    public int getNewRate() {
        return newRate;
    }

    /**
     * Gibt die Kosten dieses Upgrades zur&umml;ck.
     * 
     * @return Die Kosten dieses Upgrades.
     */
    public int getPrice() {
        return price;
    }
}