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
	
	private static Satan instance;
	private Hell hell;
	
	private Satan() {
		Thread t = new Thread(this, "Satan");
		
		hell = Hell.getInstance();
		
		t.start();
	}
	
	public static Satan getInstance() {
		if (instance == null)
			instance = new Satan();
		
		return instance;
	}
	
	@Override
	public void run() {
		while (true) {
			hell.enter(this);
		}
	}
}
