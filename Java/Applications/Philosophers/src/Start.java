
public class Start {

	public static void main(String[] args) {
		Fork[] forks = new Fork[5];
		for (int i = 0; i < 5; i++) {
			forks[i] = new Fork(i);
		}
		
		Thread wiltrud = new Thread(new Philosopher("Wiltrud", forks[4], forks[0]));
		Thread heinz = new Thread(new Philosopher("Heinz", forks[0], forks[1]));
		Thread lars = new Thread(new Philosopher("Lars", forks[1], forks[2]));
		Thread maxwell = new Thread(new Philosopher("Maxwell", forks[2], forks[3]));
		Thread sokrates = new Thread(new Philosopher("Sokrates", forks[3], forks[4]));
		
		wiltrud.start();
		heinz.start();
		lars.start();
		maxwell.start();
		sokrates.start();
	}
}
 