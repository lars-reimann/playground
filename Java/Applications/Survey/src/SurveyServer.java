import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SurveyServer {

    private static class SurveyClientThread extends Thread {
        
        private enum State {
            ANSWER, SURVEY
        }
        
        private InputStream in;
        private OutputStream out;
        private int selectedSurvey;
        private Socket socket;
        private State state;

        private SurveyClientThread(Socket socket) {
            this.socket = socket;
            try {
                in = socket.getInputStream();
                out = socket.getOutputStream();
            } catch (IOException e) {
                System.err.println(e.toString());
            }

            state = State.SURVEY;
            selectedSurvey = -1;
        }

        private void printAnswers() throws IOException {
            Answer[] answers = surveys.get(selectedSurvey).getAnswers();
            for (int i = 0; i < answers.length; i++) {
                out.write((i + " - " + answers[i].getText() + "\r\n")
                        .getBytes());
            }
            out.write("\r\n".getBytes());
        }

        private void printAnswersWithCount() throws IOException {
            Answer[] answers = surveys.get(selectedSurvey).getAnswers();
            for (int i = 0; i < answers.length; i++) {
                out.write((i + " - " + answers[i].getText() + " --- "
                        + answers[i].getCount() + "\r\n").getBytes());
            }
            out.write("\r\n".getBytes());
        }

        private void printSurveys() throws IOException {
            for (int i = 0; i < surveys.size(); i++) {
                out.write((i + " - " + surveys.get(i).getQuestion() + "\r\n")
                        .getBytes());
            }
            out.write("\r\n".getBytes());
        }

        @Override
        public void run() {
            Scanner scanner = new Scanner(in);
            try {
                out.write("Willkommen beim Umfrage-Server!\r\n\r\n".getBytes());
                selectHelp();
                while (true) {
                    showState();
                    String cmd = scanner.nextLine();
                    if ("quit".equals(cmd)) {
                        break;
                    } else if ("abort".equals(cmd)) {
                        selectAbort();
                    } else if ("help".equals(cmd)) {
                        selectHelp();
                    } else if (state == State.SURVEY
                            && cmd.matches("selSurvey\\s\\d")) {
                        selectSurvey(cmd);
                    } else if (state == State.ANSWER
                            && cmd.matches("selAnswer\\s\\d")) {
                        selectAnswer(cmd);
                    } else {
                        selectUnknown();
                    }
                }
                in.close();
                out.close();
                socket.close();
            } catch (IOException e) {
                System.err.println(e.toString());
            }
            scanner.close();
        }

        private void selectAbort() throws IOException {
            out.write("Abgebrochen\r\n\r\n".getBytes());
            state = State.SURVEY;
        }

        private void selectAnswer(String cmd) throws IOException {
            String[] tokens = cmd.split("\\s");
            int i = Integer.parseInt(tokens[1]);
            Answer[] answers = surveys.get(selectedSurvey).getAnswers();
            if (i < answers.length) {
                answers[i].increaseCount();
                out.write(("Antwort " + i + " gewaehlt.\r\n").getBytes());
                printAnswersWithCount();
                state = State.SURVEY;
            } else {
                out.write("Wert ausserhalb der Grenzen\r\n\r\n".getBytes());
            }
        }

        private void selectHelp() throws IOException {
            out.write("quit\t\t\tBeendet die Verbindung\r\n".getBytes());
            out.write("abort\t\t\tBricht die aktuelle Anfrage ab\r\n"
                    .getBytes());
            out.write("help\t\t\tZeigt diese Hilfe\r\n".getBytes());
            out.write("selSurvey_n\t\tWaehlt die nte Umfrage aus (_ ist ein Leerzeichen)\r\n"
                    .getBytes());
            out.write("selAnswer_n\t\tWaehlt die nte Antwort aus (_ ist ein Leerzeichen)\r\n"
                    .getBytes());
            out.write("\r\n".getBytes());
        }

        private void selectSurvey(String cmd) throws IOException {
            String[] tokens = cmd.split("\\s");
            int i = Integer.parseInt(tokens[1]);
            if (i < surveys.size()) {
                selectedSurvey = i;
                out.write(("Umfrage " + i + " gewaehlt.\r\n").getBytes());
                state = State.ANSWER;
            } else {
                out.write("Wert ausserhalb der Grenzen\r\n".getBytes());
            }
            out.write("\r\n".getBytes());
        }

        private void selectUnknown() throws IOException {
            out.write("Befehl nicht bekannt oder nicht passend\r\n\r\n"
                    .getBytes());
        }

        private void showState() throws IOException {
            if (state == State.SURVEY) {
                printSurveys();
            } else if (state == State.ANSWER) {
                printAnswers();
            }
        }
    }

    private static List<Survey> surveys;

    public SurveyServer() {
        surveys = readSurveys();
    }

    private List<Survey> readSurveys() {
        List<Survey> surveys = new ArrayList<Survey>();

        // TODO In Textdatei auslagern
        surveys.add(new Survey("Wie finden sie diesen Server?", new String[] {
                "Gut", "Naja", "Schlecht" }));
        surveys.add(new Survey("Ist ihnen langweilig?", new String[] { "Ja",
                "Etwas", "Nein" }));
        surveys.add(new Survey("Sitzen sie gerade am PC?", new String[] { "Ja",
                "Nein" }));

        return surveys;
    }

    public void start() {
        try {
            System.out.println("Warte auf Verbindung an Port 1234...");
            ServerSocket server = new ServerSocket(1234);
            while (true) {
                new SurveyClientThread(server.accept()).start();
            }
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }
}
