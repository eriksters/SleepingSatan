package ui;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class SatanPanel extends JPanel{

    private BufferedImage satanImage;
    private BufferedImage blankImage;
    private BufferedImage current;
    
    public static final int BLANK = 0;
    public static final int SATAN = 1;
    
    public SatanPanel() {
       try {                
          satanImage = ImageIO.read(new File("./resources/satan.png"));
          blankImage = ImageIO.read(new File("./resources/blank.png"));
          
       } catch (IOException ex) {
            System.err.println("The image fucked up. Oops");
       }
       current = blankImage;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(current, 0, 0, this);          
    }
    
    public void setCurrent(int i) {
    	if (i == BLANK) {
    		current = blankImage;
    	} else if (i == SATAN) {
    		current = satanImage;
    	}
    }

}
