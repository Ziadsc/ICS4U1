package graphics;

//Author: Michael Harwood.
//Date: Dec 2019.

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/* This is a new version of the program that draws a spiral. 
* 
* The idea is to calculate new points and draw them, updating the screen regularly.
* In this program we do it using a buffered image. Everything is drawn onto the BufferedImage.
* This means that all of the previous points are saved on the image - something that doesn't 
* happen when you draw to the screen using paintComponent. 
* All that paintComponent does is display the image at whatever stage it is at.
*  
* Two other advantages to drawing on a buffered image are 
* (i) you can easily save the drawing (as I've demonstrated in this program)
* (ii) you can do complex things with moving sprites without having 
*      the flickering that happens when you only use paintComponent. I haven't tried this yet.
* (iii) you can also do drawing a lot faster. See comments marked //SPEED\\ 
*/
public class Spiral extends JPanel  {
	Timer timer;

	private int x,y; 
	private Color c = Color.WHITE;
	private double angle = 0.0;
	private double r = 0.0;
	private static int panW = 1000;
	private static int panH = 1000;
	private int cx,cy;
	BufferedImage img;
	static String fpath="";
	static String fname="Spiral1.png";

	public static void main(String[] args) {
		JFrame frame = new JFrame("Spiral");		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);		
		Spiral panel = new Spiral();		
		frame.add(panel); //somehow it paints more smoothly if this is added before setVisible.
		frame.pack();		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);		
	}

	Spiral() {
		cx = panW/2;
		cy = panH/2;
		img = new BufferedImage(panW,panH, BufferedImage.TYPE_INT_RGB);
		this.setBackground(Color.WHITE);	//the JPanel has a white background, but you'll never see it ...
		this.setPreferredSize(new Dimension(panW, panH));

		timer = new Timer(1, new Timer1());
		timer.setInitialDelay(500);
		timer.start();
	}

	public void paintComponent(Graphics g) {
		//Draw onto a BufferedImage (elsewhere) and then display it here
		g.drawImage(img, 0, 0, null);	
	}

	class Timer1 implements ActionListener {
		
		private boolean reverse = false;
		public void actionPerformed(ActionEvent e) {

			//get the graphics object from the image so that we can draw on it.
			Graphics2D g2 = img.createGraphics();
			
			//g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setStroke(new BasicStroke(2));
			
			// --- Do all drawing here ---
			g2.setColor(c);

			//SPEED\\
			//for (int i = 0; i < 100; i++) {

			if (!reverse) {
				r += 0.15;
				angle += 0.015;
			}
			else {
				r -= 0.15; 
				angle -= 0.015;
			}

			if (r > cy) {	
				reverse = true;
				cx++;
			}
			if (r < 0.0) {
				reverse = false;				
				r = 0.0;
				cx++;
			}			

			x = (int) (cx + r*Math.cos(angle));
			y = (int) (cy + r*Math.sin(angle));

			g2.drawLine(x,y,x,y);
			//SPEED\\
			// }
			//--- end of drawing code ---

			repaint(); // to speed things up, don't repaint every single timer tick. Swing coalesces a number of repaints into one
			
			saveTimer(); //decide when to save the image and quit
		}

		
		//Determine when to save the image and quit
		final static int ENDTIME = 30; //seconds
		private long lastTime = System.currentTimeMillis();
		
		void saveTimer() {
			long now = System.currentTimeMillis();
			long elapsed = (now - lastTime)/1000;
			if (elapsed > ENDTIME) {
				saveImage(img);
				//System.exit(0); //or just 
				timer.stop();
			}
		}
	}

	
	static void saveImage(BufferedImage img) {
		System.out.print("saving...");
		try (OutputStream out = new BufferedOutputStream(new FileOutputStream(fpath + fname))) {
			ImageIO.write(img, "png", out);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("done");
	}
}
