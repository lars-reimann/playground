package aufgabe3;

/**
 * Oose Aufgabe 3
 * 
 * @version 25.06.2013
 * @author Florian Gläßer, Tobias Lenz, Lars Reimann
 */
public class Start {
	public static void main(String[] args) {
		TList<Integer> list = new TList<Integer>();
		System.out.println(list);
		for (int i = 0; i < 10; i++) {
			list.insert(i, i);
			System.out.println(list);
		}
		for (int i = 0;  i < 10; i++) {
			list.delete(0);
			System.out.println(list);
		}
	}
}
