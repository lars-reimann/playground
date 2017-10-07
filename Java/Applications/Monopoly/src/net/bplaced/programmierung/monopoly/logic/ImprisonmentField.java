package net.bplaced.programmierung.monopoly.logic;

/**
 * Das Feld "Gehe ins Gef&auml;ngnis", welches den Spieler direkt ins
 * Gef&auml;ngnis schickt, ohne dass dieser beim &Uuml;berschreiten von Los Geld
 * einziehen darf.
 * 
 * @version 11. Februar 2012
 * @author Lars Reimann
 */
public final class ImprisonmentField extends AbstractField {

    /**
     * Erstellt eine neues Feld dieser Art mit dem angegebenen Namen.
     * 
     * @param name
     *            Der Name de Feldes.
     */
    public ImprisonmentField(String name) {
        super(name);
    }

    /**
     * Schickt den entsprechenden Spieler ins Gef&auml;ngnis.
     * 
     * @param monopoly
     *            Das aktuelle Spiel.
     * @param player
     *            Der Spieler, der gerade gezogen hat.
     */
    @Override
    public void doSettleAction(Monopoly monopoly, Player player) {
        monopoly.sendToJail(player);
    }
}
