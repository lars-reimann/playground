import java.util.Scanner;

public final class Logic {
    
    private int dividend;
    private int divisor;
    private int decimalPlaces;
    private String result;

    public Logic() {
        result = "";
    }
    
    public void readInput() {
        final Scanner scanner = new Scanner(System.in);
        
        System.out.print("Dividend: ");
        dividend = scanner.nextInt();
        System.out.print("Divisor: ");
        divisor = scanner.nextInt();
        System.out.print("Anzahl der Nachkommastellen: ");
        decimalPlaces = scanner.nextInt();
        
        scanner.close();
    }
    
    public void compute() {
        
        // Vorkommateil
        result += dividend / divisor + ".";
        dividend = dividend % divisor;
        
        // Nachkommateil
        for (int i = 0; i < decimalPlaces && dividend != 0; i++) {
            result += (10 * dividend) / divisor;
            dividend = (10 * dividend) % divisor;
        }
    }
    
    public void writeOutput() {
        System.out.println(result);
    }
}
