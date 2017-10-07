import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.SwingConstants;

public final class Plotter extends JFrame {
       
    public Plotter() {
        super("Funktionsplotter");
        setLayout(new BorderLayout());
        
        Graph graph = new Graph();
        
        getContentPane().add(graph, BorderLayout.WEST);        
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setExtendedState(MAXIMIZED_BOTH);
    }
    
    public static void main(final String[] args) {
        new Plotter();
    }

}
