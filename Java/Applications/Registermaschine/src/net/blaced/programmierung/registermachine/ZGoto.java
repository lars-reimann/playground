package net.blaced.programmierung.registermachine;

/**
 * Implements the ZGOTO i instruction.
 * 
 * If the accumulator is 0, the program counter is set to i. Otherwise it is
 * increased by one. All other registers remain unchanged.
 * 
 * @version 24.02.2013
 * @author Lars Reimann
 */
public class ZGoto implements Instruction {

	/**
	 * The number of the next instruction, if the accumulator contains zero.
	 */
	private int i;

	/**
	 * @param i
	 *            The argument of the ZGOTO instruction.
	 */
	public ZGoto(int i) {
		this.i = i;
	}

	@Override
	public void evaluate(Configuration configuration) {
		if (configuration.getRegister(0) == 0) {
			configuration.setPC(i);
		} else {
			configuration.incrementPC();
		}
	}

	@Override
	public String toString() {
		return "ZGOTO " + i;
	}
}