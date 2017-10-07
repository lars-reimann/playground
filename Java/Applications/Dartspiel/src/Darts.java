import java.util.*;

public class Darts {
		
		private static Throw [][] throwArray;
		private static StringBuilder lastSolution = new StringBuilder ();
		private static int points;
		private static int darts;
		private static int length;
		private static boolean allSolutions = false;
		
	public static void main(String [] args) {
		Scanner scanner = new Scanner (System.in);
		
		input();
		
		if (darts == 3) {
			throwArray = new Throw [3][80724];
			length = 80724;
			throwThreeDarts();		
		} else if (darts == 2) {
			throwArray = new Throw [2][1302];
			length = 1302;
			throwTwoDarts();		
		} else if (darts == 1) {
			throwArray = new Throw [1][21];
			length = 21;
			throwOneDart();	
		}
		
		check();
		
		if (lastSolution.toString().equals("")) {
			System.out.print("Keine Loesung gefunden!");
		}
		
		System.out.print("\nZum Beenden Enter druecken!");
		scanner.nextLine();
		System.exit(0);
	}
	
	private static void input() {
		Scanner scanner = new Scanner (System.in);
		
		System.out.println("Wie viele Punkte brauchen Sie noch?");
		points = scanner.nextInt();
		
		System.out.println("Wie viele Darts haben Sie noch?");
		darts = scanner.nextInt();
		
		System.out.println("Moechten Sie alle Loesungen? (j/n)");
		if (scanner.next().equals("j")){
			allSolutions = true;
		}
	}
	
	private static void throwThreeDarts() {
		Throw temp;
		int temp2;
		
		/* ----------1. Wurf---------- */	
			
		/* Singles */
		for (int x = 1; x <= 20; x++) {
			temp = new Throw ();
			temp.name = "s" + x;
			temp.points = points - x;
			for (int y = ((x - 1) * 1302); y < (x * 1302); y++) {
				throwArray [0][y] = temp;
			}
		}
		
		/* Bull */
		temp = new Throw ();
		temp.name = "s25";
		temp.points = points - 25;
		for (int x = 26040; x < 27342; x ++) {
			throwArray [0][x] = temp;
		}
		
		/* Doubles */
		for (int x = 1; x <= 20; x++) {
			temp = new Throw ();
			temp.name = "d" + x;
			temp.points = points - (x * 2);
			temp.doubleCheck = true;
			for (int y = ((x + 20) * 1302); y < ((x + 21) * 1302); y++) {
				throwArray [0][y] = temp;
			}
		}
		
		/* Bull's eye */
		temp = new Throw ();
		temp.name = "d25";
		temp.points = points - 50;
		temp.doubleCheck = true;
		for (int x = 53382; x < 54684; x ++) {
			throwArray [0][x] = temp;
		}
		
		/* Triples */
		for (int x = 1; x <= 20; x++) {
			temp = new Throw ();
			temp.name = "t" + x;
			temp.points = points - (x * 3);
			for (int y = ((x + 41) * 1302); y < ((x + 42) * 1302); y++) {
				throwArray [0][y] = temp;
			}
		}
		
		/* ----------2. Wurf---------- */
		
		for (int x = 1; x <= 62; x++) {
			
			/* Singles */
			for (int y = 1; y <= 20; y++) {							
				for (int z = ((x - 1) * 1302) + ((y - 1) * 21);
						z < ((x - 1) * 1302) + (y * 21); z++) {
					temp = new Throw ();
					temp.name = "s" + y;
					temp2 = throwArray [0][z].points;
					temp.points = temp2 - y;
					throwArray [1][z] = temp;
				}
			}
			
			/* Bull */							
			for (int y = ((x - 1) * 1302) + 420;
					y < ((x - 1) * 1302) + 441; y ++) {
				temp = new Throw ();
				temp.name = "s25";
				temp2 = throwArray [0][y].points;
				temp.points = temp2 - 25;
				throwArray [1][y] = temp;
			}
			
			/* Doubles */
			for (int y = 1; y <= 20; y++) {					
				for (int z = ((x - 1) * 1302) + ((y + 20) * 21);
						z < ((x - 1) * 1302) + ((y + 21) * 21); z++) {
					temp = new Throw ();
					temp.name = "d" + y;
					temp2 = throwArray [0][z].points;
					temp.points = temp2 - (y * 2);
					temp.doubleCheck = true;
					throwArray [1][z] = temp;
				}
			}
			
			/* Bull's eye */				
			for (int y = ((x - 1) * 1302) + 861;
					y < ((x - 1) * 1302) + 882; y ++) {
				temp = new Throw ();
				temp.name = "d25";
				temp2 = throwArray [0][y].points;
				temp.points = temp2 - 50;
				temp.doubleCheck = true;
				throwArray [1][y] = temp;
			}
			
			/* Triples */
			for (int y = 1; y <= 20; y++) {						
				for (int z = ((x - 1) * 1302) + ((y + 41) * 21);
						z < ((x - 1) * 1302) + ((y + 42) * 21); z++) {
					temp = new Throw ();
					temp.name = "t" + y;
					temp2 = throwArray [0][z].points;
					temp.points = temp2 - (y * 3);
					throwArray [1][z] = temp;
				}
			}
		}
			
		/* ----------3. Wurf---------- */
			
		for (int x = 1; x <= 3844; x++) {
				
			/* Doubles */
			for (int y = 1; y <= 20; y++) {
				temp = new Throw ();
				temp.name = "d" + y;
				temp2 = throwArray [1][(x - 1) * 21 + (y - 1)].points;
				temp.points = temp2 - (y * 2);
				temp.doubleCheck = true;
				throwArray [2][(x - 1) * 21 + (y - 1)] = temp;
			}							
				
			/* Bull's eye */	
				temp = new Throw ();
				temp.name = "d25";
				temp2 = throwArray [1][(x * 21) - 1].points;
				temp.points = temp2 - 50;
				temp.doubleCheck = true;
				throwArray [2][(x * 21) - 1] = temp;
		}
	}	
	
