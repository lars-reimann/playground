public class Exercise10 {
	public static void main (String args[]) {
		int i = 0xAAAAAAAA;
        int j = 0x55555555;
        System.out.println("i: " + Integer.toBinaryString(i) + " | j: " + Integer.toBinaryString(j));
        System.out.println("~i: " + Integer.toBinaryString(~i));
        System.out.println("~j: " + Integer.toBinaryString(~j));
        System.out.println("i & j: " + Integer.toBinaryString(i & j));
        System.out.println("i | j: " + Integer.toBinaryString(i | j));
        System.out.println("i ^ j: " + Integer.toBinaryString(i ^ j));
	}
}

