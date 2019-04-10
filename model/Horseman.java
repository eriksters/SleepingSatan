package model;

public class Horseman implements Runnable {
	
	String name;
	String quote;
	
	Hell hell;
	
	public Horseman(String name, String quote) {
		Thread t = new Thread(this, name);
		
		this.name = name;
		this.quote = quote;
		this.hell = Hell.getInstance();
		
		t.start();
	}
	
	public void run() {
		
	}
	
	public String getName() {
		return name;
	}
	
}
