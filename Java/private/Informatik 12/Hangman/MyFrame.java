import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;



public class MyFrame extends JFrame {
    
    final JTextField textField;
    
    public MyFrame() {
        getContentPane().setLayout(null);
        
        
        final Hangman hangman = new Hangman(this);
        hangman.setLocation(0, 0);
        getContentPane().add(hangman);
        
        final MyActionListener actionListener = new MyActionListener(hangman, this);
        
        textField = new JTextField();
        textField.setBounds(40, 420, 200, 26);
        getContentPane().add(textField);
        
        final JButton test = new JButton("Teste");
        test.setBounds(250, 420, 100, 25);
        test.addActionListener(actionListener);
        getContentPane().add(test);
        
        final JButton next = new JButton("Naechstes");
        next.setBounds(350, 420, 100, 25);
        next.addActionListener(actionListener);
        getContentPane().add(next);
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setTitle("Hangman");
        setVisible(true);
    }
    
    public char getInput() {
        try {
            return textField.getText().toLowerCase().charAt(0);
        } catch (StringIndexOutOfBoundsException exception) {
            // ignoriere
        }
        return ' ';
    }
}
