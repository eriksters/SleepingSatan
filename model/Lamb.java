package model;

/**
 * Please refer to https://www.biblegateway.com/passage/?search=Revelation+6&version=NIV
 * 
 * @author Eriks Petersons
 *
 */
public class Lamb implements Runnable {
	
	private Scroll scroll;
	private GatesOfHell goh;
	
	public Lamb() {
		Thread t = new Thread(this, "Jesus");
		
		scroll = Scroll.getInstance();
		goh = GatesOfHell.getInstance();
		
		t.start();
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				long sleepTime = (long) ((1 + Math.random()) / 2 *  Settings.getHorsemenRate());
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (scroll.isOpenable()) {
				Horseman hm = scroll.openSeal();
				hm.setGo(true);
				goh.go();
			}
		}
		
	}
}
