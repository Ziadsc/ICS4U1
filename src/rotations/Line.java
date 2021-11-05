package rotations;

import java.awt.Graphics;

/** 
 * 
 * @author tv30254
 *
 */
class Line {
	
	private double x1, y1, x2, y2;
	public final double cx,cy; //coordinates of the centre of the line

	public Line (double x1, double y1, double x2, double y2){
		this.x1=x1;
		this.y1=y1;
		this.x2=x2;
		this.y2=y2;

		cx = (x1 + x2)/2.0;
		cy = (y1 + y2)/2.0;
	}

	/**
	 * Purpose: Paint the line on the graphics context<br> 
	 * Called from: TimerRotate.DrawingPanel.paintComponent()<br>
	 * Calls: none<br>
	 * @param g graphics context passed from subclassed JPanel
	 */
	public void paint(Graphics g) {
		g.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
	}

	/**
	 * Purpose: rotate the line about its centre<br>
	 * Called from: ActionListener for Timer in class TimerRotate<br>
	 * Calls: rotatePoint() for each endpoint<br>
	 * @param angle the angle in radians to rotate the line
	 */
	void rotate(double angle) {
		PointD tempPoint = rotatePoint(angle, x1,y1,cx,cy);
		x1 = tempPoint.x;
		y1 = tempPoint.y;

		tempPoint = rotatePoint(angle, x2,y2,cx,cy);
		x2 = tempPoint.x;
		y2 = tempPoint.y;
	}

	/* 
	 * The results (new point) are sent back as a PointD object (ie. a Point with doubles) */
	/**
	 * Purpose: to rotate any point by an angle (radians) about a centre point.
	 * 			The rotation is in the same direction as angles in math (anticlockwise).
	 * Called from: this.rotate()
	 * Calls: none (but creates PointD object to return)
	 * @param angle in radians
	 * @param x x value of initial point
	 * @param y y value of initial point
	 * @param centrex, centrey rotate initial point about this point
	 *
	 *
	 * @return PointD object containing new point in double precision
	 */
	PointD rotatePoint(double angle, double x, double y, double centrex, double centrey) {
		double newx = (x-centrex) * Math.cos(angle) + (y-centrey) * Math.sin(angle);
		double newy = -(x-centrex) * Math.sin(angle) + (y-centrey) * Math.cos(angle);
		PointD pd = new PointD(); 
		pd.x = newx+centrex;
		pd.y = newy+centrey;
		return pd;
	}


	/** This is a small class that enables the user to have a point with x,y fields that are double
	 *  instead of "int" that java.awt.Point forces you to have.
	 */
	private class PointD {
		double x, y;
	}
}