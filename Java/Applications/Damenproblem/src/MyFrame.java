import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MyFrame extends JFrame {
    
    public MyFrame() {
        final Container cp = getContentPane();
        final Field field = new Field(8);
        final JButton start = new JButton("Start");
        field.setLocation(100, 50);
        
        start.setBounds(100, 400, 300, 50);
        start.addActionListener(
            new ActionListener() {
                public void actionPerformed(final ActionEvent event) {
                    Thread thread = new Thread(field);
                    thread.start();
                }
            }
        );
        
        cp.setLayout(null);
        cp.add(field);
        cp.add(start);
        
        setTitle("Damenproblem");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        
    }
}
