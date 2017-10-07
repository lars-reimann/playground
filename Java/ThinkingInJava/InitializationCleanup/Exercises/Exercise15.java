public class Exercise15 {
    
    public String s1;
    public String s2;
    
    {
        s1 = "Hallo";
    }
    
    public Exercise15() {
        s2 = "Welt";
    }
    
    public static void main(String[] args) {
        Exercise15 ex = new Exercise15();
        System.out.println(ex.s1 + ", " + ex.s2 + "!");
    }
}
