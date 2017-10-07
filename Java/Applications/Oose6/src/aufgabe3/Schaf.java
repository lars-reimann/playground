package aufgabe3;

/**
 * Oose Aufgabe 3
 * 
 * @version 31.05.2013
 * @author Florian Gläßer, Tobias Lenz, Lars Reimann
 */
public class Schaf {

	/**
	 * DNA des Schafes.
	 */
	private DNA dna;

	/**
	 * Name des Schafes.
	 */
	private String name;

	/**
	 * Copy constructor, der eine neue Instanz dieser Klasse mit den Werten des
	 * angegebenen Schafs erstellt.
	 * 
	 * @param schaf
	 *            Das zu kopierende Schaf.
	 */
	public Schaf(Schaf schaf) {
		this.dna = schaf.dna;
		this.name = schaf.name;
	}

	/**
	 * Erstellt ein neues Schaf mit dem angegebenen Namen und einzigartiger DNA.
	 * 
	 * @param name
	 *            Der Name des Schafs.
	 */
	public Schaf(String name) {
		this.name = name;
		dna = DNA.getUniqueDNA();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Schaf)) {
			return false;
		}
		Schaf schaf = (Schaf) obj;
		 // Eigentlich müsste Name ausgenommen werden
		return dna.equals(schaf.dna) && name.equals(schaf.name);
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		int hash = 17;
		hash = 31 * hash + dna.hashCode();
		return 31 * hash + name.hashCode();
	}

	/**
	 * Gibt an, ob das angegebene Schaf ein Klon dieses Schafs ist.
	 * 
	 * @return ob das Schaf ein Klon dieses Schafes ist.
	 */
	public boolean isCloneOf(Schaf schaf) {
		/*
		 * Eigentlich falsch, da eine Änderung des Names eines Schafes dazu
		 * führen kann, dass diese Funktion einen anderen Wert zurückgibt.
		 */
		return this != schaf && this.equals(schaf);
	}

	/**
	 * Gibt einen Klon dieses Schafes zurück. Es ist garantiert, dass this ==
	 * this.klone() false ergibt, aber this.equals(this.klone()) zu true
	 * evaluiert.
	 * 
	 * @return einen Klon des Schafes.
	 */
	public Schaf klone() {
		// return this;
		return new Schaf(this);
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name + "(" + dna + ")";
	}
}
