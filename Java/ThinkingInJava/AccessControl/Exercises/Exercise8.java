import eu.codelottery.exercise8.*;

public class Exercise8 {
    public static void main(String[] args) {
        int i = 0;
        Connection connection;
        do {
            connection = ConnectionManager.getNext();
            i++;
        } while (connection != null);
        System.out.println(i);
    }
}
