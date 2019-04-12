package model;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Hell {
	
	private static Hell instance;
	
	private HellCircle[] circles;
	private Satan satan;							//Santa
	private Overworld ow;
	
	private Lock lock;
	private Condition fourHorsemenCondition;
	private Condition threeSinnersCondition;
	
	private Queue<Sinner> sinnerQueue;				//Elves
	private Queue<Horseman> horsemen;				//Raindeer
	
	private Hell() {
		ow = new Overworld();
		satan = new Satan();
		sinnerQueue = new LinkedList<>();
		horsemen = new LinkedList<>(); 
		
		lock = new ReentrantLock();
		
		threeSinnersCondition = lock.newCondition();
		fourHorsemenCondition = lock.newCondition();
		
	}
	
	public static Hell getInstance() {
		if (instance == null)
			instance = new Hell();
		return instance;
	}
	
	public Condition getThreeSinnersCondition() {
		return threeSinnersCondition;
	}
	
	public Condition getFourHorsemenCondition() {
		return fourHorsemenCondition;
	}
	

	public void enter(Sinner sinner) {
		sinnerQueue.add(sinner);	
		System.out.println("Some fresh meat has arrived! Sinners in queue: " + sinnerQueue.size());
	}
	
	public void enter(Horseman hm) {
		horsemen.add(hm);
		System.out.println("Horseman " + hm.getName() + " rides through the Hell's gates on a " + hm.getHorse() + " horse.");
	}
	
	private synchronized void check() {
		if (satan.isSleeping() && allHorsemenAreHere()) {
			fourHorsemenCondition.signal();
		} else if (satan.isSleeping()) {
			
		}
	}
	
	public boolean allHorsemenAreHere() {
		return horsemen.size() == 4 ? true : false;
	}
	
	public boolean threeSinnersAreHere() {
		return sinnerQueue.size() >= 3 ? true : false;
	}
	
	public Satan getSatan() {
		return satan;
	}
	
	public void bringOnTheApocalypse() {
		horsemen.clear();
		System.out.println("The Apocalypse is upon us!");

	}
	
	private void whipAndAssign() {
		for (int i = 0; i < 3; i++) {
			Sinner s = sinnerQueue.poll();
			System.out.println("Sinner " + i + " assigned to circle: ");
		}
	}
	
	
}
