package net.bplaced.programmierung.schach;

import java.awt.Point;

public final class King extends AbstractPiece {

	private transient boolean hasMoved = false;

	/**
	 * Konstruiert einen neuen Koenig mit vorgegebener Farbe und Position auf
	 * dem Spielfeld.
	 * 
	 * @param color
	 *            Die Farbe der Figur (weiss/schwarz).
	 * @param x
	 *            Die x-Position der Figur auf dem Schachbrett.
	 * @param y
	 *            Die y-Position der Figur auf dem Schachbrett.
	 */
	public King(final int color, final int x, final int y) {
		super();
		this.color = color;
		this.location.x = x;
		this.location.y = y;
	}

	@Override
	public boolean canBeatKing(final AbstractPiece[][] figures) {
		return canBeatKing(figures, location.x, location.y - 1)
				|| canBeatKing(figures, location.x + 1, location.y - 1)
				|| canBeatKing(figures, location.x + 1, location.y)
				|| canBeatKing(figures, location.x + 1, location.y + 1)
				|| canBeatKing(figures, location.x, location.y + 1)
				|| canBeatKing(figures, location.x - 1, location.y + 1)
				|| canBeatKing(figures, location.x - 1, location.y)
				|| canBeatKing(figures, location.x - 1, location.y - 1);
	}

	public boolean hasMoved() {
		return hasMoved;
	}

	@Override
	public void markMoves(final Field[][] fields) {
		markMove(fields, location.x, location.y - 1);
		markMove(fields, location.x + 1, location.y - 1);
		markMove(fields, location.x + 1, location.y);
		markMove(fields, location.x + 1, location.y + 1);
		markMove(fields, location.x, location.y + 1);
		markMove(fields, location.x - 1, location.y + 1);
		markMove(fields, location.x - 1, location.y);
		markMove(fields, location.x - 1, location.y - 1);
		unmarkMoves(fields);

		if (!hasMoved && fields[0][location.y].getFigure() instanceof Rook
				&& !((Rook) fields[0][location.y].getFigure()).hasMoved()
				&& fields[1][location.y].getFigure() == null
				&& fields[2][location.y].getFigure() == null
				&& fields[3][location.y].getFigure() == null) {
			fields[2][location.y].setPossible(true);
		}
		if (!hasMoved && fields[7][location.y].getFigure() instanceof Rook
				&& !((Rook) fields[7][location.y].getFigure()).hasMoved()
				&& fields[5][location.y].getFigure() == null
				&& fields[6][location.y].getFigure() == null) {
			fields[6][location.y].setPossible(true);
		}
		unmarkCastling(fields);
	}

	public void setHasMoved(final boolean hasMoved) {
		this.hasMoved = hasMoved;
	}

	@Override
	public AbstractPiece[][] simulateMove(final Point end,
			final AbstractPiece[][] figures) {
		final AbstractPiece[][] figuresCopy = Chessboard.copyFigures(figures);
		if (Math.abs(end.x - location.x) == 2) {
			if (end.x == 2) {
				figuresCopy[3][end.y] = figuresCopy[0][end.y];
				figuresCopy[0][end.y] = null;
				figuresCopy[3][end.y].setLocation(3, end.y);
			} else {
				figuresCopy[5][end.y] = figuresCopy[7][end.y];
				figuresCopy[7][end.y] = null;
				figuresCopy[5][end.y].setLocation(5, end.y);
			}
		}
		figuresCopy[end.x][end.y] = figuresCopy[location.x][location.y];
		figuresCopy[location.x][location.y] = null;
		figuresCopy[end.x][end.y].setLocation(end.x, end.y);
		((King) figuresCopy[end.x][end.y]).setHasMoved(true);
		return figuresCopy;
	}

	private boolean canBeatKing(final AbstractPiece[][] figures, final int x,
			final int y) {
		return x >= 0 && x < 8 && y >= 0 && y < 8
				&& figures[x][y] instanceof King
				&& figures[x][y].getColor() != color;
	}

	private void markMove(final Field[][] fields, final int x, final int y) {
		if (x >= 0 && x < 8 && y >= 0 && y < 8) {
			if (fields[x][y].getFigure() == null) {
				fields[x][y].setPossible(true);
			} else {
				if (fields[x][y].getFigure().getColor() != color) {
					fields[x][y].setBeatable(true);
				}
			}
		}
	}

	private void unmarkCastling(final Field[][] fields) {
		if (!hasMoved) {
			Field[][] fieldsCopy = Chessboard.copyFields(fields);
			if (Chessboard.isCheck(color, figures)) {
				fields[2][location.y].setPossible(false);
				fields[6][location.y].setPossible(false);
			} else if (fields[2][location.y].isPossible()) {
				for (int i = 1; i <= 2; i++) {
					fieldsCopy[4 - i][location.y]
							.setFigure(fieldsCopy[4][location.y].getFigure());
					fieldsCopy[4][location.y].setFigure(null);
					fieldsCopy[4 - i][location.y].getFigure().setLocation(
							4 - i, location.y);
					if (Chessboard.isCheck(color, figures)) {
						fields[2][location.y].setPossible(false);
						break;
					} else {
						fieldsCopy = Chessboard.copyFields(fields);
					}
				}
			} else if (fields[6][location.y].isPossible()) {
				for (int i = 1; i <= 2; i++) {
					fieldsCopy[4 + i][location.y]
							.setFigure(fieldsCopy[4][location.y].getFigure());
					fieldsCopy[4][location.y].setFigure(null);
					fieldsCopy[4 + i][location.y].getFigure().setLocation(
							4 + i, location.y);
					if (Chessboard.isCheck(color, figures)) {
						fields[6][location.y].setPossible(false);
						break;
					} else {
						fieldsCopy = Chessboard.copyFields(fields);
					}
				}
			}
		}
	}
}
