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
	

	private boolean isSleeping;
	private boolean isHelping;
	
	public Satan() {
		Thread t = new Thread(this, "Satan");
		
		isSleeping = true;
		
		t.start();
		
	}
	
	@Override
	public void run() {
		while (true) {
			
		}
	}

	public void setSleeping(boolean b) {
		isSleeping = b;
	}
	
	public void setHelping(boolean b) {
		isHelping = b;
	}
	
	public boolean isSleeping() {
		return isSleeping;

	}
	
	public boolean isHelping() {
		return isHelping;
	}
}
