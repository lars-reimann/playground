package net.blaced.programmierung.registermachine;

/**
 * Implements the END instruction.
 * 
 * The registers and the program counter remain unchanged.
 * 
 * @version 24.02.2013
 * @author Lars Reimann
 */
public class End implements Instruction {

	@Override
	public void evaluate(Configuration configuration) {
		// End does not change anything
	}

	@Override
	public String toString() {
		return "END";
	}
}
