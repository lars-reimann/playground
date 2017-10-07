import java.util.Scanner;

public class CaesarCipher {

	public void input() {
		Scanner scanner = new Scanner(System.in);
		int choice;
		do {
			System.out
					.println("WELCOME TO CAESAR CIPHER ENCRYPTING AND DECRYPTING SYSTEM");
			System.out.println("1.Encrypt");
			System.out.println("2.Decrypt");
			System.out.println("3.Exit");
			System.out.print("Enter your choice: ");
			choice = scanner.nextInt();
			switch (choice) {
			case 1:
				System.out.print("Enter your code to be encrypted: ");
				String encrypt = scanner.next();
				System.out.print("Enter the key: ");
				System.out.println(encryption(encrypt, scanner.nextInt()));
				break;
			case 2:
				System.out.print("Enter your code to be decrypted: ");
				String decrypt = scanner.next();
				System.out.print("Enter the key: ");
				System.out.println(decryption(decrypt, scanner.nextInt()));
				break;
			case 3:
				System.out.println("Thanks for executing my program");
				break;
			default:
				System.out.println("Wrong choice try again!");
			}
		} while (choice != 3);
		scanner.close();
	}

	private boolean isLower(char c) {
		return 'a' <= c && c <= 'z';
	}

	private boolean isUpper(char c) {
		return 'A' <= c && c <= 'Z';
	}

	private String encryption(String s, int x) {
		char[] chars = s.toCharArray();
		for (int i = 0, len = s.length(); i < len; i++) {
			if (isLower(chars[i])) {
				int mod = (chars[i] - 'a' + x) % 26;
				if (mod < 0) {
					mod += 26;
				}
				chars[i] = (char) ('a' + mod);
			} else if (isUpper(chars[i])) {
				int mod = (chars[i] - 'A' + x) % 26;
				if (mod < 0) {
					mod += 26;
				}
				chars[i] = (char) ('A' + mod);
			}
		}
		return new String(chars);
	}

	private String decryption(String s, int x) {
		return encryption(s, -x);
	}

	public static void main(String[] args) {
		CaesarCipher cipher = new CaesarCipher();
		cipher.input();
	}
}
