import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Lars Reimann
 * @version 29. Januar 2011
 */
public class MyFrame extends JFrame {

    private final JTextField textField;

    public MyFrame() {
        super();
        getContentPane().setLayout(null);

        final Hanoi hanoi = new Hanoi();
        hanoi.setLocation(0, 50);
        getContentPane().add(hanoi);

        final JLabel label = new JLabel("Anzahl Scheiben:");
        label.setBounds(50, 225, 125, 25);
        getContentPane().add(label);

        textField = new JTextField();
        textField.setBounds(200, 225, 100, 25);
        getContentPane().add(textField);

        final MyActionListener actionListener = new MyActionListener(
            this,
            hanoi
        );

        final JButton start = new JButton("Start/Stop");
        start.setBounds(475, 225, 200, 25);
        start.setActionCommand("Start/Stop");
        start.addActionListener(actionListener);
        getContentPane().add(start);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Tuerme von Hanoi");
        setSize(750, 300);
        setResizable(false);
        setVisible(true);
    }

    public int getInput() {
        int input = 0;
        try {
            input = Integer.parseInt(textField.getText());
        } catch (NumberFormatException exception) {
            // ignoriere
        }
        if (input > 0 && input <= 20) {
            return input;
        } else {
            JOptionPane.showMessageDialog(
                this,
                "Es muss eine Zahl zwischen 1 und 20 eingegeben werden!",
                "Fehler",
                JOptionPane.ERROR_MESSAGE
            );
            return 0;
        }
    }
}
