import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Lars Reimann
 * @version 29. Januar 2011
 */
public class MyActionListener implements ActionListener {

    private final Hanoi   hanoi;
    private final MyFrame myFrame;

    public MyActionListener(final MyFrame myFrame, final Hanoi hanoi) {
        super();
        this.myFrame = myFrame;
        this.hanoi = hanoi;
    }

    public void actionPerformed(final ActionEvent event) {
        if ("Start/Stop".equals(event.getActionCommand())) {
            if (myFrame.getInput() != 0 && hanoi.isStopped()) {
                hanoi.setDisks(myFrame.getInput());
                Thread thread = new Thread(hanoi);
                thread.start();
            } else {
                hanoi.setStopped(true);
            }
        }
    }
}
