import java.awt.Container;
import java.awt.Dimension;
import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.WindowConstants;
import javax.swing.JOptionPane;

/**
 * @author Lars Reimann
 * @version 1. April 2011
 */
public class MyFrame extends JFrame {
    public MyFrame() {
        final MyFrame frame = this;
        final Container cp = getContentPane();
        final JButton start = new JButton("Start");
        final JButton setSize = new JButton("Groesse aendern");
        final Board board = new Board(20);
        final ActionListener actionListener = new ActionListener() {
            public void actionPerformed(final ActionEvent event) {
                final String command = event.getActionCommand();
                if ("Start".equals(command)) {
                    final Thread thread = new Thread(board);
                    thread.start();
                } else if ("Groesse aendern".equals(command)) {
                    final String input = JOptionPane.showInputDialog(
                        board,
                        "Bitte geben Sie die neue Groesse ein (4 - 25):",
                        "Groesse aendern",
                        JOptionPane.QUESTION_MESSAGE
                    );
                    try {
                        final int size = Integer.parseInt(input);
                        if (size < 4 || size > 25) {
                            throw new NumberFormatException();
                        }
                        board.setSize(size);
                        final int newWidth = ((int) Math.round((double) 500 / (double) size)) * size;
                        board.setPreferredSize(new Dimension(newWidth, newWidth));
                        start.setPreferredSize(new Dimension(newWidth / 2, 50));
                        setSize.setPreferredSize(new Dimension(newWidth / 2, 50));
                        board.repaint();
                        frame.pack();
                    } catch (NumberFormatException exception) {
                        JOptionPane.showMessageDialog(
                            board,
                            "Ihr Eingabe enthaelt Buchstaben oder\nist nicht innerhalb der Grenzen!",
                            "Fehler",
                            JOptionPane.INFORMATION_MESSAGE
                       );
                    }
                }
            }
        };
        
        cp.setLayout(new BorderLayout(0, 0));
        
        board.setPreferredSize(new Dimension(500, 500));
        cp.add(board, BorderLayout.NORTH);
        
        start.setPreferredSize(new Dimension(250, 50));
        start.addActionListener(actionListener);
        cp.add(start, BorderLayout.WEST);
        
        setSize.setPreferredSize(new Dimension(250, 50));
        setSize.addActionListener(actionListener);
        cp.add(setSize, BorderLayout.EAST);
        
        setTitle("Damenproblem");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        pack();
    }
}
