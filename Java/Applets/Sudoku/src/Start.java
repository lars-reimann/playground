import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.TextField;

/**
 * Diese Klasse erbt aus {@code Applet} und dient somit als Einstiegspunkt in
 * das Programm. In ihr ist der grafische Grundaufbau des Applets festgelegt,
 * eine weitere Funktionalitaet besitzt die Klasse nicht. Insbesondere
 * existieren keine Methoden, die beim Starten, Stoppen oder Zerstoeren des
 * Applets aufgerufen werden.
 * 
 * @author Lars Reimann
 * @version 3. Juni 2011
 */
public final class Start extends Applet {

    /**
     * Automatisch generierte Versionsnummer.
     */
    private static final long serialVersionUID = 9190736210565546623L;

    @Override
    public void init() {

        // Deklaration und Initialisierung
        final TextField[][] textFields = new TextField[9][9];
        final Solver solver = new Solver();
        final MyActionListener actionListener = new MyActionListener(solver,
                                                                     textFields);
        final Button reset = new Button("Neustart");
        final Button solve = new Button("Loese");

        // Festlegung des Layout-Managers
        setLayout(null);
        
        // Festlegung der Hintergrundfarbe
        setBackground(Color.black);

        // Belegung der Speicherplaetze des Arrays mit neuen Textfeldern
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                textFields[i][j] = new TextField();
                textFields[i][j].setBounds(i * 30 + (i / 3 * 5), j * 30 + (j / 3 * 5), 30, 30);
                add(textFields[i][j]);
            }
        }

        // Darstellung des Neustart-Buttons
        reset.setBounds(0, 280, 140, 50);
        reset.addActionListener(actionListener);
        reset.setBackground(SystemColor.window);
        add(reset);

        // Darstellung des Loese-Buttons
        solve.setBounds(140, 280, 140, 50);
        solve.addActionListener(actionListener);
        solve.setBackground(SystemColor.window);
        add(solve);
        repaint();
    }
}