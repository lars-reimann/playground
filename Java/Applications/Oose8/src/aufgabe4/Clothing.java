package aufgabe4;

/**
 * Oose Aufgabe 4
 * 
 * @version 12.06.2013
 * @author Florian Gläßer, Tobias Lenz, Lars Reimann
 */
public class Clothing extends Product {

	/**
	 * Die Größe der Kleidung.
	 */
	private int size;

	/**
	 * Das Material der Kleidung.
	 */
	private String material;

	/**
	 * @param name
	 *            Der Name der Kleidung.
	 * @param desc
	 *            Eine Beschreibung der Kleidung.
	 * @param price
	 *            Der Preis der Kleidung.
	 * @param size
	 *            Die Größe der Kleidung.
	 * @param material
	 *            Das Material der Kleidung.
	 */
	public Clothing(String name, String desc, double price, int size,
			String material) {
		super(name, desc, price);
		this.size = size;
		this.material = material;
	}

	/**
	 * Builder für Clothing Objekte.
	 * 
	 * @version 12.06.2013
	 * @author Florian Gläßer, Tobias Lenz, Lars Reimann
	 */
	public static class Builder {
		
		/**
		 * Die Größe, die die Kleidung haben soll.
		 */
		private int size;
		
		/**
		 * Die Beschreibung, die die Kleidung haben soll.
		 */
		private String desc;
		
		/**
		 * Der Name, den die Kleidung haben soll.
		 */
		private String name;
		
		/**
		 * Der Preis, den die Kleidung haben soll.
		 */
		private double price;
		
		/**
		 * Das Material, aus dem die Kleidung bestehen soll.
		 */
		private String material;

		/**
		 * Setzt die Größe, den die Kleidung haben soll.
		 *
		 * @return diesen Builder.
		 */
		public Builder size(int size) {
			this.size = size;
			return this;
		}

		/**
		 * Setzt das Material, aus dem die Kleidung bestehen soll.
		 * 
		 * @return diesen Builder.
		 */
		public Builder material(String material) {
			this.material = material;
			return this;
		}

		/**
		 * Setzt die Beschreibung, die die Kleidung haben soll.
		 *
		 * @return diesen Builder.
		 */
		public Builder desc(String desc) {
			this.desc = desc;
			return this;
		}

		/**
		 * Setzt den Preis, den die Kleidung haben soll.
		 * 
		 * @return diesen Builder.
		 */
		public Builder price(double price) {
			this.price = price;
			return this;
		}

		/**
		 * Setzt den Namen, den die Kleidung haben soll.
		 * 
		 * @return diesen Builder.
		 */
		public Builder name(String name) {
			this.name = name;
			return this;
		}

		/**
		 * Erzeugt ein neues Clothing Objekt, mit den bisher angegebenen Werten
		 * und gibt dieses zurück.
		 */
		public Clothing build() {
			return new Clothing(name, desc, price, size, material);
		}
	}

	@Override
	public String toString() {
		return super.toString() + " - " + size + " - " + material;
	}
}
