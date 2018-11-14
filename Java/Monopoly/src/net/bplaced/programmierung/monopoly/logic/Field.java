package net.bplaced.programmierung.monopoly.logic;

/**
 * Die abstrakteste Darstellung eines Feldes des Monopolybrettes. Jedes Feld
 * besitzt einen Namen und es k&ouml;nnen vor dem Zug, w&auml;hrend des
 * Hin&uuml;berziehens &uuml;ber ein Feld und beim Landen auf einem Feld
 * Aktionen ausgef&uuml;hrt werden.
 * 
 * @version 12. Februar 2011
 * @author Lars Reimann
 */
public interface Field {

    /**
     * F&uuml;hrt eine Aktion w&auml;hrend des &Uuml;berschreiten eines Feldes
     * aus.
     * 
     * @param monopoly
     *            Das aktuelle Spiel.
     * @param player
     *            Der aktuelle Spieler.
     */
    void doPassAction(Monopoly monopoly, Player player);

    /**
     * F&uuml;hrt eine Aktion beim Landen aus einem Feld aus.
     * 
     * @param monopoly
     *            Das aktuelle Spiel.
     * @param player
     *            Der aktuelle Spieler.
     */
    void doSettleAction(Monopoly monopoly, Player player);

    /**
     * Gibt den Namen des Feldes zur&uuml;ck.
     * 
     * @return Der Name des Feldes.
     */
    String getName();
}
