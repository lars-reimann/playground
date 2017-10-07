package eu.codelottery.exercise8;

public class ConnectionManager {
    private static Connection[] connections = new Connection[10];
    private static int next = 0;
    
    static {
        for (int i = 0; i < 10; i++) {
            connections[i] = new Connection(i);
        }
    }
    
    public static Connection getNext() {
        if (next >= 10) {
            return null;
        } else {
            return connections[next++];
        }
    }
    
    private ConnectionManager() {}
}
