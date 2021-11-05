package graphics;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FlowLayout1 {

	public static void main(String[] args) {		
		new FlowLayout1(); 
	}
	
	JFrame window;
	
	FlowLayout1(){
		window = new JFrame("Flow Layout");
		window.setSize(400, 300);		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		
		/******
		 * JPanels default to FlowLayout
		 ******/		
		//This part is new  <<<<
		for (int i = 0; i < 10; i++) {
			panel.add(new JButton("HELLO"));	
		}		
		// <<<<<<<
		//Run it and resize the window
		
		window.add(panel);
		
		//the last thing you do (normally)
		window.setVisible(true);
	}

}
