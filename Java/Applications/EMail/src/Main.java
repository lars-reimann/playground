import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

public class Main {

    public static void main(String[] args) {
        new Main().init();
    }
    private ArrayList<Account> accounts = new ArrayList<Account>();

    private EMailFrame frame;

    private DefaultMutableTreeNode createTree() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Konten");
        for (Account account : accounts) {
            DefaultMutableTreeNode accountNode = new DefaultMutableTreeNode(account.getAddress());
            root.add(accountNode);
            List<Folder> folders = account.getFolders();
            for (Folder folder : folders) {
                accountNode.add(new DefaultMutableTreeNode(folder.getName()));
            }
        }
        return root;
    }
    
    public void forward() {
    }

    private void init() {
        //accounts.add(new Account("anna@kolleg.de", "anna", "geheim"));
        // accounts.add(new Account("bob@kolleg.de", "bob", "topSecret"));
        // TODO frame = new EMailFrame(createTree(), this);   
        frame = new EMailFrame(new DefaultMutableTreeNode(), this);
    }
    
    public void newAccount() {
    }
    
    public void newMail() {
    }
    
    private void quit() {
        for (Account account : accounts) {
            account.close();
        }
    }
    
    public void receive() {
        for (Account account : accounts) {
            account.receive();
        }
    }

    public void remove() {
    }

    public void newFolder() {
        // TODO Auto-generated method stub
        
    }

}
