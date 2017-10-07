package src.net.bplaced.programmierung.schach;

import net.bplaced.programmierung.schach.gui.ChessFrame;

/**
 * Beinhaltet lediglich die main-Methode, die als Einstiegspunkt in das Programm
 * dient.
 * 
 * @author Lars Reimann
 * @version 3. Maerz 2011
 */
public final class Start {

    /**
     * Verhindert, dass weitere Instanzen der Klasse {@code Start} erzeugt
     * werden koennen. Sonst hat der Konstruktor keine weitere Funktionalitaet.
     */
    private Start() {
        throw new AssertionError("-----Versuch Start zu instanziieren-----");
    }

    /**
     * Dient als Einstiegspunkt in das Programm. Es wird ein neues Fenster
     * erzeugt, welches die weiteren Aufgaben ausfuehrt.
     * 
     * @param args
     *            Der Standardparameter der main-Methode.
     */
    public static void main(final String[] args) {
        new ChessFrame();
    }
}
