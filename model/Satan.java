package model;

/**
 * Satan sleeps until either:
 * 1. All 4 horseman arrive back from their reids in heaven
 * 2. 3 sinners have arrived and need some whipping and punishing
 * 
 * 
 * @author Eriks Petersons
 *
 */

public class Satan implements Runnable {
	

	private Hell hell;
	
	public Satan(Hell h) {
		Thread t = new Thread(this, "Satan");
		
		hell = h;
		
		t.start();
	}
	
	@Override
	public void run() {
		while (true) {
			hell.enter(this);
		}
	}
}
