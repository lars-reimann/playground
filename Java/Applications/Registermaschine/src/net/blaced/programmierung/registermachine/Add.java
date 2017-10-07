package net.blaced.programmierung.registermachine;

/**
 * Implements the ADD i instruction.
 * 
 * Adds the number that is store in some register i to the current value of the
 * accumulator. All other registers remain unchanged. The program counter is
 * increased by one.
 * 
 * @version 24.02.2013
 * @author Lars Reimann
 */
public class Add implements Instruction {

	/**
	 * The number of the register that contains the summand.
	 */
	private int i;

	/**
	 * @param i
	 *            The argument of the ADD instruction.
	 */
	public Add(int i) {
		this.i = i;
	}

	@Override
	public void evaluate(Configuration configuration) {
		int m = configuration.getRegister(i);
		int n = configuration.getRegister(0);
		configuration.setRegister(0, m + n);
		configuration.incrementPC();
	}

	@Override
	public String toString() {
		return "ADD " + i;
	}
}
