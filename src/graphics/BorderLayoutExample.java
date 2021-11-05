package graphics;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BorderLayoutExample {	
	public static void main(String[] args) {
		new BorderLayoutExample();
	}	
	
	BorderLayoutExample() {
		//setup JFrame
		JFrame window = new JFrame("Border Layout Example");
		window.setSize(900, 700);
		window.setLocationRelativeTo(null); //centre window on screen
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		JPanel panelW = new JPanel();
		panelW.setBackground(Color.orange);
		panelW.add(new JLabel("WEST PANEL"));
		window.add(panelW, BorderLayout.WEST);
		
		window.setVisible(true);
	}

}
