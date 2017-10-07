package net.bplaced.programmierung.chess;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

/**
 * <p>
 * Das Hauptfenster des Programmes.
 * </p>
 * <p>
 * Dieses beinhaltet ein umfangreiches {@code MenuBar}, welches aus den drei
 * {@code Menu}s "Datei", "Spiel" und "Hilfe" besteht. Das erste ist f&uuml;r
 * Import und Export verantwortlich, sowie f&uuml;r das Beenden des Programmes.
 * Das zweite beinhaltet alle Optionen f&uuml;r den Verlauf und den Aufbau des
 * Spieles an sich. So kann man wahlweise gegen des Computer oder gegen einen
 * menschlichen Gegner spielen. Des Weiteren kann man &Uuml;bungsspiele ohne
 * eine Dame oder ohne T&uuml;rme ausf&uuml;hren.
 * </p>
 * <p>
 * Au&szlig;erdem werden die beiden Hauptkomponenten, n&auml;mlich das
 * Schachbrett und die Zuganzeige in das Fenster eingebunden.
 * </p>
 * 
 * @version 3. Juli 2011
 * @author Lars Reimann
 */
public final class ChessFrame extends JFrame {

    /**
     * Die automatisch generierte Versionsnummer.
     */
    private static final long serialVersionUID = 8250265062580174394L;

    /**
     * Zeigt an, ob ein Spiel gegen den Computer ausgef&uuml;hrt werden soll.
     */
    private final JCheckBox againstComputer;

    /**
     * Zeigt an, welche Farbe der Computer spielen soll.
     */
    private final JRadioButton computerIsBlack;

    /**
     * Das {@code JTextArea}, in welchem die Z&uuml;ge angezeigt werden.
     */
    private final JTextArea moves;

    /**
     * Zeigt an, ob das Spiel mit L&auml;ufern gestartet werden soll.
     */
    private final JCheckBox withBishops;

    /**
     * Zeigt an, ob das Spiel mit Springern gestartet werden soll.
     */
    private final JCheckBox withKnights;

    /**
     * Zeigt an, ob das Spiel mit Damen gestartet werden soll.
     */
    private final JCheckBox withQueens;

    /**
     * Zeigt an, ob das Spiel von Anfang an T&uuml;rme beinhalten soll.
     */
    private final JCheckBox withRooks;

    /**
     * <p>
     * Der einzige Konstruktor dieser Klasse.
     * </p>
     * <p>
     * Es wird einmal das vollst&auml;ndige Men&uuml; erstellt und in das
     * Fenster eingebunden. Zweitens wird das Schachbrett und die Zugliste
     * erzeugt. Schlie&szlig;lich wird noch f&uuml;r die sonstige grafische
     * Darstellung dieses Fensters gesorgt.
     * </p>
     */
    public ChessFrame() {
 
        // Schachbrett
        final ChessBoard board = new ChessBoard();
        
        // Logik
        final ChessLogic logic = new ChessLogic(board, this); 
        
        // Import/Export
        final ChessImportExport importExport = new ChessImportExport(board, logic);
        importExport.importPos("startPos" + File.separator + "bdlst");

        // ActionListener
        final ChessActionListener actionListener = 
            new ChessActionListener(this, importExport, logic);
        
        // WindowListener
        addWindowListener(new ChessWindowListener(this, importExport));

        // Menu Datei
        final JMenu file = new JMenu("Datei");
        final JMenuItem impord = new JMenuItem("Import");
        impord.addActionListener(actionListener);
        file.add(impord);
        final JMenuItem export = new JMenuItem("Export");
        export.addActionListener(actionListener);
        file.add(export);
        
        file.addSeparator();
        
        final JMenuItem exit = new JMenuItem("Beenden");
        exit.addActionListener(actionListener);
        file.add(exit);

        // Menu Spiel
        final JMenu game = new JMenu("Spiel");
        final JMenuItem newGame = new JMenuItem("Neues Spiel");
        newGame.addActionListener(actionListener);
        game.add(newGame);
        final JMenuItem takeBack = new JMenuItem("R\u00FCckg\u00E4ngig");
        takeBack.addActionListener(actionListener);
        game.add(takeBack);

        game.addSeparator();

        game.add(againstComputer = new JCheckBox("Gegen Computer"));
        final ButtonGroup computerColor = new ButtonGroup();
        computerIsBlack = new JRadioButton("Computer ist schwarz");
        computerIsBlack.setSelected(true);
        computerColor.add(computerIsBlack);
        game.add(computerIsBlack);
        final JRadioButton computerIsWhite = 
            new JRadioButton("Computer ist wei\u00DF");
        computerColor.add(computerIsWhite);
        game.add(computerIsWhite);

        game.addSeparator();

        withQueens = new JCheckBox("Mit Damen");
        withQueens.setSelected(true);
        game.add(withQueens);
        withBishops = new JCheckBox("Mit L\u00E4ufern");
        withBishops.setSelected(true);
        game.add(withBishops);
        withKnights = new JCheckBox("Mit Springern");
        withKnights.setSelected(true);
        game.add(withKnights);
        withRooks = new JCheckBox("Mit T\u00FCrmen");
        withRooks.setSelected(true);
        game.add(withRooks);

        // Menu Hilfe
        final JMenu help = new JMenu("Hilfe");
        final JMenuItem about = new JMenuItem("\u00DCber Schach");
        about.addActionListener(actionListener);
        help.add(about);

        // MenuBar
        final JMenuBar menuBar = new JMenuBar();
        menuBar.add(file);
        menuBar.add(game);
        menuBar.add(help);
        setJMenuBar(menuBar);

        // TextArea
        moves = new JTextArea("", 0, 0);
        moves.setBackground(new Color(230, 230, 230));
        moves.setEditable(false);
        moves.setBorder(new TitledBorder("Z\u00FCge"));
        moves.setPreferredSize(new Dimension(200, 400));

        // ContentPane
        final Container contentPane = getContentPane();
        contentPane.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        contentPane.add(board);
        contentPane.add(moves);

        // JFrame
        setTitle("Schach");
        setResizable(false);
        setVisible(true);
        pack();
    }

    /**
     * @return Die Farbe, die der Computer spielen soll.
     */
    public byte getComputerColor() {
        return computerIsBlack.isSelected() ? 
                   ChessConstants.BLACK : ChessConstants.WHITE;
    }

    /**
     * @return Das {@code JTextArea}, in welchem die Zugliste angezeigt wird.
     */
    public JTextArea getMoves() {
        return moves;
    }

    /**
     * @return Ob das Spiel gegen den Computer zu spielen ist.
     */
    public boolean isAgainstComputer() {
        return againstComputer.isSelected();
    }

    /**
     * @return Ob das Spiel von Beginn an L&auml;ufer enthalten soll.
     */
    public boolean isWithBishops() {
        return withBishops.isSelected();
    }

    /**
     * @return Ob das Spiel von Beginn an Springer enthalten soll.
     */
    public boolean isWithKnights() {
        return withKnights.isSelected();
    }

    /**
     * @return Ob das Spiel von Beginn an Damen enthalten soll.
     */
    public boolean isWithQueens() {
        return withQueens.isSelected();
    }

    /**
     * @return Ob das Spiel von Beginn an T&uuml;rme enthalten soll.
     */
    public boolean isWithRooks() {
        return withRooks.isSelected();
    }
}
