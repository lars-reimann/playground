package net.bplaced.programmierung.monopoly.logic;

/**
 * Ein Stra&szlig;enfeld, das gekauft werden und auf dem H&auml;ser gebaut
 * werden k&ouml;nnen. Die Miete ist abh&auml;ngig von der Anzahl der H&auml;ser
 * und der Anzahl der besessen H&auml;ser in dieser Gruppe.
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
     * Die Anzahl der H&auml;ser auf diesem Feld.
     */
    private int housesCount;

    /**
     * Die Mieten, die f&auml;llig werden, wenn ein Spieler auf dieses Feld
     * kommt, dem es nicht geh&ouml;rt.
     */
    private Rent rent;

    /**
     * Erstellt ein neues Stra&szlig;enfeld mit den &uuml;bergebenen Werten.
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
