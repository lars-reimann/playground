import java.io.IOException;
import java.util.Scanner;

public class SchereSteinPapier {
	
	public static void main (String [] args) throws IOException {
		Scanner scanner = new Scanner (System.in);
		int schwierigkeit = 0;
		
		System.out.println ("Mit welchem Gegner moechten Sie das Programm ");
		System.out.println ("starten? (1 = Zufallsgenerator; 2 = Profil)");
		schwierigkeit = scanner.nextInt ();
		
		if (schwierigkeit == 1) Schwierigkeit.zufall();
		else if (schwierigkeit == 2) Schwierigkeit.KI();	
		scanner.close();	
	}
}
