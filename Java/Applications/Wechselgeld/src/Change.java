import java.util.Scanner;

/**
 * Dieses Programm berechnet das optimale Wechselgeld, sodass man
 * moeglichst wenig Scheine und Muenzen erhaelt.
 * 
 * @version 22. Juli 2010 
 * @author Lars Reimann
 */
public class Change {

	static int count [] = new int [15];
	static int changeEuro;
	static int changeCent;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner (System.in);
		double inputToPay = inputToPay();
		double inputGiven = inputGiven();		
		changeEuro = (int) (inputGiven - inputToPay);		
		changeCent = (int) ((inputGiven - inputToPay
							 - (int) (inputGiven - inputToPay)) * 100);		
		
		/* Volle Eurobetraege */
		count [0] = calculate(500, false);
		output("500-Euroschein(e)", 0);
		count [1] = calculate(200, false);
		output("200-Euroschein(e)", 1);
		count [2] = calculate(100, false);
		output("100-Euroschein(e)", 2);
		count [3] = calculate(50, false);
		output("50-Euroschein(e)", 3);
		count [4] = calculate(20, false);
		output("20-Euroschein(e)", 4);
		count [5] = calculate(10,false );
		output("10-Euroschein(e)", 5);
		count [6] = calculate(5, false);
		output("5-Euroschein(e)", 6);
		count [7] = calculate(2, false);
		output("2-Eurostueck(e)", 7);
		count [8] = calculate(1, false);
		output("1-Eurostueck(e)", 8);
		
		/* Centbetraege */
		count [9] = calculate(50, true);
		output("50-Centstueck(e)", 9);
		count [10] = calculate(20, true);
		output("20-Centstueck(e)", 10);
		count [11] = calculate(10, true);
		output("10-Centstueck(e)", 11);
		count [12] = calculate(5, true);
		output("5-Centstueck(e)", 12);
		count [13] = calculate(2, true);
		output("2-Centstueck(e)", 13);
		count [14] = calculate(1, true);
		output("1-Centstueck(e)", 14);
		
		System.out.print("\nZum Beenden Enter druecken!");
		scanner.nextLine();
		System.exit(0);
	}
	
	/**
	 * Der Nutzer gibt den zu bezahlenden Betrag ein.
	 * 
	 * @return Den zu bezahlenden Betrag
	 */
	static double inputToPay() {		
		Scanner scanner = new Scanner (System.in);
		
		System.out.println("Wie viel Euro muessen Sie bezahlen?");
		return scanner.nextDouble();
	}
	
	/**
	 * Der Nutzer gibt den gegebenen Betrag ein.
	 * 
	 * @return Den gegebenen Betrag
	 */
	static double inputGiven () {		
		Scanner scanner = new Scanner (System.in);
		
		System.out.println("Wie viel Euro geben Sie?");
		return scanner.nextDouble();
	}
	
	/**
	 * Berechnet die noetige Menge eines Scheins/einer Muenze
	 * 
	 * @param amount Geldwert dieses Scheins/dieser Muenze
	 * @param cent Ob der Geldwert in Cent oder Euro angegeben ist
	 * @return Wie oft dieser Betrag benoetigt wird
	 */
	static int calculate(int amount, boolean cent) {		
		int count = 0;
		
		if (cent == false) {
			while (changeEuro >= amount) {
				count ++;
				changeEuro -= amount;
			}
			return count;
		}
		else {
			while (changeCent >= amount) {
				count ++;
				changeCent -= amount;
			}
			return count;
		}	
	}
	
	/**
	 * Gibt den Namen des Scheins/der Muenze und dessen Anzahl aus,
	 * falls er mindestens einmal benoetigt wird.
	 * 
	 * @param type Der Name des Scheins/der Muenze
	 * @param x Der Arrayplatz dieses Scheins/dieser Muenze
	 */
	static void output(String type, int x) {		
		if (count [x] > 0) System.out.println (type + ": " + count [x]);
	}
}