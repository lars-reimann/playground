package aufgabe4;

/**
 * Oose Aufgabe 4
 * 
 * @version 12.06.2013
 * @author Florian Gläßer, Tobias Lenz, Lars Reimann
 */
public class Product {

	public static class Builder {

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
		 * Erzeugt ein neues Produkt Objekt mit den bisher angegebene Werten und
		 * gibt dieses zurück.
		 */
		public Product build() {
			return new Product(name, desc, price);
		}

		/**
		 * Setzt die Beschreibung, die das Produkt haben soll.
		 * 
		 * @return diesen Builder.
		 */
		public Builder desc(String desc) {
			this.desc = desc;
			return this;
		}

		/**
		 * Setzt den Namen, den das Produkt haben soll.
		 * 
		 * @return diesen Builder.
		 */
		public Builder name(String name) {
			this.name = name;
			return this;
		}

		/**
		 * Setzt den Preis, den das Produkt haben soll.
		 * 
		 * @return diesen Builder.
		 */
		public Builder price(double price) {
			this.price = price;
			return this;
		}
	}

	/**
	 * Eine Beschreibung des Produktes.
	 */
	protected String description;

	/**
	 * Der Name des Produktes.
	 */
	protected String name;

	/**
	 * Der Preis des Produktes.
	 */
	protected double price; // Netto

	/**
	 * @param name
	 *            Der Name des Produktes.
	 * @param desc
	 *            Eine Beschreibung des Produktes.
	 * @param price
	 *            Der Preis des Produktes.
	 */
	public Product(String name, String desc, double price) {
		this.name = name;
		this.description = desc;
		this.price = price;
	}

	/**
	 * Gibt den Preis des Produktes inklusive Mehrwertsteuer zurück.
	 */
	final double getPriceWithTax() {

		/*
		 * double sollte nicht für monetäre Werte benutzt werden. Besser wäre
		 * es, den Preis in Cent zu speichern.
		 */
		return price * 1.19;
	}

	@Override
	public String toString() {
		return name + " - " + description + " - " + price + " EUR";
	}
}
