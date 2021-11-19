/*
 *   Ziad El-Sayed
 *   Graphics Test
 *     11/18/21
 */
package animations;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class BallBounce implements ActionListener {

	public static void main(String[] args) {
		new BallBounce();
	}

	static final int PANW = 800;
	static final int PANH = 700;
	boolean paused = false;
	DrawingPanel panel = new DrawingPanel();
	JButton pause = new JButton("PAUSE");
	Ball ball = new Ball(300,100,40,40);
	
	class Ball {
		//only add this line in if you do NOT extend Rectangle
		int x, y, width, height;
		int vx = 3;
		int vy = 0;
		int backupvx = 0;
		
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
		
		JPanel panelSouth = new JPanel();
		panelSouth.setBackground(Color.GRAY);
		panelSouth.setLayout(new GridLayout(1,2,2,2));
		
		JButton reverse = new JButton("REVERSE");
		
		reverse.setActionCommand("Reverse"); 
		reverse.addActionListener( new MyAL() );
		pause.setActionCommand("Pause"); 
		pause.addActionListener( new MyAL() );
		
		panelSouth.add(reverse);
		panelSouth.add(pause);
		panelSouth.setPreferredSize(new Dimension(PANW, 100));
		frame.add(panelSouth, BorderLayout.SOUTH);
		

		frame.add(panel);
		
		frame.pack(); //let panel set the size
		frame.setLocationRelativeTo(null);  
		frame.setVisible(true);
		
		Timer timer = new Timer(10,this);
		timer.start();
	}
	
	void moveBall() {
		ball.x += ball.vx;
		
		//*** Bounce off walls ***
		ball.x = HitWall();
	}
	
	public int HitWall() {
		if (ball.vx > 0 && ball.x > PANW) return -ball.width;
		if (ball.vx < 0 && ball.x < 0-ball.width) return PANW+ball.width;
		return ball.x;
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
	
	class MyAL implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("Reverse")) {
				// Allows reversing the ball while paused
				if (!paused) {
					ball.vx = -ball.vx;
				} else {
					ball.backupvx = -ball.backupvx;
				}
			} else if (e.getActionCommand().equals("Pause")) {
				// Pausing ball momentum
				if (!paused) {
					ball.backupvx = ball.vx;
					ball.vx = 0;
					pause.setText("UNPAUSE");
				} else {
					ball.vx = ball.backupvx;
					ball.backupvx = 0;
					pause.setText("PAUSE");
				}
				paused = !paused;
			}
		} 
		
	}	

}
