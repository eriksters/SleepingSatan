package ui;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;

import model.Sinner;

/**
 * A GUI update for sinners
 * 
 * @author eriks
 *
 */
public class SinnerUpdate extends UiUpdate{
	
	private Sinner sinner;
	private int level;			// level 0 = in queue
	private Main main;
	private JLabel lbl;
	private JList<Sinner> list;
	private static int total = 0;
	
	public SinnerUpdate(Sinner sinner, int level) {
		main = Main.getInstance();
		lbl = main.getSinnerNumber();
		list = main.getList();
		
		this.sinner = sinner;
		this.level = level;
	}

	@Override
	public void execute() {
		if (sinner.getNumber() == 0) {
			((DefaultListModel<Sinner>) list.getModel()).addElement(sinner);
			total++;
			lbl.setText(Integer.toString(total));
		} else 
			((DefaultListModel<Sinner>) list.getModel()).removeElement(sinner);
	}
}
