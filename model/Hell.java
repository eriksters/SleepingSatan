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
	

	public synchronized void enter(Sinner sinner) {
		sinnerQueue.add(sinner);	
		System.out.println(sinner.getName() + ": Some fresh meat has arrived! Sinners in queue: " + sinnerQueue.size());
		while (allHorsemenAreHere() || !satan.isSleeping() || !threeSinnersAreHere()) {
			System.out.println(sinner.getName() + " going to sleepy");
			try {
				wait();
				System.out.println(sinner.getName() + " has been notified!");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(sinner.getName() + " is bout to get whipped!");
		notifyAll();
		whipAndAssign(sinner);
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
		bringOnTheApocalypse(hm);
		notifyAll();
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
	
	private void bringOnTheApocalypse(Horseman hm) {
		satan.setSleeping(false);
		System.out.println("The Apocalypse is upon us!");
		horsemen.clear();
		Scroll.getInstance().ret(hm);
		satan.setSleeping(true);
	}
	
	private void whipAndAssign(Sinner sinner) {
		satan.setSleeping(false);
		sinnerQueue.remove(sinner);
		try {
			Thread.sleep(120);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		satan.setSleeping(true);
	}
	
	
}
