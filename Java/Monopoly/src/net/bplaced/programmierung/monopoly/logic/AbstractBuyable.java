package net.bplaced.programmierung.monopoly.logic;

/**
 * Eine Abstraktion aller kaufbaren Felder, wie Stra&szlig;en, Bahnh&ouml;fen
 * oder Werken. Auf allen diesen Feldern wird Miete f&auml;llig, wenn man sie
 * betritt und sie von einem anderen Spieler gekauft worden sind. Die Miete
 * richtet sich nach der Art des Feldes und nach der Zahl der Feldes der
 * gleichen Gruppe, die der Besitzer diese Feldes ebenfalls innehat.
 * 
 * @version 12. Februar 2011
 * @author Lars Reimann
 */
public abstract class AbstractBuyable extends AbstractField {

    /**
     * Die Gruppe, zu der dieses Feld geh&ouml;rt.
     */
    private final Group group;

    /**
     * Gibt an, ob eine Hypothek auf dieses Feld vorliegt.
     */
    private boolean isMortgaged;

    /**
     * Der Besitzer dieses Feldes.
     */
    private Player owner;

    /**
     * Der Kaufpreis diese Feldes.
     */
    private final int price;

    /**
     * Erstellt ein neues kaufbares Feld mit dem angegebenen Namen, der
     * entsprechenden Gruppe und dem &uuml;bergebenen Kaufpreis. Au&szlig;erdem
     * f&uml;gt sich das Feld selbst zur &uuml;bergebenen Gruppe hinzu.
     * 
     * @param name
     *            Der Name des Feldes.
     * @param group
     *            Die Gruppe des Feldes.
     * @param price
     *            Der Kaufpreis des Feldes.
     */
    public AbstractBuyable(String name, Group group, int price) {
        super(name);
        this.group = group;
        this.price = price;
        owner = Player.NONE;

        group.add(this);
    }

    /**
     * Berechnet die anfallenden Mietkosten, die der Spieler zu tragen hat, der
     * auf dieses Feld gekommen ist.
     * 
     * @param monopoly
     *            Das aktuelle Spiel.
     * @return Die Mietkosten.
     */
    public abstract int computeRent(Monopoly monopoly);

    /**
     * Falls das Feld schon jemandem geh&ouml;rt, werden die Mietkosten
     * berechnet und vom Kapital des ziehenden Spielers abgezogen. Der Besitzer
     * enth&auml;t den entsprechenden Betrag. Andernfalls erh&auml;lt der
     * Spieler die M&ouml;glichkeit, das Feld zu kaufen.
     */
    @Override
    public void doSettleAction(Monopoly monopoly, Player player) {
        if (owner == Player.NONE) {
            monopoly.requestActionOnOwnable(player, this);
        } else {
            int rent = computeRent(monopoly);
            owner.increaseCapital(rent);
            player.decreaseCapital(monopoly, rent);
        }
    }

    /**
     * Gibt die Gruppe zur&uuml;ck, zu der das Feld geh&ouml;rt.
     * 
     * @return Die Gruppe des Feldes.
     */
    public Group getGroup() {
        return group;
    }

    /**
     * Gibt den Besitzer des Feldes zur&uml;ck.
     * 
     * @return Der Besitzer des Feldes.
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * Gibt die Kosten des Feldes zur&uml;ck.
     * 
     * @return Die Kosten des Feldes.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Gibt an, ob eine Hypothek auf dieses Feld vorliegt.
     * 
     * @return Ob eine Hypothek auf dieses Feld vorliegt.
     */
    public boolean isMortgaged() {
        return isMortgaged;
    }

    /**
     * Setzt die Flag, die anzeigt, ob eine Hypothek auf dieses Feld vorhanden
     * ist.
     * 
     * @param isMortgaged
     *            Ob eine Hypothek auf dieses Feld vorliegt.
     */
    public void setMortgaged(boolean isMortgaged) {
        this.isMortgaged = isMortgaged;
    }

    /**
     * Setzt den Besitzer des Feldes auf den &uuml;bergebenen Weg.
     * 
     * @param player
     *            Der neue Besitzer des Feldes.
     */
    public void setOwner(Player player) {
        owner = player;
    }

    @Override
    public String toString() {
        return "Feld: " + getName() + "\r\nGruppe: " + group.getName()
                + "\r\nBesitzer: " + owner.getName() + "\r\nKosten: " + price;
    }

}
