package review;

/* This short program shows how to get input using JOption Panes.
 * The surprising thing is that you don't have to actually use a full GUI!
 * You can use it with console based programs too (instead of Scanner).
 * JOptionPane acts like both a message box and an input box.
 * There are static methods so that you don't even have to create a JOptionPane object !
*/
 
import javax.swing.JOptionPane;

/**************************************************************************************************************
   This program is a launcher for various small sub programs to do various binary and hexadecimal conversions
   It also demonstrates how to use JOptionPane  
***************************************************************************************************************/

public class BinaryHex {
	public static void main(String[] args) {
		

// ##### SHOW INPUT DIALOG ##### //
//SYTAX 	static String 	showInputDialog(Component parentComponent, Object message, Object initialSelectionValue)
//		Shows a question-message dialog requesting input from the user and parented to parentComponent.
//SYNTAX	static String 	showInputDialog(Component parentComponent, Object message, String title, int messageType)
//		Shows a dialog requesting input from the user parented to parentComponent with the dialog having the title title and message type messageType.
//SYNTAX	static Object 	showInputDialog(Component parentComponent, Object message, String title, int messageType, Icon icon, Object[] selectionValues, Object initialSelectionValue)


		String name = JOptionPane.showInputDialog(null, "What's your name?","Welcome to My OptionPane",JOptionPane.ERROR_MESSAGE);

		//handle CANCEL option
		if(name == null){
			System.exit(0);
		}


// ##### SHOW MESSAGE DIALOG ##### //
// SYNTAX	 showMessageDialog(Component parentComponent, Object message, String title, int messageType)	 
// SYNTAX	 showMessageDialog(Component parentComponent, Object message, String title, int messageType, Icon icon)	 
//							messageType: QUESTION / INFORMATION / WARNING / ERROR / PLAIN
//							** the message type makes the icon unless you add a custom icon

//		String str2 = "This is my message text";
//		JOptionPane.showMessageDialog(null, str2,"A \"plain\" message to " + name, JOptionPane.PLAIN_MESSAGE);		
//		JOptionPane.showMessageDialog(null, str2,"A \"warning\" message to " + name, JOptionPane.WARNING_MESSAGE);

// ##### SHOW CONFIRM DIALOG ##### //
//And ... there's a showConfirmDialog ...
//		int n = JOptionPane.showConfirmDialog(null, "Hey " + name + "! Would you like green eggs and ham?", "An Inane Question", JOptionPane.YES_NO_OPTION);
//		System.out.println("N=" + n);	//this is the button number (0,1,...)


// ##### SHOW OPTION DIALOG ##### //
//SYNTAX	showOptionDialog(Component parentComponent, Object message, String title, int optionType, int messageType, Icon icon, Object[] options, Object initialValue)


/************************************************************************************************************************************/


		Object[] options = {"B to D", "D to B", "B to H", "H to B", "H to D", "D to H", "Exit"};

		boolean keepLooping = true;
		while(keepLooping) {
			int m = JOptionPane.showOptionDialog(null, "Please choose a program to run", "Binary and Hex conversions", 
				JOptionPane.DEFAULT_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null, options, options[0]);

			switch (m) {		//problem: the numbers and functions called MUST match the order of the options in the array above.
			case 0:
				BtoD();
				break;
			case 1:
				DtoB();
				break;
			case 2:
				BtoH();
				break;
			case 3:
				HtoB();
				break;
			case 4:
				HtoD();
				break;
			case 5:
				DtoH();
				break;
			default:
				keepLooping = false;
				break;
			}
		}
	} // end of main

	static void BtoD() {
		String binary = JOptionPane.showInputDialog(null, "Please enter a binary number","Binary to Decimal",JOptionPane.INFORMATION_MESSAGE);
		int decimal = 0;
		int len = binary.length();
		//handle CANCEL option
		if(binary == null) return;

		//do the conversion
		for (int i = len-1; i >= 0; i--) {
		   if (binary.charAt(i) == '1')	decimal += Math.pow(2,len-1 - i);
		   else if (binary.charAt(i) != '0') {
			JOptionPane.showMessageDialog(null,"Error. You can only enter 1s and 0s" ,"Binary conversion error", JOptionPane.ERROR_MESSAGE);
			return;
		   }
//System.out.printf("B=%s,c=%c i=%d, total=%d\n",binary, binary.charAt(i), i, decimal);
		}
		JOptionPane.showMessageDialog(null,binary + " converted to decimal is " + decimal ,"Binary conversion complete", JOptionPane.INFORMATION_MESSAGE);

	}
	static void DtoB() {}
	static void BtoH() {}
	static void HtoB() {}
	static void DtoH() {}
	static void HtoD() {}
} // end of class
		