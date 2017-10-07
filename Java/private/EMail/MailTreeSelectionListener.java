import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;

public class MailTreeSelectionListener implements TreeSelectionListener {
    private Main main;
    
    public MailTreeSelectionListener(Main main) {
        this.main = main;
    }

    public void valueChanged(TreeSelectionEvent event) {
        
    }
}
