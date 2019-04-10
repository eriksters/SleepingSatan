package model;

public class Horseman implements Runnable {
	
	String name;
	String horse;
	
	Hell hell;
	
	public Horseman(String name, String horse) {
		Thread t = new Thread(this, name);
		
		this.name = name;
		this.horse = horse;
		this.hell = Hell.getInstance();
		
		t.start();
	}
	
	public void run() {
		hell.enter(this);
		
		while (hell.getSatan().isSleeping() && hell.allHorsemenAreHere()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public String getName() {
		return name;
	}
	
}