	private static void throwTwoDarts() {
		Throw temp;
		int temp2;
		
		/* ----------1. Wurf---------- */
		
		/* Singles */
		for (int x = 1; x <= 20; x++) {
			temp = new Throw ();
			temp.name = "s" + x;
			temp.points = points - x;							
			for (int y = (x - 1) * 21; y < x * 21; y++) {	
				throwArray [0][y] = temp;
			}
		}
			
		/* Bull */
		temp = new Throw ();
		temp.name = "s25";
		temp.points = points - 25;							
		for (int x = 420; x < 441; x++) {
			
			throwArray [0][x] = temp;
		}
			
		/* Doubles */
		for (int x = 1; x <= 20; x++) {
			temp = new Throw ();
			temp.name = "d" + x;
			temp.points = points - (x * 2);
			temp.doubleCheck = true;					
			for (int y = (x + 20) * 21; y < (x + 21) * 21; y++) {	
				throwArray [0][y] = temp;
			}
		}
			
		/* Bull's eye */
		temp = new Throw ();
		temp.name = "d25";
		temp.points = points - 50;
		temp.doubleCheck = true;				
		for (int x = 861; x < 882; x++) {			
			throwArray [0][x] = temp;
		}
			
		/* Triples */
		for (int x = 1; x <= 20; x++) {
			temp = new Throw ();
			temp.name = "t" + x;
			temp.points = points - (x * 3);						
			for (int y = (x + 41) * 21; y < (x + 42) * 21; y++) {	
				throwArray [0][y] = temp;
			}
		}
			
		/* ----------2. Wurf---------- */
			
		for (int x = 1; x <= 62; x++) {
				
			/* Doubles */
			for (int y = 1; y <= 20; y++) {
				temp = new Throw ();
				temp.name = "d" + y;
				temp2 = throwArray [0][(x - 1) * 21 + (y - 1)].points;
				temp.points = temp2 - (y * 2);
				temp.doubleCheck = true;
				throwArray [1][(x - 1) * 21 + (y - 1)] = temp;
			}							
				
			/* Bull's eye */	
			temp = new Throw ();
			temp.name = "d25";
			temp2 = throwArray [0][(x * 21) - 1].points;
			temp.points = temp2 - 50;
			temp.doubleCheck = true;
			throwArray [1][(x * 21) - 1] = temp;
		}
	}
	
	private static void throwOneDart() {
		Throw temp;
				
		/* Doubles */
		for (int x = 1; x <= 20; x++) {
			temp = new Throw ();
			temp.name = "d" + x;
			temp.points = points - (x * 2);
			temp.doubleCheck = true;
			throwArray [0][x - 1] = temp;
		}							
				
		/* Bull's eye */	
		temp = new Throw ();
		temp.name = "d25";
		temp.points = points - 50;
		temp.doubleCheck = true;
		throwArray [0][20] = temp;
	}	
	
	private static void check() {	
		for (int x = 0; x < length; x++) {				
			if (darts >= 3 && throwArray [2][x].points == 0) {
				output(x, 3);
			} else if (darts >= 2 && throwArray [1][x].points == 0
					&& throwArray [1][x].doubleCheck == true) {
				output(x, 2);
			} else if (darts >= 1 && throwArray [0][x].points == 0
					&& throwArray [0][x].doubleCheck == true) {
				output(x, 1);
			}
		}
		if (allSolutions == false) {
			System.out.println(lastSolution);
		}
	}
	
	private static void output(int arrayField, int numberThrows) {
		StringBuilder newSolution = new StringBuilder ();
		
		if (numberThrows == 3) {
			for (int x = 0; x < 3; x++) {
				newSolution.append(throwArray [x][arrayField].name + "\t");			
			}
		} else if (numberThrows == 2) {
			for (int x = 0; x < 2; x++) {
				newSolution.append(throwArray [x][arrayField].name + "\t");
			}
		} else if (numberThrows == 1) {
			for (int x = 0; x < 1; x++) {
				newSolution.append(throwArray [x][arrayField].name + "\t");
			}
		}
		
		if (allSolutions == true) {
			if (!newSolution.toString().equals(lastSolution.toString())) {
				System.out.println(newSolution);
				lastSolution = newSolution;
			}
		} else {
			if (newSolution.length() < lastSolution.length() - 2
					|| lastSolution.length() == 0) {
				lastSolution = newSolution;
			}
		}
	}
}