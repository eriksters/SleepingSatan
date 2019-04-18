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
	private GatesOfHell goh;
	
	public Lamb() {
		Thread t = new Thread(this, "Jesus");
		
		hell = Hell.getInstance();
		scroll = Scroll.getInstance();
		goh = hell.getGates();
		
		t.start();
	}
	
	@Override
	public void run() {
		while (true) {
			if (scroll.isOpenable()) {
				Horseman hm = scroll.openSeal();
				hm.setGo(true);
				goh.go();
			}
			try {
				long sleepTime = (long) ((Math.random() + 0.3) * 3000);
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}
