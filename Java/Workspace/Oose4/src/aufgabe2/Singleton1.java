package aufgabe2;

/**
 * Oose Aufgabe 2
 * 
 * @version 11.05.2013
 * @author Florian Gläßer, Tobias Lenz, Lars Reimann
 */
public class Singleton1 {

	/**
	 * Die einzige Instanz dieser Klasse.
	 */
	private static Singleton1 INSTANCE = new Singleton1();

	/**
	 * Gibt die einzige Instanz dieser Klasse zurück.
	 * 
	 * @return die einzige Instanz dieser Klasse.
	 */
	public static Singleton1 getInstance() {
		return INSTANCE;
	}

	/**
	 * Verhindert Instanziierung von außen.
	 */
	private Singleton1() {
	}
}
