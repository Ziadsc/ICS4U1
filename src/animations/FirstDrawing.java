package animations;
import java.awt.BasicStroke;
//	ICS4U1
/* showing how to draw using Swing */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class FirstDrawing {

	public static void main(String[] args) {
		new FirstDrawing();
	}

	//Global variables
	int panW = 800, panH = 800;
	
	FirstDrawing(){
		//setup JFrame
		JFrame window = new JFrame("Drawing on JPanel");		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		DrawingPanel panel = new DrawingPanel();
		window.add(panel);
		window.pack(); 		//sets optimum size
		window.setLocationRelativeTo(null);  //centre on screen
		window.setVisible(true);
	}
	
	class DrawingPanel extends JPanel {
		
		DrawingPanel() {
			//this controls the size ***
			this.setPreferredSize(new Dimension(panW, panH));
			this.setBackground(Color.decode("#221177"));
		}
		
		//all drawing goes in here
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g); //clear and set BGcolor
			g.setColor(Color.YELLOW);
			
			g.drawOval(150, 10, 100,100);
			//turn on antialias
			Graphics2D g2 = (Graphics2D) g; 
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					
			g2.drawOval(10, 10, 100,100);
			//how to make a thicker line
			g2.setStroke(new BasicStroke(4));
			//why does this work if I use g and not g2?
			g.drawLine(0,300,100,300);
			
		}
		
	}	
}
