package net.bplaced.programmierung.monopoly.logic;

import java.util.ArrayList;
import java.util.List;

/**
 * Ein Spieler von Monopoly, der &uuml;ber ein gewisses Kapital und einige
 * kaufbare Felder verf&uuml;gt und einen Namen hat.
 * 
 * @version 12. Februar 2012
 * @author Lars Reimann
 */
public class Player {

    /**
     * Ein Nullobjekt, das anzeigt, dass kein Spieler dieses Feld besitzt.
     */
    public static final Player NONE = new Player("-", 0);

    /**
     * Das Kapital dieses Spielers.
     */
    private int capital;

    /**
     * Das aktuelle Feld auf dem der Spieler steht.
     */
    private Field currentField;

    /**
     * Der Name dieses Spielers.
     */
    private final String name;

    /**
     * Die Liste mit den Feldern, die dieser Spieler besitzt.
     */
    private final List<AbstractBuyable> ownedFields;

    /**
     * Erstellt einen neuen Spieler mit dem &uuml;bergebenen Namen und
     * Startkapital.
     * 
     * @param name
     *            Der Name des Spielers.
     * @param capital
     *            Das Startkapital des Spielers.
     */
    public Player(String name, int capital) {
        this.name = name;
        this.capital = capital;
        ownedFields = new ArrayList<AbstractBuyable>();
    }

    /**
     * F&uuml;gt das &uuml;bergebene Feld zur Liste der vom Spieler besessenen
     * Felder hinzu.
     * 
     * @param field
     *            Das hinzuzuf&uuml;gende Feld.
     */
    public void addField(AbstractBuyable field) {
        ownedFields.add(field);
    }

    /**
     * Verringert das Kapital des Spielers um den angegebenen Betrag. Sollte der
     * Betrag gr&ouml;&szlig;er sein, als das Kapital des Spielers, so wird
     * dieser auf 0 zur&uuml;ckgesetzt und die Methode zur Behandlung eines
     * Bankrotts wird aufgerufen.
     * 
     * @param monopoly
     *            Das aktuelle Spiel.
     * @param amount
     *            Der abziziehende Betrag.
     */
    public void decreaseCapital(Monopoly monopoly, int amount) {
        if (capital > amount) {
            capital -= amount;
        } else {
            capital = 0;
            monopoly.requestActionOnBankruptcy(this);
        }
    }

    /**
     * Gibt das aktuelle Feld des Spieler zur&uuml;ck.
     * 
     * @return Das aktuelle Feld des Spielers.
     */
    public Field getCurrentField() {
        return currentField;
    }

    /**
     * Gibt den Namen des Spielers zur&uuml;ck.
     * 
     * @return Der Name des Spielers.
     */
    public String getName() {
        return name;
    }

    /**
     * Erh&ouml;ht das Kapital des Spieler um den &uuml;bergebenen Betrag.
     * 
     * @param amount
     *            Der Gewinn, den der Spieler einstreicht.
     */
    public void increaseCapital(int amount) {
        capital += amount;
    }

    /**
     * Entfernt des &uuml;bergebene Feld aus der Liste der Felder, die der
     * Spieler besitzt.
     * 
     * @param field
     *            Das zu entfernende Feld.
     */
    public void removeField(AbstractBuyable field) {
        ownedFields.remove(field);
    }

    /**
     * Setzt das Feld, auf dem des Spieler steht auf den &uuml;bergebenen Wert.
     * 
     * @param currentField
     *            Das neue Feld.
     */
    public void setCurrentField(Field newField) {
        this.currentField = newField;
    }
}
