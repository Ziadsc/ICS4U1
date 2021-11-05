package animations;
/* M. Harwood. Updated Feb 2018
 * This program demonstrates how to drag a rectangle and then draw it once the mouse is released
 */
 /************************************************
					TODO: 
 1. add comments explaining how things are working (how the variables are used, eg. why mx1=-1)
 2. add code so that you can drag upwards and left as well. 
	Currently it only works for dragging down to the right
**************************************************/	
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class DragRectangle extends JFrame implements MouseListener, MouseMotionListener {
	public static void main (String[] args){
		new DragRectangle();
	}
	
	//Constants
	final static Stroke dashed = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
	final static BasicStroke stroke = new BasicStroke(2.0f);
	
	
	//instance variables
	int scrW=400, scrH=400;	//screen width and height
	DrawingPanel panel;
	int mx1,mx2,my1,my2;	//initial and final mouse points
	boolean dragging = false;
	Color foreColour = Color.GREEN.darker();
	Color backColour = Color.WHITE;
	Color stretchColour = Color.RED;
	
	DragRectangle() {
		this.setTitle("Dragging a rectangle");
		this.setSize(scrW,scrH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		panel = new DrawingPanel();
		panel.addMouseListener(this);
		panel.addMouseMotionListener(this);
		this.add(panel);
		this.validate();
		this.setVisible(true);
	}
	
		
	private class DrawingPanel extends JPanel{
		
		private int prevx = 0, prevy = 0;
		private int prevw = 0, prevh = 0;
		
		//constructor
		DrawingPanel(){
			mx1=mx2=my1=my2=-1;			
		}
		
		public void paintComponent(Graphics g) {
			//super.paintComponent(g); No. This erases everything
			
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		//	g.setColor(backColour);
			int mw = mx2-mx1;
			int mh = my2-my1;	
		
			if (dragging) {				
				/* If you just try and draw a white rectangle, it erases all other rectangles too. Use XOR mode */
				//g2.setColor(this.getBackground());
				
				g2.setStroke(dashed);
				g2.setXORMode(this.getBackground());
				g2.setColor(stretchColour);
				
				//erase old rectangle
				if (prevw > 0 && prevh < 0) { 
					g.drawRect(mx1,my1+prevh,prevw,-prevh);
//TODO: some other code needed here (other if else statements)
				} else {	//down and right works			
					g.drawRect(mx2, my2,prevw,prevh);
				}
				
			} else {
				g2.setPaintMode();
				g2.setColor(foreColour);
				g2.setStroke(stroke);
			}	
			
//TODO:			ADD CODE HERE for different directions
			g.drawRect(mx1,my1,mw,mh);
			
			if (dragging) {
				prevx = mx1; prevy = my1;
				prevw = mw;  prevh = mh;
			} else {
				prevx = prevy = 0;
				prevw = prevh = 0;
			}
		}		
	}
	
	public void mouseEntered(MouseEvent e) { }
	public void mouseExited(MouseEvent e) { }
	public void mouseClicked(MouseEvent e) {}
	
	//this is where the first point of the rectangle is drawn
	public void mousePressed(MouseEvent e) {
		mx1=e.getX();
		my1=e.getY();
	}
	public void mouseReleased(MouseEvent e) {
		dragging=false;
		panel.repaint();
	}
	
	public void mouseMoved(MouseEvent e) { }
	public void mouseDragged(MouseEvent e) { 
		dragging=true;
		mx2 = e.getX();
		my2 = e.getY();		
		panel.repaint();
	}
}