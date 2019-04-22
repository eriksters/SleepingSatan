package model;

public class Scroll {
	
	private Horseman[] horsemen;
	
	private int currentCandidate;
	private boolean fullReset;
	
	private static Scroll instance;
	
	private Scroll() {
		currentCandidate = 0;
		horsemen = new Horseman[4];
		generateHorsemen();
	}
	
	public void generateHorsemen() {
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
	
	public  synchronized void reset() {
		for (Horseman hm : horsemen) {
			hm.setGo(false);
		}
		currentCandidate = 0;
		fullReset = true;
	}
	
	public boolean isOpenable() {
		return (currentCandidate < 4 ? true : false) && fullReset;
	}
	
	public Horseman openSeal() {
		int cc = currentCandidate;
		currentCandidate++;
		if (currentCandidate == 4) {
			fullReset = false;
		}
		return horsemen[cc];
	}
	
}
