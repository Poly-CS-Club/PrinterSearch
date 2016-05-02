package core;
import javax.swing.JFrame;

/**
 * Launches the user interface.
 * 
 * @author  Joshua Becker
 * @version 1.0
 */
public class Main {

	public static void main(String args [])
	{
		JFrame menuFrame = new MenuWindow("Menu");
		menuFrame.pack();
	    menuFrame.setVisible(true);
	}
}
