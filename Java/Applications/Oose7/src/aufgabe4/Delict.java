package aufgabe4;

/**
 * Oose Aufgabe 4
 * 
 * @version 31.05.2013
 * @author Florian Gläßer, Tobias Lenz, Lars Reimann
 */
public class Delict {

	/**
	 * Der Betrag der Geldstrafe. Vorsicht, ungenau!
	 */
	private final double fee;

	/**
	 * Die Punktestrafe für das Vergehen.
	 */
	private final int points;

	/**
	 * @param fee
	 *            Der Betrag der Geldstrafe. Vorsicht, ungenau!
	 * @param points
	 *            Die Punktestrafe für das Vergehen.
	 */
	public Delict(double fee, int points) {
		this.fee = fee;
		this.points = points;
	}

	public double getFee() {
		return fee;
	}

	public int getPoints() {
		return points;
	}
}
