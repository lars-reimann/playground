 

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Die Klasse reagiert auf {@code ActionEvent}s, die auftreten, wenn im Menue
 * des {@code JFrame}s auf die Items geklickt wird. Dann sorgt die Klasse etwa
 * dafuer, dass das Spiel neu gestartet wird.
 * 
 * @author Lars Reimann
 * @version 3. Maerz 2011
 */
public final class MyActionListener implements ActionListener {

    /**
     * Die Instanz des Schachbretts.
     */
    private final transient Chessboard chessboard;

    /**
     * Konstruiert eine neue Instanz dieser Klasse und weist dem Feld chessboard
     * die entsprechende Instanz der Klasse {@code Chessboard} zu.
     * 
     * @param chessboard
     *            Die Instanz der Klasse {@code Chessboard}.
     */
    public MyActionListener(final Chessboard chessboard) {
        super();
        this.chessboard = chessboard;
    }

    @Override
    public void actionPerformed(final ActionEvent event) {
        final String actionCommand = event.getActionCommand();
        if ("Neues Spiel".equals(actionCommand)) {
            chessboard.setup();
            chessboard.setupComputer();
        }
    }
}
