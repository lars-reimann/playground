package net.blaced.programmierung.registermachine;

/**
 * Implements the DIV i instruction.
 * 
 * Divides the number in the accumulator by the number stored in the register i.
 * All other registers remain unchanged. The program counter is increased by
 * one.
 * 
 * @version 24.02.2013
 * @author Lars Reimann
 */
public class Div implements Instruction {

	/**
	 * The number of the register that contains the divisor.
	 */
	private int i;

	/**
	 * @param i
	 *            The argument of the DIV instruction.
	 */
	public Div(int i) {
		this.i = i;
	}

	@Override
	public void evaluate(Configuration configuration) {
		int m = configuration.getRegister(i);
		int n = configuration.getRegister(0);
		configuration.setRegister(0, n / m);
		configuration.incrementPC();
	}

	@Override
	public String toString() {
		return "DIV " + i;
	}
}
