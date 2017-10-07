import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Palindrome
{
    public static void main(final String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String wort = null;
        System.out.println("Bitte geben Sie ein Wort ein!");
        System.out.println("--------------------");
        try {
            wort = reader.readLine().toUpperCase();
        } catch (IOException exception) {
        }
        System.out.println("--------------------");
        if (wort.equals(verdreheWort(wort))) {
            System.out.println("Dies ist ein Palindrom!");
        } else {
            System.out.println("Dies ist kein Palindrom!");
        }
    }
    
    private static String verdreheWort(final String wort) {
        if (wort.length() <= 1)
            return wort;
        else
            return verdreheWort(wort.substring(1,wort.length())) + wort.charAt(0);
    }
}
