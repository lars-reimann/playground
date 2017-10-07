package aufgabe3;

/**
 * Oose Aufgabe 3
 * 
 * @version 05.06.2013
 * @author Florian Gläßer, Tobias Lenz, Lars Reimann
 */
public interface Plant {
	
	/**
	 * Pflanze wächst um Standardwert.
	 */
	void grow();
	
	/**
	 * Pflanze erhält eine Standardmenge Wasser.
	 */
	void water();
	
	/**
	 * Pflanze erhält festgelegte Menge Wasser und wird eventuell mit gedüngt.
	 *
	 * @param amount Menge des Wassers.
	 * @param useFertilizer Ob Dünger benutzt werden soll.
	 */
	void water(int amount, boolean useFertilizer);
	
	/**
	 * Pflanze erhält festgelegte Menge Wasser.
	 *
	 * @param amount Menge des Wassers.
	 */
	void water(int amount);
	
	/**
	 * Pflanze wächst um festgelegten Betrag.
	 *
	 * @param amount Betrag des Wachstums.
	 */
	void grow(int amount);
}
