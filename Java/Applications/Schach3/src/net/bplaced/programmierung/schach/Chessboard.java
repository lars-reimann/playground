package net.bplaced.programmierung.schach;

import java.awt.Point;
import javax.swing.JOptionPane;

public final class Chessboard {

	private Computer computer;
	private int computerColor;
	/**
     *
     */
	Field[][] fields;
	AbstractPiece[][] figures;

	private final MyFrame frame;
	private boolean hasSelectedField;
	private int moveCount = 1;

	public int getMoveCount() {
		return moveCount;
	}

	private StringBuilder notation;
	/**
     *
     */
	private int player;
	private Point selectedField;

	ChessboardPanel data;
	private boolean withComputer;

	/**
	 * 
	 * @param size
	 */
	public Chessboard(final ChessboardPanel panel, final MyFrame frame) {
		super();
		this.frame = frame;
		data = panel;
		fields = new Field[8][8];
		figures = new AbstractPiece[8][8];

		setup();
	}

	/**
	 * 
	 * @param fields
	 * @return
	 */
	static Field[][] copyFields(final Field[][] fields) {
		Field[][] fieldsCopy = new Field[fields.length][fields.length];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				final AbstractPiece figure = fields[i][j].getFigure();
				AbstractPiece figureCopy = null;
				if (figure != null) {
					try {
						figureCopy = (AbstractPiece) fields[i][j].getFigure()
								.clone();
					} catch (CloneNotSupportedException exception) {
					}
				}
				fieldsCopy[i][j] = new Field(figureCopy);
			}
		}
		return fieldsCopy;
	}

	/**
	 * 
	 * @param fields
	 * @return
	 */
	public static AbstractPiece[][] copyFigures(final AbstractPiece[][] figures) {
		AbstractPiece[][] figuresCopy = new AbstractPiece[figures.length][figures.length];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				final AbstractPiece figure = figures[i][j];
				AbstractPiece figureCopy = null;
				if (figure != null) {
					try {
						figureCopy = (AbstractPiece) figures[i][j].clone();
					} catch (CloneNotSupportedException exception) {
					}
				}
				figuresCopy[i][j] = figureCopy;
			}
		}
		return figuresCopy;
	}

	/**
	 * 
	 * @return
	 */
	public static boolean hasPossibleMoves(final int color,
			final AbstractPiece[][] figures, Field[][] fields) {
		boolean hasPossibleMoves = false;

		Label: for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (figures[i][j] != null && figures[i][j].getColor() == color) {
					figures[i][j].markMoves(fields);
					for (int k = 0; k < 8; k++) {
						for (int l = 0; l < 8; l++) {
							if (fields[k][l].isPossible()
									|| fields[k][l].isBeatable()) {
								hasPossibleMoves = true;
								break Label;
							}
						}
					}
					for (int k = 0; k < 8; k++) {
						for (int l = 0; l < 8; l++) {
							fields[k][l].resetSelections();
						}
					}
				}
			}
		}
		for (int k = 0; k < 8; k++) {
			for (int l = 0; l < 8; l++) {
				fields[k][l].resetSelections();
			}
		}
		return hasPossibleMoves;
	}

	/**
	 * 
	 * @param color
	 * @param fieldsCopy
	 * @return
	 */
	public static boolean isCheck(final int color,
			final AbstractPiece[][] figures) {
		boolean isCheck = false;
		Label: for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (figures[i][j] != null && figures[i][j].getColor() != color
						&& figures[i][j].canBeatKing(figures)) {
					isCheck = true;
					break Label;
				}
			}
		}
		return isCheck;
	}

	public void clickOnField(final int x, final int y) {
		if (hasSelectedField) {
			if (fields[x][y].isPossible() || fields[x][y].isBeatable()) {
				Pawn.resetHasMovedTwice(player);
				resetSelections();
				noteMove(x, y);
				moveFigure(x, y);
				if (player == -1) {
					player = 1;
				} else {
					player = -1;
				}
				if (displayEndOfGame() && withComputer
						&& computerColor == player) {
					computer.moveFigure();
				}
				frame.setText(notation.toString());
			} else if (!fields[x][y].isSelected()) {
				resetSelections();
				selectField(x, y);
			}
		} else {
			selectField(x, y);
		}
		data.repaint();
	}

	public int getComputerColor() {
		return computerColor;
	}

	public String getNotation() {
		return notation.toString();
	}

	public int getPlayer() {
		return player;
	}

	public void setField(final Field[][] fields) {
		this.fields = fields;
	}

	/**
     *
     */
	public void setup() {
		setupRooks();
		setupKnights();
		setupBishops();
		setupQueens();
		setupKings();
		setupPawns();
		setupEmptyFields();

		AbstractPiece.setFields(fields);
		AbstractPiece.setFigures(figures);
		player = -1;
		moveCount = 1;
		notation = new StringBuilder();
		hasSelectedField = false;
		frame.setText(notation.toString());
		if (frame.withComputerIsSelected()) {
			withComputer = true;
		} else {
			withComputer = false;
		}
		computerColor = frame.getComputerColor();
		data.repaint();
	}

	private void setupEmptyFields() {
		for (int i = 0; i < 8; i++) {
			for (int j = 2; j < 6; j++) {
				fields[i][j] = new Field(null);
			}
		}

		for (int i = 0; i < 8; i++) {
			for (int j = 2; j < 6; j++) {
				figures[i][j] = fields[i][j].getFigure();
			}
		}
	}

	private void setupPawns() {
		for (int i = 0; i < 8; i++) {
			fields[i][1] = new Field(new Pawn(1, i, 1));
			fields[i][6] = new Field(new Pawn(-1, i, 6));
		}

		for (int i = 0; i < 8; i++) {
			figures[i][1] = fields[i][1].getFigure();
			figures[i][6] = fields[i][6].getFigure();
		}
	}

	private void setupKings() {
		fields[4][0] = new Field(new King(1, 4, 0));
		fields[4][7] = new Field(new King(-1, 4, 7));

		figures[4][0] = fields[4][0].getFigure();
		figures[4][7] = fields[4][7].getFigure();
	}

	private void setupQueens() {
		fields[3][0] = new Field(new Queen(1, 3, 0));
		fields[3][7] = new Field(new Queen(-1, 3, 7));

		figures[3][0] = fields[3][0].getFigure();
		figures[3][7] = fields[3][7].getFigure();
	}

	private void setupBishops() {
		fields[2][0] = new Field(new Bishop(1, 2, 0));
		fields[5][0] = new Field(new Bishop(1, 5, 0));
		fields[2][7] = new Field(new Bishop(-1, 2, 7));
		fields[5][7] = new Field(new Bishop(-1, 5, 7));

		figures[2][0] = fields[2][0].getFigure();
		figures[5][0] = fields[5][0].getFigure();
		figures[2][7] = fields[2][7].getFigure();
		figures[5][7] = fields[5][7].getFigure();
	}

	private void setupKnights() {
		fields[1][0] = new Field(new Knight(1, 1, 0));
		fields[6][0] = new Field(new Knight(1, 6, 0));
		fields[1][7] = new Field(new Knight(-1, 1, 7));
		fields[6][7] = new Field(new Knight(-1, 6, 7));

		figures[1][0] = fields[1][0].getFigure();
		figures[6][0] = fields[6][0].getFigure();
		figures[1][7] = fields[1][7].getFigure();
		figures[6][7] = fields[6][7].getFigure();
	}

	private void setupRooks() {
		fields[0][0] = new Field(new Rook(1, 0, 0));
		fields[7][0] = new Field(new Rook(1, 7, 0));
		fields[0][7] = new Field(new Rook(-1, 0, 7));
		fields[7][7] = new Field(new Rook(-1, 7, 7));

		figures[0][0] = fields[0][0].getFigure();
		figures[7][0] = fields[7][0].getFigure();
		figures[0][7] = fields[0][7].getFigure();
		figures[7][7] = fields[7][7].getFigure();
	}

	public void setupComputer() {
		if (computer == null) {
			computer = new Computer(this, figures, fields);
		}
		computer.setFields(fields);
		computer.setColor(computerColor);
		if (withComputer && -1 == computerColor) {
			computer.moveFigure();
		}
	}

	public boolean withComputer() {
		return withComputer;
	}

	/**
     *
     */
	private boolean displayEndOfGame() {
		final AbstractPiece[][] figures = copyFigures(this.figures);
		final boolean hasPossibleMoves = hasPossibleMoves(player, figures,
				fields);
		final boolean isCheck = isCheck(player, figures);
		if (isCheck && hasPossibleMoves) {
			notation.append("+");
		} else if (!hasPossibleMoves) {
			data.repaint();
			if (isCheck) {
				if (player == -1) {
					notation.append(" 0-1");
					frame.setText(notation.toString());
					JOptionPane.showMessageDialog(data, "Schwarz gewinnt!",
							"Schachmatt", JOptionPane.INFORMATION_MESSAGE);
				} else {
					notation.append(" 1-0");
					frame.setText(notation.toString());
					JOptionPane.showMessageDialog(data, "Weiss gewinnt!",
							"Schachmatt", JOptionPane.INFORMATION_MESSAGE);
				}
			} else {
				notation.append(" 1/2-1/2");
				frame.setText(notation.toString());
				JOptionPane.showMessageDialog(data,
						"Spiel endet unentschieden!", "Patt",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
		return hasPossibleMoves;
	}

	private void moveFigure(final int newX, final int newY) {
		final AbstractPiece newFigure = figures[newX][newY];
		final int oldX = selectedField.x;
		final int oldY = selectedField.y;
		final AbstractPiece selectedFigure = figures[oldX][oldY];

		// Umsetzen der Figur
		selectedFigure.setLocation(newX, newY);
		fields[newX][newY].setFigure(selectedFigure);
		figures[newX][newY] = selectedFigure;
		fields[oldX][oldY].setFigure(null);
		figures[oldX][oldY] = null;

		// Spezielle Zuege
		if (selectedFigure instanceof Pawn) {
			if (Math.abs(newY - oldY) == 2) {
				((Pawn) selectedFigure).setHasMovedTwice(true);
			} else {
				((Pawn) selectedFigure).setHasMovedTwice(false);
				if (Math.abs(newX - oldX) == 1 && newFigure == null) {
					fields[newX][newY - player].setFigure(null);
					figures[newX][newY - player] = null;
				} else if (newY == 0 || newY == 7) {
					if (withComputer && computerColor == player) {
						fields[newX][newY].setFigure(new Queen(computerColor,
								newX, newY));
						figures[newX][newY] = new Queen(computerColor, newX,
								newY);
						notation.append("D");
					} else {
						((Pawn) selectedFigure).promotePawn(player, notation,
								data, newX, newY);
					}
				}
			}
		} else if (selectedFigure instanceof Rook) {
			((Rook) selectedFigure).setHasMoved(true);
		} else if (selectedFigure instanceof King) {
			((King) selectedFigure).setHasMoved(true);
			if (Math.abs(newX - oldX) == 2) {
				if (newX == 2) {
					fields[3][newY].setFigure(figures[0][newY]);
					fields[0][newY].setFigure(null);
					fields[3][newY].getFigure().setLocation(3, newY);

					figures[3][newY] = figures[0][newY];
					figures[0][newY] = null;
					figures[3][newY].setLocation(3, newY);
				} else {
					fields[5][newY].setFigure(figures[7][newY]);
					fields[7][newY].setFigure(null);
					fields[5][newY].getFigure().setLocation(5, newY);

					figures[3][newY] = figures[0][newY];
					figures[0][newY] = null;
					figures[3][newY].setLocation(3, newY);
				}
			}
		}
	}

	private void noteMove(final int newX, final int newY) {
		final AbstractPiece newFigure = figures[newX][newY];
		final int oldX = selectedField.x;
		final int oldY = selectedField.y;
		final AbstractPiece selectedFigure = figures[oldX][oldY];

		if (player == -1) {
			if (moveCount > 1) {
				notation.append("\n");
			}
			notation.append(moveCount + ". ");
		} else {
			notation.append("\t");
			moveCount++;
		}

		// Notation
		if (selectedFigure instanceof Bishop) {
			notation.append("L" + (char) (97 + oldX) + (8 - oldY));
			if (newFigure == null) {
				notation.append("-");
			} else {
				notation.append("x");
			}
			notation.append(Character.toString((char) (97 + newX)) + (8 - newY));
		} else if (selectedFigure instanceof King) {
			if (Math.abs(newX - oldX) == 2) {
				if (newX == 2) {
					notation.append("0-0-0");
				} else {
					notation.append("0-0");
				}
			} else {
				notation.append("K" + (char) (97 + oldX) + (8 - oldY));
				if (newFigure == null) {
					notation.append("-");
				} else {
					notation.append("x");
				}
				notation.append(Character.toString((char) (97 + newX))
						+ (8 - newY));
			}
		} else if (selectedFigure instanceof Knight) {
			notation.append("S" + (char) (97 + oldX) + (8 - oldY));
			if (newFigure == null) {
				notation.append("-");
			} else {
				notation.append("x");
			}
			notation.append(Character.toString((char) (97 + newX)) + (8 - newY));
		}
		if (selectedFigure instanceof Pawn) {
			notation.append(Character.toString((char) (97 + oldX)) + (8 - oldY));
			if (newFigure == null) {
				notation.append("-");
			} else {
				notation.append("x");
			}
			notation.append(Character.toString((char) (97 + newX)) + (8 - newY));
			if (Math.abs(newX - oldX) == 1 && newFigure == null) {
				notation.append(" e. p.");
			}
		} else if (selectedFigure instanceof Queen) {
			notation.append("D" + (char) (97 + oldX) + (8 - oldY));
			if (newFigure == null) {
				notation.append("-");
			} else {
				notation.append("x");
			}
			notation.append(Character.toString((char) (97 + newX)) + (8 - newY));
		} else if (selectedFigure instanceof Rook) {
			notation.append("T" + (char) (97 + oldX) + (8 - oldY));
			if (newFigure == null) {
				notation.append("-");
			} else {
				notation.append("x");
			}
			notation.append(Character.toString((char) (97 + newX)) + (8 - newY));
		}
	}

	private void resetSelections() {
		for (int k = 0; k < 8; k++) {
			for (int l = 0; l < 8; l++) {
				fields[k][l].resetSelections();
			}
		}
		hasSelectedField = false;
	}

	private void selectField(final int x, final int y) {
		if (figures[x][y] != null && figures[x][y].getColor() == player) {
			fields[x][y].setSelected(true);
			selectedField = new Point(x, y);
			hasSelectedField = true;
			figures[x][y].markMoves(fields);
		}
	}
}
