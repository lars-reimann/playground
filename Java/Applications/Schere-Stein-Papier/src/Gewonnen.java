class Gewonnen {
	
	public static void waehrendSpiel (int zufall, int wahl, int [] ergebnis) {
		if (zufall == wahl) {
			if (zufall == 1) System.out.println ("Schere vs. Schere");
			else if (zufall == 2) System.out.println ("Stein vs. Stein");
			else if (zufall == 3) System.out.println ("Papier vs. Papier");
			System.out.println ("Spiel endet unentschieden!");
			System.out.println ("");
			ergebnis [0] ++;
		
		}
		else {
			if (wahl == 1 && zufall == 2) {
				System.out.println("Schere vs. Stein");
				System.out.println ("Computer gewinnt!");
				System.out.println ("");
				ergebnis [1] ++;		
			}
			else if (wahl == 1 && zufall == 3) {
				System.out.println("Schere vs. Papier");
				System.out.println ("Sie haben gewonnen!");
				System.out.println ("");
				ergebnis [2] ++;
			}
			else if (wahl == 2 && zufall == 1) {
				System.out.println("Stein vs. Schere");
				System.out.println ("Sie haben gewonnen!");
				System.out.println ("");
				ergebnis [2] ++;
			}
			else if (wahl == 2 && zufall == 3) {
				System.out.println("Stein vs. Papier");
				System.out.println ("Computer gewinnt!");
				System.out.println ("");
				ergebnis [1] ++;
			}
			else if (wahl == 3 && zufall == 1) {
				System.out.println("Papier vs. Schere");
				System.out.println ("Computer gewinnt!");
				System.out.println ("");
				ergebnis [1] ++;
			}
			else if (wahl == 3 && zufall == 2) {
				System.out.println("Papier vs. Stein");
				System.out.println ("Sie haben gewonnen!");
				System.out.println ("");
				ergebnis [2] ++;
			}
		}
	}
	
	public static void spielende (int [] ergebnis) {
		System.out.println ("Unentschieden: " + ergebnis [0] + 
				" || Sieg Computer: " + ergebnis [1] + 
				" || Sieg Spieler " + ergebnis [2]);
		if (ergebnis [2] < ergebnis [1]) System.out.println ("Schade, versuchen sie es nochmal!");
		else if (ergebnis [2] > ergebnis [1]) System.out.println ("Herzlichen Glueckwunsch!");
		System.exit(0);
	}
}
