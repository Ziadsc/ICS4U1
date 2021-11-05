package animations;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.MultipleGradientPaint.CycleMethod;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;

public class Shape {

	final static int RMAX = 200, RMIN = 50;
	int cx, cy;
	int r;	
	int vx, vy;
	static RadialGradientPaint paint;
	private int panW = GraphicsMain.panW;
	private int panH = GraphicsMain.panH;
	
	//static initializer (q.v.)
	static {
		Point2D center = new Point2D.Float(150,240);
        float radius = 60;
        float[] dist = {0.0f, 0.9f};
        Color[] colors = {Color.RED, Color.BLACK};
		paint = new RadialGradientPaint(center, radius, dist, colors,CycleMethod.REFLECT);
	}
	
	Shape() {
		
		//Set values for instance variables
		int z = (panW > panH) ? panH : panW;
		r = RMIN;
		cx = (int)(Math.random()*(z-2*r)+r);
		cy = (int)(Math.random()*(z-2*r)+r);
		vx = vy = 1;		
		
	}
	
	void paint(Graphics2D g2) {
//		g.setColor(Color.CYAN);
		
		g2.setPaint(paint);
		g2.fillOval(cx-r, cy-r, 2*r, 2*r);
		
	}
	
	void move() {
		moveCircle();
		//pulse();
	}
	
	private void moveCircle() {		
		//move
		this.cx += this.vx;
		this.cy += this.vy;
//		//bounce off walls. Add check to make sure that they don't get stuck in walls
		if (this.cx-this.r < 0    && this.vx < 0) this.vx *= -1;
		if (this.cy-this.r < 0    && this.vy < 0) this.vy *= -1;
		if (this.cx+this.r > panW && this.vx > 0) this.vx *= -1;
		if (this.cy+this.r > panH && this.cy > 0) this.vy *= -1;	
	}
	
	//this method will make the shape pulse in size when it is called repeatedly
	int tick = 0;
	private void pulse() {
		tick++;
		r = (int) (RMIN + RMIN/3*Math.sin(Math.toRadians(tick)));
	}
		
}
