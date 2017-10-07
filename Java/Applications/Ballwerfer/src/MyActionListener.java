import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;



public class MyActionListener implements ActionListener {

    private Field field;

    public MyActionListener(final Field field) {
        this.field = field;
    }
    
    public void actionPerformed(final ActionEvent event) {
        String actionCommand = event.getActionCommand();
        if ("Start/Stop".equals(actionCommand)) {
            if (field.isStopped()) {
                Thread thread = new Thread(field);
                thread.start();
            } else {
                field.setStopped(true);
            }
        } else if("Reset".equals(actionCommand)) {
            field.reset();
        }
    }
}
