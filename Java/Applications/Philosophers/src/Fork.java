
public class Fork {
	private boolean isFree = true;
	private final int number;
	
	public Fork(int number) {
		this.number = number;
	}
	
	public synchronized void take(String name) {
		while (!isFree) {
			try {
				wait();
				System.out.println(name + " muss auf Gabel " + number + " warten.");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		isFree = false;
		System.out.println(name + " nimmt Gabel " + number + ".");
	}
	
	public synchronized void putBack(String name) {
		isFree = true;
		System.out.println(name + " legt Gabel " + number + " zurück.");
		notify();
	}
}
