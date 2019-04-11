package model;

/**
 * When 3 sinners have arrived, Satan needs to whip them and send them
 * to their appropriate places. 
 * 
 * @author Eriks Petersons
 *
 */
public class Sinner implements Runnable {
	

	Hell hell;
	
	public Sinner() {
		Thread t = new Thread(this, "Sinner");
		
		hell = Hell.getInstance();

	}
	
	public void run() {
		hell.enter(this);

	}
	
}
