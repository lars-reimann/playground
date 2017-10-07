package aufgabe5;

/**
 * Oose Aufgabe 5
 * 
 * @version 25.06.2013
 * @author Florian Gläßer, Tobias Lenz, Lars Reimann
 */
public class Start {
	public static void main(String[] args) {
		DoubleMatrix matrix1 = new DoubleMatrix(new double[][]{{2, 0}, {0, 1}, {1, 1}});
		DoubleMatrix matrix2 = new DoubleMatrix(new double[][]{{2, 0, 0}, {0, 1, 3}});
		System.out.println(matrix1 + "\n");
		System.out.println(" *\n");
		System.out.println( matrix2 + "\n");
		System.out.println(" =\n");
		System.out.println(matrix1.matrixMult(matrix2));
	}
}
