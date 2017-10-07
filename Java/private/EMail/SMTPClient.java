import java.util.List;


public class SMTPClient {

    private Connection connection;

    public SMTPClient() {
        connection = new Connection("localhost", 2500);
    }
    
    public void quit() {
        connection.send("QUIT");
    }
    
    public void send(Mail mail) {
        connection.send("HELO localhost");
        connection.receive();
        connection.send("MAIL FROM: " + mail.getFrom());
        connection.receive();
        connection.send("RCPT TO: " + mail.getTo());
        connection.receive();
        connection.send("DATA");
        connection.receive();
        
        for (String s : mail.getText) {
            connection.send(s);
        }
    }
}
