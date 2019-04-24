package model;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import ui.Buffer;
import ui.LocationUpdate;
import ui.SinnerUpdate;

/**
 * Hell is where the sinners arrive when they die. Satan also waits here.
 * 
 * @author eriks
 *
 */
public class Hell {
	
	private static Hell instance;
	
	private HellCircle[] circles;
	
	private boolean wakeSatan = false;
	private GatesOfHell goh;
	private Buffer buffer;
	
	private Queue<Sinner> sinnerQueue;				//Elves
//	private Queue<Horseman> horsemen;				//Raindeer
	
	private Hell() {
		buffer = Buffer.getInstance();
		sinnerQueue = new LinkedList<>();
		goh = GatesOfHell.getInstance();
	}
	
	public static Hell getInstance() {
		if (instance == null)
			instance = new Hell();
		return instance;
	}

	public synchronized void enter(Sinner sinner) {
		sinnerQueue.add(sinner);
		System.out.println(sinner.getName() + " has arrived in the underworld!");
		if (threeSinnersAreHere()) {
			System.out.println(sinner.getName() + " sees that their punishing hour has come!");
			wakeSatan = true;
			notifyAll();
		}
		while (!sinner.canGo()) {
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
				buffer.add(new LocationUpdate(LocationUpdate.IN_BED));
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Satan has been woken!");
		
		if (goh.getWaitCondition() == WaitCondition.ALL_HORSEMEN_HERE) {
			System.out.println("Satan sees that all 4 horsemen of the apocalypse have arrived in hell. He is pleased!");
			goh.enter();
		} else if (threeSinnersAreHere()) {
			System.out.println("Satan sees that 3 sinners have arrived and is pleased that he can get some exercise!");
			whipAndAssign();
		} else {
			System.out.println("Satan sees that no condition has been met and is displeased that some programmer has fucked up!");
		}
		
		wakeSatan = false;
	}
	
	public synchronized void wakeSatan() {
		System.out.println(Thread.currentThread().getName() + " trying to wake Satan!");
		wakeSatan = true;
		notifyAll();
		System.out.println(Thread.currentThread().getName() + " has woken Satan!");
	}
	
	public synchronized boolean threeSinnersAreHere() {
		return sinnerQueue.size() >= 3 ? true : false;
	}
	
	public void whipAndAssign() {
		buffer.add(new LocationUpdate(LocationUpdate.IN_HELL));
		for (Sinner s : sinnerQueue) {
			try {
				Thread.sleep(Settings.getSinnerTime());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			s.whip();
			buffer.add(new SinnerUpdate(s, s.getNumber()));
		}
		sinnerQueue.clear();
		notifyAll();
	}
	
	public void reset() {
		instance = null;
	}
	
	
}
