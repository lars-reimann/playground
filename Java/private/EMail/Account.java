import java.util.ArrayList;
import java.util.List;

public class Account {
    private String address;
    private List<Folder> folders;
    private String password;
    private POP3Client pop3;
    private SMTPClient smtp;
    private String user;
    
    public Account(String address, String user, String password) {
        this.address = address;
        this.user = user;
        this.password = password;
        
        folders = new ArrayList<Folder>();
        folders.add(new Folder("Empfangen"));
        folders.add(new Folder("Gesendet"));
        
        pop3 = new POP3Client();
        pop3.login(user, password);
        smtp = new SMTPClient();
    }
    
    /**
     * Beendet die Verbindungen zum Pop3-Server und zum Smtp-Server.
     */
    public void close() {
        pop3.logout();
        smtp.quit();
    }
    
    /**
     * Gibt die dem Konto zurgeordnete E-Mail-Adresse zur&uuml;ck.
     * 
     * @return Die E-Mail-Adresse.
     */
    public String getAddress() {
        return address;
    }
    
    /**
     * Gibt eine Liste mit den Ordnern dieses Kontos zur&uuml;ck.
     * 
     * @return Die Ordnerliste.
     */
    public List<Folder> getFolders() {
        return folders;
    }
    
    /**
     * Gibt das Passwort des Kontos zur&uuml;ck.
     * 
     * @return Das Passwort des Kontos.
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * Gibt eine Liste mit den Mails im Ordner "Empfangen" zur&uuml;ck.
     * Die Methode verh&auml;lt sich genauso wie der Aufruf {@code getMails(0)}.
     * 
     * @return Die Mailliste des "Empfangen"-Ordners.
     */
    public List<Mail> getReceived() {
        return getMails(0);
    }
    
    /**
     * Gibt eine Liste mit den Mails im Ordner "Gesendet" zur&uuml;ck.
     * Die Methode verh&auml;lt sich genauso wie der Aufruf {@code getMails(1)}.
     * 
     * @return Die Mailliste des "Gesendet"-Ordners.
     */
    public List<Mail> getSent() {
        return getMails(1);
    }
    
    /**
     * Gibt eine Liste mit den Mails im Ordner mit dem Index {@code index} zur&uuml;ck.
     * 
     * @param index Die Nummer des Ordners.
     * @return Die Mailliste des entsprechenden Ordners.
     */
    public List<Mail> getMails(int index) {
        return folders.get(index).getMails();
    }
    
    /**
     * Gibt die Mail mit dem Index {@code mailN} im Ordner mit der Nummer {@code folderN} zur&uuml;ck.
     * 
     * @param folderN Die Nummer des Ordners.
     * @param mailN Die Nummer der Mail.
     * @return Die entsprechende Mail.
     */
    public Mail getMail(int folderN, int mailN) {
        return getMails(folderN).get(mailN);
    }
    
    /**
     * L&ouml;scht die Mail mit dem Index {@code mailN} im Ordner mit der Nummer {@code folderN}.
     * 
     * @param folderN Die Nummer des Ordners.
     * @param mailN Die Nummer der Mail.
     */
    public void removeMail(int folderN, int mailN) {
        folders.get(folderN).removeMail(mailN);
    }
    
    /**
     * Gibt den Benutzernamen zur&uuml;ck.
     * 
     * @return Den Benutzernamen.
     */
    public String getUser() {
        return user;
    }

    /**
     * Empf&auml;gt die E-Mails die auf dem Pop3-Server bereitliegen.
     */
    public void receive() {
        String[] mails = pop3.receive();
        Folder received = folders.get(0);
        
        for (String mail : mails) {
            received.addMail(new Mail(mail));
        }
    }
    
    public void send() {
        // TODO smtp.send(from, to, text);
    }
}
