package net.bplaced.programmierung.monopoly.logic;

/**
 * Das Los-Feld des Monopolyspiels. Wenn der Spieler auf dieses Feld kommt oder
 * &uuml;ber dieses Feld zieht, erh&auml;lt er einen bestimmten in der geladenen
 * xml-Datei festgelegten Betrag gutgeschrieben.
 * 
 * @version 11. Februar 2012
 * @author Lars Reimann
 */
public final class GoField extends AbstractField {

    /**
     * Der Betrag, den der Spieler erh&auml;lt, wenn er dieses Feld betritt oder
     * dar&uuml;ber hinweg zieht.
     */
    private final int gain;

    /**
     * Konstruiert ein neues Los-Feld mit dem entsprechendem Name und dem
     * entsprechenden Gewinn, den ein Spieler erh&auml;lt, wenn er diese Feld
     * betritt oder dar&uumL,ber hinwegzieht.
     * 
     * @param name
     *            Der Name dieses Feldes.
     * @param gain
     *            Der Gewinn, wenn dieses Feld betreten wird oder dar&uuml;ber
     *            hinweggezogen wird.
     */
    public GoField(String name, int gain) {
        super(name);
        this.gain = gain;
    }

    /**
     * Erh&ouml;ht das Kapital des Spieler um den vorher festegelegten Betrag.
     * 
     * @param monopoly
     *            Das aktuelle Spiel.
     * @param player
     *            Der Spieler, der gerade gezogen hat.
     */
    private void doAction(Monopoly monopoly, Player player) {
        player.increaseCapital(gain);
    }

    /**
     * Ruft die Methode {@code doAction(...)} auf.
     * 
     * @param monopoly
     *            Das entsprechende Spiel.
     * @param player
     *            Der ziehende Spieler.
     * @see doAction(...)
     */
    @Override
    public void doPassAction(Monopoly monopoly, Player player) {
        doAction(monopoly, player);
    }

    /**
     * Ruft die Methode {@code doAction(...)} auf.
     * 
     * @param monopoly
     *            Das entsprechende Spiel.
     * @param player
     *            Der ziehende Spieler.
     * @see doAction(...)
     */
    @Override
    public void doSettleAction(Monopoly monopoly, Player player) {
        doAction(monopoly, player);
    }

    @Override
    public String toString() {
        return "Feld: " + getName() + "\r\nGewinn: " + gain;
    }
}
