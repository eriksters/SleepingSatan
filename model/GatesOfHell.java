package model;

import java.util.LinkedList;
import java.util.Queue;

public class GatesOfHell {
	private boolean canGo;
	private Queue<Horseman> hmQueue;
	private Hell hell;
	
	public GatesOfHell(Hell h) {
		hmQueue = new LinkedList<Horseman>();
		hell = h;
	}
	
	public synchronized void enter(Horseman h) {
		while(!h.canGo()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.err.println(h.getName() + " rides through the gates of hell on a " + h.getHorse() + " horse!");
		if (allHere()) {
			hell.wakeSatan();
			System.err.println(h.getName() + " has woken satan!");
		}
		while (!canGo) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public synchronized void go() {
		notifyAll();
	}
	
	public void bringOnTheApocalypse() {
		canGo = true;
		notifyAll();
	}
	
	public void clear() {
		hmQueue.clear();
	}
	
	public boolean allHere() {
		return hmQueue.size() == 4;
	}
}
