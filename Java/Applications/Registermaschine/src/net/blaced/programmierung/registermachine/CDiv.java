package net.blaced.programmierung.registermachine;

/**
 * Implements the CDIV i instruction.
 * 
 * Divides the current value of the accumulator by i. All other registers remain
 * unchanged. The program counter is increased by one.
 * 
 * @version 24.02.2013
 * @author Lars Reimann
 */
public class CDiv implements Instruction {

	/**
	 * The divisor.
	 */
	private int i;

	/**
	 * @param i
	 *            The argument of the CDIV instruction.
	 */
	public CDiv(int i) {
		this.i = i;
	}

	@Override
	public void evaluate(Configuration configuration) {
		int n = configuration.getRegister(0);
		configuration.setRegister(0, n / i);
		configuration.incrementPC();
	}

	@Override
	public String toString() {
		return "CDIV " + i;
	}

}
