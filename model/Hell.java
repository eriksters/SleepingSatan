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
	
	private boolean wakeSatan = false;
	private GatesOfHell goh;
	
//	private Lock lock;
//	private Condition fourHorsemenCondition;
//	private Condition threeSinnersCondition;
	
	private Queue<Sinner> sinnerQueue;				//Elves
//	private Queue<Horseman> horsemen;				//Raindeer
	
	private Hell() {
		satan = new Satan(this);
		sinnerQueue = new LinkedList<>();
//		horsemen = new LinkedList<>(); 
		goh = new GatesOfHell(this);
		
		System.out.println("Hell generatedd");	
	}
	
	public static Hell getInstance() {
		if (instance == null)
			instance = new Hell();
		return instance;
	}
	
	public GatesOfHell getGates() {
		return goh;
	}
	
//	public Condition getThreeSinnersCondition() {
//		return threeSinnersCondition;
//	}
//	
//	public Condition getFourHorsemenCondition() {
//		return fourHorsemenCondition;
//	}
	

	public synchronized void enter(Sinner sinner) {
		sinnerQueue.add(sinner);
		System.out.println(sinner.getName() + " has arrived in the underworld!");
		if (threeSinnersAreHere()) {
			System.out.println(sinner.getName() + " sees that their punishing hour has come!");
			wakeSatan = true;
			notifyAll();
		}
		while (!sinner.canGo()) {
//			System.out.println(sinner.getName() + " waiting in hell");
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public synchronized void enter(Satan satan) {
		while (!wakeSatan) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Satan has been woken!");
		
		if (goh.allHere()) {
			System.out.println("Satan sees that all 4 horsemen of the apocalypse have arrived in hell. He is pleased!");
			goh.bringOnTheApocalypse();
		} else if (threeSinnersAreHere()) {
			System.out.println("Satan sees that 3 sinners have arrived and is pleased that he can get some exercise!");
			whipAndAssign();
		} else {
			System.out.println("Satan sees that no condition has been met and is displeased that some programmer has fucked up!");
		}
		
		wakeSatan = false;
	}
	
	public synchronized void wakeSatan() {
		wakeSatan = true;
		notifyAll();
	}
	
//	public synchronized void enter(Horseman hm) {
//		horsemen.add(hm);
//		System.out.println("Horseman " + hm.getName() + " rides through the Hell's gates on a " + hm.getHorse() + " horse.");
//		if (allHorsemenAreHere()) {
//			wakeSatan = true;
//			notifyAll();
//		}
//		while (!canRaid) {
//			try {
//				wait();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//		System.out.println(hm.getName() + " has been released for the apocalypse");
//	}
	
	public boolean threeSinnersAreHere() {
		return sinnerQueue.size() >= 3 ? true : false;
	}
	
	public Satan getSatan() {
		return satan;
	}
	
//	public void bringOnTheApocalypse() {
//		for (int i = 0; i < 4; i++) {
//			Horseman hm = horsemen.poll();
//		}
//	}
	
	public void whipAndAssign() {
		for (Sinner s : sinnerQueue) {
			s.whip();
		}
		sinnerQueue.clear();
		notifyAll();
	}
	
	
}
