import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author Lars Reimann
 * @version 23. September 2011
 */
public final class HangmanIO {
    
    public ArrayList<String>[] readFiles() {
        final int filesCount = countFiles();
        final ArrayList<String>[] wordLists = new ArrayList[filesCount];
        for (int i = 0; i < filesCount; i++) {
            wordLists[i] = readFile(i);
        }
        return wordLists;
    }
    
    private int countFiles() {
        return new File("words").list().length;
    }
    
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
    
    public void writeFiles(final String[] categories, final int[][] scores, final ArrayList<String>[] wordLists) {
        for (int i = 0; i < categories.length; i++) {
            writeFile(categories[i], scores[i], wordLists[i], i);
        }
    }
    
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
