public class Parser {
	
	private final Register[] registers = Register.values();

	private String parseFRFormat(String s) {
		
	}
	
	private String parseFIFormat(String s) {
		
	}
	
	private String parseIFormat(String instruction) {
		String opcodeBin = instruction.substring(0, 6);
		String rsBin = instruction.substring(6, 11);
		String rtBin = instruction.substring(11, 16);
		String immediateBin = instruction.substring(16);
		
		Register rs = registers[Integer.parseInt(rsBin, 1)];
		Register rt = registers[Integer.parseInt(rtBin, 1)];
		int immediate = Integer.parseInt(immediateBin, 2);
		
		switch (opcodeBin) {
		case "000100": // beq (branch on equal)
			return "beq " + rt + ", " + rs + ", " + immediate;
		case "000101": // bne (branch on not equal)
			return "bne " + rt + ", " + rs + ", " + immediate;
		case "000110": // blez (branch on less than equal zero)
			return "blez " + rs + ", " + immediate;
		case "000111": // bgtz (branch on greater than zero)
			return "bgtz " + rs + ", " + immediate;
		case "001000": // addi (addition immediate)
			return "addi " + rt + ", " + rs + ", " + immediate;
		case "001001": // addiu (addition immediate unsigned)
			return "addiu " + rt + ", " + rs + ", " + immediate;
		case "001010": // slti (set less than immediate)
			return "slti " + rt + ", " + rs + ", " + immediate;
		case "001011": // sltiu (set less than immediate unsigned)
			return "sltiu " + rt + ", " + rs + ", " + immediate;
		case "001100": // andi (and immediate)
			return "andi " + rt + ", " + rs + ", " + immediate;
		case "001101": // ori (or immediate)
			return "ori " + rt + ", " + rs + ", " + immediate;
		case "001110": // xori (xor immediate)
			return "xori " + rt + ", " + rs + ", " + immediate;
		case "001111": // lui (load upper immediate)
			return "beq " + rt + ", " + immediate;
		case "010100": // beql (branch on equal likely)
			return "beql " + rt + ", " + rs + ", " + immediate;
		case "010101": // bnel branch on not equal likely)
			return "bnel " + rt + ", " + rs + ", " + immediate;
		case "010110": // blezl (branch on less than equal zero likely)
			return "blezl " + rs + ", " + immediate;
		case "010111": // bgtzl (branch on greater than zero likely)
			return "bgtzl " + rs + ", " + immediate;
		case "100000": // lb
		case "100001": // lh
		case "100010": // lwl
		case "100011": // lw
		case "100100": // lbu
		case "100101": // lhu
		case "100110": // lwr
		case "101000": // sb
		case "101001": // sh
		case "101010": // swl
		case "101011": // sw
		case "101110": // swr TODO cache is missing
		case "110000": // ll
		case "110001": // lwc1
		case "110010": // lwc2 TODO pref is missing
		case "110101": // ldc1
		case "110110": // ldc2
		case "111000": // sc
		case "111001": // swc1
		case "111010": // swc2
		case "111101": // sdc1
		case "111110": // sdc2
			return parseIFormat(instruction);
		}
	}
	
	private String parseRFormat(String s) {
		return null;
	}
	
	private String parseJFormat(String s) {
		return null;
	}
	
	private String parseInstruction(String instruction) {
		String opcode = instruction.substring(0, 6);
		switch (opcode) {
		case "000000":
			return parseOpcode0(instruction);
		case "000001":
			return parseOpcode1(instruction);
		case "000010": // j
		case "000011": // jal
			return parseJFormat(instruction);
		case "000100": // beq
		case "000101": // bne
		case "000110": // blez
		case "000111": // bgtz
		case "001000": // addi
		case "001001": // addiu
		case "001010": // slti
		case "001011": // sltiu
		case "001100": // andi
		case "001101": // ori
		case "001110": // xori
		case "001111": // lui
		case "010100": // beql
		case "010101": // bnel
		case "010110": // blezl
		case "010111": // bgtzl
		case "100000": // lb
		case "100001": // lh
		case "100010": // lwl
		case "100011": // lw
		case "100100": // lbu
		case "100101": // lhu
		case "100110": // lwr
		case "101000": // sb
		case "101001": // sh
		case "101010": // swl
		case "101011": // sw
		case "101110": // swr TODO cache is missing
		case "110000": // ll
		case "110001": // lwc1
		case "110010": // lwc2 TODO pref is missing
		case "110101": // ldc1
		case "110110": // ldc2
		case "111000": // sc
		case "111001": // swc1
		case "111010": // swc2
		case "111101": // sdc1
		case "111110": // sdc2
			return parseIFormat(instruction);
		case "010000": // z=0
			return parseOpcode16(instruction);
		case "010001": // z=1
			return parseOpcode17(instruction);
		case "010010": // z=2
			return parseOpcode18(instruction);
		case "010011": // z=3
			return parseOpcode19(instruction);
		case "011000":
		case "011001":
		case "011010":
		case "011011":
		case "011100":
		case "011101":
		case "011110":
		case "011111":
			return parseOpcodes24To31(instruction);
		default:
			throw new IllegalArgumentException(opcode + " not implemented.");
		}
	}
	
	public void disassemble(String filepath) {
		
	}

	private String parseOpcode19(String instruction) {
		// TODO Auto-generated method stub
		
	}

	private String parseOpcode18(String instruction) {
		// TODO Auto-generated method stub
		
	}

	private String parseOpcode17(String instruction) {
		// TODO Auto-generated method stub
		
	}

	private String parseOpcode16(String instruction) {
		// TODO Auto-generated method stub
		
	}

	private String parseOpcode19(String instruction) {
		// TODO Auto-generated method stub
		
	}

	private String parseOpcodes24To31(String instruction) {
		// TODO Auto-generated method stub
		
	}

	private String parseOpcode1(String instruction) {
		// TODO Auto-generated method stub
		
	}

	private String parseOpcode0(String instruction) {
		// TODO Auto-generated method stub
		
	}
}
