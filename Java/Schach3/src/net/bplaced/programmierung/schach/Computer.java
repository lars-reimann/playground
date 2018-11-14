package net.bplaced.programmierung.schach;

import java.awt.Point;
import java.util.ArrayList;

public final class Computer implements Runnable {

	private final transient Chessboard chessboard;
	private transient int color;
	private transient Point end;
	private AbstractPiece[][] figures;
	private Field[][] fields;
	private transient final int maxDepth = 4;
	private transient ArrayList<AbstractPiece> whiteFigures;
	private transient ArrayList<AbstractPiece> blackFigures;

	private String openingMoves = "";
	private final transient int[][] positionValueBishop = {
			{ 0, 0, 2, 4, 4, 2, 0, 0 }, { 0, 8, 6, 8, 8, 6, 8, 0 },
			{ 2, 6, 12, 10, 10, 12, 6, 2 }, { 4, 8, 10, 16, 16, 10, 8, 4 },
			{ 4, 8, 10, 16, 16, 10, 8, 4 }, { 2, 6, 12, 10, 10, 12, 6, 2 },
			{ 0, 8, 6, 8, 8, 6, 8, 0 }, { -10, -10, -8, -6, -6, -8, -10, -10 } };

	private final transient int[][] positionValueKing = {
			{ -40, -40, -40, -40, -40, -40, -40, -40 },
			{ -40, -10, -10, -10, -10, -10, -10, -40 },
			{ -40, -10, 20, 20, 20, 20, -10, -40 },
			{ -40, -10, 40, 40, 40, 40, -10, -40 },
			{ -40, -10, 60, 60, 60, 60, -10, -40 },
			{ -40, -10, 60, 60, 60, 60, -10, -40 },
			{ -40, -10, -10, -10, -10, -10, -10, -40 },
			{ -40, -40, -40, -40, -40, -40, -40, -40 } };

	private final transient int[][] positionValueKnight = {
			{ -29, -19, -19, -9, -9, -19, -19, -29 },
			{ 1, 12, 18, 22, 22, 18, 12, 1 }, { 1, 14, 23, 27, 27, 23, 14, 1 },
			{ 1, 14, 23, 28, 28, 23, 14, 1 }, { 1, 12, 21, 24, 24, 21, 12, 1 },
			{ 1, 2, 19, 17, 17, 19, 2, 1 }, { 1, 2, 2, 2, 2, 2, 2, 1 },
			{ -19, -19, -19, -19, -19, -19, -19, -19 } };

	private final transient int[][] positionValuePawn = {
			{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 66, 66, 66, 66, 66, 66, 66, 66 },
			{ 10, 10, 10, 30, 30, 10, 10, 10 }, { 6, 6, 6, 16, 16, 6, 6, 6 },
			{ 3, 3, 3, 13, 13, 3, 3, 3 }, { 1, 1, 1, 10, 10, 1, 1, 1 },
			{ 0, 0, 0, -12, -12, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 } };

	private final transient int[][] positionValueQueen = {
			{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 4, 4, 4, 4, 0, 0 },
			{ 0, 4, 4, 6, 6, 4, 4, 0 }, { 0, 4, 6, 8, 8, 6, 4, 0 },
			{ 0, 4, 6, 8, 8, 6, 4, 0 }, { 0, 4, 4, 6, 6, 4, 4, 0 },
			{ 0, 0, 4, 4, 4, 4, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 } };

	private transient Point start;

