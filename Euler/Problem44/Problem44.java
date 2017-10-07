public class Problem44 {
    public static long pentagonal(long n) {
        return n * (3*n - 1) / 2;
    }
    public static boolean isPentagonal(long n) {
        long sol = (long) Math.round((1 + Math.sqrt(1 + 24*n)) / 6);
        return sol*(3*sol-1)/2 == n;
    }
    public static void main(String[] args) {
        for (long i = 1; ; i++) {
            long diff = pentagonal(i);
            if (i % 1 == 0) {
                System.out.println(i + " --- " + diff);
            }
            boolean changed = true;
            for (long j = 2; changed; j++) {
                changed = false;
                long sum = pentagonal(j);
                for (long k = j - 1; k > 0; k--) {
                    long current = sum - 2 * pentagonal(k);
                    if (current > diff) {
                        break;
                    }
                    long summand = sum - current;
                    if (current == diff) {
                        if (isPentagonal(summand)) {
                            System.out.println(diff);
                            return;
                        }
                        break;
                    }
                    changed = true;
                }
            }
        }
    }
}
