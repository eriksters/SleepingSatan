package ui;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A buffer that employs a queue. Since the point of the GUI running in a sepperate thread is to minimize time the sinner, Satan and Horsemen threads have to
 * spend on the GUI, a circular buffer can not be used because it would cause slow downs when the threads need to wait a spot to be cleared.
 * 
 * @author eriks
 *
 */
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
