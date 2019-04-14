package model;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Horseman implements Runnable {
	
	private String name;
	private String horse;
	
	private boolean canGo = false;
	private Lock lock;
	private Condition canGoCondition;
	private Thread t;
	
	private Hell hell;
	private Satan satan;
	
	public Horseman(String name, String horse) {
		t = new Thread(this, name);
		lock = new ReentrantLock();
		canGoCondition = lock.newCondition();
		
		this.name = name;
		this.horse = horse;

		this.hell = Hell.getInstance();
		this.satan = hell.getSatan();
	}
	
	public void run() {
		while(true) {
			while (!canGo) {
				try {
					canGoCondition.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			hell.enter(this);
		}
	}
	
	public void goToHell() {
		canGo = true;
		canGoCondition.signal();
	}
	
	public String getName() {
		return name;
	}
	
	public String getHorse() {
		return horse;
	}
	
}
