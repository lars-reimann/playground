import java.util.Scanner;

public class VowelCounter {					

	public static void main(String[] args) throws InterruptedException {		
		int [] vowels = new int [5];
		Scanner scanner = new Scanner (System.in);
		String sentence;
		
		System.out.println ("Geben Sie bitte Ihren Satz/Ihr Wort ein!");
		sentence = scanner.nextLine().toLowerCase();
		
		for (int x = 0; x < sentence.length(); x++) {
			switch (sentence.charAt(0 + x)) {
				case 'a': vowels [0] ++; break;
				case 'e': vowels [1] ++; break;
				case 'i': vowels [2] ++; break;
				case 'o': vowels [3] ++; break;
				case 'u': vowels [4] ++; break;		
			}		 
		}
		System.out.println ("a: " + vowels [0]);
		System.out.println ("e: " + vowels [1]);
		System.out.println ("i: " + vowels [2]);
		System.out.println ("o: " + vowels [3]);
		System.out.println ("u: " + vowels [4]);
		System.exit(0);
	}
}
