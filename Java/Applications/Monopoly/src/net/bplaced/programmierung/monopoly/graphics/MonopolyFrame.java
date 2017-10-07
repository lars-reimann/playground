package net.bplaced.programmierung.monopoly.graphics;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public final class MonopolyFrame extends JFrame {
    public MonopolyFrame() {
        
        setTitle("Monopoly");
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        pack();
    }
}
