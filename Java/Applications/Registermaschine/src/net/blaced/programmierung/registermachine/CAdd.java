package net.blaced.programmierung.registermachine;

/**
 * Implements the CADD i instruction.
 * 
 * Adds the number i to the current value of the accumulator. All other
 * registers remain unchanged. The program counter is increased by one.
 * 
 * @version 24.02.2013
 * @author Lars Reimann
 */
public class CAdd implements Instruction {

	/**
	 * The summand.
	 */
	private int i;

	/**
	 * @param i
	 *            The argument of the CADD instruction.
	 */
	public CAdd(int i) {
		this.i = i;
	}

	@Override
	public void evaluate(Configuration configuration) {
		int n = configuration.getRegister(0);
		configuration.setRegister(0, i + n);
		configuration.incrementPC();
	}

	@Override
	public String toString() {
		return "CADD " + i;
	}
}
