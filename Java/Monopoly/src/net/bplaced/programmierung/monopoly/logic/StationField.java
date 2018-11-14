package net.bplaced.programmierung.monopoly.logic;

/**
 * Ein Bahnhofsfeld, das gekauft werden kann und bei dem die Mieten von der
 * Anzahl der vom Inhaber insgesamt besessenen Bahnh&ouml;fe abh&auml;ngt.
 * 
 * @version 12. Februar 2011
 * @author Lars Reimann
 */
public class StationField extends AbstractBuyable {

    /**
     * Erstellt ein neues Objekt dieses Typs mit dem entsprechenden Namen, der
     * &uuml;bergebenen Gruppe und dem genannten Kaufpreis.
     * 
     * @param name
     *            Der Name des Feldes.
     * @param group
     *            Die Gruppe des Feldes.
     * @param price
     *            Der Kaufpreis des Feldes.
     */
    public StationField(String name, Group group, int price) {
        super(name, group, price);
    }

    @Override
    public int computeRent(Monopoly monopoly) {
        if (isMortgaged()) {
            return 0;
        } else {
            int rent = monopoly.getStationRent();
            int ownedCount = getGroup().countOwnedFields(getOwner());
            for (int i = 1; i < ownedCount; i++) {
                rent *= 2;
            }
            return rent;
        }
    }
}
