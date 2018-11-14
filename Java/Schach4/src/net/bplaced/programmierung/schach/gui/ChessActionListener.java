package net.bplaced.programmierung.schach.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import net.bplaced.programmierung.schach.logic.Engine;

/**
 * Die Klasse reagiert auf {@code ActionEvent}s, die auftreten, wenn im Men&uml;
 * des {@code JFrame}s auf die Items geklickt wird. Dann sorgt die Klasse etwa
 * daf&uuml;r, dass das Spiel neu gestartet wird.
 * 
 * @author Lars Reimann
 * @version 2. November 2011
 */
public final class ChessActionListener implements ActionListener {

    /**
     * Die Instanz des Schachbretts.
     */
    private final Engine engine;

	/**
	 * Konstruiert eine neue Instanz dieser Klasse und weist dem Feld chessboard
	 * die entsprechende Instanz der Klasse {@code Chessboard} zu.
	 * 
	 * @param chessboard
	 *            Die Instanz der Klasse {@code Chessboard}.
	 */
	public ChessActionListener(final Engine engine) {
		super();
		this.engine = engine;
	}

    @Override
    public void actionPerformed(final ActionEvent event) {
        final String actionCommand = event.getActionCommand();
        if ("Neues Spiel".equals(actionCommand)) {
            engine.setup();
        }
    }
}
