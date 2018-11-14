import java.util.List;


public class SMTPClient {

    private Connection connection;

    public SMTPClient() {
        connection = new Connection("localhost", 2500);
    }
    
    public void quit() {
        connection.send("QUIT");
    }
    
    public void send(String from, String to, List<String> text) {
        connection.send("HELO localhost");
        connection.receive();
        connection.send("MAIL FROM: " + from);
        connection.receive();
        connection.send("RCPT TO: " + to);
        connection.receive();
        connection.send("DATA");
        connection.receive();
        
        for (String s : text) {
            connection.send(s);
        }
    }
}
