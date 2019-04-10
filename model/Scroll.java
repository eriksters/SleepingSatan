package model;

public class Scroll {
	
	Horseman[] horsemen;
	int currentCandidate;
	
	public Scroll() {
		currentCandidate = 0;
		
		Horseman conquest = new Horseman("Death", "white");
		horsemen[0] = conquest;
		
		Horseman war = new Horseman("War", "fiery red");
		horsemen[1] = war;
		
		Horseman famine = new Horseman("Famine", "black");
		horsemen[2] = famine;
		
		Horseman death = new Horseman("Death", "pale");
		horsemen[3] = death;
	}
	
	public boolean isOpenable() {
		return currentCandidate < 4 ? true : false;
	}
	
	public Horseman openSeal() {
//		int hmNumber = currentCandidate;
//		currentCandidate++;
		return horsemen[currentCandidate++];
	}
	
}
