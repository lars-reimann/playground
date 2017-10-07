import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;



public class MyActionListener implements ActionListener {

    private final Maze maze;
    private final MyFrame myFrame;

    public MyActionListener(final Maze maze, final MyFrame myFrame) {
        this.maze = maze;
        this.myFrame = myFrame;
    }

    public void actionPerformed(final ActionEvent event) {
        String actionCommand = event.getActionCommand();
        if ("Start".equals(actionCommand)) {
            if (maze.findConnection(0, 0)) {
                JOptionPane.showMessageDialog(
                    myFrame,
                    "Es wurde eine Verbindung gefunden!",
                    "Nachricht",
                    JOptionPane.INFORMATION_MESSAGE
                );
            } else {
                JOptionPane.showMessageDialog(
                    myFrame,
                    "Es wurde keine Verbindung gefunden!",
                    "Nachricht",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
        } else if ("Neue Punkte".equals(actionCommand)) {
            maze.createRandomPoints();
            maze.update(maze.getGraphics());
        } else if ("Groesse aendern".equals(actionCommand)) {
            final String input = JOptionPane.showInputDialog(
                myFrame,
                "Bitte geben Sie die neue Groesse ein:"
            );
            maze.setDimension(Integer.parseInt(input));
        }
    }
}
