package aufgabe3;

/**
 * Oose Aufgabe 3
 * 
 * @version 05.06.2013
 * @author Florian Gläßer, Tobias Lenz, Lars Reimann
 */
public abstract class AbstractPlant implements Plant {

	/**
	 * Wasserstand der Pflanze.
	 */
	protected int water;

	/**
	 * Höhe der Pflanze.
	 */
	protected int height;

	/**
	 * Ob die Pflanze gedüngt wurde.
	 */
	protected boolean isFertilized;

	public AbstractPlant() {
		water = 0;
		height = 1;
	}

	public AbstractPlant(int height) {
		water = 0;
		this.height = height;
	}

	@Override
	public void water(int amount) {
		water += amount;
	}

	@Override
	public void water(int amount, boolean useFertilizer) {
		water += amount;
		isFertilized |= useFertilizer;
	}

	@Override
	public void grow(int amount) {
		height += amount;
	}
}
