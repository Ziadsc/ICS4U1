package review;

import javax.swing.JOptionPane;

public class JOption {

	public static void main(String[] args) {
		//System.out.println("💜");
		//System.out.println("\u1F64F");
		
		String answer = JOptionPane.showInputDialog(
				null, "How old are you?","Title",JOptionPane.QUESTION_MESSAGE);
		
		int age = Integer.parseInt(answer);
		
		if (age > 18) {
			System.out.println("Do you have your license?");
		}
	}

}
