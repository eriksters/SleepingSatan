package model;

/**
 * When 3 sinners have arrived, Satan needs to whip them and send them
 * to their appropriate places. 
 * 
 * @author Eriks Petersons
 *
 */
public class Sinner implements Runnable {
	
	public Sinner() {
		Thread t = new Thread(this, "Sinner");
		
		t.start();
	}
	
	public void run() {
		
	}
	
}
