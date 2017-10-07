package aufgabe3;

import java.util.Random;

public class CounterCommand implements Runnable {

	@Override
	public void run() {
		Random ran = new Random();
		for (int i = 0; i < 10; i++) {
			System.out.println(i);
			try {
				Thread.sleep(ran.nextInt(1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
