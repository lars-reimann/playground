package aufgabe5;

import java.util.HashMap;

/**
 * Oose Aufgabe 5
 * 
 * @version 11.05.2013
 * @author Florian Gläßer, Tobias Lenz, Lars Reimann
 */
public class TrafficOffice {

	/**
	 * Die registrierten Autos. Sie können über ihr Kennzeichen angesprochen
	 * werden.
	 */
	private HashMap<String, Car> cars = new HashMap<String, Car>();

	public Car getCar(String licensePlate) {
		return cars.get(licensePlate);
	}

	/**
	 * Registriert ein neues Auto bei der Verkehrsbehörde. Sollte schon ein Auto
	 * mit dem gleichen Kennzeichen gemeldet gewesen sein, wird dieses ohne
	 * Warnung überschrieben.
	 * 
	 * @param car
	 *            Das zu registrierende Auto.
	 */
	public void registerCar(Car car) {
		cars.put(car.getLicensePlate(), car);
	}
}
