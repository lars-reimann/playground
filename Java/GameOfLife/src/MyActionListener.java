import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyActionListener implements ActionListener {

    public void actionPerformed(final ActionEvent event) {
        final String command = event.getActionCommand();
        final Grid grid = Grid.getInstance();
        if ("Start".equals(command)) {
            grid.setStopped(false);
            final Thread thread = new Thread(grid);
            thread.start();
        } else if ("Stop".equals(command)) {
            grid.setStopped(true);
        } else if ("Naechster Schritt".equals(command)) {
            grid.nextStep();
        } else if ("Zuruecksetzen".equals(command)) {
            grid.resetCells();
        }
    }
}
