package ui;

import javax.swing.JProgressBar;

import model.Horseman;

public class HorsemanUpdate extends UiUpdate{
	
	private Horseman hm;
	private int updateType;
	private JProgressBar pBar;
	private Main main;
	
	public static final int EXIT = 0;
	public static final int ENTER = 1;
	
	public HorsemanUpdate(Horseman hm, int type) {
		main = Main.getInstance();
		pBar = main.getBar();
		this.hm = hm;
		this.updateType = type;
	}

	@Override
	public void execute() {
		if (updateType == EXIT) {
			pBar.setValue(pBar.getValue() - 1);
		} else if (updateType == ENTER) {
			pBar.setValue(pBar.getValue() + 1);
		}
	}
}
