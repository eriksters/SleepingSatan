package model;

/**
 * The overworld spawns sinners
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
		
		t.start();
	}
	
	@Override
	public void run() {
		while (true) {
			new Sinner(count);
			try {
				long sleepTime = (long) ((1 + Math.random()) / 2 * Settings.getSinnerRate());
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			count++;
		
		}
		
	}
}
