package net.bplaced.programmierung.schach;

import java.awt.Point;

import javax.swing.JOptionPane;

public final class Pawn extends AbstractPiece {

	private transient boolean hasMovedTwice = false;

	/**
	 * Konstruiert einen neuen Bauer mit vorgegebener Farbe und Position auf dem
	 * Spielfeld.
	 * 
	 * @param color
	 *            Die Farbe der Figur (weiss/schwarz).
	 * @param x
	 *            Die x-Position der Figur auf dem Schachbrett.
	 * @param y
	 *            Die y-Position der Figur auf dem Schachbrett.
	 */
	public Pawn(final int color, final int x, final int y) {
		super();
		this.color = color;
		this.location.x = x;
		this.location.y = y;
	}

	/**
	 * 
	 * @param color
	 * @param x
	 * @param y
	 */
	public void promotePawn(final int player, final StringBuilder notation,
			final ChessboardPanel data, final int x, final int y) {
		final Object[] options = { "Dame", "Turm", "Springer", "Laeufer" };
		final Field field = fields[x][y];
		final int option = JOptionPane.showOptionDialog(data,
				"Bitte waehlen Sie eine Figur aus.", "Aufstieg",
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				options, null);
		if (option == 0 || option == JOptionPane.CLOSED_OPTION) {
			field.setFigure(new Queen(player, x, y));
			notation.append("D");
		} else if (option == 1) {
			field.setFigure(new Rook(player, x, y));
			notation.append("T");
		} else if (option == 2) {
			field.setFigure(new Knight(player, x, y));
			notation.append("S");
		} else if (option == 3) {
			field.setFigure(new Bishop(player, x, y));
			notation.append("L");
		}
	}

	public static void resetHasMovedTwice(final int player) {
		for (int k = 0; k < 8; k++) {
			for (int l = 0; l < 8; l++) {
				if (fields[k][l].getFigure() instanceof Pawn
						&& fields[k][l].getFigure().getColor() == player) {
					((Pawn) fields[k][l].getFigure()).setHasMovedTwice(false);
				}
			}
		}
	}

	@Override
	public boolean canBeatKing(final AbstractPiece[][] figures) {
		if (location.x + 1 < 8) {
			final AbstractPiece figure = figures[location.x + 1][location.y
					+ color];
			if (figure instanceof King && figure.getColor() != color) {
				return true;
			}
		}
		if (location.x - 1 >= 0) {
			final AbstractPiece figure = figures[location.x - 1][location.y
					+ color];
			if (figure instanceof King && figure.getColor() != color) {
				return true;
			}
		}
		return false;
	}

	public boolean hasMovedTwice() {
		return hasMovedTwice;
	}

	@Override
	public void markMoves(final Field[][] fields) {
		markPossibleMoves(fields);
		markBeatableFigures(fields);
		unmarkMoves(fields);
	}

	public void setHasMovedTwice(final boolean hasMovedTwice) {
		this.hasMovedTwice = hasMovedTwice;
	}

	@Override
	public AbstractPiece[][] simulateMove(final Point end,
			final AbstractPiece[][] figures) {
		final AbstractPiece[][] figuresCopy = Chessboard.copyFigures(figures);
		figuresCopy[end.x][end.y] = figuresCopy[location.x][location.y];
		figuresCopy[location.x][location.y] = null;
		figuresCopy[end.x][end.y].setLocation(end.x, end.y);
		if (Math.abs(location.y - end.y) == 2) {
			((Pawn) figuresCopy[end.x][end.y]).setHasMovedTwice(true);
		} else {
			((Pawn) figuresCopy[end.x][end.y]).setHasMovedTwice(false);
			if (Math.abs(location.x - end.x) == 1
					&& figuresCopy[end.x][end.y] == null) {
				figuresCopy[end.x][end.y - color] = null;
			} else if (end.y == 0 || end.y == 7) {
				figuresCopy[end.x][end.y] = new Queen(color, end.x, end.y);
			}
		}
		return figuresCopy;
	}

	@Override
	public void unmarkMoves(final Field[][] fields) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (fields[i][j].isPossible() || fields[i][j].isBeatable()) {
					final AbstractPiece[][] figureCopy = simulateMove(
							new Point(i, j), figures);
					if (Chessboard.isCheck(color, figureCopy)) {
						fields[i][j].setBeatable(false);
						fields[i][j].setPossible(false);
					}
				}
			}
		}
	}

	private void markBeatableFigures(final Field[][] fields) {

		// Figur schraeg rechts
		if (location.x + 1 < 8) {
			final Field field = fields[location.x + 1][location.y + color];
			if (field.getFigure() != null) {
				final AbstractPiece figure = field.getFigure();
				if (figure.getColor() != color) {
					field.setBeatable(true);
				}
			}
		}

		// Figur schraeg links
		if (location.x - 1 >= 0) {
			final Field field = fields[location.x - 1][location.y + color];
			if (field.getFigure() != null) {
				final AbstractPiece figure = field.getFigure();
				if (figure.getColor() != color) {
					field.setBeatable(true);
				}
			}
		}

		// En passant schraeg rechts
		if (location.x + 1 < 8 && location.y == (7 - 3 * color) % 7) {
			final Field field = fields[location.x + 1][location.y];
			if (field.getFigure() instanceof Pawn) {
				final Pawn pawn = (Pawn) field.getFigure();
				if (pawn.hasMovedTwice && pawn.getColor() != color) {
					fields[location.x + 1][(7 - 2 * color) % 7]
							.setBeatable(true);
				}
			}
		}

		// En passant schraeg links
		if (location.x - 1 >= 0 && location.y == (7 - 3 * color) % 7) {
			final Field field = fields[location.x - 1][location.y];
			if (field.getFigure() instanceof Pawn) {
				final Pawn pawn = (Pawn) field.getFigure();
				if (pawn.hasMovedTwice && pawn.getColor() != color) {
					fields[location.x - 1][(7 - 2 * color) % 7]
							.setBeatable(true);
				}
			}
		}
	}

	private void markPossibleMoves(final Field[][] fields) {
		final Field field1 = fields[location.x][location.y + color];
		if (field1.getFigure() == null) {
			field1.setPossible(true);
			if (location.y == (7 + color) % 7) {
				final Field field2 = fields[location.x][location.y + 2 * color];
				if (field2.getFigure() == null) {
					field2.setPossible(true);
				}
			}
		}
	}
}
