package aufgabe4;

import aufgabe3.Cauliflower;
import aufgabe3.Food;
import aufgabe3.Spaghetti;

/**
 * Oose Aufgabe 4
 * 
 * @version 11.05.2013
 * @author Florian Gläßer, Tobias Lenz, Lars Reimann
 */
public class Student {
	public static void main(String[] args) {
		Student student = new Student();

		Cauliflower essen1 = new Cauliflower();
		student.eat(essen1);

		Spaghetti essen2 = new Spaghetti();
		student.eat(essen2);

		Food essen3 = new Cauliflower();
		student.eat(essen3);
	}

	/**
	 * Gibt eine Nachricht aus, dass der Student das Gericht nicht mag.
	 * 
	 * @param cauliflower
	 *            Ihh, Blumenkohl.
	 */
	public void eat(Cauliflower cauliflower) {
		System.out.println("I dont't like " + cauliflower.getMeal());
	}

	/**
	 * Gibt eine Nachricht aus, dass der Student das Gericht mag.
	 * 
	 * @param food
	 *            Das entsprechende Gericht.
	 */
	public void eat(Food food) {
		System.out.println("I like " + food.getMeal());
	}
}
