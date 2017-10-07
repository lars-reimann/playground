import java.io.*;
import java.util.*;

public class LetterCounter {

	public static void main(String[] args) {		
		BufferedReader reader;
		TreeMap <Character, Integer> map = new TreeMap <Character, Integer> ();
		Iterator <Character> keyIt;
		Iterator <Integer> valueIt;
		Scanner scanner = new Scanner(System.in);
		String file;
		int currentCharacter;
		int value;
		
		System.out.println("Bitte geben Sie den Pfad zu der Datei ein!");
		file = scanner.nextLine();
		
		try {
			reader = new BufferedReader(new FileReader(file));
			while ((currentCharacter = reader.read()) != -1) {
				if (map.containsKey((char) currentCharacter)) {
					value = map.get((char) currentCharacter) + 1;
				} else {
					value = 1;
				}
				map.put((char) currentCharacter, value);
			}
		} catch (FileNotFoundException e) {
			System.err.println("***Datei wurde nicht gefunden!***");
		} catch (IOException e) {
			System.err.println("***Fehler beim Lesen der Datei!***");
		}
		
		keyIt = map.keySet().iterator();
		valueIt = map.values().iterator();
		
		while (keyIt.hasNext()) {
			System.out.println(keyIt.next() + "\t\t" + valueIt.next() + "-mal");
		}
	}
}
