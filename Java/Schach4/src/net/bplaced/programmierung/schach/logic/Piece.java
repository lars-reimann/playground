package net.bplaced.programmierung.schach.logic;

import java.util.List;

/**
 * <p>
 * Die abstrakte Repr&auml;sentation einer Schachfigur.
 * </p>
 * <p>
 * Jede Figur hat eine Farbe und eine Position auf dem Schachbrett, das
 * jeweilige Verhalten wird von den erweiternden Klassen festgelegt.
 * </p>
 * 
 * @version 2. November 2011
 * @author Lars Reimann
 */
public interface Piece {

    /**
     * F&uuml;gt den angegebenen Zug zur Zugliste hinzu, wenn dieser unter der
     * &uuml;bergebenen Spielsituation legal ist.
     * 
     * @param pieces
     *            Aktueller Spielplan
     * @param moves
     *            Zugliste
     * @param move
     *            Neuer Zug
     */
    void addMove(final Piece[] pieces, final List<Move> moves, final Move move, final boolean validate);

    /**
     * Testet ob die Figur den gegnerischen K&ouml;nig beim momentanen
     * Spielaufbau Schach setzt.
     * 
     * @param figures
     *            Aktueller Spielaufbau
     * @return Ob Figur gegnerischen K&ouml;nig Schach setzt
     */
    boolean checksEnemyKing(final Piece[] pieces);

    /**
     * Erstellt eine Kopie dieser Figur mit gleicher Farbe, gleichem Typ und der
     * angegebenen neuen Position.
     * 
     * @param newPos
     *            Neue Position der Figur
     * @return Kopie der Figur
     */
    Piece copyPiece(int newPos);

    /**
     * Berechnet die Z&uuml;ge, die die Figur beim jetzigen Spielaufbau
     * ausf&uuml;hren kann.
     * 
     * @param figures
     *            Aktueller Spielaufbau
     * @return Liste der m&ouml;gichen Z&uuml;ge
     */
    List<Move> generateMoves(final Piece[] pieces, final boolean validate);

    /**
     * Gibt die Farbe der Figur (wei&szlig; oder schwarz) zur&uuml;ck.
     * 
     * @return Farbe der Figur
     */
    ChessColor getColor();

    /**
     * Gibt das K&uuml;rzel der Figur zur&uuml;ck.
     * 
     * @return K&uuml;rzel der Figur
     */
    char getName();

    /**
     * Gibt den Wert der Figur an sich zur&uuml;ck.
     * 
     * @return Wert der Figur
     */
    int getPieceValue();

    /**
     * Gibt die momentane Position der Figur zur&uuml;ck;
     * 
     * @return Position der Figur
     */
    int getPos();

    /**
     * Erstellt einen neuen Zug von oldPos nach newPos.
     * 
     * @param oldPos
     *            Position vor Zug
     * @param newPos
     *            Position nach Zug
     * @return Zug von oldPos nach newPos
     */
    Move newMove(final int oldPos, final int newPos);
}