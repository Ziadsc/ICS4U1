package games.tictactoe;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class GraphEquation {

	//Global variables
		int panW = 800, panH = 800;
		public static void main(String[] args) {
			new GraphEquation();

		}
		
		GraphEquation(){
			//setup JFrame
			JFrame window = new JFrame("Graphing Equation");		
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
				
				
			}
		}
}
