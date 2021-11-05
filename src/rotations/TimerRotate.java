package rotations;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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

public class TimerRotate implements KeyListener{

	/**
	 * main method that runs GUI in  Event-Dispatching thread for thread safety.
	 * <br> It makes a TimerRotate object. That's all. 
	 * 
	 */
	public static void main(String[] args) { 
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new TimerRotate(); 
			}
		});
	}

	/****************
	 *  Variables   *
	 ****************/
	//Window stuff
	/** constant for size of JFrame */
	static final int SIZE = 700;
	/** drawing panel object that does all graphics */
	DrawingPanel mainPanel = new DrawingPanel();

	//Timer stuff
	Timer timer;
	private int t_speed = 15; //speed of timer repeats (ms)
	int t_pause = 1000;  //initial delay (ms)
	int time;       //just to display elapsed time. This should be compared with System.currentTimeMillis()

	//Other objects and variables
	Line line = new Line(100.0, 100.0, 300.0, 300.0); 
	Line line2 = new Line(300.0, 100.0, 600.0, 300.0);
	double angle = 0.0;


	/*****************************************
	 *   Set up the window (JFrame)           *
	 *   and initialize whatever you need to  *
	 *****************************************/
	TimerRotate() {

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
		timer.start();		
	}

	/********************************************/
	/*  Inner class for Timer's ActionListener  */
	/********************************************/
	private class TimerAL implements ActionListener{

		/* Don't put a lot of code here. Instead call methods to do stuff */             
		@Override
		public void actionPerformed(ActionEvent e) {
			time++;
			//angle = angle + Math.toRadians(0.005);
			angle = 0.07;
			line.rotate(angle);
			line2.rotate(angle);
			mainPanel.repaint();
		}
	}


	private class DrawingPanel extends JPanel {

		//constructor
		DrawingPanel() {
			//put background colour here
			this.setBackground(Color.BLACK);
		}

		/***************************/
		/*  Draw all objects here  */
		/***************************/
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g); //clears the screen and repaints it

			Graphics2D g2d = (Graphics2D) g;
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g.setColor(Color.GREEN);
			g2d.setStroke(new BasicStroke(3));
			//g.drawLine(line.x1,line.y1,line.x2, line.y2);
			line.paint(g);
			line2.paint(g);

			g.drawString("TIME1=" + time*t_speed, 50,50);

			g2d.dispose(); //only dispose of graphics objects that you have created
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

