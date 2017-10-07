import java.util.Scanner;

public class Stringreverser {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bitte Wort eingeben: ");
        String string = scanner.next();

        for (int i = string.length() - 1; i >= 0; i--) {
            System.out.print(string.charAt(i));
        }
        System.out.println();
    }
}
