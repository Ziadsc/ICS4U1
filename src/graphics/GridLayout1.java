package graphics;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GridLayout1 {

	public static void main(String[] args) {		
		new GridLayout1(); 
	}
	
	JFrame window;
	
	GridLayout1(){
		window = new JFrame("Flow Layout");
		window.setSize(400, 300);		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		
		//This part is new  <<<<
		panel.setLayout(new GridLayout(3,5)); //3 rows, 5 cols
				
		for (int i = 0; i < 15; i++) {
			panel.add(new JButton("Num " + i));	
		}				
		
		window.add(panel);
		
		//the last thing you do (normally)
		window.setVisible(true);
	}

}
