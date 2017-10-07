import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.io.IOException;
import java.util.Scanner;

public class MinesweeperClient {
    InputStream in;
    OutputStream out;
    Socket socket;
    Scanner scanner;
    
    public MinesweeperClient() {
        try {
            socket = new Socket("localhost", 1248);
            in = socket.getInputStream();
            out = socket.getOutputStream();
            scanner = new Scanner(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void startClient() {
        
    }
        
    public void stopClient() {
        try {
            out.write("quit".getBytes());
            in.close();
            out.close();
            socket.close();
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
