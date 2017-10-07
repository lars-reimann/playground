package net.bplaced.programmierung.chess;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * <p>
 * Hier ist die grafische Umsetzung des Schachbrettes enthalten.
 * </p>
 * <p>
 * So wird zum einen das typische Schachbrettmuster erzeugt, aber auch
 * daf&uuml;r gesorgt, dass das ausgew&auml;hlte Feld erkennbar ist und dass
 * m&ouml;gliche Z&uuml;ge angezeigt werden. Des Weiteren werden die Figuren
 * gezeichnet.
 * </p>
 * 
 * @version 3. Juli 2011
 * @author Lars Reimann
 */
public final class ChessBoard extends JPanel {

    /**
     * Die Farbe, in der ein Feld angezeigt wird, auf dem eine schlagbare Figur
     * steht. Diese ist r = 210, g = 100, b = 100, was einem Rotton entspricht.
     */
    private static final Color BEATABLE_FIELD = new Color(210, 100, 100);

    /**
     * Die Farbe, in der ein schwarzes Feld dargestellt wird. Diese ist r = 150,
     * g = 150, b = 150, was einem dunklen Grau entspricht.
     */
    private static final Color BLACK_FIELD = new Color(150, 150, 150);

    /**
     * Das Bild eines schwarzen L&auml;ufers.
     */
    private static BufferedImage blackBishop;

    /**
     * Das Bild eines schwarzen K&ouml;nigs.
     */
    private static BufferedImage blackKing;

    /**
     * Das Bild eines schwarzen Springers.
     */
    private static BufferedImage blackKnight;

    /**
     * Das Bild eines schwarzen Bauers.
     */
    private static BufferedImage blackPawn;

    /**
     * Das Bild einer schwarzen Dame.
     */
    private static BufferedImage blackQueen;

    /**
     * Das Bild eines schwarzen Turmes.
     */
    private static BufferedImage blackRook;

    /**
     * Die Farbe, in der ein Feld gezeichnet wird, auf welches die
     * ausgew&auml;hlte Figur ziehen kann. Diese ist r = 100, g = 210, b = 100,
     * was einem Gr&uuml;nton entspricht.
     */
    private static final Color POSSIBLE_FIELD = new Color(100, 210, 100);

    /**
     * Die Farbe, in der das ausgew&auml;hlte Feld dargestellt wird. Diese ist r
     * = 100, g = 100, b = 210, was einem Blauton entspricht.
     */
    private static final Color SELECTED_FIELD = new Color(100, 100, 210);

    /**
     * Die automatisch generierte Versionsnummer.
     */
    private static final long serialVersionUID = -8520650839256132470L;

    /**
     * Die Farbe eines wei&szlig;en Feldes. Diese ist r = 225, g = 225, b = 225,
     * was einem hellen Grau entspricht.
     */
    private static final Color WHITE_FIELD = new Color(225, 225, 255);

    /**
     * Das Bild eines wei&szlig;en L&auml;ufers.
     */
    private static BufferedImage whiteBishop;

    /**
     * Das Bild eines wei&szlig;en K&ouml;nigs.
     */
    private static BufferedImage whiteKing;

    /**
     * Das Bild eines wei&szlig;en Springers.
     */
    private static BufferedImage whiteKnight;

    /**
     * Das Bild eines wei&szlig;en Bauern.
     */
    private static BufferedImage whitePawn;

    /**
     * Das Bild einer wei&szlig;en Dame.
     */
    private static BufferedImage whiteQueen;

    /**
     * Das Bild eines wei&szlig;en Turmes.
     */
    private static BufferedImage whiteRook;

    static {
        final String separator = File.separator;
        try {
            blackBishop = ImageIO.read(new File("figures" + separator +
                                                "blackBishop.gif"));
            blackKing = ImageIO.read(new File("figures" + separator +
                                              "blackKing.gif"));
            blackKnight = ImageIO.read(new File("figures" + separator +
                                                "blackKnight.gif"));
            blackPawn = ImageIO.read(new File("figures" + separator +
                                              "blackPawn.gif"));
            blackQueen = ImageIO.read(new File("figures" + separator +
                                               "blackQueen.gif"));
            blackRook = ImageIO.read(new File("figures" + separator +
                                              "blackRook.gif"));
            whiteBishop = ImageIO.read(new File("figures" + separator +
                                                "whiteBishop.gif"));
            whiteKing = ImageIO.read(new File("figures" + separator +
                                              "whiteKing.gif"));
            whiteKnight = ImageIO.read(new File("figures" + separator +
                                                "whiteKnight.gif"));
            whitePawn = ImageIO.read(new File("figures" + separator +
                                              "whitePawn.gif"));
            whiteQueen = ImageIO.read(new File("figures" + separator +
                                               "whiteQueen.gif"));
            whiteRook = ImageIO.read(new File("figures" + separator +
                                              "whiteRook.gif"));
        } catch (final IOException exception) {
            // Kann eigentlich nicht auftreten
        }
    }

    /**
     * Die Tabelle, in der die Figuren auf den einzelnen Feldern gespeichert
     * werden.
     */
    private byte[][] figures;

