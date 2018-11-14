import java.io.IOException;

/**
 * Die Klasse, die die main-Methode beinhaltet, diese dient zum Starten des Programmes. Sonst hat sie keine Funktion.
 */
public class Main {

    /**
     * Startet den Minesweeper-Server.
     * 
     * @param args Der Standardparameter der main-Methode. Er hat hier keine weitere Funktion.
     */
    public static void main(String[] args) {
        try {
            new MinesweeperServer().startServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
