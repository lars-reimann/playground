package net.bplaced.programmierung.chess;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

/**
 * Dieser Listener reagiert auf das Klicken von Men&uuml;eintr&auml;gen im Hauptfenster.
 * 
 * @version 3. Juli 2011
 * @author Lars Reimann
 */
public final class ChessActionListener implements ActionListener {

    /**
     * Die angezeigte Instanz des Hauptfensters.
     */
    private final ChessFrame frame;

    /**
     * Die Instanz des Importierers beziehungsweise Exportierers.
     */
    private final ChessImportExport importExport;

    /**
     * Die Instanz der Logik, welche den Spielverlauf koordiniert.
     */
    private final ChessLogic logic;

    /**
     * Weist allen Feldern die betreffenden Werte zu.
     * 
     * @param frame
     *            Die angezeigte Instanz des Hauptfensters.
     * @param importExport
     *            Die Instanz des Importierers beziehungsweise Exportierers.
     * @param logic
     *            Die Instanz der Logik, welche den Spielverlauf koordiniert.
     */
    public ChessActionListener(final ChessFrame frame,
                               final ChessImportExport importExport,
                               final ChessLogic logic) {
        this.frame = frame;
        this.importExport = importExport;
        this.logic = logic;
    }

    @Override
    public void actionPerformed(final ActionEvent event) {
        final String command = event.getActionCommand();
        if ("Import".equals(command)) {
            isNotSupported();
        } else if ("Export".equals(command)) {
            isNotSupported();
        } else if ("Beenden".equals(command)) {
            frame.setVisible(false);
            frame.dispose();
            System.exit(0);
        } else if ("Neues Spiel".equals(command)) {

        } else if ("R\u00FCckg\u00E4ngig".equals(command)) {
            isNotSupported();
        } else if ("\u00DCber Schach".equals(command)) {
            isNotSupported();
        }
    }
    
    private void isNotSupported() {
        JOptionPane.showMessageDialog(
            frame, 
            "Diese Funktion wird leider noch nicht unterst\u00FCtzt!"
        );
    }
}
