package net.blaced.programmierung.registermachine;

/**
 * Implements the CMULT i instruction.
 * 
 * Multiplies the current value of the accumulator with i. All other registers
 * remain unchanged. The program counter is increased by one.
 * 
 * @version 24.02.2013
 * @author Lars Reimann
 */
public class CMult implements Instruction {

	/**
	 * The factor.
	 */
	private int i;

	/**
	 * @param i
	 *            The argument of the CMult instruction.
	 */
	public CMult(int i) {
		this.i = i;
	}

	@Override
	public void evaluate(Configuration configuration) {
		int n = configuration.getRegister(0);
		configuration.setRegister(0, i * n);
		configuration.incrementPC();
	}

	@Override
	public String toString() {
		return "CMULT " + i;
	}
}
