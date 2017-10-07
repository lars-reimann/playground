import java.util.Scanner;

/**
 * Dieses Programm testet, ob ein gegebenes Wort ein Palindrom ist,
 * also ob es von vorne und von hinten gelesen gleich ist.
 * 
 * @version 22. Juli 2010 
 * @author Lars Reimann
 */
public class Palindrome {

	public static void main (String [] args) {
		Scanner scanner = new Scanner (System.in);
		int length;
		char fromStart;
		char fromEnd;
		String word;
		String wordLowerCase;
		boolean palindrom = true;
		
		System.out.println("Bitte geben Sie das Wort ein!");
		word = scanner.next();
		
		/* Gross-/Kleinschreibung wird ignoriert. */
		wordLowerCase = word.toLowerCase();
		length = word.length();
		for (int x = 0; x <= length - 1; x ++) {
			
			/* 
			 * Die Charaktere von vorne und von hinten aus gezaehlt werden
			 * der Reihe nach auf Gleichheit ueberprueft. 
			 */
			fromStart = wordLowerCase.charAt(0 + x);
			fromEnd = wordLowerCase.charAt(length - 1 - x);
			if (fromStart != fromEnd) {
				palindrom = false;
				break;
			}
		}
		if (palindrom == true) {
			System.out.println(word + " ist ein Palindrom!");
		} else {
			System.out.println(word + " ist kein Palindrom!");
		}
		System.out.print("\nZum Beenden Enter druecken!");
		scanner.nextLine();
		scanner.nextLine();
		System.exit(0);
	}
}
