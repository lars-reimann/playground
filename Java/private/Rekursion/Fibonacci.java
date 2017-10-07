import java.util.Scanner;

public class Fibonacci
{

    private int count = 0;
    private static int end = 0;

    public static void main(final String[] args) {
        System.out.println("Wie viele Werte sollen ausgegeben werden?");
        Scanner scanner = new Scanner(System.in);
        end = scanner.nextInt();
        System.out.println("Fibonacci-Folge:");
        new Fibonacci().fibonacci(1, 1);
    }
    
    private void fibonacci(final int zahl1, final int zahl2) {
        if (count < end) {
            count++;
            final int zahl3 = zahl1 + zahl2;
            System.out.print(zahl1 + "\t");
            fibonacci(zahl2, zahl3);
        }
    }
}
