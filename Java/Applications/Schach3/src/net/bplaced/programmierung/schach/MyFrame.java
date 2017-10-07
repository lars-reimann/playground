package net.bplaced.programmierung.schach;

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
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class MyFrame extends JFrame {

	/**
	 * Serialisierungsversion.
	 */
	private static final long serialVersionUID = -2745410922673626734L;
	private final transient JRadioButtonMenuItem playBlack;

	private final transient JRadioButtonMenuItem playWhite;
	private final transient JTextArea textArea;
	private final transient JCheckBoxMenuItem withComputer;

	public MyFrame() {
		super();

		// Initialisierung der Checkboxen
		withComputer = new JCheckBoxMenuItem("Gegen Computer", false);

		// Deklarierung und Initialisierung der weiteren Komponenten
		final ButtonGroup computerColor = new ButtonGroup();
		playWhite = new JRadioButtonMenuItem("Computer spielt weiss", false);
		playBlack = new JRadioButtonMenuItem("Computer spielt schwarz", true);
		computerColor.add(playWhite);
		computerColor.add(playBlack);
		textArea = new JTextArea(0, 20);
		final ChessboardPanel panel = new ChessboardPanel(750);
		final Chessboard chessboard = new Chessboard(panel, this);
		panel.setChessboard(chessboard);

		final MyActionListener actionListener = new MyActionListener(chessboard);
		final JMenuBar menuBar = new JMenuBar();
		final JMenu game = new JMenu("Spiel");
		final JMenuItem newGame = new JMenuItem("Neues Spiel");

		// Hinzufuegen eines ActionListeners zu den MenuItems
		newGame.addActionListener(actionListener);

		// Hinzufuegen der einzelnen Items zum Spiel-Menue
		game.add(newGame);
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
		getContentPane().add(panel, BorderLayout.WEST);
		getContentPane().setBackground(Color.lightGray);

		final JScrollPane scrollPane = new JScrollPane(textArea,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
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

	public int getComputerColor() {
		if (playWhite.isSelected()) {
			return -1;
		} else if (playBlack.isSelected()) {
			return 1;
		} else {
			return -1 + 1;
		}
	}

	public void setText(final String notation) {
		textArea.setText(notation);
	}

	public boolean withComputerIsSelected() {
		return withComputer.isSelected();
	}
}
