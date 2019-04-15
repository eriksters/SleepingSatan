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
				synchronized (this) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			hell.enter(this);
			System.out.println(t.getName() + " has gone to hell and is back!");
			canGo = false;
		}
	}
	
	public synchronized void goToHell() {
		canGo = true;
		notify();
	}
	
	public String getName() {
		return name;
	}
	
	public String getHorse() {
		return horse;
	}
	
}
