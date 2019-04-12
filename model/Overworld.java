package model;

/**
 * The overworld spawns sinners with a random level of sin
 * 
 * @author Eriks Petersons
 *
 */
public class Overworld implements Runnable {
	
	int count;
	
	public Overworld() {
		Thread t = new Thread(this, "Overworld");
		
		System.out.println("Das lord has made das overworld!");
		
		//t.start();
	}
	
	@Override
	public void run() {
		
		while (true) {
			new Sinner(count);
			try {
				long sleepTime = (long) (Math.random() * 1000);
				System.out.println("Overworld is sinnning in " + sleepTime + " milisecconds!");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			count++;
		}
		
	}
}
