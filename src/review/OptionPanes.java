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

public class OptionPanes {
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
			System.out.println("Cancel pressed");
			System.exit(0);
		}

		if (name.equals("")) {
			name = "No Name";
		}

// ##### SHOW MESSAGE DIALOG ##### //
// SYNTAX	 showMessageDialog(Component parentComponent, Object message, String title, int messageType)	 
// SYNTAX	 showMessageDialog(Component parentComponent, Object message, String title, int messageType, Icon icon)	 
//							messageType: QUESTION / INFORMATION / WARNING / ERROR / PLAIN
//							** the message type makes the icon unless you add a custom icon

		String str2 = "This is my message text";
		str2 = str2  + " ... more text to make the box wider ...";
//		JOptionPane.showMessageDialog(null, str2,"A \"plain\" message to " + name, JOptionPane.PLAIN_MESSAGE);		
		JOptionPane.showMessageDialog(null, str2,"A \"warning\" message to " + name, JOptionPane.WARNING_MESSAGE);

// ##### SHOW CONFIRM DIALOG ##### //
//And ... there's a showConfirmDialog ...
		int n = JOptionPane.showConfirmDialog(null, "Hey " + name + "! Would you like green eggs and ham?", "An Inane Question", JOptionPane.YES_NO_OPTION);
		System.out.printf("Button #%d pressed%n", n);	//this is the button number (0,1,...)
		if (n==0) System.out.println("Are you nuts?!");
		if (n==1) System.out.println("Playing it safe?");

// ##### SHOW OPTION DIALOG ##### //
//SYNTAX	showOptionDialog(Component parentComponent, Object message, String title, int optionType, int messageType, Icon icon, Object[] options, Object initialValue)
	// see binary to hex program for this one.
	
	} // end of main

} // end of class
		