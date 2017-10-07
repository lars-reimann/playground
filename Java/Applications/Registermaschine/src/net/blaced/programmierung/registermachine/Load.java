package net.blaced.programmierung.registermachine;

/**
 * Implements the LOAD i instruction.
 * 
 * Loads the content of the register i into the accumulator. All other registers
 * remain unchanged. The program counter is increased by one.
 * 
 * @version 24.02.2013
 * @author Lars Reimann
 */
public class Load implements Instruction {

	/**
	 * The number of the register with the value which should be stored in the
	 * accumulator.
	 */
	private int i;

	/**
	 * @param i
	 *            The argument of the Load instruction.
	 */
	public Load(int i) {
		this.i = i;
	}

	@Override
	public void evaluate(Configuration configuration) {
		int m = configuration.getRegister(i);
		configuration.setRegister(0, m);
		configuration.incrementPC();
	}

	@Override
	public String toString() {
		return "LOAD " + i;
	}
}
