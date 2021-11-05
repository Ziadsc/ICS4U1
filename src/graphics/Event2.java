package graphics;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Event2 implements ActionListener {
	public static void main(String[] args) {
		new Event2();
	}

	//global variables here
	JFrame window;
	JPanel panel;
	JButton button;
	int numClicks = 0;

	Event2(){
		//make JFrame... 
		window = new JFrame("Event demo");
		window.setSize(400,400);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//setup panel
		panel = new JPanel(); 
		button = new JButton("Yes");
		button.addActionListener( this ); //Step 1 and 3

		JButton btnExit = new JButton("Exit \u2996");
		btnExit.setActionCommand("Exit"); 
		btnExit.addActionListener( this );
		
		panel.add(button);
		panel.add(btnExit);
		window.add(panel);		
		window.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Exit")) {
			window.dispose();
			// System.exit(0); //For emergencies
		}
		//Another method to see what object triggers the event.
		//** but the object must be global **
		if (e.getSource() == button ) {
			panel.setBackground(Color.PINK);			
			numClicks++;
			window.setTitle( numClicks + " clicks");
		}
	}

}



