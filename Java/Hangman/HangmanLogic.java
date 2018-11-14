import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Die Spiellogik des Hangmanspieles, die den gesamten Ablauf des Spieles steuert. Dies
 * sind zum Beispiel die &Uuml;berpr&uuml;fung der Eingabe, das Testen der Siegbedingungen,
 * das Reagieren auf die Anweisung, das Programm zu Beenden, das Anzeigen neuer W&ouml;rter
 * und das Ver&auml;ndern derselben.
 * 
 * @author Lars Reimann
 * @version 23. September 2011
 */
public final class HangmanLogic {

    /**
     * Die Liste mit den Namen der Kategorien .
     */
    private String[] categories;
    
    /**
     * Die momentan ausgew&auml;hlte Kategorie.
     */
    private int currentCategory = 0;
    
    /**
     * Die momentane Bewertung der Statistik der Kategorie.
     */
    private int currentLevel;
    
    /**
     * Das zu erratende Wort.
     */
    private String currentWord;
    
    /**
     * Die Positition, bis zu welchem die W&ouml;rter in den entsprechenden Kategorien
     * zuf&auml;llig ausgew&auml;hlt werden. Dies verhindert, dass ein Wort zweimal
     * hintereinander w&auml;hrend einer einzelnen Sitzung zu erraten ist. Erst wenn das
     * Programm geschlossen und wieder neu ge&ouml;ffnet wird oder wenn alle W&ouml;rter
     * der Wortliste einmal ausgew&auml;hlt wurden, kann ein Wort erneut vorkommen.
     */
    private int[] ends;
    
    /**
     * Die Anzahl der gemachten Fehler, also der falsch geratenen Buchstaben.
     */
    private int errorCount;
    
    /**
     * Das Fenster, in dem das Programm abl&auml;uft.
     */
    private final HangmanFrame frame;
    
    /**
     * Die Anzahl der geratenen Buchstaben insgesamt, ohne zu schauen, ob sie richtig oder falsch waren.
     */
    private int guessCount;
    
    /**
     * Die grafische Anzeige des aktuellen Spielstandes.
     */
    private final HangmanGuyDisplay guyDisplay;
    
    /**
     * Die Instanz der Ein- und Ausgabe.
     */ 
    private final HangmanIO io;
    
    /**
     * Die Buchstabenliste, die verwendet wird, um nachzuhalten, welche Buchstaben schon geraten wurden
     * und welche noch zur Verf&uuml;gung stehen. Dabei steht der Literal false daf&uuml;r, dass der Buchstabe
     * noch nicht geteste worden ist und der Literal true dokumentiert, dass selbiger bereits
     * &uuml;berpr&uuml;ft worden ist.
     */
    private boolean[] letters;
    
    /**
     * Der Teil des Programmes, der f&uuml;r die grafische Ausgabe der Buchstabenliste verantwortlich ist.
     */
    private final HangmanLettersDisplay lettersDisplay;
    
    /**
     * Die bisherigen Ergebnisse in den einzelnen Kategorien (richtig geratene W&ouml;rter/insgesamt zu
     * ratende W&ouml;rter/richtig geratene Buchstaben/ingesamt geratene Buchstaben).
     */
    private int[][] scores;
    
    /**
     * Das angezeigt, verschl&uuml;sselte Wort.
     */
    private String shownWord;
    
    /**
     * Das Textfeld zur Eingabe der Buchstaben.
     */
    private final JTextField textField;
    
    /**
     * Die Anzeige des zu erratenden, verschl&uuml;sselten Wortes.
     */
    private final HangmanWordDisplay wordDisplay;
    
    /**
     * Die Wortlisten aufgeteilt nach Kategorien.
     */
    private ArrayList<String>[] wordLists;
    
    /**
     * Die x-Position des momentanen Wortes in den Wortlisten.
     */
    private int xIndex;
    
    /**
     * Die y-Position des momentanen Wortes in den Wortlisten.
     */
    private int yIndex;

    /**
     * Die Instanzvariablen werden entsprechend mit den &uuml;bergebenen
     * Parametern initialisiert. Dem boolean-Array f&uuml;r die
     * Buchstabenliste wird eine neue Instanz zugewiesen, welche 26
     * Wahrheitswerte enth&auml,lt.
     * 
     * @param textField DasTextfeld zur Eingabe von Buchstaben.
     * @param guyDisplay Die grafische Anzeige des Spielstandes.
     * @param lettersDisplay Die grafische Anzeige der Buchstabenliste.
     * @param wordDisplay Die grafische Anzeige des Wortes.
     * @param frame Das Programmfenster.
     */
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

