import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MinesweeperServer {

    public void startServer() throws IOException {
        ServerSocket serverSocket = new ServerSocket(1248);
        System.out.println("Server wartet an Port 1248");
        while (true) {
            new MinesweeperClientThread(serverSocket.accept()).start();
        }
    }

    private static class MinesweeperClientThread extends Thread {

        Socket socket;
        InputStream in;
        OutputStream out;
        Minesweeper minesweeper;
        State state;

        private enum State {
            SETUP, WORKING
        }

        public MinesweeperClientThread(Socket socket) {
            this.socket = socket;
            try {
                in = socket.getInputStream();
                out = socket.getOutputStream();
            } catch (IOException e) {
                throw new RuntimeException(
                        "Verbindung konnte nicht aufgebaut werden", e);
            }
            minesweeper = new Minesweeper();
            state = State.SETUP;
        }

        @Override
        public void run() {
            Scanner scanner = new Scanner(in);
            try {
                while (true) {
                    if (state == State.WORKING) {
                        printRepresentation();
                    }
                    String cmd = scanner.nextLine();
                    if ("quit".equals(cmd)) {
                        break;
                    } else if ("help".equals(cmd)) {

                    } else if ("restart".equals(cmd)) {
                        state = State.SETUP;
                    } else if (state == State.SETUP) {
                        if (cmd.matches("size\\s\\d*\\s\\d*\\s\\d*")) {
                            String[] tokens = cmd.split("\\s");
                            minesweeper.setSize(Integer.parseInt(tokens[2]),
                                    Integer.parseInt(tokens[1]),
                                    Integer.parseInt(tokens[3]));
                            state = State.WORKING;
                        } else {
                            printWrongUsage("size_Breite_Hoehe_Bombenzahl (_ ist Leerzeichen)"); // TODO Nichtquadratische Groessen
                        }
                    } else if (state == State.WORKING) {
                        if (cmd.matches("sel\\s\\d*\\s\\d*")) {
                            String[] tokens = cmd.split("\\s");
                            minesweeper.sel(Integer.parseInt(tokens[2]), // TODO x/y anders loesen
                                    Integer.parseInt(tokens[1]));
                            out.write(("*STATESTART*" + minesweeper.getState().toString() + "*STATEEND*").getBytes());
                        } else if (cmd.matches("mark\\s\\d*\\s\\d*")) {
                            String[] tokens = cmd.split("\\s");
                            minesweeper.mark(Integer.parseInt(tokens[2]),
                                    Integer.parseInt(tokens[1]));
                        } else {
                            printWrongUsage("sel_x_y (_ ist Leerzeichen)");
                            printWrongUsage("mark_x_y (_ ist Leerzeichen");
                        }
                    }
                }
                scanner.close();
                socket.close();
                in.close();
                out.close();
            } catch (IOException e) {
                throw new RuntimeException(
                        "Verbindung konnte nicht aufgebaut werden", e);
            }

        }

        private void printWrongUsage(String string) throws IOException {
            out.write(("*ERRORSTART*Falsche Benutzung " + string + "*ERROREND*").getBytes());
        }

        private void printRepresentation() throws IOException {
            out.write("*REPSTART*".getBytes());
            String[] representation = minesweeper.getRepresentation();
            for (String s : representation) {
                out.write((s + "\\").getBytes());
            }
            out.write("*REPEND*".getBytes());
        }
    }
}
