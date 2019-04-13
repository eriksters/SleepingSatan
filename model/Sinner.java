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
	
	int number;
	Hell hell;
	Satan satan;
	Condition threeSinnersCondition;
	Thread t;
	
	public Sinner(int num) {
		t = new Thread(this, "Sinner" + num);
		
		hell = Hell.getInstance();
		satan = hell.getSatan();
		
		threeSinnersCondition = hell.getThreeSinnersCondition();
		
		t.start();
	}
	
	public void run() {
		hell.enter(this);
		while (!satan.isSleeping() || hell.allHorsemenAreHere()) {
			try {
				synchronized (hell) {
					threeSinnersCondition.await();
					System.out.println("Continuing from three Sinnders condition");
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} 	
	}
	
	public String getName() {
		return t.getName();
	}
	
}
