package model;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Horseman implements Runnable {
	
	private String name;
	private String horse;
	
	private boolean canGo = false;
//	private Lock lock;
//	private Condition canGoCondition;
	private Thread t;
	
	private Hell hell;
	private GatesOfHell goh;
	
	public Horseman(String name, String horse) {
		t = new Thread(this, name);
//		lock = new ReentrantLock();
//		canGoCondition = lock.newCondition();
		
		this.name = name;
		this.horse = horse;

		this.hell = Hell.getInstance();
		goh = hell.getGates();
	}
	
	public void run() {
		while(true) {
			System.err.println(getName() + " has received the order to gtfo");
			goh.enter(this);
			canGo = false;
		}
	}
	
	public boolean canGo() {
		return canGo;
	}
	
	public Thread getThread() {
		return t;
	}
	
	public String getName() {
		return name;
	}
	
	public String getHorse() {
		return horse;
	}
	
}
