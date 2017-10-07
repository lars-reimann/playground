public class Exercise8 {
    
    public int getValue() {
        return 42;
    }
    
    public void print() {
        System.out.println(this.getValue());
        System.out.println(getValue());
    }
    
    public static void main(String[] args) {
        Exercise8 ex = new Exercise8();
        ex.print();
    }
}
