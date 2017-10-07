package net.bplaced.programmierung.monopoly.logic;

import java.util.ArrayList;
import java.util.List;

/**
 * Eine zusammengeh&ouml;hrige Gruppe von kaufbaren Feldern. Diese kann aus
 * einigen H&auml;sern oder aus einem Zusammenschluss von Bahnh&ouml;fen
 * bestehen.
 * 
 * @version 12. Februar 2012
 * @author Lars Reimann
 */
public class Group {

    /**
     * Die Felder, die zu dieser Gruppe geh&ouml;ren.
     */
    private final List<AbstractBuyable> fields;

    /**
     * Der Name der Gruppe.
     */
    private final String name;

    /**
     * Erstellt eine neue Gruppe mit dem entsprechenden Namen.
     * 
     * @param name
     *            Der Name der Gruppe.
     */
    public Group(String name) {
        fields = new ArrayList<AbstractBuyable>();
        this.name = name;
    }

    /**
     * F&uuml;gt das &uuml;bergebene kaufbare Feld zu dieser Gruppe hinzu.
     * 
     * @param field
     *            Das hinzuzuf&uuml;gende Feld.
     */
    public void add(AbstractBuyable field) {
        fields.add(field);
    }

    /**
     * Z&auml;hlt, wie viele Felder dieser Gruppe der referenziert Spieler
     * besitzt.
     * 
     * @param player
     *            Der zu &uuml;berpr&uuml;fende Spieler.
     * @return Die Anzahl der besessenen Felder.
     */
    public int countOwnedFields(Player player) {
        int count = 0;
        for (AbstractBuyable field : fields) {
            if (field.getOwner() == player) {
                count++;
            }
        }
        return count;
    }

    /**
     * Gibt den Namen der Gruppe zu&uuml;ck.
     * 
     * @return Der Name der Gruppe.
     */
    public String getName() {
        return name;
    }

    /**
     * Testet, ob der &uuml;bergebene Spieler alle Felder dieser Gruppe besitzt.
     * 
     * @param player
     *            Der zu testende Spieler.
     * @return Ob der Spieler alle Felder dieser Gruppe besitzt.
     */
    public boolean ownsAllFields(Player player) {
        for (AbstractBuyable field : fields) {
            if (field.getOwner() != player) {
                return false;
            }
        }
        return true;
    }
}
