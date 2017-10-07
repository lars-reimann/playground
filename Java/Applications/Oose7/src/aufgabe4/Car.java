package aufgabe4;

import java.util.Arrays;

/**
 * Oose Aufgabe 4
 * 
 * @version 31.05.2013
 * @author Florian Gläßer, Tobias Lenz, Lars Reimann
 */
public class Car {

	/**
	 * Die Adresse des Besitzers.
	 */
	private final String address;

	/**
	 * Liste der Vergehen des Autobesitzers.
	 */
	private final Delict[] delicts = new Delict[500];

	/**
	 * Einfügeposition des nächsten Vergehens.
	 */
	private int insertPos = 0;

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
	public Car(String licensePlate, String owner, String address) {
		this.address = address;
		this.licensePlate = licensePlate;
		this.owner = owner;
	}

	public String getAddress() {
		return address;
	}

	public Delict[] getDelicts() {
		return Arrays.copyOf(delicts, insertPos);
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public String getOwner() {
		return owner;
	}

	/**
	 * Registriert ein Vergehen für dieses Auto.
	 *
	 * @param delict Das angeblich begangene Vergehen.
	 */
	public void registerDelict(Delict delict) {
		delicts[insertPos++] = delict;
	}

	@Override
	public String toString() {
		return String.format("Kennzeichen: %s\nHalter: %s\nAdresse: %s",
				licensePlate, owner, address);
	}
}
