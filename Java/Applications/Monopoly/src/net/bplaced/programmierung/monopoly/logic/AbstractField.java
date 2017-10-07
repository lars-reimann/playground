package net.bplaced.programmierung.monopoly.logic;

/**
 * Implementiert einige Methoden des Interfaces {@code Field}, damit abgeleitete
 * Klassen diese nicht zwangsl&auml;ufig implementieren m&uuml;ssen. Stattdessen
 * k&ouml;nnen diese Methoden bei Bedarf &uuml;berschrieben werden. Hier werden
 * nur Standardimplementierungen vorgenommen, die in der Regel keine weitere
 * Funktionalit&auml;t haben.
 * 
 * @version 12. Februar 2011
 * @author Lars Reimann
 */
public abstract class AbstractField implements Field {

    /**
     * Der Name des Feldes.
     */
    private final String name;

    /**
     * Erstellt ein neues Feld mit dem &uuml;bergebenen Namen.
     * 
     * @param name
     *            Der Name des Feldes.
     */
    public AbstractField(String name) {
        this.name = name;
    }

    @Override
    public void doPassAction(Monopoly monopoly, Player player) {
    }

    @Override
    public void doSettleAction(Monopoly monopoly, Player player) {
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Feld: " + name;
    }
}
