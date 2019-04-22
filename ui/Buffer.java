package ui;

import java.util.LinkedList;
import java.util.Queue;

public class Buffer {
	
	private Queue<UiUpdate> q;
	private static Buffer instance;
	
	private Buffer () {
		q = new LinkedList<>();
	}
	
	public boolean hasNext() {
		return !q.isEmpty();
	}
	
	public synchronized void sleep() {
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized UiUpdate get() {
		return q.poll();
	}
	
	public synchronized void add(UiUpdate update) {
		q.add(update);
		notifyAll();
	}
	
	public synchronized static Buffer getInstance() {
		if (instance == null)
			instance = new Buffer();
		
		return instance;
	}
}
