package net.blaced.programmierung.registermachine;

/**
 * Implements the MULT i instruction.
 * 
 * Multiplies the number that is stored in the accumulator with the content of
 * the register i. All other registers remain unchanged. The program counter is
 * increased by one.
 * 
 * @version 24.02.2013
 * @author Lars Reimann
 */
public class Mult implements Instruction {

	/**
	 * The number of the register that contains the summand.
	 */
	private int i;

	/**
	 * @param i
	 *            The argument of the MULT instruction.
	 */
	public Mult(int i) {
		this.i = i;
	}

	@Override
	public void evaluate(Configuration configuration) {
		int m = configuration.getRegister(i);
		int n = configuration.getRegister(0);
		configuration.setRegister(0, m * n);
		configuration.incrementPC();
	}

	@Override
	public String toString() {
		return "MULT " + i;
	}
}
