package net.bplaced.programmierung.schach;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ChessboardPanel extends JPanel {
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

	private Field[][] fields;
	private Set<AbstractPiece> figures;

	private Chessboard data;

	public ChessboardPanel(final int size) {
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

	public void setChessboard(final Chessboard chessboard) {
		this.data = chessboard;
		addMouseListener(new MyMouseListener(chessboard, this));
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
	void paintField(Chessboard chessboard, final Graphics graphics) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				final Field field = chessboard.fields[i][j];
				if (field.isSelected()) {
					graphics.setColor(ChessConstants.SELECTED_FIELD);
				} else if (field.isBeatable()) {
					graphics.setColor(ChessConstants.BEATABLE_FIELD);
				} else if (field.isPossible()) {
					graphics.setColor(ChessConstants.POSSIBLE_FIELD);
				} else if ((i + j) % 2 == 0) {
					graphics.setColor(ChessConstants.WHITE_FIELD);
				} else {
					graphics.setColor(ChessConstants.BLACK_FIELD);
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
		paintField(data, graphics);
		paintFigures(data, graphics);
	}

	/**
	 * 
	 * @param chessboard
	 *            TODO
	 * @param graphics
	 */
	void paintFigures(Chessboard chessboard, final Graphics graphics) {
		final int length = (int) Math.round((double) size / 12);
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				final AbstractPiece figure = chessboard.fields[i][j]
						.getFigure();
				final int x = (int) Math.round((double) size / 8 * i
						+ (double) size / 16 - length / 2);
				final int y = (int) Math.round((double) size / 8 * j
						+ (double) size / 16 - length / 2);
				if (figure == null) {
					continue;
				} else if (figure instanceof Bishop) {
					if (figure.getColor() == -1) {
						graphics.drawImage(whiteBishop, x, y, length, length,
								this);
					} else {
						graphics.drawImage(blackBishop, x, y, length, length,
								this);
					}
				} else if (figure instanceof King) {
					if (figure.getColor() == -1) {
						graphics.drawImage(whiteKing, x, y, length, length,
								this);
					} else {
						graphics.drawImage(blackKing, x, y, length, length,
								this);
					}
				} else if (figure instanceof Knight) {
					if (figure.getColor() == -1) {
						graphics.drawImage(whiteKnight, x, y, length, length,
								this);
					} else {
						graphics.drawImage(blackKnight, x, y, length, length,
								this);
					}
				} else if (figure instanceof Pawn) {
					if (figure.getColor() == -1) {
						graphics.drawImage(whitePawn, x, y, length, length,
								this);
					} else {
						graphics.drawImage(blackPawn, x, y, length, length,
								this);
					}
				} else if (figure instanceof Queen) {
					if (figure.getColor() == -1) {
						graphics.drawImage(whiteQueen, x, y, length, length,
								this);
					} else {
						graphics.drawImage(blackQueen, x, y, length, length,
								this);
					}
				} else if (figure instanceof Rook) {
					if (figure.getColor() == -1) {
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