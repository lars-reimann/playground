package net.blaced.programmierung.registermachine;

/**
 * Implements the GOTO i instruction.
 * 
 * The program counter is set to i. All other registers remain unchanged.
 * 
 * @version 24.02.2013
 * @author Lars Reimann
 */
public class Goto implements Instruction {

	/**
	 * The number of the next instruction.
	 */
	private int i;

	/**
	 * @param i
	 *            The argument of the GOTO instruction.
	 */
	public Goto(int i) {
		this.i = i;
	}

	@Override
	public void evaluate(Configuration configuration) {
		configuration.setPC(i);
	}

	@Override
	public String toString() {
		return "GOTO " + i;
	}
}
