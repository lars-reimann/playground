package net.blaced.programmierung.registermachine;

/**
 * Implements the CLOAD i instruction.
 * 
 * Loads the number i into the accumulator. All other registers remain
 * unchanged. The program counter is increased by one.
 * 
 * @version 24.02.2013
 * @author Lars Reimann
 */
public class CLoad implements Instruction {

	/**
	 * The number to be loaded into the accumulator.
	 */
	private int i;

	/**
	 * @param i
	 *            The argument of the CLoad instruction.
	 */
	public CLoad(int i) {
		this.i = i;
	}

	@Override
	public void evaluate(Configuration configuration) {
		configuration.setRegister(0, i);
		configuration.incrementPC();
	}

	@Override
	public String toString() {
		return "CLOAD " + i;
	}

}
