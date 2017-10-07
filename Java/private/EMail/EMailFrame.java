import java.awt.Dimension;
import java.awt.Frame;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.JTable;
import java.awt.event.ActionListener;

public final class EMailFrame extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 4065934733888045048L;
    
    private final JTree folders;
    private final JTextArea mail;
    private final JTable maillist;
    private final Vector<Vector<String>> rowData;

    public EMailFrame(TreeNode tree, Main main) {
        
        // Tabelle
        rowData = new Vector<Vector<String>>();

        Vector<String> columnNames = new Vector<String>();
        columnNames.add("Betreff");
        columnNames.add("Absender");
        columnNames.add("Uhrzeit");
        
        maillist = new JTable(rowData, columnNames);
        maillist.getModel().addTableModelListener(new MailTableModelListener(main));
        maillist.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        maillist.setColumnSelectionAllowed(false);
        maillist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane maillistPane = new JScrollPane(maillist);
        maillistPane.setMaximumSize(new Dimension(Integer.MAX_VALUE, 500));
        
        // Buttons
        ActionListener actionListener = new MailActionListener(main);
        
        JButton receive = new JButton("Empfangen");
        receive.addActionListener(actionListener);
        
        JButton send = new JButton("Neue Mail");
        send.addActionListener(actionListener);
        
        JButton forward = new JButton("Weiterleiten");
        forward.addActionListener(actionListener);
        
        JButton remove = new JButton("Loeschen");
        remove.addActionListener(actionListener);
        
        JButton folder = new JButton("Neuer Ordner");
        folder.addActionListener(actionListener);
        
        JButton account = new JButton("Neues Konto");
        account.addActionListener(actionListener);

        // Baum
        folders = new JTree(tree);
        folders.addTreeSelectionListener(new MailTreeSelectionListener(main));
        folders.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        
        JScrollPane foldersPane = new JScrollPane(folders);
        foldersPane.setMaximumSize(new Dimension(500, Integer.MAX_VALUE));

        // Textfeld
        mail = new JTextArea();
        mail.setEditable(false);

        JScrollPane mailPane = new JScrollPane(mail);      

        // Layout
        Box buttons = Box.createHorizontalBox();
        buttons.add(receive);
        buttons.add(send);
        buttons.add(forward);
        buttons.add(remove);
        buttons.add(Box.createHorizontalGlue());
        buttons.add(folder);
        buttons.add(account);

        Box mails = Box.createVerticalBox();
        mails.add(maillistPane);
        mails.add(mailPane);

        Box view = Box.createHorizontalBox();
        view.add(foldersPane);
        view.add(mails);

        Box mainBox = Box.createVerticalBox();  
        mainBox.add(buttons);
        mainBox.add(view);

        getContentPane().add(mainBox);

        // Sonstiges
        setSize(new Dimension(500, 500));
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setTitle("Stormeagle");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        pack();
    }

    public void setFolders(TreeNode tree) {
        folders.setModel(new DefaultTreeModel(tree));
    }

    public void setMail(String text) {
        mail.setText(text);
    }

    public void setMaillist(Vector<Vector<String>> rowVectors) {
        rowData.removeAllElements();
        for (Vector<String> rowVector : rowVectors) {
            rowData.add(rowVector);
        }
    }
}