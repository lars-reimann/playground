import java.util.Arrays;

public class Exercise6 {
    
    private static char[] generateFacArray(int fac1, int fac2) {
        String facString = Integer.toString(fac1) + Integer.toString(fac2);
        char[] facCharArray = facString.toCharArray();
        Arrays.sort(facCharArray);
        return facCharArray;
    }
    
    private static char[] generateProdArray(int prod) {
        char[] prodCharArray = Integer.toString(prod).toCharArray();
        Arrays.sort(prodCharArray);
        return prodCharArray;
    }
    
    /**
     * Gibt an, ob die beiden Faktoren zusammen aus den gleichen Ziffern
     * bestehen, wie prod.
     * Dabei wird angenommen, dass fac1 und fac2 jeweils zwei Ziffern haben
     * und prod vier. Ferner soll gelten fac1 * fac2 = prod. Dies wird allerdings
     * nicht getestet und muss gesondert überprüft werden.
     */ 
    private static boolean isVampiric(int fac1, int fac2, int prod) {
        char[] facArray = generateFacArray(fac1, fac2); 
        char[] prodArray = generateProdArray(prod);
        
        for (int i = 0; i < 4; i++) {
            if (facArray[i] != prodArray[i]) {
                return false;
            }
        }
        
        return true;
    }
    
    public static void main(String[] args) {
        for (int i = 10; i <= 99; i++) {
            for (int j = (int) Math.ceil(1000.0 / i); j <= 99; j++) {
                if (isVampiric(i, j, i * j)) {
                    System.out.println(i + "*" + j + "=" + i * j);
                }
            }
        }
    }
}
