package net.blaced.programmierung.registermachine;

/**
 * Implements a register machine. It has a configuration and an array of
 * instructions which encodes the program.
 * 
 * @version 24.02.2013
 * @author Lars Reimann
 */
public class Machine {

	/**
	 * The current configuration of the register machine.
	 */
	private Configuration configuration;

	/**
	 * The current program of the register machine.<s
	 */
	private Instruction[] instructions;

	public Machine(Configuration configuration, Instruction[] instructions) {
		this.configuration = configuration;
		this.instructions = instructions;
	}

	/**
	 * @param n
	 *            The number of an instruction.
	 * @return if the instruction at position n is an endpoint of the program.
	 */
	private boolean isEnd(int n) {
		return n < 0 || n >= instructions.length
				|| instructions[n] instanceof End;
	}

	/**
	 * Executed the whole program.
	 */
	public void executeAll() {
		int n = configuration.getPC();
		while (!isEnd(n)) {
			instructions[n].evaluate(configuration);
			n = configuration.getPC();
		}
	}

	/**
	 * Executed the next instruction.
	 */
	public void executeNext() {
		int n = configuration.getPC();
		if (!isEnd(n)) {
			instructions[n].evaluate(configuration);
		}
	}

	/**
	 * @return a description of the current configuration.
	 */
	public String verboseConfiguration() {
		return configuration.toString();
	}

	/**
	 * @return the source code of the whole program.
	 */
	public String verboseAllInstructions() {
		StringBuffer s = new StringBuffer();
		for (int i = 0; i < instructions.length; i++) {
			s.append(instructions[i].toString());
			if (i != instructions.length - 1) {
				s.append("\n");
			}
		}
		return s.toString();
	}

	/**
	 * @return a description of the next instruction.
	 */
	public String verboseNextInstruction() {
		int n = configuration.getPC();
		if (!isEnd(n)) {
			return instructions[n].toString();
		} else {
			return "END";
		}
	}
}
