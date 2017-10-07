package aufgabe4;

/**
 * Oose Aufgabe 4
 * 
 * @version 31.05.2013
 * @author Florian Gläßer, Tobias Lenz, Lars Reimann
 */
public class StartC {
	public static void main(String[] args) {
		Car car = new Car("EN-TE-42", "Dagobert Duck", "Entenhausen");
		TrafficOffice office = new TrafficOffice();
		office.registerCar(car);
		TrafficWarden warden = new TrafficWarden(office);
		warden.checkCar("EN-TE-42");
		warden.fine();
		warden.fine();
		warden.fine();
		warden.printTicket();
	}
}
