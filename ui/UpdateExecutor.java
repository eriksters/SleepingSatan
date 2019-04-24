package ui;


/**
 * Class/Thread for executing GUI updates.
 * 
 * @author eriks
 *
 */
public class UpdateExecutor implements Runnable{

	Buffer buffer;
	Thread t;
	
	public UpdateExecutor() {
		t = new Thread(this, "Update Executor");
		buffer = Buffer.getInstance();
		
		t.start();
	}
	
	@Override
	public void run() {
		Main.getInstance().testMethod();
		while (true) {
			if (buffer.hasNext()) {
				UiUpdate update = buffer.get();
				update.execute();
			} else {
				buffer.sleep();
			}
		}
	}

}
