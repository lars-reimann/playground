import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Diese Klasse liest die W&ouml;rter in den Textdateien im Ordner "words" aus und erstellt die
 * Wortlisten der einzelnen Kategorien aus diesen. Au&szlig;erdem werden beim Beenden des Spieles
 * die neuen Spielst&auml;nde in die Dateien geschrieben und modifizierte W&ouml;rter (Gro&szlig;buchstaben,
 * ver&auml;nderte Umlaute) werden in die Dateien geschrieben, um den Aufwand beim EInlesen der W&ouml;rter
 * zu minimieren.
 *
 * @author Lars Reimann
 * @version 12. Oktober 2011
 */
public final class HangmanIO {
    
    /**
     * Liest den Inhalt der Textdateien (Spielst&auml;nde und W&ouml;rter) im Ordner "words" aus und gibt ihn zur&uuml;ck.
     * 
     * @return Den Inhalt der Textdateien.
     */
    public ArrayList<String>[] readFiles() {
        final int filesCount = countFiles();
        final ArrayList<String>[] wordLists = new ArrayList[filesCount];
        for (int i = 0; i < filesCount; i++) {
            wordLists[i] = readFile(i);
        }
        return wordLists;
    }
    
    /**
     * Gibt die Anzahl der Dateien im Ordner "words" zur&uuml;ck.
     * 
     * @return Die Anzahl der Dateien im Ordner "words.
     */
    private int countFiles() {
        return new File("words").list().length;
    }
    
    /**
     * Liest die Spielst&auml;nde und die W&ouml;rter in der Textdatei "index.txt" aus und speichert sie
     * entsprechend. "index" steht hierbei f&uuml;r den Dateinamen, welche von null bis countFiles() - 1
     * durchnummeriert sind.
     * 
     * @param index Der Dateiname der Textdatei.
     * @return Den Inhalt der Textdatei, also die Spielst&auml;nde und die W&ouml;rter.
     */
    private ArrayList<String> readFile(final int index) {
        final ArrayList<String> wordList = new ArrayList<String>();
        try {
            final Scanner scanner = new Scanner(new File("words" + File.separator + index + ".txt"));
            while (scanner.hasNextLine()) {
                final String word = scanner.nextLine();
                if (word.length() <= 25) {
                    wordList.add(word);
                }
            }
        } catch (final FileNotFoundException e) {
            e.printStackTrace();
        }
        return wordList;
    }
    
    /**
     * Schreibt die neuen Spielst&auml;de und die modifizierten Wortlisten in die entsprechenden Textdateien im
     * Ordner "words".
     * 
     * @param categories Die Namen der Kategorien bzw. Wortfelder.
     * @param scores Die neuen Spielst&auml;nde.
     * @param wordLists Die Wortlisten der einzelnen Kategorien.
     */
    public void writeFiles(final String[] categories, final int[][] scores, final ArrayList<String>[] wordLists) {
        for (int i = 0; i < categories.length; i++) {
            writeFile(categories[i], scores[i], wordLists[i], i);
        }
    }
    
    /**
     * Schreibt die neuen Spielst&auml;nde und die modifizierte Wortliste in die Textdatei "index.txt" im Ordner "words".
     * 
     * @param category Der Name der Kategorie bzw. des Wortfeldes.
     * @param score Der neue Spielstand in dieser Kategorie.
     * @param wordLists Die modifizierte Wortliste dieser Kategorie.
     * @param index Der Name der Textdatei.
     */
    private void writeFile(final String category, final int[] score, final ArrayList<String> wordList, final int index) {
        final StringBuilder stringBuilder = new StringBuilder();
        final String lineSeparator = System.getProperty("line.separator");
        stringBuilder.append(category + lineSeparator);
        for (int i = 0; i < score.length; i++) {
            stringBuilder.append(score[i] + lineSeparator);
        }
        for (final String s : wordList) {
            stringBuilder.append(s + lineSeparator);
        }
        try {
            final FileWriter fileWriter = new FileWriter("words" + File.separator + index + ".txt");
            fileWriter.write(stringBuilder.toString());
            fileWriter.flush();
            fileWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}