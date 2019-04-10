package model;

import java.util.LinkedList;
import java.util.Queue;

public class Hell {
	
	HellCircle[] circles;
	Satan satan;
	Overworld ow;
	
	Queue<Sinner> sinnerQueue;				//Elves
	Queue<Horseman> horsemen;				//Raindeer
	
	static Hell instance;
	
	
	private Hell() {
		ow = new Overworld();
		satan = new Satan();
		sinnerQueue = new LinkedList<>();
		horsemen = new LinkedList<>();
		
	}
	
	public static Hell getInstance() {
		if (instance == null)
			instance = new Hell();
		return instance;
	}
	
	public synchronized void enter(Sinner sinner) {
		sinnerQueue.add(sinner);	
		System.out.println("Some fresh meat has arrived!");
	}
	
	public synchronized void enter(Horseman hm) {
		horsemen.add(hm);
		System.out.println("Horseman " + hm.getName() + " has arrived in hell.");
	}
	
	private void whipAndAssign() {
		for (int i = 0; i < 3; i++) {
			Sinner s = sinnerQueue.poll();
			System.out.println("Sinner " + i + " assigned to circle: ");
		}
	}
	
	
}
