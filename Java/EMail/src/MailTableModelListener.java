import javax.swing.event.TableModelListener;
import javax.swing.event.TableModelEvent;

public class MailTableModelListener implements TableModelListener {

    private final Main main;

    public MailTableModelListener(Main main) {
        this.main = main;
    }

    public void tableChanged(TableModelEvent event) {
    }
}