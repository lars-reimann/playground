import java.io.IOException;


public class Main {
    public static void main(String[] args) {
        try {
            new MinesweeperServer().startServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
