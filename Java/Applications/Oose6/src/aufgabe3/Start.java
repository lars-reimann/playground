package aufgabe3;

/**
 * Oose Aufgabe 3
 * 
 * @version 31.05.2013
 * @author Florian Gläßer, Tobias Lenz, Lars Reimann
 */
public class Start {
	public static void main(String[] args) {
		Schaf schaf1 = new Schaf("Dolly");
		Schaf schaf2 = schaf1.klone();
		// schaf2.setName("Klaus");
		// System.out.println(schaf1.getName());

		if (schaf1.isCloneOf(schaf2)) {
			System.out.println(schaf1 + " ist ein Klon von " + schaf2 + ".");
		} else {
			System.out.println(schaf1 + " ist kein Klon von " + schaf2 + ".");
		}
	}
}
