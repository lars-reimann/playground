package Aufgabe1;

public class Start {
	
	public static double power(double a, int n) {
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		return powerHelper(a, n);
	}
	
	private static double powerHelper(double a, int n) {
		assert n >= 0;
		
		if (n == 0) {
			return 1;
		} else if (n % 2 == 0) {
			double temp = powerHelper(a, n / 2);
			return temp * temp;
		} else {
			return a * powerHelper(a, n - 1);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(power(2, 3));
	}

}
