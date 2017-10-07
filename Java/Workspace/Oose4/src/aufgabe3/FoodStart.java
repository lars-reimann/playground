package aufgabe3;

/**
 * Oose Aufgabe 3
 * 
 * @version 11.05.2013
 * @author Florian Gläßer, Tobias Lenz, Lars Reimann
 */
public class FoodStart {
	public static void main(String[] args) {
		Food food;
		food = new Cauliflower();
		System.out.println(food.getMeal());
		food = new Spaghetti();
		System.out.println(food.getMeal());
	}
}
