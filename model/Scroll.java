package model;

public class Scroll {
	
	private Horseman[] horsemen;
	
	private int currentCandidate;
	private boolean fullReset;
	
	private static Scroll instance;
	
	private Scroll() {
		currentCandidate = 0;
		horsemen = new Horseman[4];
		
		Horseman conquest = new Horseman("Conquest", "white", this);
		horsemen[0] = conquest;
		
		Horseman war = new Horseman("War", "fiery red", this);
		horsemen[1] = war;
		
		Horseman famine = new Horseman("Famine", "black", this);
		horsemen[2] = famine;
		
		Horseman death = new Horseman("Death", "pale", this);
		horsemen[3] = death;
		
		fullReset = true;
	}
	
	public static Scroll getInstance() {
		if (instance ==  null)
			instance = new Scroll();
		return instance;
	}
	
	public void ret(Horseman hm) {
		currentCandidate--;
		System.err.println(hm.getName() + " returns to the depths that he came from! Current cand: " + currentCandidate);
		if (currentCandidate == 0) 
			fullReset = true;
		System.err.println("Is openable: " + isOpenable());
	} 
	
	public boolean isOpenable() {
		return (currentCandidate < 4 ? true : false) && fullReset;
	}
	
	public Horseman openSeal() {
//		int hmNumber = currentCandidate;
		int cc = currentCandidate;
		currentCandidate++;
		if (currentCandidate == 4) {
			fullReset = false;
			System.err.println("Setting fullReset to false");
		}
		return horsemen[cc];
	}
	
}
