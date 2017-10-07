package net.blaced.programmierung.registermachine;

/**
 * Implements the SUB i instruction.
 * 
 * Subtracts the content of the accumulator by the number stored in the register
 * i. If the result were less than 0, it is set to 0. All other registers remain
 * unchanged. The program counter is increased by one.
 * 
 * @version 24.02.2013
 * @author Lars Reimann
 */
public class Sub implements Instruction {

	/**
	 * The number of the register that contains the summand.
	 */
	private int i;

	/**
	 * @param i
	 *            The argument of the SUB instruction.
	 */
	public Sub(int i) {
		this.i = i;
	}

	@Override
	public void evaluate(Configuration configuration) {
		int m = configuration.getRegister(i);
		int n = configuration.getRegister(0);
		if (n > m) {
			configuration.setRegister(0, n - m);
		} else {
			configuration.setRegister(0, 0);
		}
		configuration.incrementPC();
	}

	@Override
	public String toString() {
		return "SUB " + i;
	}
}
