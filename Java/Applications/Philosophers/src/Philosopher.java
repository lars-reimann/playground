
public class Philosopher implements Runnable {
	private String name;
	private Fork leftFork;
	private Fork rightFork;
	
	public Philosopher(String name, Fork leftFork, Fork rightFork) {
		this.name = name;
		this.leftFork = leftFork;
		this.rightFork = rightFork;
	}
	
	private void eat() {
		System.out.println(name + " hat Hunger.");
		leftFork.take(name);
		rightFork.take(name);
		System.out.println(name + " isst...");
		try {
			Thread.sleep((long) (Math.random() * 10));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		leftFork.putBack(name);
		rightFork.putBack(name);
	}
	
	private void think() {
		System.out.println(name + " denkt nach...");
		try {
			Thread.sleep((long) (Math.random() * 10));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while (true) {
			think();
			eat();
		}
	}
}
