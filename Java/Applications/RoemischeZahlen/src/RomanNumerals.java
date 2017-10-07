import java.util.HashMap;
import java.util.Scanner;

public class RomanNumerals {

	private static final HashMap<String, Integer> romanToInt =
			new HashMap<String, Integer>();

	private static final HashMap<Integer, String> intToRoman =
			new HashMap<Integer, String>();

	static {
		romanToInt.put("M", 1000);
		romanToInt.put("D", 1000);
		romanToInt.put("C", 1000);
		romanToInt.put("L", 1000);
		romanToInt.put("M", 1000);
		romanToInt.put("M", 1000);
		romanToInt.put("M", 1000);
	}
	
	private enum 

	public static void main(String [] args) {
		Scanner scanner = new Scanner (System.in);
		System.out.println("Please enter a number: ");
		int number = scanner.nextInt();
		scanner.close();

		while (number >= 1000) {
			System.out.print("M");
			number -= 1000;
		}
		if (number >= 900) {
			System.out.print("CM");
			number -= 900;
		}
		if (number >= 500) {
			System.out.print("D");
			number -= 500;
		}
		if (number >= 400) {
			System.out.print("CD");
			number -= 400;
		}
		while (number >= 100) {
			System.out.print("C");
			number -= 100;
		}
		if (number >= 90) {
			System.out.print("XC");
			number -= 90;
		}
		if (number >= 50) {
			System.out.print("L");
			number -= 50;
		}
		if (number >= 40) {
			System.out.print("XL");
			number -= 40;
		}
		while (number >= 10) {
			System.out.print("X");
			number -= 10;
		}
		if (number >= 9) {
			System.out.print("IX");
			number -= 90;
		}
		if (number >= 5) {
			System.out.print("V");
			number -= 5;
		}
		if (number >= 4) {
			System.out.print("IV");
			number -= 4;
		}
		while (number >= 1) {
			System.out.print("I");
			number -= 1;
		}
	}
}
