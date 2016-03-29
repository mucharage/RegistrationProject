/**
 * 
 * @author Steven Hullander
 * Group 5
 * 
 * This is the class for the forgot password screen
 * This class generates a new pop-up window that 
 * prompts the user on how to retrieve his or her password.
 */

package com.github.fantastic_five.GUI;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class GUIForgotPass
{

	// Declare frame as type JFrame
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
			    // Try to create the new pop-up window
				try
				{
					GUIForgotPass window = new GUIForgotPass();
					window.frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * @return A new window with notification for a forgotten password
	 */
	public GUIForgotPass()
	{
		  // Initialize frame as a new JFrame
		frame = new JFrame("Password Recovery");
		  // Set the dimensions of the frame
		frame.setBounds(100, 100, 303, 141);
		  // When the frame is closed, it simply goes away
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

		  // Create a text area to go inside the frame
		JTextArea txtpnPleaseContactThe = new JTextArea();
		  // Making the font "Tahoma", plain text, size 15
		txtpnPleaseContactThe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		  // Menu is the color of the text area background
		txtpnPleaseContactThe.setBackground(UIManager.getColor("menu"));
		  // Make the text not editable by the user
		txtpnPleaseContactThe.setEditable(false);
		  // The text that is displayed
		txtpnPleaseContactThe.setText("Please contact the System\r\n  Administration Office to\r\n   obtain your password.\r\n          Thank you.");
		  // Set the dimensions of the frame
		txtpnPleaseContactThe.setBounds(57, 11, 178, 80);
		  // add the text area to the pane and frame
		frame.getContentPane().add(txtpnPleaseContactThe);
	}
}
