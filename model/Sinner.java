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
//		synchronized (hell) {
			hell.enter(this);
//			while (!hell.threeSinnersAreHere()) {
//				System.out.println(t.getName() + " waiting!");
//				sleep();
//			}
//		}
	}
	
//	public synchronized void sleep() {
//		while (circleNumber == 0) {
//			while(!canGo) {
//				try {
//					wait();
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}

	public boolean canGo() {
		return canGo;
	}
	
	public synchronized void getWhipped() {
		System.out.println(t.getName() + " has gotten a whooping!");
		canGo = true;
		circleNumber = 1;
		notifyAll();
	}
	
	public String getName() {
		return t.getName();
	}
	
}
