import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * @author Lars Reimann
 * @version 23. September 2011
 */
public final class HangmanLogic {

    private String[] categories;
    private int currentCategory = 0;
    private int currentLevel;
    private String currentWord;
    private int[] ends;
    private int errorCount;
    private final HangmanFrame frame;
    private int guessCount;
    private final HangmanGuyDisplay guyDisplay;
    private final HangmanIO io;
    private boolean[] letters;
    private final HangmanLettersDisplay lettersDisplay;
    private int[][] scores;
    private String shownWord;
    private final JTextField textField;
    private final HangmanWordDisplay wordDisplay;
    private ArrayList<String>[] wordLists;
    private int xIndex;
    private int yIndex;

    public HangmanLogic(final JTextField textField,
                        final HangmanGuyDisplay guyDisplay,
                        final HangmanLettersDisplay lettersDisplay,
                        final HangmanWordDisplay wordDisplay,
                        final HangmanFrame frame) {
        this.textField = textField;
        this.guyDisplay = guyDisplay;
        this.lettersDisplay = lettersDisplay;
        this.wordDisplay = wordDisplay;
        this.frame = frame;
        this.io = new HangmanIO();

        letters = new boolean[26];
    }

    public void check() {
        final String input = textField.getText().toUpperCase();
        final int inputLength = input.length();
        final int wordLength = currentWord.length();
        for (int i = 0; i < inputLength; i++) {
            final char inputChar = input.charAt(i);
            if (inputChar >= 'A' && inputChar <= 'Z' &&
                !letters[inputChar - 'A']) {
                letters[inputChar - 'A'] = true;
                if (currentWord.contains(Character.toString(inputChar))) {
                    scores[currentCategory][2]++;
                    for (int j = 0; j < wordLength; j++) {
                        if (currentWord.charAt(j) == inputChar) {
                            shownWord = shownWord.substring(0, j) + inputChar +
                                        shownWord.substring(j + 1);
                        }
                    }
                } else {
                    errorCount++;
                }
                guessCount++;

                scores[currentCategory][3]++;
                checkEndOfGame();
            }

        }
        wordDisplay.setShownWord(shownWord);
        wordDisplay.repaint();

        lettersDisplay.setLetters(letters);
        lettersDisplay.repaint();

        guyDisplay.setErrorCount(errorCount);
        guyDisplay.repaint();

        textField.setText("");
    }

    private void checkEndOfGame() {
        final boolean isWon = shownWord.equals(currentWord);
        final boolean isLost = errorCount == 10;

        if (errorCount > 10) {
            JOptionPane
                    .showMessageDialog(
                                       frame,
                                       "Ein unbekannter Programmfehler ist aufgetreten.\nBitte wende dich an den Entwickler.\nDas Programm wird ohne Speichern beendet.");
            System.exit(1);
        }

        if (isWon || isLost) {
            if (isWon) {
                guyDisplay.setWon(true);
                Thread thread = new Thread(guyDisplay);
                thread.start();
                JOptionPane.showMessageDialog(frame, "Gut gemacht! (Richtig: " +
                                                     (guessCount - errorCount) +
                                                     " Falsch: " + errorCount +
                                                     ").");

                scores[currentCategory][0]++;
            } else if (isLost) {
                guyDisplay.setWon(false);
                Thread thread = new Thread(guyDisplay);
                thread.start();

                JOptionPane
                        .showMessageDialog(frame,
                                           "Das war wohl nichts... (Richtig: " +
                                                   (guessCount - errorCount) +
                                                   " Falsch: " + errorCount +
                                                   ")\nDie Antwort war " +
                                                   currentWord + ".");

            }

            scores[currentCategory][1]++;

            if (currentLevel > computeLevel(currentCategory)) {
                final int newLevel = computeLevel(currentCategory);
                JOptionPane
                        .showMessageDialog(
                                           frame,
                                           "Du bist abgestiegen!\nNeue Bewertung: " +
                                                   newLevel +
                                                   " (" +
                                                   (newLevel - currentLevel) +
                                                   ")");
                currentLevel = computeLevel(currentCategory);
            } else if (currentLevel < computeLevel(currentCategory)) {
                final int newLevel = computeLevel(currentCategory);
                JOptionPane
                        .showMessageDialog(
                                           frame,
                                           "Du bist aufgestiegen!\nNeue Bewertung: " +
                                                   newLevel +
                                                   " (+" +
                                                   (newLevel - currentLevel) +
                                                   ")");
                currentLevel = computeLevel(currentCategory);
            }
            swap();
            nextWord(true);
        }

    }

