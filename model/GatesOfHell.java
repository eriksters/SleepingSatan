package model;

import java.util.LinkedList;
import java.util.Queue;

public class GatesOfHell {
	private Queue<Horseman> hmQueue;
	private Hell hell;
	private Scroll scr;
	private int doneRaiding;

	private boolean canGo = false;
	private boolean ready = false;
	
	private Conditions waitCond;
	enum Conditions {
		ALL_HORSEMEN_HERE,
		SATAN_HERE,
		ALL_HORSEMEN_
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
		if (allHere()) {
			System.err.println(h.getName() + " sees that all horsemen have arrived and is going to get their master!");
			hell.wakeSatan();
		}
		
				//	Wait for Satan to start the apocalypse
		while (waitCond != Conditions.SATAN_HERE) {
			try {
				System.err.println(Thread.currentThread().getName() + " is waiting until he can ride!");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		raid();
		System.err.println(Thread.currentThread().getName() + " is going out to cause some havoc!");
		hmQueue.remove(h);
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
	
	public void raid() {
		try {
			if (hmQueue.size() == 0) {
				ready = true;
				notifyAll();
			}
			while (!ready) {
				System.err.println(Thread.currentThread().getName() + " waiting for all horsemen to be ready!");
				wait();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void doneRaiding() {
		doneRaiding++;
	}
	
	public synchronized void go() {
		notifyAll();
	}
	
	public synchronized void enter() {
		doneRaiding = 0;
				
				//	Notify the horsemen that Satan is ready for the apocalypse! 
		canGo = true;
		notifyAll();
		hmQueue.clear();
		
				//	Wait for the apocalypse to be done!
		while (doneRaiding != 4) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.err.println("Apocalypse done. Nice job, bois");
		canGo = false;
		notifyAll();
	}

	
	public boolean allHere() {
		return hmQueue.size() == 4;
	}
}