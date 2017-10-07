import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JOptionPane;

/**
 * @author Lars Reimann
 * @version 1. April 2011
 */
public class Board extends JPanel implements Runnable {

    private int size;
    private int fieldSize;
    private boolean[][] field;

    public Board(final int size) {
        setSize(size);
    }
    
    public void paint(final Graphics graphics) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (field[i][j]) {
                    graphics.setColor(Color.red);
                } else if ((i + j) % 2 == 0) {
                    graphics.setColor(Color.darkGray);
                } else {
                    graphics.setColor(Color.lightGray);
                }
                graphics.fillRect(fieldSize * i, fieldSize * j, fieldSize, fieldSize);
            }
        }
    }

    
    public void setSize(final int size) {
        this.size = size;
        fieldSize = (int) Math.round((double) 500 / (double) size);
        field = new boolean[size][size];
    }
    
    public void run() {
        if (solveRow(0)) {
            repaint();
            JOptionPane.showMessageDialog(
                this,
                "Es wurde eine Loesung gefunden!",
                "Ergebnis",
                JOptionPane.INFORMATION_MESSAGE
            );
        } else {
            JOptionPane.showMessageDialog(
                this,
                "Es wurde leider keine Loesung gefunden!",
                "Ergebnis",
                JOptionPane.INFORMATION_MESSAGE
            );
        }
    }
    
    private boolean solveRow(final int row) {
        if (row >= size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            field[i][row] = true;
            if ((row == size - 1 && isSolved()) || (isCorrect(i, row) && solveRow(row + 1))) {
                return true;
            }
            field[i][row] = false;
        }
        return false;
    }
    
    private boolean isCorrect(final int column, final int row) {

        // Nach rechts
        for (int i = 1; column + i < size; i++) {
            if (field[column + i][row]) {
                return false;
            }
        }
        
        //Nach links
        for (int i = 1; column - i >= 0; i++) {
            if (field[column - i][row]) {
                return false;
            }
        }
        
        //Nach unten
        for (int i = 1; row + i < size; i++) {
            if (field[column][row + i]) {
                return false;
            }
        }
        
        // Nach oben
        for (int i = 1; row - i >= 0; i++) {
            if (field[column][row - i]) {
                return false;
            }
        }
        
        // Nach unten rechts
        for (int i = 1; row + i < size && column + i < size; i++) {
            if (field[column + i][row + i]) {
                return false;
            }
        }
        
        //Nach oben rechts
        for (int i = 1; row - i >= 0 && column + i < size; i++) {
            if (field[column + i][row - i]) {
                return false;
            }
        }
        
        //Nach unten links
        for (int i = 1; row + i < size && column - i >= 0; i++) {
            if (field[column - i][row + i]) {
                return false;
            }
        }
        
        // Nach oben links
        for (int i = 1; row - i >= 0 && column - i >= 0; i++) {
            if (field[column - i][row - i]) {
                return false;
            }
        }
        return true;
    }
    
    private boolean isSolved() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (field[i][j] && !isCorrect(i, j)) {
                    return false;
                }
            }
        }
        return true;       
    }           
}
        