    public String chooseWord() {
        final Random random = new Random();
        String word;
        if (currentCategory == -1) {
            xIndex = random.nextInt(wordLists.length);
            yIndex = random.nextInt(ends[xIndex]);
            word = wordLists[xIndex].get(yIndex);
        } else {
            xIndex = currentCategory;
            yIndex = random.nextInt(ends[currentCategory]);
            word = wordLists[currentCategory].get(yIndex);
        }
        word = word.toUpperCase();
        word = word.replace("Ä", "AE");
        word = word.replace("Ö", "OE");
        word = word.replace("Ü", "UE");
        word = word.replace("ß", "SS");
        return word;
    }

    /**
     * Speichert die Statistiken ab und schlie&szlig;t das Fenster. So wird das
     * Programm beendet.
     */
    public void close() {
        if (categories.length != scores.length ||
            categories.length != wordLists.length) {
            JOptionPane
                    .showMessageDialog(
                                       frame,
                                       "Ein unbekannter Programmfehler ist aufgetreten.\nBitte wende dich an den Entwickler.\nDas Programm wird ohne Speichern beendet.");
            System.exit(1);
        }
        io.writeFiles(categories, scores, wordLists);
        frame.setVisible(false);
        frame.dispose();
    }

    /**
     * Berechnet eine Bewertung der aktuellen Statistik. Dabei wird sowohl der
     * quantitative Aspekt, wie auch der qualitative Aspekt einbezogen. Das
     * bedeutet, dass man mehr Punkte erh&auml;t, wenn man insgesamt mehr
     * Begriffe oder Buchstaben gefunden hat und ebenfalls mehr Punkte, wenn die
     * prozentuale Trefferquote besonders gut ist. Eine gute Trefferquote auf
     * die ganzen W&ouml;rter wird bei beidem st&auml;rker belohnt als eine auf
     * einzelne Buchstaben. Auch flie&szlig;t der Prozentsatz st&auml;rker ein
     * als der absolute Wert der gewussten W&ouml;rter bzw. Buchstaben. Des
     * Weiteren wird mit h&ouml;herem Punktestand das Gewinnen neuer Punkte
     * immer schwieriger und zeitaufw&auml;diger, w&auml;hrend das Verlieren von
     * Punkten immer schneller passieren kann. Theoretisch sind zwar unendlich
     * hohe Puntzahlen m&ouml;glich, aber es sollte selbst bei perfektem und
     * andauerndem Spiel nicht m&ouml;glich sein, den Integerbereich in einem
     * Menschenleben zu knacken, au&szlig;er man manipuliert die Textdateien.
     * Aber das macht ja sicher keiner...
     * 
     * @param category
     *            Die Kategorie f&uuml;r die die Statistik erstellt werden soll.
     * @return Die berechnete Bewertung.
     */
    private int computeLevel(final int category) {
        try {
            if (scores[category][0] > scores[category][1] || scores[category][2] > scores[category][3]) {
                throw new NumberFormatException();
            }
            final int level = (int) Math
                .round(10 *
                       Math.log(2 * scores[category][0] + scores[category][2]) *
                       (1000 *
                        Math.pow((double) scores[category][0] /
                                 (double) scores[category][1], 2) *
                        (double) scores[category][2] / (double) scores[category][3]));
            if (level < 0) {
                throw new RuntimeException();
            }
            return level;
        } catch (final NumberFormatException e) {
            punishCheater(category);
            return 0;
        }
        
    }

    /**
     * @param category
     */
    private void punishCheater(final int category) {
        JOptionPane.showMessageDialog(frame, "Schummler!\nSolcherlei Eingaben in den Textdateien sind nicht erlaubt!\nUnd ganz nebenbei vernichtest du dir selbst die Freude am Spiel!");
        scores[category][0] = 0;
        scores[category][1] = 0;
        scores[category][2] = 0;
        scores[category][3] = 0;
    }

