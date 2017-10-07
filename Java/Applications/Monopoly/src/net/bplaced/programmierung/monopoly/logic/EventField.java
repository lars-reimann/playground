package net.bplaced.programmierung.monopoly.logic;

/**
 * Ein Ereignisfeld, das ein zuf&auml;lliges Ereignis ausl&ouml;st. Dies kann
 * etwa der Verlust oder Gewinn eine bestimmten Geldmenge sein oder der Sprung
 * auf ein bestimmtes Feld.
 * 
 * @version 12. Februar 2012
 * @author Lars Reimann
 */
public final class EventField extends AbstractField {

    /**
     * Erstellt ein neues Ereignisfeld mit dem angegebenen Namen.
     * 
     * @param name
     *            Der Name des Feldes.
     */
    public EventField(String name) {
        super(name);
    }

    /**
     * L&ouml;st ein zuf&auml;lliges Ereignis aus.
     * 
     * @param monopoly
     *            Das aktuelle Spiel.
     * @param player
     *            Der aktuelle Spieler.
     */
    @Override
    public void doSettleAction(Monopoly monopoly, Player player) {
        monopoly.triggerEvent(player);
    }

    @Override
    public String toString() {
        return "Feld: " + getName();
    }
}
