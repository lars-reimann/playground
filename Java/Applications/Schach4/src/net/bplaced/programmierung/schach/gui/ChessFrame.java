package net.bplaced.programmierung.schach.gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import net.bplaced.programmierung.schach.logic.Engine;

public class ChessFrame extends JFrame {

    /**
     * Serialisierungsversion.
     */
    private static final long                    serialVersionUID = -2745410922673626734L;

    public ChessFrame() {
        super();

        final ChessPanel panel = new ChessPanel(500);
        final Engine engine = new Engine(panel);
        panel.setEngine(engine);
        
        final ChessActionListener actionListener = new ChessActionListener(engine);

        // Konfiguration der ContentPane
        getContentPane().setLayout(new BorderLayout(3, 0));
        getContentPane().add(panel, BorderLayout.WEST);
        getContentPane().setBackground(Color.lightGray);

        // Konfiguration des Fensters
        setTitle("Schach");
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
    }
}
