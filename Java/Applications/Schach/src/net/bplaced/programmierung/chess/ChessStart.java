package net.bplaced.programmierung.chess;

/**
 * Diese Klasse beinhaltet lediglich die main-Methode.
 * 
 * @version 4. August 2011
 * @author Lars Reimann
 */
public final class ChessStart {

    /**
     * Der Einstiegspunkt in das Programm. Es wird nur eine neue Instanz von
     * {@code ChessFrame} erzeugt, welche die GUI darstellt.
     * 
     * @param args
     *            Der Standardparameter der main-Methode.
     */
    public static void main(final String[] args) {
        new ChessFrame();
    }
}
