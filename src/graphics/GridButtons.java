package graphics;

import java.awt.BorderLayout;
import java.awt.Color;

/** Program to test how components are added to a grid layout when there are the wrong number of buttons for the grid.
Mr. Harwood, Sept 2021. **/

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GridButtons {
	public static void main(String[] args) {
		new GridButtons();
	}
	
	/* 1-4 buttons: 1 column
	 * 5 buttons: 2x2 + 1 below
	 * 5-8: 2 columns filling up until we get 4 rows (4x2)
	 * 9 buttons: 3x3 grid
	 * 10-12: add buttons on 4th row to get the full number.
	 * 12: 4 rows, 3 columns as desired.
	 * 13-15: move extra buttons to the end of each row (giving 4 columns) and start row 4 again
	 * 16: 4x4
	 * 17-20: take 3 last buttons off bottom row, add another column (5 columns) and rearrange the buttons. 
	 * 		  16 and 17 are now on the bottom and there are 3 empty spaces.
	 * 20: 4x5
	 * each time a row is filled up, this repeats. Add another column, move 3 buttons to the end of each row, 
	 * and start row 4 again.  
	 */
	
	GridButtons() {
		// JFrames default to BorderLayout
		JFrame window = new JFrame("Adding Buttons to 4rows x 3cols Grid Layout");
		window.setSize(600, 300);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		
		window.setResizable(true);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4,3,2,2));
		panel.setBackground(new Color(150,0,100));
			
		window.add(panel, BorderLayout.CENTER);
		
		window.setVisible(true);
		
		//Add buttons
		for (int i = 1; i <= 19; i++) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {}
			panel.add(new JButton(""+i));
			
			window.validate();
			//window.repaint();  <-- does not run validate	
			//window.setVisible(true);  //validates, as does pack()
			//resizing the window also runs validate()
		}
	}
}
