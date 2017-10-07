package aufgabe3;

/**
 * Oose Aufgabe 3
 * 
 * @version 31.05.2013
 * @author Florian Gläßer, Tobias Lenz, Lars Reimann
 */
public class DNA {

	/**
	 * Code der nächsten DNA.
	 */
	private static int nextDNA = 0;

	/**
	 * Code dieser DNA.
	 */
	private final int code;

	/**
	 * Static factory für DNA Objekte. Es sind maximal 2^32 einzigartige Objekte
	 * erzeugbar.
	 *
	 * @return ein neues DNA Objekt.
	 */
	public static DNA getUniqueDNA() {
		DNA dna = new DNA();
		nextDNA++;
		return dna;
	}

	private DNA() {
		this.code = nextDNA;
	}

	public int getCode() {
		return code;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof DNA)) {
			return false;
		}
		DNA dna = (DNA) obj;
		return code == dna.code;
	}

	@Override
	public int hashCode() {
		return 31 * 17 + code;
	}

	@Override
	public String toString() {
		return "" + code;
	}
}
