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
	
	private Lock lock;
	private Condition fourHorsemenCondition;
	private Condition threeSinnersCondition;
	
	private Queue<Sinner> sinnerQueue;				//Elves
	private Queue<Horseman> horsemen;				//Raindeer
	
	private Hell() {
		satan = new Satan(this);
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
	

	public synchronized void enter(Sinner sinner) {
		sinnerQueue.add(sinner);
		if (threeSinnersAreHere()) {
			satan.wake();
		}
		while (!sinner.canGo()) {
			synchronized (sinner) {
					System.out.println("waiting in hell");
					try {
						wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		}
		//satan.wake();
		System.out.println(sinner.getName() + ": Some fresh meat has arrived! Sinners in queue: " + sinnerQueue.size());
	}
	
	public synchronized void enter(Horseman hm) {
		horsemen.add(hm);
		System.out.println("Horseman " + hm.getName() + " rides through the Hell's gates on a " + hm.getHorse() + " horse.");
		while (!satan.isSleeping() || !allHorsemenAreHere()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
//	public synchronized void check() {
//		System.out.println("Performing a nice check of hell!");
//		if (satan.isSleeping() && allHorsemenAreHere()) {
//			System.out.println("Bringing the apocalypse");
//			satan.setSleeping(false);
//			fourHorsemenCondition.signal();
//		} else if (satan.isSleeping() && threeSinnersAreHere()) {
//			System.out.println("Whipping some rapists and murderers!");
//			satan.setSleeping(false);
//			threeSinnersCondition.signal();
//		}
//	}
	
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
		for (int i = 0; i < 4; i++) {
			Horseman hm = horsemen.poll();
		}
	}
	
	public synchronized void whipAndAssign() {
		for (int i = 0; i < 3; i++) {
			Sinner s = sinnerQueue.poll();
			System.out.println(s.getName() + " has been chosen");
			synchronized (s) {
				System.out.println("Whipping " + s.getName());
				s.getWhipped();
				s.notify();
			}
		}
	}
	
	
}
