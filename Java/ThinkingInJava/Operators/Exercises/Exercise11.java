class Exercise11 {
    public static void main(String[] args) {
        for (int i = 31, n = 0x80000000; i >= 0; i--, n >>= 1) {
            System.out.println(Integer.toBinaryString(n));
        }
    }
}