    public String disguiseWord() {
        final int length = currentWord.length();
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            final char currentChar = currentWord.charAt(i);
            if (currentChar >= 'A' && currentChar <= 'Z') {
                builder.append("=");
            } else {
                builder.append(currentChar);
            }
        }
        return builder.toString();
    }

    public void nextWord(final boolean isFinished) {
        if (!isFinished && guessCount != 0) {
            scores[currentCategory][1]++;
        }

        Arrays.fill(letters, false);
        guessCount = 0;
        errorCount = 0;

        textField.setText("");

        lettersDisplay.setLetters(letters);
        lettersDisplay.repaint();

        guyDisplay.setErrorCount(0);
        guyDisplay.repaint();

        currentWord = chooseWord();
        shownWord = disguiseWord();

        wordDisplay.setShownWord(shownWord);
        wordDisplay.repaint();
    }

    public void setup() {
        wordLists = io.readFiles();
        categories = new String[wordLists.length];
        scores = new int[wordLists.length][4];
        ends = new int[wordLists.length];
        for (int i = 0; i < wordLists.length; i++) {
            categories[i] = wordLists[i].get(0);
            wordLists[i].remove(0);
            for (int j = 0; j < 4; j++) {
                try {
                    scores[i][j] = Integer.parseInt(wordLists[i].get(0));
                } catch (NumberFormatException e) {
                    punishCheater(i);
                    for (; j < 4; j++) {
                        wordLists[i].remove(0);
                    }
                    break;
                }
                wordLists[i].remove(0);
            }
            ends[i] = wordLists[i].size();
        }

        currentWord = chooseWord();
        shownWord = disguiseWord();

        wordDisplay.setShownWord(shownWord);
        wordDisplay.repaint();

        currentLevel = computeLevel(currentCategory);
    }

    public void showScores() {
        final JOptionPane op = new JOptionPane("Deine bisherigen Ergebnisse:\n");
        final JTextArea textArea = new JTextArea(15, 30);
        textArea.setEditable(false);
        for (int i = 0; i < categories.length; i++) {
            textArea.append(categories[i] + ": \n\t" + scores[i][0] + "/" +
                            scores[i][1] + " Begriffe richtig (");
            if (scores[i][1] == 0) {
                textArea.append("0%)");
            } else {
                textArea.append(Math.round((double) scores[i][0] * 100 /
                                           (double) scores[i][1]) +
                                "%)");
            }
            textArea.append("\n\t" + scores[i][2] + "/" + scores[i][3] +
                            " Buchstaben richtig (");
            if (scores[i][3] == 0) {
                textArea.append("0%)");
            } else {
                textArea.append(Math.round((double) scores[i][2] * 100 /
                                           (double) scores[i][3]) +
                                "%)");
            }
            textArea.append("\n\tBewertung: " + computeLevel(i) + "\n\n");
        }
        final JScrollPane sp = new JScrollPane(textArea);
        sp.setBorder(null);
        op.add(sp);
        final JDialog di = op.createDialog(frame, "Statistik");
        di.setVisible(true);
    }

    public void showSettings() {
        final String category = (String) JOptionPane
                .showInputDialog(frame, "Wechsele zu einer neuen Kategorie.",
                                 "Optionen", JOptionPane.PLAIN_MESSAGE, null,
                                 categories, categories[0]);
        for (int i = 0; i < categories.length; i++) {
            if (categories[i].equals(category)) {
                if (guessCount == 0) {
                    currentCategory = i;
                    nextWord(true);
                } else {
                    scores[currentCategory][1]++;
                    currentCategory = i;
                    nextWord(true);
                }
                currentLevel = computeLevel(i);
            }
        }
    }

    private void swap() {
        wordLists[xIndex].remove(yIndex);
        wordLists[xIndex].add(currentWord);
        ends[xIndex]--;
        if (ends[xIndex] == 0) {
            ends[xIndex] = wordLists[xIndex].size();
        }
    }
}
