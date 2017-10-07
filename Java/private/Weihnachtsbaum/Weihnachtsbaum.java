import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * @author Lars Reimann
 * @version 05.12.2010
 */
public class Weihnachtsbaum {

    /**
     * Fragt den Nutzer nach der Anzahl der Dreicke, aus denen der Weihnachtsbaum
     * bestehen soll.
     * Anschliessend wird der Baum auf der Konsole ausgegeben.
     * 
     * @param args Standardparameter
     */
    public static void main(final String[] args) {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int anzahlDreiecke = 0;
        
        // Anzahl der Dreiecke
        System.out.print("Anzahl Dreiecke: ");   
        try {
            anzahlDreiecke = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException exception) {
            System.out.println("Keine Zahl!\n\n----Programm beendet----");
        } catch (IOException exception) {
            System.out.println("Fehler beim Einlesen!\n\n----Programm beendet----");
        }
        
        // Ausgabe
        for (int i = 1; i <= anzahlDreiecke; i++) {
            for (int j = 0; j <= i; j++) {
                for (int k = 0; k < anzahlDreiecke - j; k++) {
                    System.out.print("   ");
                }
                for (int k = 0; k <= j; k++) {
                    System.out.print("**    ");
                }
                System.out.println();
            }
        }
        for (int i = 0; i < anzahlDreiecke; i++) {
            for (int j = 0; j < anzahlDreiecke * 3 - 2; j++) {
                System.out.print(" ");
            }
            System.out.println("******");
        }
    }
}