	public Computer(final Chessboard chessboard,
			final AbstractPiece[][] figures, final Field[][] fields) {
		this.chessboard = chessboard;
		whiteFigures = new ArrayList<AbstractPiece>(16);
		blackFigures = new ArrayList<AbstractPiece>(16);
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (figures[i][j] != null) {
					if (figures[i][j].getColor() == -1) {
						whiteFigures.add(figures[i][j]);
					} else {
						blackFigures.add(figures[i][j]);
					}
				}
			}
		}
		this.figures = figures;
		this.fields = fields;
	}

	public int getColor() {
		return color;
	}

	public int maxMove(final int depth, int alpha, int beta,
			final Field[][] fields, final AbstractPiece[][] figures) {
		if (depth > 0) {
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (figures[i][j] != null
							&& figures[i][j].getColor() == color) {
						for (int k = 0; k < 8; k++) {
							for (int l = 0; l < 8; l++) {
								fields[k][l].resetSelections();
							}
						}
						figures[i][j].markMoves(fields);
						for (int k = 0; k < 8; k++) {
							for (int l = 0; l < 8; l++) {
								if (fields[k][l].isBeatable()
										|| fields[k][l].isPossible()) {
									final AbstractPiece[][] figuresCopy = figures[i][j]
											.simulateMove(new Point(k, l),
													figures);
									final int fitness = minMove(depth - 1,
											alpha, beta, fields, figuresCopy);
									if (fitness >= beta) {
										return beta;
									}
									if (fitness > alpha) {
										alpha = fitness;
										if (depth == maxDepth) {
											start = new Point(i, j);
											end = new Point(k, l);
										}
									}
								}
							}
						}
					}
				}
			}
			if (alpha == Integer.MIN_VALUE) {
				return computeFitness(fields, figures);
			} else {
				return alpha;
			}
		} else {
			return computeFitness(fields, figures);
		}
	}

	public int minMove(final int depth, int alpha, int beta,
			final Field[][] fields, final AbstractPiece[][] figures) {
		if (depth > 0) {
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (figures[i][j] != null
							&& figures[i][j].getColor() != color) {
						for (int k = 0; k < 8; k++) {
							for (int l = 0; l < 8; l++) {
								fields[k][l].resetSelections();
							}
						}
						figures[i][j].markMoves(fields);
						for (int k = 0; k < 8; k++) {
							for (int l = 0; l < 8; l++) {
								if (fields[k][l].isBeatable()
										|| fields[k][l].isPossible()) {
									final AbstractPiece[][] figuresCopy = figures[i][j]
											.simulateMove(new Point(k, l),
													figures);
									final int fitness = maxMove(depth - 1,
											alpha, beta, fields, figuresCopy);
									if (fitness <= alpha) {
										return alpha;
									}
									if (fitness < beta) {
										beta = fitness;
									}
								}
							}
						}
					}
				}
			}
			if (beta == Integer.MAX_VALUE) {
				return computeFitness(fields, figures);
			} else {
				return beta;
			}
		} else {
			return computeFitness(fields, figures);
		}
	}

	public void moveFigure() {
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		if (color == -1 && chessboard.getMoveCount() == 1) {
			chessboard.clickOnField(4, 6);
			chessboard.clickOnField(4, 4);
		} else if (openingMoves.equals("")) {
			maxMove(maxDepth, Integer.MIN_VALUE, Integer.MAX_VALUE,
					Chessboard.copyFields(fields),
					Chessboard.copyFigures(figures));
			chessboard.clickOnField(start.x, start.y);
			chessboard.clickOnField(end.x, end.y);
		}
	}

	public void setColor(final int color) {
		this.color = color;
	}

	public void setFields(final Field[][] fields) {
		this.fields = fields;
	}

	public void setFigures(final AbstractPiece[][] figures) {
		this.figures = figures;
	}

	private int computeFitness(final Field[][] fields,
			final AbstractPiece[][] figures) {
		int fitness = 0;
		int playerColor = color == -1 ? 1 : -1;
		if (!Chessboard.hasPossibleMoves(playerColor, figures, fields)) {
			if (Chessboard.isCheck(playerColor, figures)) {
				fitness = 1000000;
			} else {
				fitness = -500000;
			}
		} else if (!Chessboard.hasPossibleMoves(color, figures, fields)) {
			if (Chessboard.isCheck(color, figures)) {
				fitness = -1000000;
			} else {
				fitness = 500000;
			}
		} else {
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					final AbstractPiece figure = figures[i][j];
					int fitnessChange = 0;
					if (figure != null) {
						if (figure instanceof Bishop) {
							fitnessChange += 325;
							if (figure.getColor() == -1) {
								fitnessChange += positionValueBishop[i][j];
								if (i - 1 >= 0
										&& j + 1 < 8
										&& fields[i - 1][j + 1].getFigure() instanceof Pawn
										&& fields[i - 1][j + 1].getFigure()
												.getColor() == -1) {
									fitnessChange += 18;
								}
								if (i + 1 < 8
										&& j + 1 < 8
										&& fields[i + 1][j + 1].getFigure() instanceof Pawn
										&& fields[i + 1][j + 1].getFigure()
												.getColor() == -1) {
									fitnessChange += 18;
								}
							} else {
								fitnessChange += positionValueBishop[i][7 - j];
								if (i - 1 >= 0
										&& j - 1 >= 0
										&& fields[i - 1][j - 1].getFigure() instanceof Pawn
										&& fields[i - 1][j - 1].getFigure()
												.getColor() == 1) {
									fitnessChange += 18;
								}
								if (i + 1 < 8
										&& j - 1 >= 0
										&& fields[i + 1][j - 1].getFigure() instanceof Pawn
										&& fields[i + 1][j - 1].getFigure()
												.getColor() == 1) {
									fitnessChange += 18;
								}
							}
						} else if (figure instanceof Knight) {
							fitnessChange += 325;
							if (figure.getColor() == -1) {
								fitnessChange += positionValueKnight[i][j];
							} else {
								fitnessChange += positionValueKnight[i][7 - j];
							}
						} else if (figure instanceof King) {
							if (figure.getColor() == -1) {
								fitnessChange += positionValueKing[i][j];
							} else {
								fitnessChange += positionValueKing[i][7 - j];
							}
						} else if (figure instanceof Pawn) {
							fitnessChange += 100;
							if (figure.getColor() == -1) {
								fitnessChange += positionValuePawn[i][j];
							} else {
								fitnessChange += positionValuePawn[i][7 - j];
							}
						} else if (figure instanceof Queen) {
							fitnessChange += 1050 + positionValueQueen[i][j];
						} else if (figure instanceof Rook) {
							fitnessChange += 500;
						}
						if (figure.getColor() != color) {
							fitnessChange *= -1;
						}
					}
					fitness += fitnessChange;
				}
			}
		}
		return fitness;
	}
}