    /**
     * Die Liste der m&ouml;glichen Z&uuml;ge.
     */
    private Point[] possibleMoves;

    /**
     * Das momentan ausgew&auml;hlte Feld.
     */
    private Point selectedField;

    /**
     * <p>
     * F&uuml;hrt alles n&ouml;tigen Operationen zur Konstruktion einer neuen
     * Instanz dieser Klasse aus.
     * </p>
     * <p>
     * Die drei Instanzvariablen werden mit Startwerten initialisiert und es
     * wird ein {@code MouseListener} hinzugef&uuml;gt. Au&szlig;erdem wird die
     * gew&uuml;nschte Gr&ouml;&szlig;e auf 400x400 Pixel festgelegt.
     * </p>
     */
    public ChessBoard() {
        figures = new byte[8][8];
        possibleMoves = new Point[27];
        selectedField = null;

        addMouseListener(new ChessMouseListener());
        setPreferredSize(new Dimension(400, 400));
    }

    @Override
    public void paint(final Graphics g) {
        paintFields(g);
        paintFigures(g);
    }

    /**
     * Eine Hilfsmethode zum Zeichnen des Feldes.
     * 
     * @param g
     *            Der Grafikkontext, in welchem gezeichnet werden soll.
     */
    private void paintFields(final Graphics g) {

        // Felder
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                    g.setColor(WHITE_FIELD);
                } else {
                    g.setColor(BLACK_FIELD);
                }
                g.fillRect(i * 50, j * 50, 50, 50);
            }
        }

        // Ausgewaehltes Feld
        if (selectedField != null) {
            g.setColor(SELECTED_FIELD);
            g.fillRect(selectedField.x * 50, selectedField.y * 50, 50, 50);
        }

        // Moegliche Felder
        for (Point p : possibleMoves) {
            if (p == null) {
                break;
            } else if (figures[p.x][p.y] == ChessConstants.EMPTY) {
                g.setColor(POSSIBLE_FIELD);
            } else {
                g.setColor(BEATABLE_FIELD);
            }
            g.fillRect(p.x * 50, p.y * 50, 50, 50);
        }
    }

    /**
     * Eine Hilfsmethode zum Zeichnen der Figuren.
     * 
     * @param g
     *            Der Grafikkontext, in welchem gemalt werden soll.
     */
    private void paintFigures(final Graphics g) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                switch (figures[i][j]) {
                    case ChessConstants.BLACK_BISHOP:
                        g.drawImage(blackBishop, i * 50 + 5, j * 50 + 5, 40,
                                    40, this);
                        break;
                    case ChessConstants.BLACK_KING:
                        g.drawImage(blackKing, i * 50 + 5, j * 50 + 5, 40, 40,
                                    this);
                        break;
                    case ChessConstants.BLACK_KNIGHT:
                        g.drawImage(blackKnight, i * 50 + 5, j * 50 + 5, 40,
                                    40, this);
                        break;
                    case ChessConstants.BLACK_PAWN:
                        g.drawImage(blackPawn, i * 50 + 5, j * 50 + 5, 40, 40,
                                    this);
                        break;
                    case ChessConstants.BLACK_QUEEN:
                        g.drawImage(blackQueen, i * 50 + 5, j * 50 + 5, 40, 40,
                                    this);
                        break;
                    case ChessConstants.BLACK_ROOK:
                        g.drawImage(blackRook, i * 50 + 5, j * 50 + 5, 40, 40,
                                    this);
                        break;
                    case ChessConstants.WHITE_BISHOP:
                        g.drawImage(whiteBishop, i * 50 + 5, j * 50 + 5, 40,
                                    40, this);
                        break;
                    case ChessConstants.WHITE_KING:
                        g.drawImage(whiteKing, i * 50 + 5, j * 50 + 5, 40, 40,
                                    this);
                        break;
                    case ChessConstants.WHITE_KNIGHT:
                        g.drawImage(whiteKnight, i * 50 + 5, j * 50 + 5, 40,
                                    40, this);
                        break;
                    case ChessConstants.WHITE_PAWN:
                        g.drawImage(whitePawn, i * 50 + 5, j * 50 + 5, 40, 40,
                                    this);
                        break;
                    case ChessConstants.WHITE_QUEEN:
                        g.drawImage(whiteQueen, i * 50 + 5, j * 50 + 5, 40, 40,
                                    this);
                        break;
                    case ChessConstants.WHITE_ROOK:
                        g.drawImage(whiteRook, i * 50 + 5, j * 50 + 5, 40, 40,
                                    this);
                        break;
                }
            }
        }
    }

    /**
     * @param figures
     *            Die neue Tabelle der Figuren.
     */
    public void setFigures(final byte[][] figures) {
        this.figures = figures;
    }

    /**
     * @param possibleMoves
     *            Die neue Liste der m&ouml;glichen Z&uuml;ge.
     */
    public void setPossibleMoves(final Point[] possibleMoves) {
        this.possibleMoves = possibleMoves;
    }

    /**
     * @param selectedField
     *            Das ausgew&auml;hlte Feld.
     */
    public void setSelectedField(final Point selectedField) {
        this.selectedField = selectedField;
    }
}
