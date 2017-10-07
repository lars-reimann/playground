import java.util.ArrayList;
import java.util.List;

public class Folder {
    private List<Mail> mails = new ArrayList<Mail>();
    private String name;
    
    public Folder(String name) {
        this.name = name;
    }
    
    public void addMail(Mail mail) {
        mails.add(mail);
    }
    
    public Mail getMail(int index) {
        return mails.get(index);
    }
    
    public List<Mail> getMails() {
        return mails;
    }
    
    public String getName() {
        return name;
    }
    
    public void removeMail(int index) {
        mails.remove(index);
    }
}
