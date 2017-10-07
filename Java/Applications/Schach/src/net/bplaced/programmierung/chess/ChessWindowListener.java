package net.bplaced.programmierung.chess;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Dieser Listener reagiert auf das Schlie&szlig;en des Fensters.
 * 
 * @version 3. Juli 2011
 * @author Lars Reimann
 */
public final class ChessWindowListener extends WindowAdapter {

    /**
     * Die angezeigte Instanz des Hauptfensters.
     */
    private final ChessFrame frame;

    /**
     * Die Instanz des Importierers beziehungsweise Exportierers.
     */
    private final ChessImportExport importExport;

    /**
     * Weist allen Feldern die betreffenden Werte zu.
     * 
     * @param frame
     *            Die angezeigte Instanz des Hauptfensters.
     * @param importExport
     *            Die Instanz des Importierers beziehungsweise Exportierers.
     */
    public ChessWindowListener(final ChessFrame frame,
                               final ChessImportExport importExport) {
        this.frame = frame;
        this.importExport = importExport;
    }
    
    @Override
    public void windowClosing(final WindowEvent event) {
        frame.setVisible(false);
        frame.dispose();
        System.exit(0);
    }
}
