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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

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
		// Make the text the color BLUE
		btnForgotPassword.setForeground(Color.GRAY);
		// When button is clicked, GUIForgotPass is called
		btnForgotPassword.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				{
					// Initialize frame as a new JFrame
					JFrame frame = new JFrame("Password Recovery");
					// Set the dimensions of the frame
					frame.setBounds(100, 100, 303, 141);
					// When the frame is closed, it simply goes away
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.setLocationRelativeTo(StudentRegistrationMain.mainWindow);
					frame.getContentPane().setLayout(null);
					frame.setResizable(false);
					frame.setVisible(true);

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
					JButton btnNo = new JButton("No");
					btnNo.addActionListener(new ActionListener()
					{
						@Override
						public void actionPerformed(ActionEvent e)
						{
							frame.dispose();
						}
					});
					// Dispose if esc key is pressed
					btnNo.addKeyListener(new KeyAdapter() {
						 public void keyPressed(KeyEvent ke) {  // handler
							    if(ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
							      System.out.println("escaped ?");
							      frame.dispose();
							      } 
							     else {
							      System.out.println("not escaped");
							      }
							     } 
							});
					frame.getContentPane().add(btnNo);
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
				else
				{
					passwordField.setText("");
					JLabel lblInvalidPassword = new JLabel("Invalid Username/Password combination");
					lblInvalidPassword.setFont(new Font("Monospaced", Font.PLAIN, 12));
					lblInvalidPassword.setHorizontalAlignment(SwingConstants.CENTER);
					lblInvalidPassword.setForeground(new Color(255, 51, 0));
					lblInvalidPassword.setBounds(164, 300, 280, 25);
					add(lblInvalidPassword);
					revalidate();
					repaint();
				}
			}
		});
		// Add button to panel
		add(btnLogin);
		
		// User can press enter after entering password and it will click Login
		passwordField.addKeyListener(new KeyListener()
		{
			@Override
			public void keyTyped(KeyEvent e)
			{
				if (e.getKeyChar() == KeyEvent.VK_ENTER)
				{
					btnLogin.doClick();
				}
			}

			@Override
			public void keyPressed(KeyEvent e)
			{
				/** Do Nothing */
			}

			@Override
			public void keyReleased(KeyEvent e)
			{
				/** Do Nothing */
			}
		});
		
		// User can press enter after entering username and it will click Login
		usernameField.addKeyListener(new KeyListener()
		{
            @Override
            public void keyTyped(KeyEvent e)
            {
                if(e.getKeyChar()== KeyEvent.VK_ENTER)
                {
                	btnLogin.doClick();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {/**Do Nothing*/}

            @Override
            public void keyReleased(KeyEvent e) {/**Do Nothing*/}
         });

		// Guest is a new JButton object
		// Guest will show up as a button with "Guest" inside it
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
