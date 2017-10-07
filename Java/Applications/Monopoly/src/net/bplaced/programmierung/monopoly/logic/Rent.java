package net.bplaced.programmierung.monopoly.logic;

/**
 * Kapselt die verschiedenen Mieten (normal, ein Haus, zwei H&auml;user, ...,
 * Hotel) in einem Objekt zusammen.
 * 
 * @version 12. Februar 2012
 * @author Lars Reimann
 */
public class Rent {

    /**
     * Die Miete f&uuml;r ein Feld mit vier H&auml;usern.
     */
    private final int fourHousesRent;

    /**
     * Die Miete f&uuml;r ein Feld mit einem Hotel.
     */
    private final int hotelRent;

    /**
     * Die Miete f&uuml;r ein Feld ohne H&auml;user.
     */
    private final int normalRent;

    /**
     * Die Miete f&uuml;r ein Feld mit einem Haus.
     */
    private final int oneHouseRent;

    /**
     * Die Miete f&uuml;r ein Feld mit drei H&auml;usern.
     */
    private final int threeHousesRent;

    /**
     * Die Miete f&uuml;r ein Feld mit zwei H&auml;usern.
     */
    private final int twoHousesRent;

    /**
     * Erstellt ein neues Objekt dieses Typs mit den &uuml;bergebenen Werten.
     * 
     * @param normalRent
     *            Normale Miete.
     * @param oneHouseRent
     *            Miete mit einem Haus.
     * @param twoHousesRent
     *            Miete mit zwei H&auml;usern.
     * @param threeHousesRent
     *            Miete mit drei H&auml;usern.
     * @param fourHousesRent
     *            Miete mit vier H&auml;usern.
     * @param hotelRent
     *            Miete mit einem Hotel.
     */
    public Rent(int normalRent, int oneHouseRent, int twoHousesRent,
            int threeHousesRent, int fourHousesRent, int hotelRent) {
        this.normalRent = normalRent;
        this.oneHouseRent = oneHouseRent;
        this.twoHousesRent = twoHousesRent;
        this.threeHousesRent = threeHousesRent;
        this.fourHousesRent = fourHousesRent;
        this.hotelRent = hotelRent;
    }

    /**
     * Gibt die Miete zur&uuml;ck, die f&uuml;r ein Feld zu zahlen ist, auf dem
     * vier H&auml;ser stehen.
     * 
     * @return Miete mit vier H&auml;sern.
     */
    public int getFourHousesRent() {
        return fourHousesRent;
    }

    /**
     * Gibt die Miete zur&uuml;ck, die f&uuml;r ein Feld zu zahlen ist, auf dem
     * ein Hotel steht.
     * 
     * @return Miete mit einem Hotel.
     */
    public int getHotelRent() {
        return hotelRent;
    }

    /**
     * Gibt die Miete zur&uuml;ck, die f&uuml;r ein Feld zu zahlen ist, auf dem
     * kein Haus steht.
     * 
     * @return Miete ohne H&auml;ser.
     */
    public int getNormalRent() {
        return normalRent;
    }

    /**
     * Gibt die Miete zur&uuml;ck, die f&uuml;r ein Feld zu zahlen ist, auf dem
     * ein Haus steht.
     * 
     * @return Miete mit einem Haus.
     */
    public int getOneHouseRent() {
        return oneHouseRent;
    }

    /**
     * Gibt die Miete zur&uuml;ck, die f&uuml;r ein Feld zu zahlen ist, auf dem
     * drei H&auml;ser stehen.
     * 
     * @return Miete mit drei H&auml;sern.
     */
    public int getThreeHousesRent() {
        return threeHousesRent;
    }

    /**
     * Gibt die Miete zur&uuml;ck, die f&uuml;r ein Feld zu zahlen ist, auf dem
     * zwei H&auml;ser stehen.
     * 
     * @return Miete mit zwei H&auml;sern.
     */
    public int getTwoHousesRent() {
        return twoHousesRent;
    }
}
