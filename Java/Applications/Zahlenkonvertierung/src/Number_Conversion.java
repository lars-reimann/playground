import java.util.Scanner;

/**
 * Dieses Programm konvertiert eine Zahl mit beliebiger Basis in die vier
 * gaengigen Zahlensysteme (Binaer-, Oktal-, Dezimal-, Hexadezimalsystem).
 * 
 * @version 22. Juli 2010 
 * @author Lars Reimann
 */
public class Number_Conversion {
	
	public static void main(String [] args)
	throws InterruptedException {
		Scanner scanner = new Scanner (System.in);
		String numberString;
		int number;
		int base;
		
		/* 
		 * Solange die Zahl nicht existiert, also Ziffern enthalten sind,
		 * die fuer das gewaehlte Zahlensystem nicht vorgesehen sind, wird
		 * die Schleife wiederholt.
		 */
		while (true) {
			System.out.println("Bitte geben Sie eine Zahl ein!");
			numberString = scanner.next();
			System.out.println("Zu welcher Basis ist die eingegebenen "
								+ "Zahl?");
			base = scanner.nextInt();
			try {
				number = Integer.parseInt(numberString, base);
			} catch (NumberFormatException e) {
				System.out.println();
				Thread.sleep(100);
				System.err.println("***Eine solche Zahl existiert " 
								   + "nicht!***");
				System.err.println("***Bitte ueberpruefen Sie Ihre " 
								   + "Eingabe!***");
				Thread.sleep(100);
				System.out.println();
				continue;
			}
			break;
		}
		
		System.out.println("Binaer: " + Integer.toBinaryString(number));
		System.out.println("Oktal: " + Integer.toOctalString(number));
		System.out.println("Dezimal: " + number);
		System.out.println("Hexadezimal: " + Integer.toHexString(number));
		
		System.out.print("\nZum Beenden Enter druecken!");
		scanner.nextLine();
	    scanner.nextLine();
		System.exit(0);
	}
}