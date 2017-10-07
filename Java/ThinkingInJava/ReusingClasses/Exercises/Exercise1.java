public class Exercise1 {
    
    private static Test test;
    
    public static void print() {
        if (test == null) {
            test = new Test();
            test.print();
        }
    }
    
    public static void main(String[] args) {
        print();
    }
}

class Test {
    public void print() {
        System.out.println("Hallo, Welt!");
    }
}
