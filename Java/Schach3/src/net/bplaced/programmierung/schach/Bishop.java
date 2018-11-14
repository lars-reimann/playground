package net.bplaced.programmierung.schach;

/**
 * 
 * @author Lars Reimann
 * @version 2. Maerz 2011
 */
public final class Bishop extends AbstractPiece {

	/**
	 * Konstruiert einen neuen Laeufer mit vorgegebener Farbe und Position auf
	 * dem Spielfeld.
	 * 
	 * @param color
	 *            Die Farbe der Figur (weiss/schwarz).
	 * @param x
	 *            Die x-Position der Figur auf dem Schachbrett.
	 * @param y
	 *            Die y-Position der Figur auf dem Schachbrett.
	 */
	public Bishop(final int color, final int x, final int y) {
		super();
		this.color = color;
		this.location.x = x;
		this.location.y = y;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canBeatKing(final AbstractPiece[][] figures) {
		return canBeatKing(figures, 1, 1) || canBeatKing(figures, -1, -1)
				|| canBeatKing(figures, -1, 1) || canBeatKing(figures, 1, -1);
	}

	@Override
	public void markMoves(final Field[][] fields) {
		markMoves(fields, 1, 1);
		markMoves(fields, -1, -1);
		markMoves(fields, -1, 1);
		markMoves(fields, 1, -1);
		unmarkMoves(fields);
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
