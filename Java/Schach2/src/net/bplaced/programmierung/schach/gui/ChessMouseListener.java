package net.bplaced.programmierung.schach.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import net.bplaced.programmierung.schach.logic.Engine;

/**
 * Reagiert auf Mausklicks auf das Schachbrett und waehlt das entsprechende Feld
 * aus. So wird etwa eine Figur auf dieses Feld bewegt oder es wird einfach nur
 * farbig unterlegt und die betreffende Figur ausgewaehlt.
 * 
 * @author Lars Reimann
 * @version 5. Maerz 2011
 */
public final class ChessMouseListener extends MouseAdapter {

	/**
	 * Die Instanz des Schachbrettes.
	 */
	private final Engine engine;
	private final ChessPanel panel;

	/**
	 * Konstruiert eine neue Instanz dieser Klasse und weist dem Feld chessboard
	 * die entsprechende Instanz der Klasse {@code Chessboard} zu.
	 * 
	 * @param chessboard
	 *            Die Instanz der Klasse {@code Chessboard}.
	 */
	public ChessMouseListener(final Engine engine, final ChessPanel panel) {
		super();
		this.engine = engine;
		this.panel = panel;
	}

	@Override
	public void mousePressed(final MouseEvent event) {
		final int size = panel.getWidth();
		final int y = 8 * event.getX() / size;
		final int x = 8 * event.getY() / size;
		engine.click(21 + x * 10 + y);
	}
}
