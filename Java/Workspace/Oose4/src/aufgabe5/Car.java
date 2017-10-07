package aufgabe5;

/**
 * Oose Aufgabe 5
 * 
 * @version 11.05.2013
 * @author Florian Gläßer, Tobias Lenz, Lars Reimann
 */
public class Car {

	/**
	 * Die Adresse des Besitzers.
	 */
	private final String address;

	/**
	 * Das Kennzeichen des Autos.
	 */
	private final String licensePlate;

	/**
	 * Der Besitzer des Autos.
	 */
	private final String owner;

	/**
	 * @param address
	 *            Die Adresse des Besitzers.
	 * @param licensePlate
	 *            Das Kennzeichen des Autos.
	 * @param owner
	 *            Der Besitzer des Autos.
	 */
	public Car(String address, String licensePlate, String owner) {
		this.address = address;
		this.licensePlate = licensePlate;
		this.owner = owner;
	}

	public String getAddress() {
		return address;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public String getOwner() {
		return owner;
	}
}
