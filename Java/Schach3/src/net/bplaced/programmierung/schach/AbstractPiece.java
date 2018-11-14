package net.bplaced.programmierung.schach;

import java.awt.Point;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * <p>
 * Die abstrakte Repr&auml;sentation einer Schachfigur.
 * </p>
 * <p>
 * Jede Figur hat eine Farbe und eine Position auf dem Schachbrett, das
 * jeweilige Verhalten wird von den erweiternden Klassen festgelegt.
 * </p>
 * 
 * @version 1. November 2011
 * @author Lars Reimann
 */
public abstract class AbstractPiece {

	/**
	 * Die Farbe der Figur (wei&szlig; oder schwarz).
	 */
	protected Color color;

	/**
	 * Die momentane Position der Figur.
	 */
	protected int pos;

	/**
	 * Testet ob die Figur den gegnerischen K&ouml;nig beim momentanen
	 * Spielaufbau Schach setzt.
	 * 
	 * @param figures
	 *            Aktueller Spielaufbau
	 * @return Ob Figur gegnerischen K&ouml;nig Schach setzt
	 */
	public abstract boolean checksKing(final AbstractPiece[] pieces);

	/**
	 * Gibt die Farbe der Figur (wei&szlig; oder schwarz) zur&uuml;ck.
	 * 
	 * @return Farbe der Figur
	 */
	public final Color getColor() {
		return color;
	}

	/**
	 * Berechnet die Z&uuml;ge, die die Figur beim jetzigen Spielaufbau
	 * ausf&uuml;hren kann.
	 * 
	 * @param figures
	 *            Aktueller Spielaufbau
	 * @return Liste der m&ouml;gichen Z&uuml;ge
	 */
	public abstract Collection<Point> computeMoves(final AbstractPiece[] pieces);

	/**
	 * Gibt die momentane Position der Figur zur&uuml;ck;
	 * 
	 * @return y-Position der Figur
	 */
	public final int getPos() {
		return pos;
	}

	/**
	 * Setzt die momentane Position der Figur auf den &uuml;bergebenen Wert.
	 * 
	 * @param yPos
	 *            Neue Position
	 */
	public final void setPos(final int pos) {
		assert pos >= 0 && pos < 64 : "Inkorrekte y-Position";
		this.pos = pos;
	}

	public AbstractPiece[][] simulateMove(final AbstractPiece[] pieces,
			final int xPos, final int yPos) {
		final AbstractPiece[][] figuresCopy = Chessboard.copyFigures(figures);
		figuresCopy[end.x][end.y] = figuresCopy[location.x][location.y];
		figuresCopy[location.x][location.y] = null;
		figuresCopy[end.x][end.y].setLocation(end.x, end.y);
		return figuresCopy;
	}
}
