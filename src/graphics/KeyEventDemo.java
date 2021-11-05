package graphics;
/* This is a program to demonstrate key listeners.
I'm using a Jpanel. There are a couple of ways of moving a JLabel, but in real life you wouldn't be doing this.

Oct 2021.
M. Harwood
*/

import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class KeyEventDemo extends JFrame implements KeyListener{

	public static void main(String[] args) {
		new KeyEventDemo();		
	}
	
	JPanel panel = new JPanel();
	JLabel label;
	
	KeyEventDemo(){
		this.setTitle("Reacting to keys");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,500);
		
		label = new JLabel("####");
		label.setOpaque(true);
		label.setBackground(Color.RED);

		panel.add(label);
		panel.addKeyListener(this);
		this.add(panel);
		this.setVisible(true);

		panel.requestFocus();
		/* If you have a JButton or anything on the panel that normally accepts keypresses,
		 * you won't be able to get the focus back to the panel, so the keylistener won't work.
		 * You will have to have a loop that repeately requests focus.
		 * Just do a find and replace JLabel --> JButton to see this happen.
		 */
	}


  /*===========================================================/
  /                Methods for event listeners                 /
  /===========================================================*/
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		this.setTitle("key=" + key);
		
		Point p = label.getLocation();
		
		if (key == 38 || key == 'W') label.setLocation(p.x, --p.y); //up
		if (key == 37 || key == 'A') p.x -=4; //left arrow
		if (key == KeyEvent.VK_RIGHT) p.x +=4;  //#39 right
		if (key == 40) p.y +=4; //down
		if (key == 'Q') System.exit(0); 
		
		label.setLocation(p.x, p.y);		
	}

	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}


}
