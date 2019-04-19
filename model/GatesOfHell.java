package model;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;

public class GatesOfHell {
	private Queue<Horseman> hmQueue;
	private Hell hell;
	private Scroll scr;
	private int raiding;

	private Conditions waitCond;
	enum Conditions {
		ALL_HORSEMEN_HERE,
		SATAN_READY,
		ALL_RAIDING,
		APOC_DONE,
		ALL_RETURNED
	}
	
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
			waitCond = Conditions.ALL_HORSEMEN_HERE;
			hell.wakeSatan();
		}
		
				//	Wait for Satan to start the apocalypse
		while (waitCond != Conditions.SATAN_READY) {
			try {
				System.err.println(Thread.currentThread().getName() + " is waiting until he can ride!");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		raiding++;
		if (raiding == 4) {
			waitCond = Conditions.ALL_RAIDING;
			notifyAll();
		}
		
		while (waitCond != Conditions.APOC_DONE) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
//		raid();
//		System.err.println(Thread.currentThread().getName() + " is going out to cause some havoc!");
		
//					Wait for the apocalypse to be over
		
//		while (canGo) {
//			try {
//				System.err.println(Thread.currentThread().getName() + " currently banging earth girls!");
//				wait();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
				//	Return to where he came from
		System.err.println(Thread.currentThread().getName() + " should return");
		Scroll.getInstance().ret(h);
	}
	
//	public void raid() {
//		try {
//			if (hmQueue.size() == 0) {
//				ready = true;
//				notifyAll();
//			}
//			while (!ready) {
//				System.err.println(Thread.currentThread().getName() + " waiting for all horsemen to be ready!");
//				wait();
//			}
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}
	
//	public synchronized void doneRaiding() {
//		doneRaiding++;
//	}
//	
//	public synchronized void go() {
//		notifyAll();
//	}
	
	
	//Satan's execution
	public synchronized void enter() {
		
				
				//	Notify the horsemen that Satan is ready for the apocalypse! 
		waitCond = Conditions.SATAN_READY;
		notifyAll();
		
		while (waitCond != Conditions.ALL_RAIDING) {
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
		waitCond = Conditions.APOC_DONE;
		System.err.println("Apocalypse done. Nice job, bois");
		
		while (waitCond != Conditions.ALL_RETURNED) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.err.println("All horsemen have returned. Resetting things");
		canGo = false;
		raiding = 0;
		hmQueue.clear();
		
		notifyAll();
	}

	public Conditions getWaitCondition() {
		return waitCond;
	}
}
