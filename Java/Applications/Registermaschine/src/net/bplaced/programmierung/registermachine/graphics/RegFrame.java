package net.bplaced.programmierung.registermachine.graphics;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Font;
import javax.swing.JTextPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTable;
import java.awt.Insets;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.Box;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class RegFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegFrame frame = new RegFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegFrame() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int i = JOptionPane.showConfirmDialog(RegFrame.this, Messages.getString("RegFrame.saveDialog.text")); //$NON-NLS-1$
				if (i == 0) {
					// TODO save dialog
				} else if (i == 1) {
					RegFrame.this.dispose();
				}
			}
		});
		setTitle(Messages.getString("RegFrame.this.title")); //$NON-NLS-1$
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu(Messages.getString("RegFrame.mnFile.text")); //$NON-NLS-1$
		menuBar.add(mnFile);
		
		JMenuItem mntmNew = new JMenuItem(Messages.getString("RegFrame.mntmNew.text")); //$NON-NLS-1$
		mnFile.add(mntmNew);
		
		JMenuItem mntmOpen = new JMenuItem(Messages.getString("RegFrame.mntmOpen.text")); //$NON-NLS-1$
		mnFile.add(mntmOpen);
		
		JMenuItem mntmClose = new JMenuItem(Messages.getString("RegFrame.mntmClose.text")); //$NON-NLS-1$
		mnFile.add(mntmClose);
		
		JMenuItem mntmSave = new JMenuItem(Messages.getString("RegFrame.mntmSave.text")); //$NON-NLS-1$
		mnFile.add(mntmSave);
		
		JMenuItem mntmSaveAs = new JMenuItem(Messages.getString("RegFrame.mntmSaveAs.text")); //$NON-NLS-1$
		mnFile.add(mntmSaveAs);
		
		JMenuItem mntmExit = new JMenuItem(Messages.getString("RegFrame.mntmExit.text")); //$NON-NLS-1$
		mnFile.add(mntmExit);
		
		JMenu mnRun = new JMenu(Messages.getString("RegFrame.mnRun.text")); //$NON-NLS-1$
		menuBar.add(mnRun);
		
		JMenuItem mntmInitialize = new JMenuItem(Messages.getString("RegFrame.mntmInitialize.text")); //$NON-NLS-1$
		mnRun.add(mntmInitialize);
		
		JMenuItem mntmRun = new JMenuItem(Messages.getString("RegFrame.mntmRun.text")); //$NON-NLS-1$
		mnRun.add(mntmRun);
		
		JMenuItem mntmNextStep = new JMenuItem(Messages.getString("RegFrame.mntmNextStep.text")); //$NON-NLS-1$
		mnRun.add(mntmNextStep);
		
		JMenu mnHelp = new JMenu(Messages.getString("RegFrame.mnHelp.text")); //$NON-NLS-1$
		menuBar.add(mnHelp);
		
		JMenuItem mntmHelpContent = new JMenuItem(Messages.getString("RegFrame.mntmHelpContent.text")); //$NON-NLS-1$
		mnHelp.add(mntmHelpContent);
		
		JMenuItem mntmSearch = new JMenuItem(Messages.getString("RegFrame.mntmSearch.text")); //$NON-NLS-1$
		mnHelp.add(mntmSearch);
		
		JMenuItem mntmAbout = new JMenuItem(Messages.getString("RegFrame.mntmAbout.text")); //$NON-NLS-1$
		mnHelp.add(mntmAbout);
		getContentPane().setLayout(new MigLayout("", "[150px:n,grow 5][grow]", "[200.00px:n,grow][]"));
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, "cell 0 0,grow");
		
		JTextArea textArea = new JTextArea();
		textArea.setToolTipText(Messages.getString("RegFrame.textArea.toolTipText")); //$NON-NLS-1$
		textArea.setTabSize(4);
		textArea.setFont(new Font("Dialog", Font.PLAIN, 15));
		scrollPane.setViewportView(textArea);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		getContentPane().add(scrollPane_1, "cell 1 0,grow");
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		JLabel lblNumberOfRegisters = new JLabel(Messages.getString("RegFrame.lblNumberOfRegisters.text")); //$NON-NLS-1$
		getContentPane().add(lblNumberOfRegisters, "cell 0 1,alignx trailing");
		
		textField = new JTextField();
		textField.setToolTipText(Messages.getString("RegFrame.textField.toolTipText")); //$NON-NLS-1$
		textField.setText("10");
		getContentPane().add(textField, "cell 1 1,growx");
		textField.setColumns(10);
	}

}
