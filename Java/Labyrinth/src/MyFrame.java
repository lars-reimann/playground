import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 *
 * @author Lars Reimann
 * @version 17.12.2010
 */
public class MyFrame extends JFrame {


    // ----Konstruktoren-------------------------------------------------------

    public MyFrame() {
        Maze maze = new Maze(25);
        MyActionListener myActionListener = new MyActionListener(maze, this);

        getContentPane().setLayout(null);

        maze.setLocation(50, 50);
        getContentPane().add(maze);

        // "Start"-Button
        final JButton start = new JButton("Start");
        start.setBounds(25, 500, 150, 50);
        start.addActionListener(myActionListener);
        start.setActionCommand("Start");
        getContentPane().add(start);

        // "Neue Punkte"-Button
        final JButton newPoints = new JButton("Neue Punkte");
        newPoints.setBounds(175, 500, 150, 50);
        newPoints.addActionListener(myActionListener);
        newPoints.setActionCommand("Neue Punkte");
        getContentPane().add(newPoints);

        // Groesse aendern
        final JButton setDimension = new JButton("Groesse aendern");
        setDimension.setBounds(325, 500, 150, 50);
        setDimension.addActionListener(myActionListener);
        setDimension.setActionCommand("Groesse aendern");
        getContentPane().add(setDimension);

        setTitle("Labyrinth");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
}
