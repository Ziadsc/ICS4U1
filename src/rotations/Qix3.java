package rotations;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
//import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Qix3 extends JPanel implements ActionListener {

	//constants
	private static final int TIMERSPEED = 20;
	private static final int NUMTRI = 9;
	
	//Global & system Variables
	Timer timer;
	static JFrame frame;
	Color colorTri = new Color(255,0,0,127);

	public static void main(String[] args) {
		frame = new JFrame("Moving star");
		frame.add(new Qix3());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(850,850);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	Polygon[] triArray = new Polygon[NUMTRI];

	private Polygon triA = new Polygon(); //this is the basic triangle that is copied and moved around.
	
	private int CX, CY;	//non-moving centre that things move around. Based on size of screen.
	private int rotX, rotY; //centre of circles
	private int rx = 200, ry = 200; //radii of circles
	private double angle = 0;

	//constructor
	Qix3() {
		this.setBackground(Color.BLACK);
		timer = new Timer(TIMERSPEED, this);
		timer.start();

		//temporary value for CX, CY, near centre of JPanel.
		CX=300; CY = 300;
		rotX=CX; rotY=CY; //centre
	
		//make an equilateral triangle pointing up
		int side = 150;
		triA.addPoint(rotX-side/2, rotY);
		triA.addPoint(rotX+side/2, rotY);
		triA.addPoint(rotX, (int)(rotY-Math.sqrt(3)*side/2));
		//triA.translate(0, (int)(Math.sqrt(3)*side/2));

		//populate the array with copies of the first triangle
		for (int i=0; i<triArray.length; i++) {
			triArray[i] = new Polygon(triA.xpoints, triA.ypoints, triA.npoints);		
		}

	}


	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setStroke(new BasicStroke(2.0f));

		//TODO: instead of this, write code to make CX, CY increase up to 2*rx, then down to rx. 
		CX = getWidth()/2;
		CY = getHeight()/2;

		
		//draw outer circles. Rotate them in opposite direction and slower than triangles
		int xx=0;
		int yy=0;
		g.setColor(Color.BLUE.brighter());
		for (int i=1; i<=12; i++){
			xx = (int)(rx  * Math.cos(i*Math.PI/6 + angle/10));
			yy = (int)(rx  * Math.sin(i*Math.PI/6 + angle/10));
			g2d.drawOval(CX - rx + xx, CY - rx + yy, 2* rx, 2* ry);
		}

		//draw centre Triangle!
		//g.setColor(Color.YELLOW);
		//g2d.fillPolygon(triA);
		
		//draw all triangles
		g.setColor(colorTri);
		for (int i=0; i<triArray.length; i++)
			g2d.fillPolygon(triArray[i]);


		//centre circle
		g.setColor(Color.CYAN);
		g2d.drawOval(CX-rx,CY-ry, 2* rx, 2* ry);
	

		
		//g2d.drawOval(cx2, cy2, 2* rx, 2* ry);
		
		
		//draw green lines to top left corner
		g2d.setStroke(new BasicStroke(1.0f));
		g2d.setColor(Color.GREEN.brighter().brighter());
		
		for (int i=0; i< triArray.length; i++) {
					
			g2d.drawLine(triArray[i].xpoints[0], triArray[i].ypoints[0], 10,10);
			//g2d.drawLine(triArray[i].xpoints[0], triArray[i].ypoints[0], xx, yy);
	
			//g2d.drawLine(cx, cy, triB.xpoints[1], triB.ypoints[1]);
			//g2d.drawLine(cx, cy, triB.xpoints[2], triB.ypoints[2]);
		}
		
		//draw centre dot
		g.setColor(Color.RED);
		g2d.fillOval(CX-2, CY-2, 4, 4);

	}

	//NOTE: triangleDist is measured from the cyan reference circle. So -300 goes through and back, and then it reverses
	//		+300 is 300 pixels OUTSIDE of the circle.
	int triangleDist = 0;
	boolean growing = false;
	public void actionPerformed(ActionEvent e) {
		angle += 0.02;
		
		triangleDist = triangleDist + (growing ? -1 : +1);
		if (Math.abs(triangleDist) > 300) growing = !growing;

		rotateAllTriangles();
		this.repaint();
	}

	Point rotatePoint(int x, int y){
		
		int px = (int)(x * Math.sin(angle) + y * Math.cos(angle));
		int py = (int)(x * Math.cos(angle) - y * Math.sin(angle));

		return new Point(px,py);
	}
	
	private void rotateAllTriangles(){
		for (int i=0; i<triArray.length; i++) {
			rotateTriangle(triArray[i], angle + 2*i*Math.PI / NUMTRI);
		}
	}
	
	private void rotateTriangle(Polygon t, double angle){
		t.reset();

		for (int i=0; i< triA.npoints; i++) {

			//get each point and set position relative to (0,0)
			int a = triA.xpoints[i]-CX + triangleDist;
			int b = triA.ypoints[i]-CY + triangleDist;

			//rotate it (relative to (0,0)
			int a2 = (int)(a * Math.sin(angle) + b * Math.cos(angle));
			int b2 = (int)(a * Math.cos(angle) - b * Math.sin(angle));

			//triA.xpoints[i] = a2;
			//triA.ypoints[i] = b2;
			t.addPoint(CX + a2,CY + b2);	
		}
	}
}
