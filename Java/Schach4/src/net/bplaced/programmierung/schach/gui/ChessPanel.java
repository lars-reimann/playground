package net.bplaced.programmierung.schach.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import net.bplaced.programmierung.schach.logic.Bishop;
import net.bplaced.programmierung.schach.logic.ChessColor;
import net.bplaced.programmierung.schach.logic.DefaultPiece;
import net.bplaced.programmierung.schach.logic.EnPassant;
import net.bplaced.programmierung.schach.logic.Engine;
import net.bplaced.programmierung.schach.logic.King;
import net.bplaced.programmierung.schach.logic.Knight;
import net.bplaced.programmierung.schach.logic.Move;
import net.bplaced.programmierung.schach.logic.Pawn;
import net.bplaced.programmierung.schach.logic.Piece;
import net.bplaced.programmierung.schach.logic.Queen;
import net.bplaced.programmierung.schach.logic.Rook;

public class ChessPanel extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 1998449481963484369L;

    public BufferedImage blackBishop;
    public BufferedImage blackKing;
    public BufferedImage blackKnight;
    public BufferedImage blackPawn;
    public BufferedImage blackQueen;
    public BufferedImage blackRook;
    public BufferedImage whiteBishop;
    public BufferedImage whiteKing;
    public BufferedImage whiteKnight;
    public BufferedImage whitePawn;
    public BufferedImage whiteQueen;
    public BufferedImage whiteRook;
    private int size;

    // TODO Ausgewaehlte Figur, von dort moegliche Zuege holen und entsprechend
    // auswerten

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

    private Engine engine;

    public ChessPanel(final int size) {
	this.size = size;
	setSize(size, size);
	String separator = File.separator;
	try {
	    blackBishop = ImageIO.read(new File("Figures" + separator
		    + "BlackBishop.gif"));
	    blackKing = ImageIO.read(new File("Figures" + separator
		    + "BlackKing.gif"));
	    blackKnight = ImageIO.read(new File("Figures" + separator
		    + "BlackKnight.gif"));
	    blackPawn = ImageIO.read(new File("Figures" + separator
		    + "BlackPawn.gif"));
	    blackQueen = ImageIO.read(new File("Figures" + separator
		    + "BlackQueen.gif"));
	    blackRook = ImageIO.read(new File("Figures" + separator
		    + "BlackRook.gif"));
	    whiteBishop = ImageIO.read(new File("Figures" + separator
		    + "WhiteBishop.gif"));
	    whiteKing = ImageIO.read(new File("Figures" + separator
		    + "WhiteKing.gif"));
	    whiteKnight = ImageIO.read(new File("Figures" + separator
		    + "WhiteKnight.gif"));
	    whitePawn = ImageIO.read(new File("Figures" + separator
		    + "WhitePawn.gif"));
	    whiteQueen = ImageIO.read(new File("Figures" + separator
		    + "WhiteQueen.gif"));
	    whiteRook = ImageIO.read(new File("Figures" + separator
		    + "WhiteRook.gif"));
	} catch (IOException exception) {
	    // Kann eigentlich nicht auftreten
	    exception.printStackTrace();
	}
    }

    public void setEngine(final Engine engine) {
	this.engine = engine;
	addMouseListener(new ChessMouseListener(engine, this));
    }

    @Override
    public Dimension getPreferredSize() {
	return new Dimension(size, size);
    }

    /**
     * 
     * @param chessboard
     * @param graphics
     */
    void paintField(final Graphics graphics) {
	for (int i = 0; i < 8; i++) {
	    for (int j = 0; j < 8; j++) {
		if ((i + j) % 2 == 0) {
		    graphics.setColor(WHITE_FIELD);
		} else {
		    graphics.setColor(BLACK_FIELD);
		}
		graphics.fillRect(i * size / 8, j * size / 8, size / 8,
			size / 8);
	    }
	}
	graphics.setColor(Color.black);
	for (int i = 1; i < 8; i++) {
	    graphics.drawLine(i * size / 8 - 1, 0, i * size / 8 - 1, size);
	    graphics.drawLine(0, i * size / 8 - 1, size, i * size / 8 - 1);
	}
	graphics.drawRect(0, 0, size - 1, size - 1);
    }

    /**
    *
    */
    @Override
    protected void paintComponent(final Graphics graphics) {
	paintField(graphics);
	paintSelectedFigure(graphics);
	paintMoves(graphics);
	paintBorders(graphics);
	paintFigures(graphics);
    }

    private void paintSelectedFigure(final Graphics graphics) {
	if (engine.getSelectedPiece() != DefaultPiece.NULL) {
	    final int pos = engine.getSelectedPiece().getPos();
	    final int y = (pos - 22) / 10;
	    final int x = (pos - 22) % 10;
	    graphics.setColor(SELECTED_FIELD);
	    graphics.fillRect(x * size / 8, y * size / 8, size / 8, size / 8);
	}

    }

    private void paintBorders(final Graphics graphics) {
	graphics.setColor(Color.black);
	for (int i = 1; i < 8; i++) {
	    graphics.drawLine(i * size / 8 - 1, 0, i * size / 8 - 1, size);
	    graphics.drawLine(0, i * size / 8 - 1, size, i * size / 8 - 1);
	}
	graphics.drawRect(0, 0, size - 1, size - 1);

    }

    private void paintMoves(final Graphics graphics) {
	if (engine.getSelectedPiece() != DefaultPiece.NULL) {
	    final List<Move> moves = engine.getSelectedPieceMove();
	    for (Move move : moves) {
		final int pos = move.getNewPos();
		final int y = (pos - 22) / 10;
		final int x = (pos - 22) % 10;
		if (move instanceof EnPassant) {
		    graphics.setColor(BEATABLE_FIELD);
		} else if (engine.getPiece(pos) == DefaultPiece.NULL) {
		    graphics.setColor(POSSIBLE_FIELD);
		} else {
		    graphics.setColor(BEATABLE_FIELD);
		}
		graphics.fillRect(x * size / 8, y * size / 8, size / 8,
			size / 8);
	    }
	}

    }

    /**
     * 
     * @param engine
     * @param graphics
     */
    void paintFigures(final Graphics graphics) {
	final int length = (int) Math.round((double) size / 12);
	for (int i = 0; i < 8; i++) {
	    for (int j = 0; j < 8; j++) {
		final Piece piece = engine.getPiece(22 + i * 10 + j);
		final int y = (int) Math.round((double) size / 8 * i
			+ (double) size / 16 - length / 2);
		final int x = (int) Math.round((double) size / 8 * j
			+ (double) size / 16 - length / 2);
		if (piece == DefaultPiece.NULL) {
		    continue;
		} else if (piece instanceof Bishop) {
		    if (piece.getColor() == ChessColor.WHITE) {
			graphics.drawImage(whiteBishop, x, y, length, length,
				this);
		    } else {
			graphics.drawImage(blackBishop, x, y, length, length,
				this);
		    }
		} else if (piece instanceof King) {
		    if (piece.getColor() == ChessColor.WHITE) {
			graphics.drawImage(whiteKing, x, y, length, length,
				this);
		    } else {
			graphics.drawImage(blackKing, x, y, length, length,
				this);
		    }
		} else if (piece instanceof Knight) {
		    if (piece.getColor() == ChessColor.WHITE) {
			graphics.drawImage(whiteKnight, x, y, length, length,
				this);
		    } else {
			graphics.drawImage(blackKnight, x, y, length, length,
				this);
		    }
		} else if (piece instanceof Pawn) {
		    if (piece.getColor() == ChessColor.WHITE) {
			graphics.drawImage(whitePawn, x, y, length, length,
				this);
		    } else {
			graphics.drawImage(blackPawn, x, y, length, length,
				this);
		    }
		} else if (piece instanceof Queen) {
		    if (piece.getColor() == ChessColor.WHITE) {
			graphics.drawImage(whiteQueen, x, y, length, length,
				this);
		    } else {
			graphics.drawImage(blackQueen, x, y, length, length,
				this);
		    }
		} else if (piece instanceof Rook) {
		    if (piece.getColor() == ChessColor.WHITE) {
			graphics.drawImage(whiteRook, x, y, length, length,
				this);
		    } else {
			graphics.drawImage(blackRook, x, y, length, length,
				this);
		    }
		}
	    }
	}
    }
}