package net.blaced.programmierung.registermachine;

/**
 * This realizes a configuration of a register machine. It has a program counter
 * which stores the number of the next instruction. It also contains several
 * registers, one of which is the accumulator. However, it does not have an
 * infinite amount of registers, but the amount of registers needed has to be
 * specified beforehand.
 * 
 * @version 24.02.2013
 * @author Lars Reimann
 */
public final class Configuration {

	/**
	 * This is the program counter. It indicated which instruction is to be
	 * executed next.
	 */
	private int pc;

	/**
	 * The values stored in the registers. The register at position 0 in the
	 * array is the accumulator.
	 */
	private int[] registers;

	/**
	 * Construct a new configuration with a program counter and n registers.
	 * 
	 * @param n
	 *            The amount of registers the machine should have.
	 */
	public Configuration(int n) {
		registers = new int[n];
		init();
	}

	/**
	 * Sets the program counter and all registers to zero.
	 */
	public void init() {
		pc = 0;
		for (int i = 0; i < registers.length; i++) {
			registers[i] = 0;
		}
	}

	/**
	 * Increases the program counter by one.
	 */
	public void incrementPC() {
		pc++;
	}

	/**
	 * This way jumps can be realized.
	 * 
	 * @param n
	 *            the new value of the program counter.
	 */
	public void setPC(int n) {
		pc = n;
	}

	/**
	 * @return the number stored in the program counter.
	 */
	public int getPC() {
		return pc;
	}

	/**
	 * @param reg
	 *            The number of the register to be set.
	 * @param n
	 *            The value which should be stored in the register.
	 */
	public void setRegister(int reg, int n) {
		registers[reg] = n;
	}

	/**
	 * @param reg
	 *            The number of the register to be returned.
	 * @return the content of the register reg.
	 */
	public int getRegister(int reg) {
		return registers[reg];
	}

	@Override
	public String toString() {
		StringBuffer s = new StringBuffer();
		s.append("(" + pc);
		for (int i = 0; i < registers.length; i++) {
			s.append(", " + registers[i]);
		}
		s.append(")");
		return s.toString();
	}
}
