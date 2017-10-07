package net.blaced.programmierung.registermachine;

/**
 * Implements the CSUB i instruction.
 * 
 * Decreases the current value of the accumulator by i. If the result would be
 * less than 0, it is set to 0. All other registers remain unchanged. The
 * program counter is increased by one.
 * 
 * @version 24.02.2013
 * @author Lars Reimann
 */
public class CSub implements Instruction {

	/**
	 * The number to be subtracted.
	 */
	private int i;

	/**
	 * @param i
	 *            The argument of the CSUB instruction.
	 */
	public CSub(int i) {
		this.i = i;
	}

	@Override
	public void evaluate(Configuration configuration) {
		int n = configuration.getRegister(0);
		if (n > i) {
			configuration.setRegister(0, n - i);
		} else {
			configuration.setRegister(0, 0);
		}
		configuration.incrementPC();
	}

	@Override
	public String toString() {
		return "CSUB " + i;
	}
}
