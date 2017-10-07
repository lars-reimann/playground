package net.bplaced.programmierung.schach;

import java.awt.Color;

/**
 * Enthaelt die Konstanten, die fuer das Schachspiel noetig sind. Das sind
 * einmal die Konstanten fuer schwarze und weisse Figuren, aber auch die Farben,
 * in der die einzelnen Felder angezeigt werden.
 * 
 * @author Lars Reimann
 * @version 3. Maerz 2011
 * @see Field
 */
public final class ChessConstants {

	/**
	 * Die Farbe, in der ein Feld dargestellt wird, dessen Figur von der
	 * ausgewaehlen Figur geschlagen werden kann. Es ist ein Rotton.
	 */
	public static final Color BEATABLE_FIELD = new Color(200, 100, 100);

	/**
	 * Die Farbe, in der eine Feld dargestellt wird, das weder ausgewaehlt noch
	 * schlagbar noch moeglich ist. Die schwarze Dame steht zum Beispiel anfangs
	 * auf solch einem Feld. Es ist ein dunkler Grauton.
	 */
	public static final Color BLACK_FIELD = new Color(125, 125, 125);

	/**
	 * Die Farbe, die zum Zeichnen eines Feldes benutzt wird, auf das die
	 * ausgewaehlte Figur ziehen kann. Es ist ein Gruenton.
	 */
	public static final Color POSSIBLE_FIELD = new Color(100, 200, 100);

	/**
	 * Die Farbe, in der das ausgewaehlte Feld dargestellt wird. Es ist ein
	 * Blauton.
	 */
	public static final Color SELECTED_FIELD = new Color(100, 100, 200);

	/**
	 * Die Farbe, in der eine Feld dargestellt wird, das weder ausgewaehlt noch
	 * schlagbar noch moeglich ist. Die weisse Dame steht zum Beispiel anfangs
	 * auf solch einem Feld. Es ist ein heller Grauton.
	 */
	public static final Color WHITE_FIELD = new Color(225, 225, 225);

	/**
	 * Dieser private Konstruktor verhindert die Instanziierung dieser
	 * Hilfsklasse. Da sie lediglich Konstanten enthaelt, ist es nicht noetig,
	 * Instanzen zu erzeugen. Sonst hat der Konstruktor keine weiteren
	 * Funktionen.
	 */
	private ChessConstants() {
		super();
	}
}
