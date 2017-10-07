package aufgabe4;

import java.util.HashSet;
import java.util.Set;

/**
 * Oose Aufgabe 4
 * 
 * @version 12.06.2013
 * @author Florian Gläßer, Tobias Lenz, Lars Reimann
 */
public class Catalogue {

	public static void main(String[] args) {
		Catalogue catalogue = new Catalogue();
		catalogue.add(
			new Product.Builder()
				.name("Papier")
				.desc("A4")
				.price(2.52)
				.build()
		);
		catalogue.add(
			new Clothing.Builder()
				.name("Hemd")
				.desc("Herren-Hemd")
				.price(33.61)
				.size(48)
				.material("Baumwolle")
				.build()
		);
		catalogue.printMeWithTax();
	}

	/**
	 * Eine Liste der Produkte in diesem Katalog.
	 */
	private Set<Product> products = new HashSet<Product>();
	
	/**
	 * Fügt ein Produkt zum Katalog hinzu.
	 */
	public void add(Product product) {
		products.add(product);
	}

	/**
	 * Druckt eine Liste mit Informationen über die einzelnen Produkte.
	 */
	public void printMe() {
		for (Product product : products) {
			System.out.println(product);
		}
	}

	/**
	 * Druckt eine Liste mit Informationen über die einzelnen Produkte. Es 
	 * werden zusätzlich die Preise inklusive Mehrwertsteuer ausgedruckt.
	 */
	public void printMeWithTax() {
		for (Product product : products) {
			System.out.println(product + " - " + product.getPriceWithTax());
		} 
	}
}
