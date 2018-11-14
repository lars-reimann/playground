 

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class MyFrame extends JFrame {

    /**
     * Serialisierungsversion.
     */
    private static final long                    serialVersionUID = -2745410922673626734L;

    private final transient JCheckBoxMenuItem    withBishops;
    private final transient JCheckBoxMenuItem    withKnights;
    private final transient JCheckBoxMenuItem    withQueens;
    private final transient JCheckBoxMenuItem    withRooks;
    private final transient JCheckBoxMenuItem    withComputer;

    private final transient JRadioButtonMenuItem playWhite;
    private final transient JRadioButtonMenuItem playBlack;
    private final transient JTextArea            textArea;

    public MyFrame() {
        super();

        // Initialisierung der Checkboxen
        withRooks = new JCheckBoxMenuItem("Mit Tuermen", true);
        withQueens = new JCheckBoxMenuItem("Mit Damen", true);
        withBishops = new JCheckBoxMenuItem("Mit Laeufern", true);
        withKnights = new JCheckBoxMenuItem("Mit Springern", true);
        withComputer = new JCheckBoxMenuItem("Gegen Computer", false);

        // Deklarierung und Initialisierung der weiteren Komponenten
        final ButtonGroup computerColor = new ButtonGroup();
        playWhite = new JRadioButtonMenuItem("Computer spielt weiss", false);
        playBlack = new JRadioButtonMenuItem("Computer spielt schwarz", true);
        computerColor.add(playWhite);
        computerColor.add(playBlack);
        textArea = new JTextArea(0, 20);
        final Chessboard chessboard = new Chessboard(this, 500);
        final MyActionListener actionListener = new MyActionListener(chessboard);
        final JMenuBar menuBar = new JMenuBar();
        final JMenu game = new JMenu("Spiel");
        final JMenuItem newGame = new JMenuItem("Neues Spiel");

        // Hinzufuegen eines ActionListeners zu den MenuItems
        newGame.addActionListener(actionListener);

        // Hinzufuegen der einzelnen Items zum Spiel-Menue
        game.add(newGame);
        game.addSeparator();
        game.add(withRooks);
        game.add(withKnights);
        game.add(withBishops);
        game.add(withQueens);
        game.addSeparator();
        game.add(withComputer);
        game.add(playWhite);
        game.add(playBlack);

        // Hinzufuegen des Menues Spiel zur Menueleiste
        menuBar.add(game);

        // Konfiguration der anderen Elemente
        textArea.setEditable(false);
        textArea.setBackground(Color.lightGray);

        // Konfiguration der ContentPane
        getContentPane().setLayout(new BorderLayout(3, 0));
        getContentPane().add(chessboard, BorderLayout.WEST);
        getContentPane().setBackground(Color.lightGray);

        final JScrollPane scrollPane = new JScrollPane(textArea,
                        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(new TitledBorder(new LineBorder(Color.black),
                        "Z\u00FCge:"));
        scrollPane.setBackground(Color.lightGray);

        getContentPane().add(scrollPane, BorderLayout.EAST);

        // Konfiguration des Fensters
        setJMenuBar(menuBar);
        setTitle("Schach");
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
    }

    public boolean withBishopsIsSelected() {
        return withBishops.isSelected();
    }

    public boolean withKnightsIsSelected() {
        return withKnights.isSelected();
    }

    public boolean withQueensIsSelected() {
        return withQueens.isSelected();
    }

    public boolean withRooksIsSelected() {
        return withRooks.isSelected();
    }

    public void setText(final String notation) {
        textArea.setText(notation);
    }

    public boolean withComputerIsSelected() {
        return withComputer.isSelected();
    }

    public int getComputerColor() {
        return playBlack.isSelected() ? ChessConstants.BLACK
                        : ChessConstants.WHITE;
    }
}
