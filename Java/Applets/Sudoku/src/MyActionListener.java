import java.awt.Color;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Dieser {@code ActionListener} fuehrt nach Betaetigen der Buttons die ihnen
 * zugewiesenen Funktionen aus. Bei Druecken von "Neustart" wird der Inhalt
 * aller Textfelder geloescht. Bei Druecken von "Loese" wird dann logischerweise
 * der versucht, dass Raetsel zu loesen.
 * 
 * @author Lars Reimann
 * @version 3. Juni 2011
 */
public final class MyActionListener implements ActionListener {

    /**
     * Die Instanz von Solver, die fuer das Loesen des Raetsels zustaendig ist.
     */
    private final Solver solver;

    /**
     * Die Textfelder, die fuer Ein- und Ausgabe genutzt werden.
     */
    private final TextField[][] textFields;

    /**
     * Konstruiert einen neuen ActionListener mit den uebergebenen Parametern.
     * 
     * @param solver
     *            Die Instanz von Solver, die fuer das Loesen des Raetsels
     *            zustaendig ist.
     * @param textFields
     *            Die Textfelder, die fuer Ein- und Ausgabe genutzt werden.
     */
    public MyActionListener(final Solver solver, final TextField[][] textFields) {
        this.solver = solver;
        this.textFields = textFields;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        final String actionCommand = event.getActionCommand();
        if ("Neustart".equals(actionCommand)) {
            reset();
        } else if ("Loese".equals(actionCommand)) {
            try {
                setOutput(solver.solve(getInput()));
            } catch (IllegalArgumentException exception) {
                // ignoriere
            }
        }
    }

    /**
     * Verwandelt die eingegebenen Daten in ein int-Array, welches zur internen
     * Repraesentation derselben verwendet wird.
     * 
     * @return Die eingegeben Daten.
     * @throws IllegalArgumentException
     */
    private int[][] getInput() throws IllegalArgumentException {
        final int[][] input = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                try {
                    if ("0".equals(textFields[i][j].getText()) ||
                        "".equals(textFields[i][j].getText())) {
                        input[i][j] = 0;
                    } else {
                        input[i][j] = Integer.parseInt(textFields[i][j]
                                .getText());
                        if (input[i][j] < 0 || input[i][j] > 9 ||
                            !solver.isCorrect(input, i, j, input[i][j])) {
                            throw new IllegalArgumentException();
                        }
                    }
                    textFields[i][j].setBackground(Color.white);
                } catch (NumberFormatException exception) {
                    textFields[i][j].setBackground(Color.red);
                    throw new IllegalArgumentException();
                } catch (IllegalArgumentException exception) {
                    textFields[i][j].setBackground(Color.green);
                    throw new IllegalArgumentException();
                }
            }
        }
        return input;
    }

    /**
     * Loescht den Inhalt aller Textfelder.
     */
    private void reset() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                textFields[i][j].setText("");
                textFields[i][j].setBackground(Color.white);
            }
        }
    }

    /**
     * Gibt die berechnete Loesung auf den Textfeldern aus.
     * 
     * @param output
     *            Die berechnete Loesung.
     */
    private void setOutput(int[][] output) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                textFields[i][j].setText(Integer.toString(output[i][j]));
                switch (output[i][j]) {
                    case 1:
                        textFields[i][j].setBackground(Color.blue);
                        break;
                    case 2:
                        textFields[i][j].setBackground(Color.cyan);
                        break;
                    case 3:
                        textFields[i][j].setBackground(Color.gray);
                        break;
                    case 4:
                        textFields[i][j].setBackground(Color.green);
                        break;
                    case 5:
                        textFields[i][j].setBackground(Color.lightGray);
                        break;
                    case 6:
                        textFields[i][j].setBackground(Color.magenta);
                        break;
                    case 7:
                        textFields[i][j].setBackground(Color.orange);
                        break;
                    case 8:
                        textFields[i][j].setBackground(Color.pink);
                        break;
                    case 9:
                        textFields[i][j].setBackground(Color.red);
                        break;
                }
            }
        }
    }
}
