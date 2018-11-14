package net.bplaced.programmierung.monopoly.logic;

/**
 * Ein Steuer_Feld, auf dem der Spieler einen vorher festgelegten Betrag an die
 * Bank zu entrichten hat.
 * 
 * @version 12. Februar 2012
 * @author Lars Reimann
 */
public final class TaxField extends AbstractField {

    /**
     * Der zu zahlende Betrag.
     */
    private final int amount;

    /**
     * Erstellt ein neues Steuer-Feld, welches den angegebenen Namen besitzt und
     * das Kapital des Spielers um den angegebenen Betrag verringert.
     * 
     * @param name
     *            Der Name des Feldes.
     * @param amount
     *            Der zu zahlende Betrag.
     */
    public TaxField(String name, int amount) {
        super(name);
        this.amount = amount;
    }

    /**
     * Verringert das Kapital des Spielers um den vorher eingestellten Betrag.
     * 
     * @param monopoly
     *            Das aktuelle Spiel.
     * @param player
     *            Der ziehende Spieler.
     */
    @Override
    public void doSettleAction(Monopoly monopoly, Player player) {
        player.decreaseCapital(monopoly, amount);
    }

    @Override
    public String toString() {
        return "Feld: " + getName() + "\r\nBetrag: " + amount;
    }
}
