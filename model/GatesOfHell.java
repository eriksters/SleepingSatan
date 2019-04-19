package model;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;

public class GatesOfHell {
	private Hell hell;
	private Scroll scr;
	
		//Reset after every apocalypse
	private int raiding;
	private Queue<Horseman> hmQueue;
	private WaitCondition waitCond;
	
	public GatesOfHell(Hell h) {
		hmQueue = new LinkedList<Horseman>();
		hell = h;
		waitCond = null;
	}
	
	public synchronized void enter(Horseman h) {
		
		
				//	Wait for the scroll to be opened!
		while(!h.canGo()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		hmQueue.add(h);
		System.err.println(h.getName() + " rides through the gates of hell on a " + h.getHorse() + " horse!");

				//	Check if all the horsemen are here.
				//	If they are, Satan is woken!
		if (hmQueue.size() == 4) {
			System.err.println(h.getName() + " sees that all horsemen have arrived and is going to get their master!");
			waitCond = WaitCondition.ALL_HORSEMEN_HERE;
			hell.wakeSatan();
		}
		
				//	Wait for Satan to start the apocalypse
		while (waitCond != WaitCondition.SATAN_READY) {
			try {
				System.err.println(Thread.currentThread().getName() + " is waiting until he can ride!");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.err.println(Thread.currentThread().getName() + " has began the shite");
		raiding++;
		if (raiding == 4) {
			waitCond = WaitCondition.ALL_RAIDING;
			System.err.println("Wait condition updated to " + waitCond);
			notifyAll();
		}
		
		while (waitCond != WaitCondition.APOC_DONE) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		raiding--;
		if (raiding == 0) {
			System.err.println("All horsemen have returned!");
			waitCond = WaitCondition.ALL_RETURNED;
			notifyAll();
		}
	}
	
	//Satan's execution
	public synchronized void enter() {
		
				
				//	Notify the horsemen that Satan is ready for the apocalypse! 
		waitCond = WaitCondition.SATAN_READY;
		notifyAll();
		
		while (waitCond != WaitCondition.ALL_RAIDING) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
		try {
			Thread.sleep((long) (Math.random() * 3000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		waitCond = WaitCondition.APOC_DONE;
		notifyAll();
		System.err.println("Apocalypse done. Nice job, bois");
		
		while (waitCond != WaitCondition.ALL_RETURNED) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.err.println("All horsemen have returned. Resetting things");
		
		hmQueue.clear();
		waitCond = null;
		
				//Test condition. Find a better way to do this TODO
		scr = Scroll.getInstance();
		scr.reset();
		
		notifyAll();
	}

	public WaitCondition getWaitCondition() {
		return waitCond;
	}
	
	public synchronized void go() {
		notifyAll();
	}
}
