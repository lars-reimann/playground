import java.awt.Font;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Hangman extends JPanel {
    
    private int errorCount = 0;
    private ArrayList<String> list = new ArrayList<String>();
    private String solution = new String();
    private String displayedWord = new String();
    private MyFrame myFrame;
    
    public Hangman(final MyFrame myFrame) {
        this.myFrame = myFrame;
        setSize(500, 400);
        readFile();
        nextWord();
        repaint();
    }
    
    public void paint(final Graphics graphics) {
        graphics.clearRect(0, 0, getWidth(), getHeight());
        switch (errorCount) {
            case 10:
                graphics.drawLine(280, 160, 300, 140);
                // faellt durch
            case 9:
                graphics.drawLine(280, 160, 260, 140);
                // faellt durch
            case 8:
                graphics.drawLine(280, 180, 300, 200);
                // faellt durch
            case 7:
                graphics.drawLine(280, 180, 260, 200);
                // faellt durch
            case 6:
                graphics.drawLine(280, 140, 280, 180);
                // faellt durch
            case 5:
                graphics.drawOval(270, 120, 20, 20);
                // faellt durch
            case 4:
                graphics.drawLine(280, 100, 280, 120);
                // faellt durch
            case 3:
                graphics.drawLine(220, 100, 280, 100);
                // faellt durch
            case 2:
                graphics.drawLine(220, 100, 220, 230);
                // faellt durch
            case 1:
                graphics.drawLine(200, 230, 300, 230);
                break;
        }
        graphics.setFont(new Font(null, Font.BOLD, 50));
        graphics.drawString(displayedWord, 50, 350);
    }
    
    public void nextWord() {
        errorCount = 0;
        Random random = new Random();
        solution = list.get(random.nextInt(list.size()));
        displayedWord = "";
        for (int i = 0; i < solution.length(); i++) {
            displayedWord += "*";
        }
        repaint();
    }
    
    public void test(final char character) {
        final String solution = this.solution.toLowerCase();
        boolean hasLetter = false;
        for (int i = 0; i < solution.length(); i++) {
            if (solution.charAt(i) == character) {
                displayedWord = displayedWord.substring(0, i) +
                                this.solution.charAt(i) +
                                displayedWord.substring(i + 1, displayedWord.length());
                hasLetter = true;
            }
        }
        if (!hasLetter) {
            errorCount++;
        }
        repaint();
        if (errorCount == 10) {
            JOptionPane.showMessageDialog(
                myFrame,
                "Sie haben leider verloren.\nDie richtige Loesung waere " +
                    this.solution + " gewesen.",
                "Verloren",
                JOptionPane.INFORMATION_MESSAGE
            );
        }
        if (displayedWord.equals(this.solution)) {
            JOptionPane.showMessageDialog(
                myFrame,
                "Herzlichen Glueckwunsch!\nSie haben mit " + errorCount +
                    " falschen Buchstaben gewonnen.",
                "Gewonnen",
                JOptionPane.INFORMATION_MESSAGE
           );
        }
        
    }
    
    private void readFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("hangman.txt"));
            String word = new String();
            while ((word = reader.readLine()) != null) {
                list.add(word);
            }
            reader.close();
        } catch (FileNotFoundException exception) {
            // ingoriere
        } catch (IOException exception) {
            // ignoriere
        }
    }
}
