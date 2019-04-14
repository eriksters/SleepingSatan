package model;

/**
 * The lamb opens the seals on the scroll every once in a while.
 * Every opened seal spawns a horseman
 * 
 * @author Eriks Petersons
 *
 */
public class Lamb implements Runnable {
	
	private Scroll scroll;
	private Hell hell;
	
	public Lamb() {
		Thread t = new Thread(this, "Jesus");
		
		hell = Hell.getInstance();
		scroll = Scroll.getInstance();
		
		t.start();
	}
	
	@Override
	public void run() {
		while (true) {
			if (scroll.isOpenable()) {
				Horseman hm = scroll.openSeal();
				//hm.goToHell();
				//System.out.println(hm.getName() + " rides on a " + hm.getHorse() + " horse.");
				//hm.run();
			}
			try {
				long sleepTime = (long) ((Math.random() + 0.3) * 3000);
				//System.out.println("Lamb is doing irresponsible things in " + sleepTime + " milliseconds!");
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}
