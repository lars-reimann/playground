public class Exercise9 {
    
    public final int value;
    
    public Exercise9(int value) {
        this.value = value;
    }
    
    public Exercise9() {
        this(42);
    }
    
    public static void main(String[] args) {
        Exercise9 ex1 = new Exercise9();
        Exercise9 ex2 = new Exercise9(21);
        System.out.println("ex1: " + ex1.value);
        System.out.println("ex2: " + ex2.value);
    }
}
