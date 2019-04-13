package model;

import java.util.concurrent.locks.Condition;

public class Horseman implements Runnable {
	
	private String name;
	private String horse;
	
	private Condition fourHorsemenCondition;
	private Thread t;
	
	private Hell hell;
	private Satan satan;
	
	public Horseman(String name, String horse) {
		t = new Thread(this, name);
		
		this.name = name;
		this.horse = horse;

		this.hell = Hell.getInstance();
		this.satan = hell.getSatan();
	}
	
	public void run() {
		hell.enter(this);
	}
	
	public void goToHell() {
		t.start();
	}
	
	public String getName() {
		return name;
	}
	
	public String getHorse() {
		return horse;
	}
	
}
