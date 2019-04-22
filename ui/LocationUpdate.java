package ui;

import java.util.Random;

public class LocationUpdate extends UiUpdate {
	
	private int locationIndex;			//0 = bed, 1 = gates of hell, 2 = hell
	private Main main;
	
	private SatanPanel[] panels;
	
	public static final int IN_BED = 0;
	public static final int AT_GATES = 1;
	public static final int IN_HELL = 2;
	
	
	
	public LocationUpdate(int locationIndex) {
		this.locationIndex = locationIndex;
		
		main = Main.getInstance();
		panels = main.getSatanPanels();
	}

	@Override
	public void execute() {
		for (int i = 0; i < panels.length; i++) {
			panels[i].setCurrent(SatanPanel.BLANK);
		}
		panels[locationIndex].setCurrent(SatanPanel.SATAN);
		for (int i = 0; i < panels.length; i++) {
			panels[i].repaint();
		}
	}
	
	
}
