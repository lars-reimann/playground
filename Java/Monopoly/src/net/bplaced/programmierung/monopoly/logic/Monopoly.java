package net.bplaced.programmierung.monopoly.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.bplaced.programmierung.monopoly.graphics.MonopolyGraphics;

public final class Monopoly {
    private final List<Event> events;
    private final List<Field> fields;
    private final int startingCapital;
    private final int stationRent;
    private final int supplyMultiplier;
    private int pipCount;
    private List<Player> players;
    private final MonopolyGraphics monopolyGraphics;

    public Monopoly(List<Event> events, List<Field> fields,
            int startingCapital, int stationRent, int supplyMultiplier) {
        this.events = events;
        this.fields = fields;
        this.startingCapital = startingCapital;
        this.stationRent = stationRent;
        this.supplyMultiplier = supplyMultiplier;
        monopolyGraphics = new MonopolyGraphics(this);
        players = requestPlayers();
    }

    private List<Player> requestPlayers() {
        List<String> playerNames = monopolyGraphics.requestPlayerNames();
        List<Player> players = new ArrayList<Player>();
        
        for (String playerName : playerNames) {
            players.add(new Player(playerName, startingCapital));
        }
        return players;
    }
    
    private int rollDice() {
        return (int) (Math.random() * 11) + 2; // TODO Pasch 
    }

    /**
     * Gibt die gew&uuml;rfelte Augenzahl zur&uuml;ck.
     * 
     * @return Die gew&uuml;rfelte Augenzahl.
     */
    public int getPipCount() {
        return pipCount;
    }

    /**
     * Gibt die Miete zur&uuml;ck, die zu zahlen ist, wenn ein Spieler genau
     * einen Bahnhof besitzt und ein anderes Spieler auf diesem Feld zum Stehen
     * kommt.
     * 
     * @return Die Miete, die zu zahlen ist.
     */
    public int getStationRent() {
        return stationRent;
    }

    /**
     * Gibt den Multiplikator zur&uuml;ck, mit dem die gew&uuml;rfelte Augenzahl
     * zu multiplizieren ist, wenn ein Spieler genau ein Werk besitzt und ein
     * anderes Spieler auf diesem Feld zum Stehen kommt.
     * 
     * @return Der Multiplikator.
     */
    public int getSupplyMultiplier() {
        return supplyMultiplier;
    }

    /**
     * F&uuml;hrt den Dialog aus, ob ein Spieler ein kaufbares Feld kaufen
     * m&ouml;chte oder nicht. Sollte es sich dagegen entscheiden, k&ouml;nnen
     * alle Spieler auf das Objekt bieten. Der Spieler mit dem h&ouml;chsten
     * Gebot erh&auml;lt es.
     * 
     * @param player
     *            Der aktuelle Spieler.
     * @param ownable
     *            Das betroffene Objekt.
     */
    public void requestActionOnOwnable(Player player, AbstractBuyable ownable) {
        // TODO Auto-generated method stub
    }

    /**
     * Schickt den betroffenen Spieler in ein zuf&auml;llig ausgew&auml;hltes
     * Gef&auml;ngnis. Sollte kein Gef&auml;ngnis vorhanden sein, passiert gar
     * nichts.
     * 
     * @param player
     *            Der aktuelle Spieler.
     */
    public void sendToJail(Player player) {
        List<JailField> jailFields = new ArrayList<JailField>();
        for (Field field : fields) {
            if (fields instanceof JailField) {
                jailFields.add((JailField) field);
            }
        }
        int size = jailFields.size();
        if (size == 0) {
            return;
        } else {
            JailField jailField = jailFields.get(new Random().nextInt(size));
            jailField.imprison(player);
            player.setCurrentField(jailField);
        }
    }

    /**
     * L&ouml;st ein zuf&auml;lliges Ereignis aus.
     * 
     * @param player
     *            Der aktuelle Spieler.
     */
    public void triggerEvent(Player player) {
        // TODO Auto-generated method stub
    }

    /**
     * F&uuml;hrt einen Dialog mit dem aktuellen Spieler aus, um einen Bankrott
     * zu verhindern, wenn dessen Kapital auf 0 gesunken ist.
     * 
     * @param player
     *            Der aktuelle Spieler.
     */
    public void requestActionOnBankruptcy(Player player) {
        // TODO Auto-generated method stub
    }
}
