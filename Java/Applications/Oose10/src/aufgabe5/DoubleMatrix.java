package aufgabe5;

/**
 * Oose Aufgabe 5
 * 
 * @version 25.06.2013
 * @author Florian Gläßer, Tobias Lenz, Lars Reimann
 */
public class DoubleMatrix {

	/**
	 * Anzahl der Spalten der Matrix.
	 */
	private final int columns;

	/**
	 * Die Koeffizienten der Matrix.
	 */
	private final double[][] matrix;

	/**
	 * Anzahl der Zeilen der Matrix.
	 */
	private final int rows;

	/**
	 * Erstellt eine neue Matrix mit den angegebenen Koeffizienten.
	 * 
	 * @param matrix
	 *            Die Koeffizienten der Matrix.
	 * @throws IllegalArgumentException
	 *             Wird geworfen, wenn nicht alle Koeffizienten angegeben sind.
	 */
	public DoubleMatrix(double[][] matrix) {
		columns = matrix[0].length;
		rows = matrix.length;
		this.matrix = new double[rows][columns];
		for (int i = 0; i < rows; i++) {
			if (matrix[i].length != columns) {
				throw new IllegalArgumentException(
						"Manche Zellen sind nicht besetzt.");
			}
			for (int j = 0; j < columns; j++) {
				this.matrix[i][j] = matrix[i][j];
			}
		}
	}

	/**
	 * @return die Anzahl der Spalten dieser Matrix.
	 */
	public int columns() {
		return columns;
	}

	/**
	 * Multipliziert diese Matrix mit der angegebenen Matrix (this * matrix2)
	 * und gibt das Ergebnis als neue Matrix zurück.
	 * 
	 * @param matrix2
	 *            Der rechte Faktor
	 * @return
	 * @throws IllegalArgumentException
	 *             Wird geworfen, wenn die Matrixmultiplikation nicht definiert
	 *             ist.
	 */
	public DoubleMatrix matrixMult(DoubleMatrix matrix2) {
		if (this.columns != matrix2.rows) {
			throw new IllegalArgumentException(
					"Matrixmultiplikation nicht definiert.");
		}
		double[][] res = new double[this.rows][matrix2.columns];
		for (int i = 0; i < this.rows; i++) { // row
			for (int j = 0; j < matrix2.columns; j++) { // column
				double entry = 0;
				for (int k = 0; k < this.columns; k++) { // entry
					entry += this.matrix[i][k] * matrix2.matrix[k][j];
				}
				res[i][j] = entry;
			}
		}
		return new DoubleMatrix(res);
	}

	/**
	 * @return die Anzahl der Zeilen dieser Matrix.
	 */
	public int rows() {
		return rows;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < rows; i++) {

			// Klammerung vorne
			if (i == 0) {
				builder.append("/ ");
			} else if (i == rows - 1) {
				builder.append("\\ ");
			} else {
				builder.append("| ");
			}

			// Einträge
			for (int j = 0; j < columns; j++) {
				builder.append(String.format("%10f", matrix[i][j]));
				builder.append(" ");
			}

			// Klammerung hinten
			if (i == 0) {
				builder.append("\\");
			} else if (i == rows - 1) {
				builder.append("/");
			} else {
				builder.append("|");
			}

			// Neue Zeile
			if (i < rows - 1) {
				builder.append("\n");
			}
		}
		return builder.toString();
	}
}
