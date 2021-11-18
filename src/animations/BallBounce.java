package animations;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.Timer;

public class BallBounce implements ActionListener {

	public static void main(String[] args) {
		new BallBounce();
	}

	static final int PANW = 800;
	static final int PANH = 700;
	DrawingPanel panel = new DrawingPanel();
	Ball ball = new Ball(300,100,40,40);
	
	class Ball {
		//only add this line in if you do NOT extend Rectangle
		int x, y, width, height;
		int vx = 3;
		int vy = 2;
		
		Ball(int x, int y, int w, int h) {
			this.x = x;
			this.y = y;
			width=w;
			height = h;
		}
	}
	
	BallBounce(){
		//setup Jframe
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );				

		frame.add(panel);
		
		frame.pack(); //let panel set the size
		frame.setLocationRelativeTo(null);  
		frame.setVisible(true);
		
		Timer timer = new Timer(10,this);
		timer.start();
	}
	
	void moveBall() {
		ball.x += ball.vx;
		ball.y += ball.vy;
		
		//*** Bounce off walls ***
		//Super simple bounce
		/*if (ball.x > PANW) {
			ball.vx = -ball.vx;
		}*/
		
		//More general method to avoid glitches
		// if(ball velocity > 0 (going to the right) and ball.x(top left position of the ball)+ball.width(adds the width of ball, ie(80) to the x, which gives you the right side of the ball) is greater than panelw (if ball position reaches right side (panel w = 800))
		if (ball.vx > 0 && ball.x+ball.width > PANW ) {
			ball.vx = -ball.vx;  //reverse direction
			//undo the last move
			ball.x -= ball.vx;
			ball.y -= ball.vy;
		}
		if (ball.vx < 0 && ball.x < 0 ) {
			ball.vx = -ball.vx;  //reverse direction
			//undo the last move
			ball.x -= ball.vx;
			ball.y -= ball.vy;
		}
		else if (ball.vy > 0 && ball.y+ball.height > PANH ) {
			ball.vy = -ball.vy;  //reverse direction
			//undo the last move
			ball.x -= ball.vx;
			ball.y -= ball.vy;
		}
		else if (ball.vy < 0 && ball.y < 0 ) {
			ball.vy = -ball.vy;  //reverse direction
			//undo the last move
			ball.x -= ball.vx;
			ball.y -= ball.vy;
		}
		
		//right wall is done. Need to do the other 3 walls
		
		
	}
	
	class DrawingPanel extends JPanel {
		DrawingPanel() {
			this.setPreferredSize(new Dimension (PANW, PANH));
		}
		
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.fillOval(ball.x, ball.y, ball.width, ball.height);
		}
	}

	//this is for the TIMER
	@Override
	public void actionPerformed(ActionEvent e) {
		moveBall();
		panel.repaint();		
	}

}
