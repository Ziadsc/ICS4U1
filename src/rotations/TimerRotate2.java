package rotations;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * This program demonstrates how to rotate a line ...
 * timers ...
 * @author tv30254
 * @date May 20, 2018
 * @version 1.1
 */

public class TimerRotate2 implements KeyListener{

	/**
	 * main method that runs GUI in  Event-Dispatching thread for thread safety.
	 * <br> It makes a TimerRotate object. That's all. 
	 * 
	 */
	public static void main(String[] args) { 
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new TimerRotate2(); 
			}
		});
	}

	/****************
	 *  Variables   *
	 ****************/
	//Window stuff
	/** constant for size of JFrame */
	static final int SIZE = 500;
	/** drawing panel object that does all graphics */
	DrawingPanel mainPanel = new DrawingPanel();

	//Timer stuff
	Timer timer;
	private int t_speed = 5; //speed of timer repeats (ms)
	int t_pause = 1000;  //initial delay (ms)
	int time;       //just to display elapsed time. This should be compared with System.currentTimeMillis()

	//Other objects and variables
	Line line = new Line(100.0, 100.0, 300.0, 300.0); 
	Rectangle r = new Rectangle(100,100,200,150);
	
	double angle = Math.toRadians(0.005);


	/*****************************************
	 *   Set up the window (JFrame)           *
	 *   and initialize whatever you need to  *
	 *****************************************/
	TimerRotate2() {

		//all window stuff
		JFrame window = new JFrame("Timers");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(SIZE,SIZE);

		/***********************************************************
		 * This is how you get the monitor screen resolution size  *
		 * and make your program take up the whole screen.         *
		 ***********************************************************/
		// Dimension fullScreen = Toolkit.getDefaultToolkit().getScreenSize();
		// window.setSize(fullScreen);

		window.setLocationRelativeTo(null);

		window.addKeyListener(this);
		window.add(mainPanel);
		window.setVisible(true);

		//all timer stuff (start after window is shown)
		timer = new Timer(t_speed, new TimerAL());
		timer.setInitialDelay(t_pause);
		timer.start();
		timer.setInitialDelay(0); //if we don't do this, then restarting the timer is delayed

	}

	/********************************************/
	/*  Inner class for Timer's ActionListener  */
	/********************************************/
	private class TimerAL implements ActionListener{

		/* Don't put a lot of code here. Instead call methods to do stuff */             
		@Override
		public void actionPerformed(ActionEvent e) {
			time++;
			angle = angle + Math.toRadians(1.0); 
//			//line.rotate(angle);
			
			r.y = (int) (100 + 100*Math.sin(time/100.0));
			
			mainPanel.repaint();
		}
	}


	private class DrawingPanel extends JPanel {

		//constructor
		DrawingPanel() {
			//put background colour here
			this.setBackground(Color.WHITE);
		}

		/***************************/
		/*  Draw all objects here  */
		/***************************/
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g); //clears the screen and repaints it

			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			/******************/
			/* BEGIN ROTATING */
			/******************/
			
			// Rotating with a positive angle theta rotates points on the positive x axis toward the positive y axis,
			// exactly the same as in math, but here +y is down so the rotation goes in the opposite direction.
			g2.rotate(angle/50.0, 200,200);                       
//			g.drawLine(100, 100, 300, 300);
//			g2.rotate(angle, 100,100);
//			g.drawRect(100, 100, 200, 200);
			
			g.setColor(Color.RED);
			g.fillRect(r.x,  r.y,  r.width, r.height);
			
			g2.rotate(-angle/50.0, 200,200);
			
			
			/* OR: using an AffineTransform */

//			AffineTransform transform = new AffineTransform();
//			transform.rotate(angle, line.cx, line.cy);
//			AffineTransform old = g2.getTransform();
//			g2.transform(transform);
//			line.paint(g);                        
//			g2.setTransform(old);

			/******************/
			/*  END ROTATING  */
			/******************/


			g.drawString("TIME1=" + time*t_speed, 50,50);

			g2.dispose(); //only dispose of graphics objects that you have created
		}
	}

	/****************************/
	/* All event listeners here */
	/****************************/

	@Override
	public void keyPressed(KeyEvent e) {
		//Press any key to pause timer
		if (timer.isRunning()) {
			timer.stop();
		} else {
			timer.restart();
		}
	}

	@Override
	public void keyReleased (KeyEvent e) {}
	@Override
	public void keyTyped    (KeyEvent e) {}

}

