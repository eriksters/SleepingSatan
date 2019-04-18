package model;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Horseman implements Runnable {
	
	private String name;
	private String horse;
	
	private boolean canGo = false;
	private Thread t;
	
	private Hell hell;
	private GatesOfHell goh;
	
	public Horseman(String name, String horse, Scroll sc) {
		t = new Thread(this, name);
//		lock = new ReentrantLock();
//		canGoCondition = lock.newCondition();
		
		this.name = name;
		this.horse = horse;

		this.hell = Hell.getInstance();
		goh = hell.getGates();
		
		t.start();
	}
	
	public void run() {
		while(true) {
			canGo = false;
			goh.enter(this);
		}
	}
	
	public void setGo(boolean b) {
		canGo = b;
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
