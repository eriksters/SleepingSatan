package model;

import java.util.concurrent.locks.Condition;

public class Horseman implements Runnable {
	
	private String name;
	private String horse;
	
	private boolean canGo;
	private Condition fourHorsemennCondition;
	
	private Hell hell;
	private Satan satan;
	
	public Horseman(String name, String horse) {
		Thread t = new Thread(this, name);
		
		this.name = name;
		this.horse = horse;

		this.hell = Hell.getInstance();
		this.satan = hell.getSatan();
		
		t.start();
	}
	
	public void run() {
		hell.enter(this);
		
		while (!canGo) {
			try {
				fourHorsemennCondition.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String getName() {
		return name;
	}
	
	public String getHorse() {
		return horse;
	}
	
}
