package rotations;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Qix2 extends JPanel implements ActionListener {

	//objects
	Timer timer;
	static JFrame window;

	private static final int TIMERSPEED = 15;
	private static int winW = 800;
	private static int winH = 800;

	//variables
	private int cx, cy;	//centre of circle large circle
	private int rx, ry; //radii of large circle
	private int radius = 10; //radius of small circles
	private int rotate=0;	//value of rotation
	private double angle = 0;
	private int connectorNum = 1;

	// STATES (state machine)
	//	final static int ROTATE = 1;
	final static int EXPAND = 2;
	final static int SHRINK = 4;
	final static int STRINGART = 8;
	private int state = EXPAND;
	private boolean rotateMode = false;

	public static void main(String[] args) {
		window = new JFrame("Moving star");
		window.add(new Qix2());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(winW, winH);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}

	//constructor
	Qix2() {
		this.setBackground(Color.BLACK);
		timer = new Timer(TIMERSPEED, this);

		timer.start();

		cx=winW/2;	cy=winH/2;
		rx=winW/4; 	ry=winH/4;

	}

	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setStroke(new BasicStroke(2.0f));



		int h = getHeight();
		int w = getWidth();


		super.paintComponent(g);
		g2d.setXORMode(Color.BLACK);		

		Point[] circle1 = addDots(cx-radius/2, cy-radius/2);

		// ### all setup of variables is finished

		g.setColor(Color.RED);

		g2d.drawOval(cx-rx,cy-ry, 2* rx, 2* ry);

		//DEBUG
		/*		for (int i=0; i<36; i++) {
			g.setColor(Color.WHITE);
			g2d.drawLine(circle1[i].x, circle1[i].y, circle1[(i+11)%36].x, circle1[(i+11)%36].y);
		}
		if (1==1) return;
		 */	//END DEBUG

		//		g.setColor(Color.WHITE);
		for (int i=0; i<36; i++) {
			g.setColor(Color.WHITE);
			g2d.fillOval(circle1[i].x, circle1[i].y, radius, radius);
			//g2d.drawLine(circle1[i].x, circle1[i].y, cx2, cy2);



			if (state == STRINGART) {
				g.setColor(Color.WHITE);
				g2d.drawLine(circle1[i].x, circle1[i].y, circle1[(i+connectorNum)%36].x, circle1[(i+connectorNum)%36].y);
			}
		}

	}

	//add dots every 10 degrees
	Point[] addDots(int cx_, int cy_) {
		Point[] pts = new Point[36];		
		for(int i=0; i < 36; i++) {
			double angle = Math.toRadians(i*10 + rotate);			
			pts[i] = new Point(cx_ + (int)(rx * Math.cos(angle)), cy_ + (int)(ry * Math.sin(angle)));
		}
		return pts;
	}


	public void actionPerformed(ActionEvent e) {

		if (rotateMode) rotate ++;		
		if (state == EXPAND) radius++;
		if (state == SHRINK) radius--;
		
		if (state == EXPAND && radius >= 350) {
			state = SHRINK;
			rotateMode = true;
		}
		
		if (radius < 5) {
			state = STRINGART;
			rotateMode = true;
		}

		if (state == STRINGART) {			
			if (rotate % 40 == 0  && connectorNum < 14 ) { 
				connectorNum++;
			} else {
//				rotateMode = false;
//				rotate = 0;
//				connectorNum = 0;
//				mode = EXPAND;
			}
				
			// 			if (connectorNum == 11 ) connectorNum = 24;
			// 			if (connectorNum == 36 ) connectorNum = 0;
		}

		//window.setTitle("" + radius);
		this.repaint();
	}
}
