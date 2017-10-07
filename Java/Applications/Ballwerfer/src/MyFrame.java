import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 *
 * @author Lars Reimann
 * @version 17.12.2010
 */
public class MyFrame extends JFrame {


    private final JTextField velocity = new JTextField();
    private final JTextField acceleration = new JTextField();
    private final JTextField zoom = new JTextField();
    private final JTextField sleeptime = new JTextField();

    public MyFrame() {
        Field field = new Field(this);
        MyActionListener myActionListener = new MyActionListener(field);

        getContentPane().setLayout(null);

        field.setLocation(0, 0);
        getContentPane().add(field);

        // "Start"-Button
        final JButton start = new JButton("Start/Stop");
        start.setBounds(25, 400, 150, 50);
        start.addActionListener(myActionListener);
        start.setActionCommand("Start/Stop");
        getContentPane().add(start);
        
        // "Reset"-Button
        final JButton reset = new JButton("Reset");
        reset.setBounds(825, 400, 150, 50);
        reset.addActionListener(myActionListener);
        reset.setActionCommand("Reset");
        getContentPane().add(reset);
        
        final JLabel velocityL = new JLabel("x-Geschwindigkeit:");
        velocityL.setBounds(230, 400, 125, 20);
        getContentPane().add(velocityL);
        
        velocity.setBounds(230, 430, 100, 20);
        velocity.setToolTipText("Die Startgeschwindigkeit in x-Richtung (m/s).");
        getContentPane().add(velocity);
        
        final JLabel accelerationL = new JLabel("y-Beschleunigung:");
        accelerationL.setBounds(380, 400, 125, 20);
        getContentPane().add(accelerationL);
        
        acceleration.setBounds(380, 430, 100, 20);
        acceleration.setToolTipText("Die Beschleunigung in y-Richtung (m/s²)");
        getContentPane().add(acceleration);
        
        final JLabel zoomL = new JLabel("Zoom:");
        zoomL.setBounds(530, 400, 125, 20);
        getContentPane().add(zoomL);
        
        zoom.setBounds(530, 430, 100, 20);
        zoom.setToolTipText("Werte groesser 1 vergroessern den Ausschnitt oben links, waehrend Werte zwischen 0 und 1 ein Herauszoomen ermoeglichen.");
        getContentPane().add(zoom);
        
        final JLabel sleeptimeL = new JLabel("Wartezeit:");
        sleeptimeL.setBounds(680, 400, 125, 20);
        getContentPane().add(sleeptimeL);
        
        sleeptime.setBounds(680, 430, 100, 20);
        sleeptime.setToolTipText("Die Zeit, die gewartet werden soll.");
        getContentPane().add(sleeptime);

        setTitle("Ball");
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
    
    public double getVelocity() {
        try {
            return Double.parseDouble(velocity.getText());
        } catch (NumberFormatException exception) {
            return 100;
        }
    }
    
    public double getAcceleration() {
        try {
            return Double.parseDouble(acceleration.getText());
        } catch (NumberFormatException exception) {
            return 9.81;
        }
    }
    
    public double getZoom() {
        try {
            return Double.parseDouble(zoom.getText());
        } catch (NumberFormatException exception) {
            return 1;
        }
    }
    
    public int getSleeptime() {
        try {
            return Integer.parseInt(sleeptime.getText());
        } catch (NumberFormatException exception) {
            return 10;
        }
    }
}
