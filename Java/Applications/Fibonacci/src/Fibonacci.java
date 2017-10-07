import java.math.BigDecimal;
import java.util.Scanner;

/**
 * Dieses Programm gibt beliebig viele Werte der Fibonacci-Folge auf der
 * Konsole aus.
 * 
 * @version 7. August 2010 
 * @author Lars Reimann
 */
public class Fibonacci {
	
	public static void main(String [] args) {			
		final BigDecimal start = new BigDecimal (1);
		int length;
		Scanner scanner = new Scanner (System.in);
		BigDecimal [] numbers = {start, start};
		BigDecimal temp;
		StringBuilder stringBuilder = new StringBuilder ();
		
		System.out.println("Wie viele Werte sollen ausgegeben werden?");
		length = scanner.nextInt();
		
		for (int x = 1; x <= length; x ++) {
			if (x != length) {
				stringBuilder.append(numbers [0] + ", ");					
			} else if (x == length) {
				System.out.println(stringBuilder.append (numbers [0]));
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
			
			/* 
			 * Die beiden Arrayplaetze werden jeweils mit dem naechsten 
			 * Wert der Folge bestueckt.
			 */
			temp = numbers [0].add(numbers [1]);
			numbers [0] = numbers [1];
			numbers [1] = temp;
		}
		System.out.print("\nZum Beenden Enter druecken!");
		scanner.nextLine();
		scanner.nextLine();
		System.exit(0);
	}
}