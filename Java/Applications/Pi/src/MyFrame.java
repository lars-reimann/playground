import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

public class MyFrame extends JFrame {
    public MyFrame() {
        final Container cp = getContentPane();
        final JButton start = new JButton("Start");
        final Circle circle = new Circle();
        
        start.setBounds(150, 400, 200, 50);
        start.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent event) {
                Thread thread = new Thread(circle);
                thread.start();
            }
        }
        );
        cp.add(start);
        
        circle.setLocation(50, 0);
        circle.setBorder(new LineBorder(Color.black));
        cp.add(circle);
        
        cp.setLayout(null);
        
        setTitle("Berechnung von PI");
        setSize(500, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
