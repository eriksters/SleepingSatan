package model;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;

import ui.Buffer;
import ui.HorsemanUpdate;
import ui.LocationUpdate;

/**
 * Gates of hell is where all the Horsemen of the apocalypse meet up and once they are all here, the last one wakes satan who is sleeping in
 * is Hell. 
 * Enums are used for storing the state of the apocalypse (See model.WaitCondition.java).
 * 
 * @author eriks
 *
 */
public class GatesOfHell {
	private Hell hell;
	private Scroll scr;
	private static GatesOfHell instance;
	 
		//Reset after every apocalypse
	private int raiding;
	private Queue<Horseman> hmQueue;
	private WaitCondition waitCond;
	private Buffer buffer;
	
	private GatesOfHell() {
		hmQueue = new LinkedList<Horseman>();
		buffer = Buffer.getInstance();
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
		buffer.add(new HorsemanUpdate(h, HorsemanUpdate.ENTER));
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
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		raiding++;
		if (raiding == 4) {
			waitCond = WaitCondition.ALL_RAIDING;
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
		buffer.add(new HorsemanUpdate(h, HorsemanUpdate.EXIT));
		if (raiding == 0) {
			System.err.println("All horsemen have returned!");
			waitCond = WaitCondition.ALL_RETURNED;
			notifyAll();
		}
	}
	
			//Satan's execution
	public synchronized void enter() {
		buffer.add(new LocationUpdate(LocationUpdate.AT_GATES));
				
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
			Thread.sleep(Settings.getApocalyposeLength());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		waitCond = WaitCondition.APOC_DONE;
		notifyAll();
		System.err.println("Apocalypse done.");
		
		while (waitCond != WaitCondition.ALL_RETURNED) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
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
	
	public static GatesOfHell getInstance() {
		if (instance == null)
			instance = new GatesOfHell();
		return instance;
	}

	public void setHell(Hell h) {
		hell = h;
	}
	
	public void reset() {
		instance = null;
	}
}
