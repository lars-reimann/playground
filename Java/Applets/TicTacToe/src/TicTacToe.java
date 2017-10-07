import java.awt.*;
import java.applet.*;
import java.util.Scanner;

public class TicTacToe extends Applet
{
	public void paint (Graphics g) {
		Scanner scanner = new Scanner (System.in);
		int nextInt = 0;
		int zaehler = 1;
		boolean [][] felder = new boolean [9][2]; //Zeilen = Feld (0-8 bzw. 1-9)
		boolean spieler = false; //Kreis = false; Kreuz = true;
		Gewonnen gewonnen = new Gewonnen ();
		for (int x = 0; x<=8; x++)
			{
			for (int y = 0; y<=1 ; y++)	felder [x][y] = false;
			}
				
		showStatus ("TicTacToe");
		g.drawLine(75, 25, 75, 175);
		g.drawLine(125, 25, 125, 175);
		g.drawLine(25, 75, 175, 75);
		g.drawLine(25, 125, 175, 125);
		System.out.println ("Jedem Feld ist eine Zahl von 1-9 zugewiesen. Das Feld");
		System.out.println ("mit der Nummer 1 befindet sich links oben, das mit der");
		System.out.println ("Nummer 9 rechts unten. Die Eingabe erfolgt per Tastatur ");
		System.out.println ("und der Eingabetaste.");
		System.out.println ("");
		while (Gewonnen.gewonnen == false && zaehler <= 9) {
			if (spieler == false){
				do {
					System.out.println("Spieler Kreis, wo moechten Sie setzen?");
				nextInt = scanner.nextInt();
				}
				while (nextInt < 0 || nextInt > 9);
				if (felder [nextInt - 1][0] == false) {
					spieler = !spieler;
					zaehler ++;
				}	
				if (nextInt == 1 && felder [nextInt - 1][1] == false) {
					g.drawOval(35, 35, 30, 30); //Oben links
					felder [nextInt - 1][0] = true;
				}
				else if (nextInt == 2 && felder [nextInt - 1][1] == false) {
					g.drawOval(85, 35, 30, 30); //Oben mitte
					felder [nextInt - 1][0] = true;
				}
				else if (nextInt == 3 && felder [nextInt - 1][1] == false) {
					g.drawOval(135, 35, 30, 30); //Oben rechts
					felder [nextInt - 1][0] = true;
				}
				else if (nextInt == 4 && felder [nextInt - 1][1] == false) {
					g.drawOval(35, 85, 30, 30); //Mitte links
					felder [nextInt - 1][0] = true;
				}
				else if (nextInt == 5 && felder [nextInt - 1][1] == false) {
					g.drawOval(85, 85, 30, 30); //Mitte
					felder [nextInt - 1][0] = true;
				}
				else if (nextInt == 6 && felder [nextInt - 1][1] == false) {
					g.drawOval(135, 85, 30, 30); //Mitte rechts
					felder [nextInt - 1][0] = true;
				}
				else if (nextInt == 7 && felder [nextInt - 1][1] == false) {
					g.drawOval(35, 135, 30, 30); //Unten links
					felder [nextInt - 1][0] = true;
				}
				else if (nextInt == 8 && felder [nextInt - 1][1] == false) {
					g.drawOval(85, 135, 30, 30); //Unten mitte
					felder [nextInt - 1][0] = true;
				}
				else if (nextInt == 9 && felder [nextInt - 1][1] == false) {
					g.drawOval(135, 135, 30, 30); //Unten rechts
					felder [nextInt - 1][0] = true;
				}			
			}
			else {
				do {
					System.out.println("Spieler Kreuz, wo moechten Sie setzen?");
					nextInt = scanner.nextInt();
				}
				while (nextInt < 0 || nextInt > 9);
				if (felder [nextInt - 1][0] == false) {					
					spieler = !spieler;
					zaehler ++;
				}
				if (nextInt == 1 && felder [nextInt - 1][1] == false) {
					g.drawLine(35, 35, 65, 65); //Oben links
					g.drawLine(65, 35, 35, 65);
					felder [nextInt - 1][0] = true;
					felder [nextInt - 1][1] = true;
				}
				else if (nextInt == 2 && felder [nextInt - 1][1] == false) {
					g.drawLine(85, 35, 115, 65); //Oben mitte
					g.drawLine(115, 35, 85, 65);
					felder [nextInt - 1][0] = true;
					felder [nextInt - 1][1] = true;
				}
				else if (nextInt == 3 && felder [nextInt - 1][1] == false) {
					g.drawLine(135, 35, 165, 65); //Oben rechts
					g.drawLine(165, 35, 135, 65);
					felder [nextInt - 1][0] = true;
					felder [nextInt - 1][1] = true;
				}
				else if (nextInt == 4 && felder [nextInt - 1][1] == false) {
					g.drawLine(35, 85, 65, 115); //Mitte links
					g.drawLine(65, 85, 35, 115);
					felder [nextInt - 1][0] = true;
					felder [nextInt - 1][1] = true;
				}
				else if (nextInt == 5 && felder [nextInt - 1][1] == false) {
					g.drawLine(85, 85, 115, 115); //Mitte
					g.drawLine(115, 85, 85, 115);
					felder [nextInt - 1][0] = true;
					felder [nextInt - 1][1] = true;
				}
				else if (nextInt == 6 && felder [nextInt - 1][1] == false) {
					g.drawLine(135, 85, 165, 115); //Mitte rechts
					g.drawLine(165, 85, 135, 115);
					felder [nextInt - 1][0] = true;
					felder [nextInt - 1][1] = true;
				}
				else if (nextInt == 7 && felder [nextInt - 1][1] == false) {
					g.drawLine(35, 135, 65, 165); //Unten links
					g.drawLine(65, 135, 35, 165);
					felder [nextInt - 1][0] = true;
					felder [nextInt - 1][1] = true;
				}
				else if (nextInt == 8 && felder [nextInt - 1][1] == false) {
					g.drawLine(85, 135, 115, 165); //Unten mitte
					g.drawLine(115, 135, 85, 165);
					felder [nextInt - 1][0] = true;
					felder [nextInt - 1][1] = true;
				}
				else if (nextInt == 9 && felder [nextInt - 1][1] == false) {
					g.drawLine(135, 135, 165, 165); //Unten rechts
					g.drawLine(165, 135, 135, 165);
					felder [nextInt - 1][0] = true;
					felder [nextInt - 1][1] = true;
				}				
			}
			gewonnen.gewonnen (felder);		
		}
		if (zaehler == 10 && gewonnen.gewonnen == false) System.out.println ("Spiel endet unentschieden!");		
		try {
			Thread.sleep(5000);
		}
		catch (InterruptedException e) {}
		System.exit(0);
	}
}
