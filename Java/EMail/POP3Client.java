public class POP3Client {

    private Connection connection;

    public void logout() {
        if (connection != null && !connection.isClosed()) {
            connection.send("QUIT");
            connection.close();
        } else {
            System.out.println("Keine Verbindung hergestellt.");
        }
    }

    public String login(String user, String password) {
        if (connection == null || connection.isClosed()) {
            connection = new Connection("localhost", 1100);
            if (connection.receive().startsWith("+OK")) {
                connection.send("USER " + user);
            } else {
                return "Der Nutzer scheint nicht bekannt zu sein.";
            }
            if (connection.receive().startsWith("+OK")) {
                connection.send("PASS " + password);  
            } else {
                return "Das Passwort ist inkorrekt.";
            }
            if (!connection.receive().startsWith("+OK")) {
                return "Es ist ein unbekannter Fehler aufgetreten.";
            }
        }
        return null;
    }

    private int countMails() {
        connection.send("STAT");

        String message = connection.receive();
        String[] tokens = message.split(" ");
        int count = Integer.parseInt(tokens[1]);

        return count;
    }

    public void remove(int nr) {
        if (connection != null && !connection.isClosed()) {
            connection.send("DELE " + nr);
            connection.receive();
        } else {
            System.out.println("Keine Verbindung hergestellt.");
        }
    }

    public String[] receive() {
        if (connection != null && !connection.isClosed()) {
            int count = countMails();
            String[] mails = new String[count];
            for (int i = 1; i <= count; i++) {
                StringBuilder builder = new StringBuilder();
                connection.send("RETR " + i);
                while (true) {
                    String line = connection.receive();
                    builder.append(line);
                    if (".".equals(line)) {
                        break;
                    }
                }
                mails[i] = builder.toString();
            }
            return mails;
        }
        return new String[] {};
    }
}
