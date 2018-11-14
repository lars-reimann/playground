import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyActionListener implements ActionListener {
    
    private final Hangman hangman;
    private final MyFrame myFrame;
    
    public MyActionListener(final Hangman hangman, final MyFrame myFrame) {
        this.hangman = hangman;
        this.myFrame = myFrame;
    }

    public void actionPerformed(final ActionEvent event) {
        if ("Teste".equals(event.getActionCommand())) {
            hangman.test(myFrame.getInput());
        } else if ("Naechstes".equals(event.getActionCommand())) {
            hangman.nextWord();
        }
    }
}
