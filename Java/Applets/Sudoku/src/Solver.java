/**
 * Diese Klasse beinhaltet den Algorithmus zum Loesen der Sudokus.
 * 
 * @author Lars Reimann
 * @version 3. Juni 2011
 */
public class Solver {

    /**
     * Hier wird die Loesung gespeichert.
     */
    private int[][] sudoku;

    /**
     * Diese Methode zaehlt die noch freien Felder aus. So kann schliesslich
     * beim Loesen ein Zaehler dekrementiert werden, so dass das Erreichen eines
     * bestimmten Wertes als Abbruchbedingung fuer die Rekursion fungieren kann.
     * 
     * @return Die Anzahl der noch freien Felder.
     */
    private int countFreeFields() {
        int count = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudoku[i][j] == 0) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Schaut, ob das uebergebene Raetsel bis dato korrekt ist. Dies
     * funktioniert so: Die Reihe, Spalte und das 3x3 Rechteck, in welches die
     * Zahl n eingefuegt worden ist, werden durchlaufen. Wenn dort bereits eine
     * solche Zahl vorhanden war, wird false zurueckgegeben, was anzeigt, dass
     * die momentane Loesung nicht korrekt ist. Sonst wird, wenn alle Tests
     * bestanden sind, true zurueckgegeben.<br>
     * Diese Methode wird auch zur Validierung der Eingabe genutzt und wird
     * somit vor dem eigentlichen Loesen aufgerufen. Wenn eine inkorrekt
     * Eingabe, wie etwa zwei neunen in der gleichen Reihe, gefunden wird, wird
     * dies entsprechend angezeigt.
     * 
     * @param sudoku
     *            Das zu ueberpruefende Raetsel.
     * @param x
     *            Die x-Koordinate der Position, wo die Zahl eingefuegt worden
     *            ist.
     * @param y
     *            Die y-Koordinate der Position, wo die Zahl eingefuegt worden
     *            ist.
     * @param number
     *            Die eingesetzte Zahl.
     * @return Ob das Raetsel bisher korrekt ist.
     */
    public boolean isCorrect(final int[][] sudoku, final int x, final int y,
                             final int number) {
        int squareX = (x / 3) * 3;
        int squareY = (y / 3) * 3;
        for (int i = 0; i < 9; i++) {
            if ((sudoku[x][i] == number && i != y) ||
                (sudoku[i][y] == number && i != x) ||
                (sudoku[squareX + (i % 3)][squareY + (i / 3)] == number &&
                 squareX + (i % 3) != x && squareY + (i / 3) != y)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Diese oeffentliche Methode dient als Schnittstelle zu anderen Teile des
     * Programmes. Ihr wird das zu loesende Raetsel uebergeben und anschliessend
     * wird die eigentliche Methode zum Loesen aufgerufen. Das geloeste Sudoku
     * wird dann zurueckgegeben.
     * 
     * @param sudoku
     *            Das zu loesende Raetsel.
     * @return Das geloeste Raetsel.
     */
    public int[][] solve(final int[][] sudoku) {
        this.sudoku = sudoku;
        solveSudoku(countFreeFields());
        return this.sudoku;
    }

    /**
     * In dieser Methode ist der eigentliche Loesungsalgorithmus implementiert.
     * Das Array wird Wert fuer Wert durchlaufen und wenn ein bisher leeres Feld
     * (Wert 0) gefunden wird, so werden dort der Reihe nach die Zahlen 1 bis 9
     * eingesetzt. Dann wird ueberprueft, ob die bisherige Loesung richtig ist,
     * also keine Zahl doppelt in einer Reihe, Spalte oder in einem 3x3 Quadrat
     * zu finden ist. Wenn dies gilt und keine freien Felder mehr vorhanden sind
     * (n gleich 1), wird true zurueckgegeben. Andernfalls wird, wenn die
     * Loesung soweit in Ordnung ist die Methode selbst aufgerufen. Wenn auf
     * diese Weise nach mehreren Rekursionsschritten eine Loesung gefunden wird,
     * wird der Rueckgabewert true quasi nach oben im Rekursionsbaum
     * durchgereicht und das Verfahren endet. Falls keine der eingesetzten
     * Zahlen zu einer Loesung fuehrt, wird ebenfalls auf hoehere
     * Rekursionsebenen zurueckgegangen.
     * 
     * @param n
     *            Die bisher noch freien Felder.
     * @return Ob das Raetsel geloest worden ist.
     */
    private boolean solveSudoku(final int n) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudoku[i][j] == 0) {
                    for (int k = 1; k <= 9; k++) {
                        sudoku[i][j] = k;
                        if (isCorrect(sudoku, i, j, k) &&
                            (n == 1 || solveSudoku(n - 1))) {
                            return true;
                        }
                    }
                    sudoku[i][j] = 0;
                    return false;
                }
            }
        }
        return false;
    }
}
