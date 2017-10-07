public class Aufgabe2 {
	public static void main(String[] args) {
		int[] zahlen = { 1, 2, 3, 10, 25, 36, 70 };
		for (int i = 1; i <= zahlen.length; i++) {
			if (zahlen[i] == 25) {
				System.out.println("Die Zahl wurde gefunden!");
			}
		}
	}
}
