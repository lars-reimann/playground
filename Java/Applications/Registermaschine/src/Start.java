import net.blaced.programmierung.registermachine.*;


public class Start {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Configuration configuration = new Configuration(10);
		Instruction[] instructions = {
				new CLoad(913),
				new Store(1),
				new CLoad(2),
				new Store(2),
				new Load(1),
				new ZGoto(9), // TODO
				new Sub(2),
				new Store(1),
				new Goto(4),
				new End()
		};
		Machine machine = new Machine(configuration, instructions);
		for (int i = 0; i < 10000; i++) {
			System.out.println(machine.verboseConfiguration());
			System.out.println(machine.verboseNextInstruction());
			machine.executeNext();
		}
	}

}
