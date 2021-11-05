package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

public class MigIsGreat2 {
	public static void main(String[] args) {
		new MigIsGreat2();
	}

	int WINW = 600, WINH = 500;
	
	MigIsGreat2() {
		// JFrames default to BorderLayout
		JFrame window = new JFrame("Virtualbox - Preferences ");
		window.setSize(WINW, WINH);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);

		window.setResizable(true);
		JPanel mainPanel = new JPanel();
		//insets are an outer margin
		mainPanel.setLayout(new MigLayout("wrap 4, insets 10"));
		mainPanel.setBackground(new Color(215,215,210));
		
		JTextArea jtf = new JTextArea("\u4DC2 General", 4, 10);
		
		jtf.setFont(new Font("Courier", Font.PLAIN, 18));
		jtf.setForeground(new Color(50,60,120));
		jtf.append("\n\u3208 Input");
		jtf.append("\n\u2996 Network");
		jtf.append("\n\u2605 Proxy");
		mainPanel.add(jtf, "spany, growy");
		
		JLabel lblHead = new JLabel(" Update");
		lblHead.setBackground(Color.WHITE);
		lblHead.setOpaque(true);
		lblHead.setFont(new Font("Arial", Font.BOLD, 22));
		mainPanel.add(lblHead, "height 35!,span 3, growx, wrap");
		
		
		JCheckBox ck1 = new JCheckBox("Check for Updates");
		ck1.setSelected(true);
		ck1.setOpaque(false);
		mainPanel.add(ck1, "span 2, wrap");
		
		JSeparator j = new JSeparator();
		j.setPreferredSize (new Dimension(WINW,1));
		mainPanel.add(j, "wrap, span 5");
				
		mainPanel.add(new JLabel("Once per:"), "align right");
		String s1[] = { "1 week", "1 month", "1 year", "1 century", "never" };
		JComboBox  combo1 = new JComboBox(s1);
		mainPanel.add(combo1, "wrap");
		
		//this is the longest item in column 1, so adding 10px will move all to the right
		mainPanel.add(new JLabel("Next Check:"), "gapx 10px, align right");
				
		JLabel lblNextCheck = new JLabel("2021-10-13");
		mainPanel.add(lblNextCheck,"wrap");
		
		mainPanel.add(new JLabel("Check for:"), "align right, gapy 10px"); //gapy is ABOVE this row

		JRadioButton rb1 = new JRadioButton("Stable Release Versions"); 
		JRadioButton rb2 = new JRadioButton("All New Releases");
		JRadioButton rb3 = new JRadioButton("All New Releases and Pre-Releases");
		
		rb1.setOpaque(false);
		rb2.setOpaque(false);
		rb3.setOpaque(false);
		
		ButtonGroup bg1 = new ButtonGroup();
		bg1.add(rb1);
		bg1.add(rb2);
		bg1.add(rb3);
		
		//trying to squish these closer vertically: neither "gapy 0" nor "pad 0 0 1 1" work
		mainPanel.add(rb1, "wrap");
		mainPanel.add(rb2, "pad 0, skip 2, wrap");
		mainPanel.add(rb3, "skip 2, wrap");
		
		JButton btn1 = new JButton("Cancel");
		JButton btn2 = new JButton("OK");
//		btn1.setBackground(Color.green);
//		btn1.setForeground(Color.WHITE);
		
		//mainPanel.setBorder(BorderFactory.createTitledBorder("Choose Wisely"));
		
		mainPanel.add(btn1, "skip 2, split, align right, sizegroup bttn");
		mainPanel.add(btn2, "gapy 35px, sizegroup bttn");
		
		window.add(mainPanel, BorderLayout.CENTER);
		window.pack(); //you can ignore the WINW, WINH size if you use this.
		window.setVisible(true);

	}
}