public class Exercise5 {
    public static void main(String[] args) {
        Test test = new Test();
        // System.out.println(test.priv); // No access
        System.out.println(test.pack);
        System.out.println(test.prot);
        System.out.println(test.publ);
        // test.priv(); // No access
        test.pack();
        test.prot();
        test.publ();
    }
}

class Test {
    private int priv = 1;
    int pack = 2;
    protected int prot = 3;
    public int publ = 4;
    
    private void priv() {
        System.out.println("priv");
    }
    
    void pack() {
        System.out.println("pack");
    }
    
    protected void prot() {
        System.out.println("prot");
    }
    
    public void publ() {
        System.out.println("publ");
    }
}
