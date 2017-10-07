public class Exercise18 {
    
    public Exercise18(String s) {
        System.out.println(s);
    }
    
    public static void main(String[] args) {
        Exercise18[] ex = new Exercise18[10];
        for (int i = 0; i < 10; i++) {
            ex[i] = new Exercise18(Integer.toString(i));
        }
    }
}
