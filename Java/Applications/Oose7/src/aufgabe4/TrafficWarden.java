package aufgabe4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 * Oose Aufgabe 4
 * 
 * @version 31.05.2013
 * @author Florian Gläßer, Tobias Lenz, Lars Reimann
 */
public class TrafficWarden {
	
	/**
	 * Das zu untersuchende Auto.
	 */
	private Car carToCheck;
	
	/**
	 * Die Verkehrsbehörde, für die diese Person arbeitet.
	 */
	private final TrafficOffice office;

	/**
	 * @param office Die Verkehrsbehörde, für die diese Person arbeitet.
	 */
	public TrafficWarden(TrafficOffice office) {
		this.office = office;
	}

	/**
	 * Setzt das zu untersuchende Auto auf das mit dem angegebenen Kennzeichen.
	 *
	 * @param licensePlate das Kennzeichen des zu untersuchenden Autos.
	 */
	public void checkCar(String licensePlate) {
		carToCheck = office.getCar(licensePlate);
	}

	/**
	 * Weist dem untersuchten Auto eine zuf&auml;llige Strafe zu. Dabei sind
	 * Geldstrafen zwischen 10 und 100 Euro einschlie&szlig;lich m&ouml;glich.
	 * Ferner werden 0-3 Punkte verteilt.
	 */
	public void fine() {
		Random random = new Random();
		int fee = 10 + random.nextInt(91);
		int points = random.nextInt(4);
		carToCheck.registerDelict(new Delict(fee, points));
	}

	/**
	 * Gibt ein Array mit vier Strings zurück. Der erste ist eine Liste der
	 * Geldstrafen, der zweite eine Liste der Punktestrafen. Dann folgt die
	 * gesamte Geldstrafe und die gesamte Punktestrafe.
	 *
	 * @return ein Array mit vier Strings.
	 */
	private String[] delictStrings() {
		StringBuilder fees = new StringBuilder();
		StringBuilder points = new StringBuilder();
		int totalFee = 0;
		int totalPoints = 0;
		Delict[] delicts = carToCheck.getDelicts();

		for (int i = 0; i < delicts.length; i++) {
			double currentFee = delicts[i].getFee();
			int currentPoints = delicts[i].getPoints();
			fees.append((i + 1) + ": " + currentFee);
			points.append((i + 1) + ": " + currentPoints);
			if (i < delicts.length - 1) {
				fees.append("\n");
				points.append("\n");
			}
			totalFee += currentFee;
			totalPoints += currentPoints;
		}

		return new String[] { fees.toString(), points.toString(),
				"" + totalFee, "" + totalPoints };
	}

	/**
	 * Druckt den Strafzettel. Dabei wird der Name des Ungl&uuml;cklichen, seine
	 * Adresse und eine Liste seiner Vergehen ausgegeben.
	 */
	public void printTicket() {
		try {
			Scanner scanner = new Scanner(new File("template.txt"));
			scanner.useDelimiter("\\Z"); // \Z means end of input
			String template = scanner.next();
			scanner.close();

			template = template.replaceAll("#Contact#", carToCheck.toString());
			String[] delictStrings = delictStrings();
			template = template.replaceAll("#FeeList#", delictStrings[0]);
			template = template.replaceAll("#PointsList#", delictStrings[1]);
			template = template.replaceAll("#TotalFee#", delictStrings[2]);
			template = template.replaceAll("#TotalPoints#", delictStrings[3]);

			System.out.println(template);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
