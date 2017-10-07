import java.util.*;

/**
 * Diese Klasse fuellt eine Map mit Zufallzahlen und deren Haeufigkeit.
 * Der Nutzer kann dabei waehlen, wie oft ein Zufallszahl erzeugt werden
 * soll und was der maximal zu erzeugende Wert ist. Wird etwa als Maximum
 * 5 eingeben, werden Werte zwischen 1 und 5 erzeugt. <br />
 * Ab einer Anzahl von 1.000.000 Durchlaeufen wird ausserdem ein Ladebalken
 * eingeblendet, der den Fortschritt des Programm anzeigt. Nach jedem Prozent
 * Fortschritt wird ein "=" ausgegeben und alle 10 Prozent Fortschritt wird
 * ein 
 * 
 * @version 17. Juli 2010
 * @author Lars Reimann
 */
public class Zufallszahlen {
	
	/** 
	 * In dieser Treemap werden Zufallszahlen und ihre Haeufigkeit 
	 * gespeichert. 
	 */
	private static TreeMap <Integer, Integer> map = new TreeMap <Integer, Integer>();
	
	private static int mostCommonValue = 0;
	private static int mostCommonKey = 0;	
	private static int leastCommonValue;
	private static int leastCommonKey = 0;
	private static int max;
	private static double count;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);	
		
		/* Benutzereingaben */
		System.out.println("Wie oft sollen Zufallswerte erzeuget werden?");
		count = scanner.nextInt();
		leastCommonValue = (int) count + 1;
		System.out.println("Was ist der maximal zu erzeugende Wert?");
		max = scanner.nextInt();
		
		fillMap();
		
		printResult();
		
		System.out.print("\nZum Beenden Enter druecken!");
		scanner.nextLine();
		scanner.nextLine();
		System.exit(0);
    }
	
	private static void fillMap() {
		Random ran = new Random ();
		int key;
		
		for (Integer x = 1; x <= count; x++) {			
			key = ran.nextInt(max) + 1;
			map.put(key, setValue (key));
			if (count >= 1000000) {
				if (x % (count / 100) == 0) {
					System.out.print("=");
				}
				if (x % (count / 10) == 0) {
					System.out.print(x / (count / 100) + "%");
				}
			}		
		}
		System.out.println("\n");
	}
	
	private static int setValue(int key) {
		return (map.containsKey(key) ? map.get(key) + 1 : 1);
	}
	
	private static void setMostLeast(int key, int value) {
		if (value > mostCommonValue) {
			mostCommonValue = value;
			mostCommonKey = key;
		}
		if (value < leastCommonValue) {
			leastCommonValue = value;
			leastCommonKey = key;
		}
	}
	
	private static void printResult() {
		double percent;	
		Iterator<Map.Entry<Integer, Integer>> it = map.entrySet().iterator();
		Map.Entry <Integer, Integer> entry;
		
		while (it.hasNext()) {
			entry = (Map.Entry<Integer, Integer>) it.next();
			percent = (double)entry.getValue() / (count / 100);
			System.out.println(entry.getKey() + ":\t" 
					+ entry.getValue() + "\t " + percent + "%");
			setMostLeast(entry.getKey(), entry.getValue());
		}
		
		percent = (double) (mostCommonValue - leastCommonValue) 
				  / (count / 100);
		
		System.out.println("\n\nHaufigster Schluessel:\t" + mostCommonKey 
						   + "\t(" + mostCommonValue + "-mal)");
		System.out.println("Seltenster Schluessel:\t" + leastCommonKey
						   + "\t(" + leastCommonValue + "-mal)");
		System.out.println("Differenz:\t\t\t " 
						   + (mostCommonValue - leastCommonValue));
		System.out.println("Differenz in Prozent:\t\t " + percent + "%");
	}
}