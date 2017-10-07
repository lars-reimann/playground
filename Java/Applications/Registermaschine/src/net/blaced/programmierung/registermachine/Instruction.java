package net.blaced.programmierung.registermachine;

/**
 * @version 24.02.2013
 * @author Lars Reimann
 */
public interface Instruction {

	/**
	 * Executes the instruction using the given configuration.
	 * 
	 * @param configuration
	 *            The current configuration of the register machine.
	 */
	void evaluate(Configuration configuration);
}
