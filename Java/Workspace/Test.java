public class Test {
    
    private int value = 0;
    
    public int getValue(Test test) {
        return test.value;
    }
    
    public static void main(String[] args) {
        Test test1 = new Test();
        Test test2 = new Test();
        System.out.println(test1.getValue(test2));
    }
}
