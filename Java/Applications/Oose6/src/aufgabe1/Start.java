package aufgabe1;

/**
 * Oose Aufgabe 1
 * 
 * @version 31.05.2013
 * @author Florian Gläßer, Tobias Lenz, Lars Reimann
 */
public class Start {
	public static void main(String[] args) {
		LautesTier tier1 = new Katze();
		LautesTier tier2 = new Hund();
		for (int i = 1; i <= 10; i++) {
			tier1.gibLaut();
			tier2.gibLaut();
		}
	}
}
