package net.bplaced.programmierung.schach;

import java.awt.Point;

public final class Rook extends AbstractPiece {

	private transient boolean hasMoved = false;

	/**
	 * Konstruiert einen neuen Turm mit vorgegebener Farbe und Position auf dem
	 * Spielfeld.
	 * 
	 * @param color
	 *            Die Farbe der Figur (weiss/schwarz).
	 * @param x
	 *            Die x-Position der Figur auf dem Schachbrett.
	 * @param y
	 *            Die y-Position der Figur auf dem Schachbrett.
	 */
	public Rook(final int color, final int x, final int y) {
		super();
		this.color = color;
		this.location.x = x;
		this.location.y = y;
	}

	@Override
	public boolean canBeatKing(final AbstractPiece[][] figures) {
		return canBeatKing(figures, 1, 0) || canBeatKing(figures, -1, 0)
				|| canBeatKing(figures, 0, 1) || canBeatKing(figures, 0, -1);
	}

	public boolean hasMoved() {
		return hasMoved;
	}

	@Override
	public void markMoves(final Field[][] fields) {
		markMoves(fields, 1, 0);
		markMoves(fields, -1, 0);
		markMoves(fields, 0, 1);
		markMoves(fields, 0, -1);
		unmarkMoves(fields);
	}

	public void setHasMoved(final boolean hasMoved) {
		this.hasMoved = hasMoved;
	}

	@Override
	public AbstractPiece[][] simulateMove(final Point end,
			final AbstractPiece[][] figures) {
		final AbstractPiece[][] figuresCopy = Chessboard.copyFigures(figures);
		figuresCopy[end.x][end.y] = figuresCopy[location.x][location.y];
		figuresCopy[location.x][location.y] = null;
		figuresCopy[end.x][end.y].setLocation(end.x, end.y);
		((Rook) figuresCopy[end.x][end.y]).setHasMoved(true);
		return figuresCopy;
	}

	private boolean canBeatKing(final AbstractPiece[][] figures,
			final int xChange, final int yChange) {
		boolean canBeatKing = false;
		for (int i = 1; location.x + i * xChange >= 0
				&& location.x + i * xChange < 8
				&& location.y + i * yChange >= 0
				&& location.y + i * yChange < 8; i++) {
			final AbstractPiece figure = figures[location.x + i * xChange][location.y
					+ i * yChange];
			if (figure != null) {
				if (figure instanceof King && figure.getColor() != color) {
					canBeatKing = true;
				}
				break;
			}
		}
		return canBeatKing;
	}

	private void markMoves(final Field[][] fields, final int xChange,
			final int yChange) {
		for (int i = 1; location.x + i * xChange >= 0
				&& location.x + i * xChange < 8
				&& location.y + i * yChange >= 0
				&& location.y + i * yChange < 8; i++) {
			final Field field = fields[location.x + i * xChange][location.y + i
					* yChange];
			if (field.getFigure() == null) {
				field.setPossible(true);
			} else {
				if (field.getFigure().getColor() != color) {
					field.setBeatable(true);
				}
				break;
			}
		}
	}
}
