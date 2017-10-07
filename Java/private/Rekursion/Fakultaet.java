import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Fakultaet
{
    public static void main(final String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        long zahl = 0;
        System.out.println("Bitte geben Sie eine Zahl ein!");
        System.out.println("--------------------");
        try {
            zahl = Integer.parseInt(reader.readLine());
        } catch (IOException exception) {
        }
        System.out.println("--------------------");
        System.out.println(zahl + "! = " + fakultaet(zahl));
    }
    
    private static long fakultaet(final long zahl) {
        if (zahl == 0 || zahl == 1) {
            return 1;
        } else {
            return zahl * fakultaet(zahl - 1);
        }
    }
}
