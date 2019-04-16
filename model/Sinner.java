package model;

import java.util.concurrent.locks.Condition;

/**
 * When 3 sinners have arrived, Satan needs to whip them and send them
 * to their appropriate places. 
 * 
 * @author Eriks Petersons
 *
 */
public class Sinner implements Runnable {
	
	private int circleNumber;
	private Hell hell;
	private Thread t;
	private boolean canGo;
	
	
	public Sinner(int num) {
		t = new Thread(this, "Sinner " + num);
		System.out.println("Sinner number " + num + " has been sent to the abyss!");
		
		hell = Hell.getInstance();
		circleNumber = 0;
		
		t.start();
	}
	
	public Thread getThread() {
		return t;
	}
	
	
	public void run() {
		hell.enter(this);
		System.out.println(t.getName() + " has come and gone!");
	}

	public boolean canGo() {
		return canGo;
	}
	
	public void whip() {
		try {
			Thread.sleep((long) (Math.random() * 300));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		canGo = true;
		circleNumber = 1;
		System.out.println(t.getName() + " has gotten a whooping and has been"
				+ " assigned to circle " + circleNumber);
	}
	
	public String getName() {
		return t.getName();
	}
	
}
