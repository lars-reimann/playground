package net.bplaced.programmierung.monopoly.logic;

/**
 * Ein Straßenfeld, das gekauft werden und auf dem Häuser gebaut
 * werden können. Die Miete ist abhängig von der Anzahl der Häuser
 * und der Anzahl der besessen Häuser in dieser Gruppe.
 * 
 * @version 12. Februar 2011
 * @author Lars Reimann
 */
public class StreetField extends AbstractBuyable {

    /**
     * Der Preis eines Hauses.
     */
    private final int housePrice;

    /**
     * Die Anzahl der Häuser auf diesem Feld.
     */
    private int housesCount;

    /**
     * Die Mieten, die fällig werden, wenn ein Spieler auf dieses Feld
     * kommt, dem es nicht gehört.
     */
    private Rent rent;

    /**
     * Erstellt ein neues Straßenfeld mit den übergebenen Werten.
     * 
     * @param name
     *            Der Name des Feldes.
     * 
     * @param group
     *            Die Gruppe des Feldes
     * @param price
     *            Der Kaufpreis des Feldes.
     * @param housePrice
     *            Der Preis eines Hauses.
     * @param rent
     *            Die zu zahlenden Mieten auf diesem Feld.
     */
    public StreetField(String name, Group group, int price, int housePrice,
            Rent rent) {
        super(name, group, price);
        this.housePrice = housePrice;
        this.rent = rent;
    }

    @Override
    public int computeRent(Monopoly monopoly) {
        if (isMortgaged()) {
            return 0;
        } else {
            if (housesCount == 0) {
                if (getGroup().ownsAllFields(getOwner())) {
                    return 2 * rent.getNormalRent();
                } else {
                    return rent.getNormalRent();
                }
            } else if (housesCount == 1) {
                return rent.getOneHouseRent();
            } else if (housesCount == 2) {
                return rent.getTwoHousesRent();
            } else if (housesCount == 3) {
                return rent.getThreeHousesRent();
            } else if (housesCount == 4) {
                return rent.getFourHousesRent();
            } else if (housesCount == 5) {
                return rent.getHotelRent();
            } else {
                throw new RuntimeException("Zu viele Haeuser auf " + getName());
            }
        }
    }

    @Override
    public String toString() {
        return "Feld: " + getName() + "\r\nGruppe: " + getGroup().getName()
                + "\r\nBesitzer: " + getOwner().getName() + "\r\nKosten: "
                + getPrice() + "\r\nHauskosten: " + housePrice
                + "\r\nMiete:\r\n" + rent.toString();
    }
}
