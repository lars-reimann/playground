package aufgabe4;

public class Queensproblem {

	public static void main(String[] arg) {
		Queensproblem test = new Queensproblem(8);
		test.solve();
	}

	/**
	 * Die Belegung des Schachbretts mit Damen.
	 */
	private final int[][] board;

	/**
	 * Die Größe des Schachbretts.
	 */
	private final int size;

	/**
	 * Die Anzahl der bisher gefundenen Lösungen.
	 */
	private int solCount = 0;

	public Queensproblem(int d) {
		size = d;
		board = new int[size][size];
	}

	private boolean isValid(int file, int rank) {
		int count = 0;
		
		// Zeile
		for (int i = 0; i < size; i++) {
			count += board[i][rank];
		}
		if (count > 1) {
			return false;
		}

		// Hauptdiagonale
		count = 0;
		for (int i = Math.max(-file, rank - (size - 1));  i <= Math.min(-file + (size - 1), rank); i++) {
			count += board[file + i][rank - i];
		}
		if (count > 1) {
			return false;
		}

		// Nebendiagonale
		count = 0;
		for (int i = -Math.min(file, rank); i <= size - 1 - Math.max(file, rank); i++) {
			count += board[file + i][rank + i];
		}
		if (count > 1) {
			return false;
		}

		return true;
	}

	private void place(int file) {
		if (file == size) {
			solCount++;
			printSolution();
			System.out.println();
		} else {
			for (int rank = 0; rank < size; rank++) {
				board[file][rank] = 1;
				if (isValid(file, rank)) {
					place(file + 1);
				}
				board[file][rank] = 0;
			}
		}
	}

	private void printFileLabels() {
		System.out.print("  ");
		for (int file = 0; file < size; file++) {
			System.out.print((char) ('A' + file) + " ");
		}
		System.out.println();
	}

	private void printRankLabel(int rank) {
		System.out.print(rank + " ");
	}

	private void printSolution() {
		printFileLabels();
		for (int rank = size - 1; rank >= 0; rank--) {
			printRankLabel(rank + 1);
			for (int file = 0; file < size; file++) {
				if (board[file][rank] == 1) {
					System.out.print("\u2655 ");
				} else {
					System.out.print(". ");
				}
			}
			printRankLabel(rank + 1);
			System.out.println();
		}
		printFileLabels();
	}

	public void solve() {
		place(0);
		System.out.println("Es wurden " + solCount + " Lösungen gefunden.");
	}
}