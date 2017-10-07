package aufgabe4;

/**
 * Oose Aufgabe 4
 * 
 * @version 31.05.2013
 * @author Florian Gläßer, Tobias Lenz, Lars Reimann
 */
public class StartD {
	public static void main(String[] args) {
		mainHelper("EN-TE-42", "Dagobert Duck", "Entenhausen");
		System.out.println("\n\n");
		mainHelper("DD-313", "Donald Duck", "Entenhausen");
	}
	
	/**
	 * @param licensePlate Kennzeichen des Autos.
	 * @param owner Besitzer des Autos.
	 * @param address Adresse des Besitzers.
	 */
	private static void mainHelper(String licensePlate, String owner, String address) {
		Car car = new Car(licensePlate, owner, address);
		TrafficOffice office = new TrafficOffice();
		office.registerCar(car);
		TrafficWarden warden = new TrafficWarden(office);
		warden.checkCar(licensePlate);
		warden.fine();
		warden.fine();
		warden.fine();
		warden.printTicket();
	}
}
