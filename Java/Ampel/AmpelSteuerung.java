import java.util.Scanner;
/**
 * Beschreiben Sie hier die Klasse AmpelSteuerung.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class AmpelSteuerung
{
    public void main (String [] args)
    {
        Scanner scanner = new Scanner (System.in);
        
        System.out.println ("Möchten Sie Fussgaengerampeln? (true/false)");
        boolean fussgaengerAmpel = scanner.nextBoolean ();
        
        System.out.println ("Möchten Sie Autoampeln? (true/false)");
        boolean autoAmpel = scanner.nextBoolean ();
        
        
        FussgaengerAmpel fuss = new FussgaengerAmpel ();
        if (fussgaengerAmpel == true) fuss.zeichneFussgaengerAmpel ();
        
        AutoAmpel auto = new AutoAmpel ();
        if (autoAmpel == true) auto.zeichneAutoAmpel ();
        
        
        System.out.println ("Druecken? (true/false)");
        boolean druecken = scanner.nextBoolean ();
        
        if (druecken == true) 
        {
            fuss.start ();
            auto.start ();
        }       
    }
}
