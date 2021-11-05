package graphics;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Event1 {
	public static void main(String[] args) {
		new Event1();
	}

	//global variables here
	JFrame window;
	JPanel panel;
	
	Event1(){
		//make JFrame... 
		window = new JFrame("Event demo");
		window.setSize(400,400);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//setup panel
		panel = new JPanel(); 
		JButton button = new JButton("Yes");
		button.addActionListener( new MyAL() ); //Step 1 and 3
		
		panel.add(button);
		window.add(panel);		
		window.setVisible(true);
	}
	
	//inner class
	//Step 2:  "implements ..." means "use the interface ..." 
	class MyAL implements ActionListener {
		int numClicks = 0;
		
		@Override
		public void actionPerformed(ActionEvent ev) {
			//change background colour
			panel.setBackground(Color.PINK);			
			numClicks++;
			window.setTitle( numClicks + " clicks");
		} 
		
	}	
}



