import java.util.Scanner;

/**
 * Dieses Programm berechnet alle Faktoren einer gegeben Zahl und gibt
 * diese auf der Konsole aus.
 * 
 * @version 22. Juli 2010 
 * @author Lars Reimann
 */
public class Factors {

	public static void main(String[] args) {
		Scanner scanner = new Scanner (System.in);
		long number;
		StringBuilder stringBuilder = new StringBuilder ();
		
		System.out.println("Bitte geben Sie eine Zahl ein!");
		number = scanner.nextInt();
		System.out.println("Faktoren von " + number + ":");
		for (int x = 1; x <= number; x++) {	
			if (number % x == 0 && x != number) {
				stringBuilder.append(x + ", ");					
			} else if (x == number) {
				System.out.println(stringBuilder.append(x));
				break;
			}
			
			/*
			 * Wenn die Laenge des StringBuilder Objekts 50 ueberschreitet,
			 * wird stringBuilder ausgegeben und anschliessend geloescht. So
			 * kann eine mehr oder weniger gleichmaessige Zeilenlaenge bei 
			 * der Ausgabe erzeugt werden.
			 */
			if (stringBuilder.length() >= 50) {
				System.out.println(stringBuilder);
				stringBuilder.delete(0, stringBuilder.length());
			}
		}
		System.out.print("\nZum Beenden Enter druecken!");
		scanner.nextLine();
		scanner.nextLine();
		System.exit(0);
	}
}