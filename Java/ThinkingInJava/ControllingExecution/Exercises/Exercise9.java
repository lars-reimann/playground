public class Exercise5 {
    
    public static void main(String[] args) {
        int m = 1, n = 1;
        int end = Integer.parseInt(args[0]);
        if (end > 0) {
            System.out.println(m);
        }
        for (int i = 1; i < end; i++) {
            System.out.println(n);
            n += m;
            m = n - m;
        }
    }
}
