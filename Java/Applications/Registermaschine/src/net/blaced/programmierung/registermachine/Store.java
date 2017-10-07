package net.blaced.programmierung.registermachine;

/**
 * Implements the STORE i instruction.
 * 
 * Stores the content of the accumulator in the register i. All other registers
 * remain unchanged. The program counter is increased by one.
 * 
 * @version 24.02.2013
 * @author Lars Reimann
 */
public class Store implements Instruction {

	/**
	 * The number of the register where to store the content of the accumulator.
	 */
	private int i;

	/**
	 * @param i
	 *            The argument of the STORE instruction.
	 */
	public Store(int i) {
		this.i = i;
	}

	@Override
	public void evaluate(Configuration configuration) {
		int n = configuration.getRegister(0);
		configuration.setRegister(i, n);
		configuration.incrementPC();
	}

	@Override
	public String toString() {
		return "STORE " + i;
	}
}
