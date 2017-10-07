 

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Reagiert auf Mausklicks auf das Schachbrett und waehlt das entsprechende Feld
 * aus. So wird etwa eine Figur auf dieses Feld bewegt oder es wird einfach nur
 * farbig unterlegt und die betreffende Figur ausgewaehlt.
 * 
 * @author Lars Reimann
 * @version 5. Maerz 2011
 */
public final class MyMouseListener extends MouseAdapter {

    /**
     * Die Instanz des Schachbrettes.
     */
    private final transient Chessboard chessboard;

    /**
     * Konstruiert eine neue Instanz dieser Klasse und weist dem Feld chessboard
     * die entsprechende Instanz der Klasse {@code Chessboard} zu.
     * 
     * @param chessboard
     *            Die Instanz der Klasse {@code Chessboard}.
     */
    public MyMouseListener(final Chessboard chessboard) {
        super();
        this.chessboard = chessboard;
    }

    @Override
    public void mousePressed(final MouseEvent event) {
        if (!chessboard.withComputer() ||
            chessboard.getComputerColor() != chessboard.getPlayer()) {
            final int size = chessboard.getWidth();
            final int x = 8 * event.getX() / size;
            final int y = 8 * event.getY() / size;
            chessboard.clickOnField(x, y);
        }

    }
}
