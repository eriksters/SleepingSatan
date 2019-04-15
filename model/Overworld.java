package model;

/**
 * The overworld spawns sinners with a random level of sin
 * 
 * @author Eriks Petersons
 *
 */
public class Overworld implements Runnable {
	
	int count = 1;
	Hell hell;
	
	public Overworld() {
		Thread t = new Thread(this, "Overworld");
		hell = Hell.getInstance();
		
		System.out.println("Das lord has made das overworld!");
		
		t.start();
	}
	
	@Override
	public void run() {
		while (true) {
			new Sinner(count);
			try {
				long sleepTime = (long) (Math.random() * 1000);
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			count++;
		
		}
		
	}
}
