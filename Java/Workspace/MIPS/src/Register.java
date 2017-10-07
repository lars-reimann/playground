/**
 * Lists all 32 registers of a MIPS processor in order. The names of the enum
 * elements correspond to the names of the registers.
 * 
 * @version 12.05.2013
 * @author Lars Reimann
 */
public enum Register {
	
	/**
	 * Constant value zero.
	 */
	$zero,
	
	/**
	 * Assembler temporary.
	 */
	$at,
	
	/**
	 * Registers for function results and expression evaluation.
	 */
	$v0, $v1,
	
	/**
	 * Registers for subroutine arguments.
	 */
	$a0, $a1, $a2, $a3,
	
	/**
	 * Temporary values.
	 */
	$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7,
	
	/**
	 * Saved temporary values.
	 */
	$s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7,
	
	/**
	 * Temporary values.
	 */
	$t8, $t9,
	
	/**
	 * Registers for OS kernel.
	 */
	$k0, $k1,
	
	/**
	 * Global pointer.
	 */
	$gp,
	
	/**
	 * Stack pointer.
	 */
	$sp,
	
	/**
	 * Frame pointer.
	 */
	$fp,
	
	/**
	 * Return address.
	 */
	$ra;
}
