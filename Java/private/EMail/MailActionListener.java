import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MailActionListener implements ActionListener {
    
    private final Main main;

    public MailActionListener(Main main) {
        this.main = main;
    }

    public void actionPerformed(ActionEvent event) {
        String cmd = event.getActionCommand();
        if ("Empfangen".equals(cmd)) {
            main.receive();
        } else if ("Neue Mail".equals(cmd)) {
            main.newMail();
        } else if ("Weiterleiten".equals(cmd)) {
            main.forward();
        } else if ("Loeschen".equals(cmd)) {
            main.remove();
        } else if ("Neuer Ordner".equals(cmd)) {
            main.newFolder();
        } else if ("Neues Konto".equals(cmd)) {
            main.newAccount();
        }
        
    }
}