    /**
     * &Uuml;berpr&uuml;ft, ob der eingegeben Buchstabe oder die eingegebenen Buchstaben im
     * zu erratenden Wort enthalten sind. Au&szlig;erdem wird die Siegbedingung getestet und
     * entsprechende Meldungen werden gemacht.
     */
    public void check() {
        final String input = textField.getText().toUpperCase();
        final int inputLength = input.length();
        final int wordLength = currentWord.length();
        textField.setText("");
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
                wordDisplay.setShownWord(shownWord);
                wordDisplay.repaint();

                lettersDisplay.setLetters(letters);
                lettersDisplay.repaint();

                guyDisplay.setErrorCount(errorCount);
                guyDisplay.repaint();
                if (checkEndOfGame()) {
                    break;
                }
            }

        }
    }

    /**
     * Testet, ob das aktuelle Spiel gewonnen oder verloren ist. Ist dies der Fall, wird eine
     * entsprechende Meldung angezeigt.
     * 
     * @return Ob das Spiel beendet ist.
     */
    private boolean checkEndOfGame() {
        final boolean isWon = shownWord.equals(currentWord);
        final boolean isLost = errorCount == 10;

        if (errorCount > 10) {
            JOptionPane
                    .showMessageDialog(
                                       frame,
                                       "Ein unbekannter Programmfehler ist aufgetreten." + 
                                       "\nBitte wende dich an den Entwickler.\nDas Programm" + 
                                       " wird ohne Speichern beendet.");
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
               
                JOptionPane.showMessageDialog(frame,
                                              "Das war wohl nichts... (Richtig: " +
                                              (guessCount - errorCount) +
                                              " Falsch: " + errorCount +
                                              ")\nDie Antwort war " +
                                               currentWord + ".");

            }

            scores[currentCategory][1]++;

            if (currentLevel > computeLevel(currentCategory)) {
                final int newLevel = computeLevel(currentCategory);
                JOptionPane.showMessageDialog(frame,
                                              "Du bist abgestiegen!\nNeue Bewertung: " +
                                              newLevel +
                                              " (" +
                                              (newLevel - currentLevel) +
                                              ")");
                currentLevel = computeLevel(currentCategory);
            } else if (currentLevel < computeLevel(currentCategory)) {
                final int newLevel = computeLevel(currentCategory);
                JOptionPane.showMessageDialog(frame,
                                              "Du bist aufgestiegen!\nNeue Bewertung: " +
                                              newLevel +
                                              " (+" +
                                              (newLevel - currentLevel) +
                                              ")");
                currentLevel = computeLevel(currentCategory);
            }
            swap();
            nextWord(true);
            return true;
        }
        return false;
    }

    /**
     * W&auml;hlt ein Wort aus der Wortliste der aktuellen Kategorie aus.
     * 
     * @return Das ausgew&auml;hlte Wort.
     */
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
                                       "Ein unbekannter Programmfehler ist aufgetreten." + 
                                       "\nBitte wende dich an den Entwickler.\nDas Programm " +
                                       "wird ohne Speichern beendet.");
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
     * Sollten unm&ouml;gliche Eingaben in der Statistik einer Textdatei erkannt
     * werden, so sorgt diese Methode daf&uuml;r, den Benutzer &uuml;ber dies in
     * Kenntnis gesetzt. Des Weiteren wird die gesamte Statistik dieser Kategorie
     * gel&ouml;scht bzw. zur&uuml;ckgesetzt.
     * 
     * @param category Die Kategorie, in welcher der Fehler gefunden worden ist.
     */
    private void punishCheater(final int category) {
        JOptionPane.showMessageDialog(frame, "Solcherlei Eingaben in den Textdateien sind nicht erlaubt.\n" + 
                                             "Und ganz nebenbei vernichtest du dir selbst die Freude am" + 
                                             " Spiel!\n Der Spielstand wird vernichtet...");
        scores[category][0] = 0;
        scores[category][1] = 0;
        scores[category][2] = 0;
        scores[category][3] = 0;
    }

    /**
     * Erstellt aus dem aktuellen Wort, das verschleierte Wort, welches nur aus "=" und eventuellen
     * Sonderzeichen besteht. Dies ist das Wort, welches anfangs angezeigt wird und welches bei korrekter
     * Eingabe von Buchstaben ver&auml;ndert wird.
     */
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

    /**
     * Sorgt f&uuml; die Auswahl und das Anzeigen eines neuen Wortes. Wenn das Spiel begonnen worden,
     * aber noch nicht beendet worden ist, wird der Wert der verlorenen Spiele erh&ouml;ht.
     * 
     * @param isFinished Gibt an, ob das Spiel beendet worden ist (gewonnen oder verloren).
     */
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

    /**
     * Nimmt die Initialisierung des Spieles vor, indem die Wortlisten eingelesen werden und die
     * Anzeigen entsprechend eingerichtet werden. Dies ist nicht Teil des Konstruktors, da hier keine
     * Instantiierungen von Variablen an sich vorgenommen werden, sondern der Spielaufbau generiert wird.
     */
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

    /**
     * Zeigt die aktuellen Spielst&auml;nde in einem neuen Fenster im Frame an.
     */
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

    /**
     * Zeigt einen Dialog an, in welchem man die Kategorie ver&auml;ndern kann. Der Spielstand
     * ist davon nicht betroffen, wenn das Spiel noch nicht begonnen worden ist, sonst wird das
     * aktuelle Spiel als verloren gewertet.
     */
    public void showSettings() {
        final String category = (String) JOptionPane.showInputDialog(frame,
                                 "Wechsele zu einer neuen Kategorie.",
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

    /**
     * Verschiebt das aktuelle Wort, welches entweder geraten oder aufgel&ouml;st
     * worden ist, an das Ende der Wortliste und schiebt den Zeiger um eine Position
     * nach links. Dieser Zeiger gibt an, bis zu welchem Wort der Zufallsgenerator
     * ausw&auml;hlen soll. Dadurch kann innerhalb einer Sitzung des Programmeskein
     * Wort zweimal drankommen, au&szlig;er wenn die gesamte Wortliste durchlaufen
     * worden ist.
     */
    private void swap() {
        wordLists[xIndex].remove(yIndex);
        wordLists[xIndex].add(currentWord);
        ends[xIndex]--;
        if (ends[xIndex] == 0) {
            ends[xIndex] = wordLists[xIndex].size();
        }
    }
}