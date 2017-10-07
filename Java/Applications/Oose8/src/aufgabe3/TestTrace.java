package aufgabe3;

/**
 * Oose Aufgabe 3
 * 
 * @version 12.06.2013
 * @author Florian Gläßer, Tobias Lenz, Lars Reimann
 */
class CallEg {

	public void methodA() throws ArithmeticException {
		// int a = 1 / 0; // Aufgabe a)
		// methodB(); // Aufgabe b)
		try {
			methodB();
		} catch (ArithmeticException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void methodB() throws ArithmeticException {
		// methodC(); // Aufgabe b);
		try {
			methodC();
		} catch (ArithmeticException e) {
			e.printStackTrace();
			//throw e;
			int a = 1/0;
		}
	}

	public void methodC() throws ArithmeticException {
		int a = 1 / 0;
	}
}

public class TestTrace {
	public static void main(String[] args) {
		CallEg eg = new CallEg(); // use default constructor
		try {
			eg.methodA();
		} catch (ArithmeticException oops) {
			oops.printStackTrace();
		}
	}
}
