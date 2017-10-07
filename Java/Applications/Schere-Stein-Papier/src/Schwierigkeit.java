import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

class Schwierigkeit {
	
	public static void zufall () {
		Scanner scanner = new Scanner (System.in);
		int zaehler = 0;
		int wahl = 0;
		int zufall = 0;
		int [] ergebnis = {0, 0, 0};
		System.out.println ("Wie oft moechten Sie spielen?");
		zaehler = scanner.nextInt ();
		for (int i = 1; i <= zaehler; i++) {
			do {
				System.out.println ("Was setzen Sie? (1 = Schere; 2 = Stein; 3 = Papier)");
				wahl = scanner.nextInt();
			}
			while (wahl != 1 && wahl != 2 && wahl != 3);
			do {
				zufall = (int) (Math.random () * 10 / 3);
			}
			while (zufall != 1 && zufall != 2 && zufall != 3);
			Gewonnen.waehrendSpiel(zufall, wahl, ergebnis);
		}
		Gewonnen.spielende (ergebnis);
	}
	
	public static void KI () throws IOException {		
		BufferedReader bufferFile = null;
		BufferedReader bufferInput = null;
		Scanner scanner = new Scanner (System.in);
		String nameInput = new String ();
		String input = new String ();
		double zufall1 = 0;
		int zufall2 = 0;
		int zaehler = 0;
		int wahl = 0;
		int [] ergebnis = {0, 0, 0};
		int [] letzteWahl = {0, 0, 0};
		boolean eingetragen = false;
		double schere = 0;
		double schereNeu = 0;
		double stein = 0;
		double steinNeu = 0;
		double papier = 0;	
		double papierNeu = 0;
		double gesamt = 0;
			
		System.out.println ("Wie heißen Sie?");
		bufferInput = new BufferedReader(new InputStreamReader (System.in));
		nameInput = bufferInput.readLine();
		File file = new File ("C:\\Users\\Lars\\Desktop\\SchereSteinPapier\\" + nameInput + ".txt");
		if (file.exists ()== true) {
			bufferFile = new BufferedReader(new FileReader(file));
			schere = Double.valueOf(bufferFile.readLine()).doubleValue();
			stein = Double.valueOf(bufferFile.readLine()).doubleValue();
			papier = Double.valueOf(bufferFile.readLine()).doubleValue();
			gesamt  = schere + stein + papier;
			System.out.println ("Profil geladen!");
		}
		else {
			System.out.println ("Ihr Name wurde nicht gefunden, moechten" +
					" Sie nun ein Profil anlegen? (ja/nein)");	
			input = bufferInput.readLine();
			if (input.equals("nein")) {
				System.out.println ("---Programm beendet---");
				System.exit(0);
			}
			else if (input.equals("ja")) {
				file.createNewFile();
				BufferedWriter writerFile = new BufferedWriter(
					    new OutputStreamWriter(
					    new FileOutputStream(file, true)));
				 writerFile.write("0.0");
				 writerFile.newLine();
				 writerFile.write("0.0");
				 writerFile.newLine();
				 writerFile.write("0.0");
				 writerFile.newLine();
				 writerFile.flush();
				 writerFile.close();
			}
		}
		System.out.println ("Wie oft moechten Sie spielen?");
		zaehler = scanner.nextInt ();
		for (int i = 1; i <= zaehler; i++) {	
			if (schereNeu == 3) zufall2 = 2;
			else if (steinNeu == 3) zufall2 = 3;
			else if (papierNeu == 3) zufall2 = 1;
			else {
				if (gesamt >= 1.0) {
					zufall1 = Math.random();
					if (zufall1 <= (((schere / gesamt) + (schereNeu / 3)) / 2)) zufall2 = 2;
					else if ((((schere / gesamt) + (schereNeu / 3)) / 2) < zufall1 
							&&	zufall1 <= ((((schere / gesamt) + (schereNeu / 3)) / 2)
									+ (((stein / gesamt) + (steinNeu / 3) / 2)))) 
						zufall2 = 3;
					else if (((((schere / gesamt) + (schereNeu / 3)) / 2)
							+ (((stein / gesamt) + (steinNeu / 3) / 2))) < zufall1)
						zufall2 = 1;
				}
				else if (gesamt == 0.0) zufall2 = (int) (Math.random () * 10 / 3);
			}		
			do {
				System.out.println ("Was setzen Sie? (1 = Schere; 2 = Stein; 3 = Papier)");
				wahl = scanner.nextInt();
			}
			while (wahl != 1 && wahl != 2 && wahl != 3);
			if (wahl == 1) schere ++;
			else if (wahl == 2) stein ++;
			else if (wahl == 3) papier ++;
			for (int b = 0; b<=2; b++) {
				if (letzteWahl [b] == 0) {
					letzteWahl [b] = wahl;
					eingetragen = true;
					break;
				}
			}
			if (eingetragen == false) {
				for (int b = 0; b<2; b++) {
					letzteWahl [b] = letzteWahl [b + 1];
				}
				letzteWahl [2] = wahl;
			}
			schereNeu = 0;
			steinNeu = 0;
			papierNeu = 0;
			for (int b = 0; b<=2; b++) {
				if (letzteWahl [b] == 1) schereNeu ++;
				else if(letzteWahl [b] == 2) steinNeu ++;
				else if (letzteWahl [b] == 3) papierNeu ++;
			}
			gesamt = schere + stein + papier;		
			eingetragen = false;
			Gewonnen.waehrendSpiel(zufall2, wahl, ergebnis);
		}
		BufferedWriter writerFile = new BufferedWriter(new FileWriter(file));
		writerFile.write(String.valueOf(schere).toString());
		writerFile.newLine();
		writerFile.write(String.valueOf(stein).toString());
		writerFile.newLine();
		writerFile.write(String.valueOf(papier).toString());
		writerFile.newLine();
		writerFile.flush();
		writerFile.close();
		Gewonnen.spielende (ergebnis);
	}
}