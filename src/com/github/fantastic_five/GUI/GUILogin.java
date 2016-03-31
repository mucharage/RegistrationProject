/**
 * @author Steven Hullander
 * Group 5
 * 
 * This is the class for the login screen.
 * This is the first thing a user will see when
 * he or she starts the program. 
 * 
 */

package com.github.fantastic_five.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.github.fantastic_five.StudentRegistrationMain;
import com.github.fantastic_five.Logic.UserProfile;
import com.github.fantastic_five.Logic.UserProfileDatabase;

@SuppressWarnings("serial")
public class GUILogin extends JPanel
{
	// private instance variables
	private JTextField usernameField;
	private JPasswordField passwordField;

	// Create the login screen panel.
	public GUILogin()
	{
		setLayout(null);

		// usernameField is a new JTextField object
		// this is the box the user will type his/her name in
		usernameField = new JTextField();
		// Set the dimensions of the field
		usernameField.setBounds(204, 182, 202, 20);
		// Add field to panel
		add(usernameField);

		// passwordField is a new JTextField object
		// this is the box the user will type his/her password in
		passwordField = new JPasswordField();
		// Set the dimensions of the field
		passwordField.setBounds(204, 227, 202, 20);
		// Add field to panel
		add(passwordField);

		// lblWelcomeMSG is a new JLabel object
		// lblWelcomeMSG will show up as "WELCOME!"
		JLabel lblWelcomeMSG = new JLabel("WELCOME!");
		// Making the font "Verdana", BOLD text, size 70
		lblWelcomeMSG.setFont(new Font("Verdana", Font.BOLD, 70));
		// Set the dimensions of the label
		lblWelcomeMSG.setBounds(90, 49, 700, 70);
		// Add greeting to panel
		add(lblWelcomeMSG);

		// lblUsername is a new JLabel object
		// lblUsername will show up as "Username:"
		JLabel lblUsername = new JLabel("Username:");
		// Set the dimensions of the label
		lblUsername.setBounds(210, 165, 300, 20);
		// Add label to panel
		add(lblUsername);

		// lblPassword is a new JLabel object
		// lblPassword will show up as "Password:"
		JLabel lblPassword = new JLabel("Password:");
		// Set the dimensions of the label
		lblPassword.setBounds(211, 209, 300, 20);
		// Add label to panel
		add(lblPassword);

		// btnForgotPassword is a new JButton object
		// btnForgotPassword will show up as "Forgot Password?" without borders
		JButton btnForgotPassword = new JButton("Forgot Password?");
		// Set the dimensions of the button
		btnForgotPassword.setBounds(290, 244, 120, 23);
		// Make the button appear as just a clickable text
		btnForgotPassword.setContentAreaFilled(false);
		// Make the button borders invisible
		btnForgotPassword.setBorderPainted(false);
		// Make the text the color RED
		btnForgotPassword.setForeground(Color.RED);
		// When button is clicked, GUIForgotPass is called
		btnForgotPassword.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				{
					GUIForgotPass.main(null);
				}
			}
		});
		// Add button to panel
		add(btnForgotPassword);

		// Login is a new JButton object
		// Login will show up as a button with "Login" inside it
		JButton btnLogin = new JButton("Login");
		// Set the dimensions of the button
		btnLogin.setBounds(204, 272, 90, 23);
		btnLogin.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				UserProfile user = UserProfileDatabase.getUserProfile(usernameField.getText(), passwordField.getPassword());
				if (user != null)
				{
					StudentRegistrationMain.loggedIn.add(user);
					StudentRegistrationMain.replaceMainWindowContents(UserProfileDatabase.getGUIFromPerm(user.getPermLevel()));
				}
			}
		});
		// Add button to panel
		add(btnLogin);

		// Login is a new JButton object
		// Login will show up as a button with "Login" inside it
		JButton btnGuest = new JButton("Guest");
		// Set the dimensions of the button
		btnGuest.setBounds(316, 272, 90, 23);
		btnGuest.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				StudentRegistrationMain.replaceMainWindowContents(new GUIViewCourses());
			}
		});
		// Add button to panel
		add(btnGuest);
	}
}
