import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Panel;

/**
 * 
 * 
 * @author Lars Reimann
 * @version 16. August 2010
 */
public final class GameOfLife extends Applet{

    // -----Class variables----------------------------------------------------

    private static final long serialVersionUID = 8552446425348196982L;



    public void init() {
        setLayout(new BorderLayout());
        add(Grid.getInstance(), BorderLayout.NORTH);

        final MyActionListener actionListener = new MyActionListener();
        final Panel panel = new Panel();

        final Button button1 = new Button("Start");
        button1.setActionCommand("Start");
        button1.addActionListener(actionListener);
        panel.add(button1);

        final Button button2 = new Button("Stop");
        button2.setActionCommand("Stop");
        button2.addActionListener(actionListener);
        panel.add(button2);

        final Button button3 = new Button("Naechster Schritt");
        button3.setActionCommand("Naechster Schritt");
        button3.addActionListener(actionListener);
        panel.add(button3);

        final Button button4 = new Button("Zuruecksetzten");
        button4.setActionCommand("Zuruecksetzen");
        button4.addActionListener(actionListener);
        panel.add(button4);

        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    public void stop() {
        Grid.getInstance().setStopped(true);
    }
}
