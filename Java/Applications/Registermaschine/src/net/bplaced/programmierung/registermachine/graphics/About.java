package net.bplaced.programmierung.registermachine.graphics;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

public class About extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			About dialog = new About();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public About() {
		setResizable(false);
		setTitle(Messages.getString("About.this.title")); //$NON-NLS-1$
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[grow]", "[grow]"));
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, "cell 0 0,grow");
			{
				JTextArea txtrRegisterMachineVersion = new JTextArea();
				txtrRegisterMachineVersion.setEditable(false);
				txtrRegisterMachineVersion.setFont(new Font("Dialog",
						Font.PLAIN, 15));
				txtrRegisterMachineVersion.setText(Messages
						.getString("About.txtrRegisterMachineVersion.text")); //$NON-NLS-1$
				scrollPane.setViewportView(txtrRegisterMachineVersion);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton(
						Messages.getString("About.okButton.text")); //$NON-NLS-1$
				okButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									WindowEvent wev = new WindowEvent(
											About.this,
											WindowEvent.WINDOW_CLOSING);
									About.this.dispatchEvent(wev);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
					}
				});
				okButton.setActionCommand(Messages
						.getString("About.okButton.actionCommand")); //$NON-NLS-1$
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
