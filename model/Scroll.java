package model;

public class Scroll {
	
	private Horseman[] horsemen;
	
	private int currentCandidate;
	private boolean fullReset;
	
	private static Scroll instance;
	
	private Scroll() {
		currentCandidate = 0;
		horsemen = new Horseman[4];
		
		Horseman conquest = new Horseman("Conquest", "white");
		horsemen[0] = conquest;
		
		Horseman war = new Horseman("War", "fiery red");
		horsemen[1] = war;
		
		Horseman famine = new Horseman("Famine", "black");
		horsemen[2] = famine;
		
		Horseman death = new Horseman("Death", "pale");
		horsemen[3] = death;
		
		fullReset = true;
	}
	
	public static Scroll getInstance() {
		if (instance ==  null)
			instance = new Scroll();
		return instance;
	}
	
	public void ret(Horseman hm) {
		System.out.println(hm.getName() + " returns to the depths that he came from!");
		currentCandidate--;
		if (currentCandidate == 0) 
			fullReset = true;
		
	} 
	
	public boolean isOpenable() {
		return (currentCandidate < 4 ? true : false) && fullReset;
	}
	
	public Horseman openSeal() {
//		int hmNumber = currentCandidate;
//		currentCandidate++;
		if (currentCandidate == 4)
			fullReset = false;
		return horsemen[currentCandidate++];
	}
	
}
