package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.SystemColor;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.swing.border.EtchedBorder;

import model.GatesOfHell;
import model.Hell;
import model.Lamb;
import model.Overworld;
import model.Satan;
import model.Sinner;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.Toolkit;

public class Main extends JFrame {

	private JPanel contentPane;
	private SatanPanel crib_image_panel;
	private SatanPanel gates_image_panel;
	private SatanPanel hell_image_panel;
	
	private static Main instance;
	private JButton reset_button;
	private JProgressBar horsemenBar;
	private JList<Sinner> list;
	private JLabel lblT;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = Main.getInstance();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	private Main() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/resources/icon_32.png")));
		setTitle("Sleeping Satan's monitor");
		instance = this;
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 655, 438);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBackground(SystemColor.window);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBackground(SystemColor.window);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.setBackground(SystemColor.window);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(SystemColor.window);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 200, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)))
					.addContainerGap())
		);
		
		JButton stop_button = new JButton("Stop");
		stop_button.setEnabled(false);
		
		JButton start_button = new JButton("Start");
		start_button.setEnabled(false);
		
		reset_button = new JButton("Reset");
		reset_button.setEnabled(false);

		
		JLabel lblControls = new JLabel("Controls");
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
								.addComponent(reset_button, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
								.addGroup(gl_panel_3.createSequentialGroup()
									.addComponent(stop_button, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(start_button, GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)))
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_panel_3.createSequentialGroup()
							.addComponent(lblControls)
							.addGap(76))))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_3.createSequentialGroup()
					.addComponent(lblControls)
					.addPreferredGap(ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
					.addComponent(reset_button)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(stop_button)
						.addComponent(start_button))
					.addContainerGap())
		);
		panel_3.setLayout(gl_panel_3);
		
		JLabel lblHell = new JLabel("Hell");
		
		hell_image_panel = new SatanPanel();
		
		JLabel lblTotal = new JLabel("Total:");
		
		lblT = new JLabel("0");
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap(94, Short.MAX_VALUE)
					.addComponent(lblHell)
					.addGap(85))
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(48)
					.addComponent(hell_image_panel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(48, Short.MAX_VALUE))
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(lblTotal)
					.addGap(18)
					.addComponent(lblT, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(Alignment.LEADING, gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addComponent(lblHell)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTotal)
						.addComponent(lblT))
					.addGap(18)
					.addComponent(hell_image_panel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		list = new JList<>();
		list.setModel(new DefaultListModel<Sinner>());
		scrollPane.setViewportView(list);
		panel_2.setLayout(gl_panel_2);
		
		JLabel lblGatesOfHell = new JLabel("Gates of hell");
		
		gates_image_panel = new SatanPanel();
		
		horsemenBar = new JProgressBar();
		horsemenBar.setValue(0);
		horsemenBar.setMaximum(4);
		horsemenBar.setOrientation(SwingConstants.VERTICAL);
		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(71)
					.addComponent(lblGatesOfHell)
					.addContainerGap(65, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addContainerGap(50, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
						.addComponent(horsemenBar, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(gates_image_panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
					.addGap(46))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(lblGatesOfHell)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(horsemenBar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(gates_image_panel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblSatansCrib = new JLabel("Satan's crib");
		
		crib_image_panel = new SatanPanel();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(69)
					.addComponent(lblSatansCrib)
					.addContainerGap(53, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(50, Short.MAX_VALUE)
					.addComponent(crib_image_panel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addGap(48))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(lblSatansCrib)
					.addPreferredGap(ComponentPlacement.RELATED, 123, Short.MAX_VALUE)
					.addComponent(crib_image_panel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		
		addEvents();
		
		//testMethod();
		startExecution();
	}
	
	private void addEvents() {
		reset_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				testMethod();
			}
		});
	}
	
	public static Main getInstance() {
		if (instance == null)
			instance = new Main();
		return instance;
	}
	
	public SatanPanel[] getSatanPanels() {
		SatanPanel[] ret = new SatanPanel[3];
		
		ret[0] = crib_image_panel;
		ret[1] = gates_image_panel;
		ret[2] = hell_image_panel;
		
//		crib_image_panel.setCurrent(1);
//		crib_image_panel.repaint();
		
		return ret;
	}
	
	public JProgressBar getBar() {
		return horsemenBar;
	}
	
	public JList<Sinner> getList() {
		return list;
	}
	
	public JLabel getSinnerNumber() {
		return lblT;
	}
	
	public void testMethod() {
		new LocationUpdate(LocationUpdate.IN_BED).execute();
	}
	
	private void reset() {
		
		startExecution();
	}
	
	private void startExecution() {
		Buffer.getInstance();
		new UpdateExecutor();
		Satan.getInstance();
		Hell h = Hell.getInstance();
		GatesOfHell goh = GatesOfHell.getInstance();
		goh.setHell(h);
		
		new Lamb();
		new Overworld();
	}
}
