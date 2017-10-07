public class Gewonnen {
	
	public static boolean gewonnen;
	
	public void gewonnen (boolean [][] felder) {
		//1. Zeile voll (Spieler Kreis)
		if (
				felder [0][0] == true &&
				felder [0][1] == false &&
				felder [1][0] == true &&
				felder [1][1] == false &&
				felder [2][0] == true &&
				felder [2][1] == false	
		) {
			gewonnen = true; 
			System.out.println("Spieler Kreis gewinnt!");
		}
		//2.Zeile voll (Spieler Kreis)
		else if (
				felder [3][0] == true &&
				felder [3][1] == false &&
				felder [4][0] == true &&
				felder [4][1] == false &&
				felder [5][0] == true &&
				felder [5][1] == false	
		) {
			gewonnen = true; 
			System.out.println("Spieler Kreis gewinnt!");
		}
		//3.Zeile voll (Spieler Kreis)
		else if (
				felder [6][0] == true &&
				felder [6][1] == false &&
				felder [7][0] == true &&
				felder [7][1] == false &&
				felder [8][0] == true &&
				felder [8][1] == false	
		) {
			gewonnen = true; 
			System.out.println("Spieler Kreis gewinnt!");
		}
		//1. Spalte voll (Spieler Kreis)
		else if (
				felder [0][0] == true &&
				felder [0][1] == false &&
				felder [3][0] == true &&
				felder [3][1] == false &&
				felder [6][0] == true &&
				felder [6][1] == false	
		) {
			gewonnen = true; 
			System.out.println("Spieler Kreis gewinnt!");
		}
		//2.Spalte voll (Spieler Kreis)
		else if (
				felder [1][0] == true &&
				felder [1][1] == false &&
				felder [4][0] == true &&
				felder [4][1] == false &&
				felder [7][0] == true &&
				felder [7][1] == false	
		) {
			gewonnen = true; 
			System.out.println("Spieler Kreis gewinnt!");
		}
		//3.Spalte voll (Spieler Kreis)
		else if (
				felder [2][0] == true &&
				felder [2][1] == false &&
				felder [5][0] == true &&
				felder [5][1] == false &&
				felder [8][0] == true &&
				felder [8][1] == false	
		) {
			gewonnen = true; 
			System.out.println("Spieler Kreis gewinnt!");
		}
		//Diagonale links-rechts voll (Spieler Kreis)
		else if (
				felder [0][0] == true &&
				felder [0][1] == false &&
				felder [4][0] == true &&
				felder [4][1] == false &&
				felder [8][0] == true &&
				felder [8][1] == false	
		) {
			gewonnen = true; 
			System.out.println("Spieler Kreis gewinnt!");
		}
		//Diagonale rechts-links voll (Spieler Kreis)
		else if (
				felder [2][0] == true &&
				felder [2][1] == false &&
				felder [4][0] == true &&
				felder [4][1] == false &&
				felder [6][0] == true &&
				felder [6][1] == false	
		) {
			gewonnen = true; 
			System.out.println("Spieler Kreis gewinnt!");
		}
		
		
		
		//1. Zeile voll (Spieler Kreuz)
		else if (
				felder [0][0] == true &&
				felder [0][1] == true &&
				felder [1][0] == true &&
				felder [1][1] == true &&
				felder [2][0] == true &&
				felder [2][1] == true	
		) {
			gewonnen = true; 
			System.out.println("Spieler Kreuz gewinnt!");
		}
		//2.Zeile voll (Spieler Kreuz)
		else if (
				felder [3][0] == true &&
				felder [3][1] == true &&
				felder [4][0] == true &&
				felder [4][1] == true &&
				felder [5][0] == true &&
				felder [5][1] == true	
		) {
			gewonnen = true; 
			System.out.println("Spieler Kreuz gewinnt!");
		}
		//3.Zeile voll (Spieler Kreuz)
		else if (
				felder [6][0] == true &&
				felder [6][1] == true &&
				felder [7][0] == true &&
				felder [7][1] == true &&
				felder [8][0] == true &&
				felder [8][1] == true	
		) {
			gewonnen = true; 
			System.out.println("Spieler Kreuz gewinnt!");
		}
		//1. Spalte voll (Spieler Kreuz)
		else if (
				felder [0][0] == true &&
				felder [0][1] == true &&
				felder [3][0] == true &&
				felder [3][1] == true &&
				felder [6][0] == true &&
				felder [6][1] == true	
		) {
			gewonnen = true; 
			System.out.println("Spieler Kreuz gewinnt!");
		}
		//2.Spalte voll (Spieler Kreuz)
		else if (
				felder [1][0] == true &&
				felder [1][1] == true &&
				felder [4][0] == true &&
				felder [4][1] == true &&
				felder [7][0] == true &&
				felder [7][1] == true	
		) {
			gewonnen = true; 
			System.out.println("Spieler Kreuz gewinnt!");
		}
		//3.Spalte voll (Spieler Kreuz)
		else if (
				felder [2][0] == true &&
				felder [2][1] == true &&
				felder [5][0] == true &&
				felder [5][1] == true &&
				felder [8][0] == true &&
				felder [8][1] == true	
		) {
			gewonnen = true; 
			System.out.println("Spieler Kreuz gewinnt!");
		}
		//Diagonale links-rechts voll (Spieler Kreuz)
		else if (
				felder [0][0] == true &&
				felder [0][1] == true &&
				felder [4][0] == true &&
				felder [4][1] == true &&
				felder [8][0] == true &&
				felder [8][1] == true	
		) {
			gewonnen = true; 
			System.out.println("Spieler Kreuz gewinnt!");
		}
		//Diagonale rechts-links voll (Spieler Kreuz)
		else if (
				felder [2][0] == true &&
				felder [2][1] == true &&
				felder [4][0] == true &&
				felder [4][1] == true &&
				felder [6][0] == true &&
				felder [6][1] == true	
		) {
			gewonnen = true; 
			System.out.println("Spieler Kreuz gewinnt!");
		}
	}
}
