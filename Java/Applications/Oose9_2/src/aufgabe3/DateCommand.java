package aufgabe3;

import java.util.Date;
import java.util.Random;

public class DateCommand implements Runnable {

	@Override
	public void run() {
		Random ran = new Random();
		for (int i = 0; i < 10; i++) {
			Date date = new Date();
			System.out.println(date.toString());
			try {
				Thread.sleep(ran.nextInt(1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
