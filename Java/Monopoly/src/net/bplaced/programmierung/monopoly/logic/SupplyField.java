package net.bplaced.programmierung.monopoly.logic;

/**
 * Ein Werkfeld, welches gekauft werden kann und auf welches eine Miete
 * abh&auml;ngig von der gew&uuml;rfelten Augenzahl erhoben wird.
 * 
 * @version 12. Februar 2011
 * @author Lars Reimann
 */
public class SupplyField extends AbstractBuyable {

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
    public SupplyField(String name, Group group, int price) {
        super(name, group, price);
    }

    @Override
    public int computeRent(Monopoly monopoly) {
        if (isMortgaged()) {
            return 0;
        } else {
            int supplyMultiplier = monopoly.getSupplyMultiplier();
            int pipCount = monopoly.getPipCount();
            int ownedCount = getGroup().countOwnedFields(getOwner());
            for (int i = 1; i < ownedCount; i++) {
                supplyMultiplier = (supplyMultiplier + 1) * 2;
            }
            return pipCount * supplyMultiplier;
        }
    }
}
