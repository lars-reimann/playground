import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class Field extends JPanel implements Runnable {
    
    private final int dim;
    private final boolean[][] fields;
    
    public Field(final int dim) {
        this.dim = dim;
        fields = new boolean[dim][dim];
        setSize(300, 300);
    }
    
    @Override
    public void paint(final Graphics graphics) {
        for (int i = 0; i < dim; i += 2) {
            for (int j = 0; j < dim; j += 2) {
                graphics.setColor(Color.lightGray);
                graphics.fillRect(i * 300 / dim, j * 300 / dim, 300 / dim + 1, 300 / dim + 1);
                graphics.setColor(Color.darkGray);
                graphics.fillRect((i + 1) * 300 / dim, j * 300 / dim, 300 / dim + 1, 300 / dim + 1);
                graphics.fillRect(i * 300 / dim, (j + 1) * 300 / dim, 300 / dim + 1, 300 / dim + 1);
                graphics.setColor(Color.lightGray);
                graphics.fillRect((i + 1) * 300 / dim, (j + 1) * 300 / dim, 300 / dim, 300 / dim + 1);
            }
        }
        graphics.setColor(Color.red);
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (fields[i][j]) {
                    graphics.fillRect(i * 300 / dim, j* 300 / dim, 300 / dim, 300 / dim + 1);
                }
            }
        }
    }

    @Override
    public void run() {
        solve(0);
        repaint();
    }
    
    private boolean solve(final int row) {
        if (row < dim) {
            for (int i = 0; i < dim; i++) {
                fields[i][row] = true;
                if (isCorrect(i, row)) {
                    if (row == dim - 1 || solve(row + 1)) {
                        return true;
                    }
                }
                fields[i][row] = false;
            }
        }
        return false;
    }
    
    private boolean isCorrect(final int column, final int row) {
        // Spalte
        boolean hasQueen = false;
        for (int i = 0; i < dim; i++) {
            if (fields[column][i]) {
                if (hasQueen) {
                    return false;
                } else {
                    hasQueen = true;
                }
                
            }
        }
        
        // Zeile
        hasQueen = false;
        for (int i = 0; i < dim; i++) {
            if (fields[i][row]) {
                if (hasQueen) {
                    return false;
                } else {
                    hasQueen = true;
                }
            }
        }
        
        // 1. Diagonale
        hasQueen = false;
        for (int i = 0; i < dim; i++) {
            if (column + i < dim && row + i < dim && fields[column + i][row + i]) {
                if (hasQueen) {
                    return false;
                } else {
                    hasQueen = true;
                } 
            }
            if (i != 0 && column - i >= 0 && row - i >= 0 && fields[column - i][row - i]) {
                if (hasQueen) {
                    return false;
                } else {
                    hasQueen = true;
                } 
            }
        }
        
        // 2. Diagonale
        hasQueen = false;
        for (int i = 0; i < dim; i++) {
            if (column + i < dim && row - i >= 0 && fields[column + i][row - i]) {
                if (hasQueen) {
                    return false;
                } else {
                    hasQueen = true;
                } 
            }
            if (i != 0 && column - i >= 0 && row + i < dim && fields[column - i][row + i]) {
                if (hasQueen) {
                    return false;
                } else {
                    hasQueen = true;
                } 
            }
        }
        
        return true;
    }
}
