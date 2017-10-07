import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * Berechnet die vergangene Zeit in Sekunden, Minuten, usw. von einem
 * gegebenen Datum bis zum heutigen Tag.
 * 
 * @version 9. August 2010 
 * @author Lars Reimann
 */
public class TimePeriod {

	public static void main(String[] args) {	
		Scanner scanner = new Scanner (System.in);
		GregorianCalendar now = new GregorianCalendar ();
		GregorianCalendar input = new GregorianCalendar ();
		double seconds = 0;
		double minutes = 0;
		double hours = 0;
		double days = 0;
		double years = 0;
		
		System.out.println("Jahr:");
		int year = scanner.nextInt();
		System.out.println("Monat:");
		int month = scanner.nextInt();
		System.out.println("Tag:");
		int date = scanner.nextInt();
		System.out.println("Stunde:");
		int hourOfDay = scanner.nextInt();
		System.out.println("Minute:");
		int minute = scanner.nextInt();
		System.out.println("Sekunde:");
		int second = scanner.nextInt();
		
		input.set(year, (month - 1), date, hourOfDay, minute, second);
		seconds = (now.getTimeInMillis () - input.getTimeInMillis ()) / 1000;
		while (seconds >= 60) {
			minutes ++;
			seconds -= 60;
		}
		while (minutes >= 60) {
			hours ++;
			minutes -= 60;
		}
		while (hours >= 24) {
			days ++;
			hours -= 24;
		}
		System.out.println ((int) days + " Tage, " + (int) hours + " Stunden, "
				+ (int) minutes + " Minuten, " + (int) seconds + " Sekunden.");
		while (days >= 365) {
			years ++;
			days -= 365;
		}
		System.out.println ("~" + (int) years + " Jahre, " + (int) days + " Tage, "
				+ (int) hours + " Stunden, " + (int) minutes + " Minuten, " 
				+ (int) seconds + " Sekunden.");
		
		System.out.print("\nZum Beenden Enter druecken!");
		scanner.nextLine();
		scanner.nextLine();
		System.exit(0);
	}
}