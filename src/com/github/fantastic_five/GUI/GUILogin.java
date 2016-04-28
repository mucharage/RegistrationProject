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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.github.fantastic_five.StudentRegistrationMain;
import com.github.fantastic_five.GUIAdministrator.GUIAdmin;
import com.github.fantastic_five.GUIStudent.GUIStudent;
import com.github.fantastic_five.GUITA.GUITeacherAssistant;
import com.github.fantastic_five.GUITeacher.GUITeacher;
import com.github.fantastic_five.Logic.UserProfile;

@SuppressWarnings("serial")
public class GUILogin extends JPanel
{
	// private instance variables
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JLabel lblInvalidPassword = new JLabel();

	// Create the login screen panel.
	public GUILogin()
	{
		setBounds(0, 0, 618, 434);
		setLayout(null);

		// this is the box the user will type his/her name in
		usernameField = new JTextField();
		usernameField.setBounds(204, 182, 202, 20);
		usernameField.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				usernameField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
				passwordField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
				lblInvalidPassword.setText("");
				revalidate();
				repaint();
			}
		});
		add(usernameField);

		// this is the box the user will type his/her password in
		passwordField = new JPasswordField();
		passwordField.setBounds(204, 227, 202, 20);
		passwordField.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				usernameField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
				passwordField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
				lblInvalidPassword.setText("");
				revalidate();
				repaint();
			}
		});
		add(passwordField);

		// lblWelcomeMSG will show up as "WELCOME!"
		JLabel lblWelcomeMSG = new JLabel("WELCOME!");
		lblWelcomeMSG.setFont(new Font("Verdana", Font.BOLD, 70));
		lblWelcomeMSG.setBounds(90, 49, 700, 70);
		add(lblWelcomeMSG);

		// lblUsername will show up as "Username:"
		JLabel lblUsername = new JLabel("User ID:");
		lblUsername.setBounds(210, 165, 300, 20);
		add(lblUsername);

		// lblPassword will show up as "Password:"
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(211, 209, 300, 20);
		add(lblPassword);

		// btnForgotPassword will show up as "Forgot Password?" without borders
		JButton btnForgotPassword = new JButton("Forgot Password?");
		btnForgotPassword.setBounds(290, 244, 120, 23);
		btnForgotPassword.setContentAreaFilled(false);
		btnForgotPassword.setBorderPainted(false);
		btnForgotPassword.setForeground(Color.GRAY);
		btnForgotPassword.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				{
					// Initialize frame as a new JDialog
					JDialog popup = new JDialog(StudentRegistrationMain.mainWindow, "Password Recovery");
					popup.setBounds(100, 100, 303, 141);
					popup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					popup.setLocationRelativeTo(StudentRegistrationMain.mainWindow);
					popup.getContentPane().setLayout(null);
					popup.setResizable(false);
					popup.setVisible(true);
					popup.setAlwaysOnTop(true);

					// Create a text area to go inside the frame
					JTextArea txtpnPleaseContactThe = new JTextArea();
					txtpnPleaseContactThe.setFont(new Font("Tahoma", Font.PLAIN, 15));
					txtpnPleaseContactThe.setBackground(UIManager.getColor("menu"));
					txtpnPleaseContactThe.setEditable(false);
					txtpnPleaseContactThe.setText("Please contact the System\r\n  Administration Office to\r\n   obtain your password.\r\n          Thank you.");
					txtpnPleaseContactThe.setBounds(57, 11, 178, 80);
					
					popup.getContentPane().add(txtpnPleaseContactThe);
					JButton btnNo = new JButton("No");
					btnNo.addActionListener(new ActionListener()
					{
						@Override
						public void actionPerformed(ActionEvent e)
						{
							popup.dispose();
						}
					});
					btnNo.addKeyListener(new KeyAdapter()
					{
						public void keyPressed(KeyEvent ke)
						{
							if (ke.getKeyCode() == KeyEvent.VK_ESCAPE)
								popup.dispose();
						}
					});
					popup.getContentPane().add(btnNo);
				}
			}
		});
		add(btnForgotPassword);

		// Login will show up as a button with "Login" inside it
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(204, 272, 90, 23);
		btnLogin.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (usernameField.getText().length() > 0)
				{
					UserProfile user = StudentRegistrationMain.profiles.getUserProfile(usernameField.getText());

					if (user != null && user.passwordIs(new String(passwordField.getPassword())))
					{
						StudentRegistrationMain.logOut();
						StudentRegistrationMain.loggedIn.set(0, user);
						StudentRegistrationMain.replaceMainWindowContents(getGUIFromPerm(user.getPermLevel()));
					}
					else
					{
						createError();
					}
				}
				else
				{
					createError();
				}
			}
		});
		add(btnLogin);

		// User can press enter after entering password and it will click Login
		passwordField.addKeyListener(new KeyListener()
		{
			@Override
			public void keyTyped(KeyEvent e)
			{

				usernameField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
				passwordField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
				lblInvalidPassword.setText("");
				revalidate();
				repaint();

				if (e.getKeyChar() == KeyEvent.VK_ENTER)
				{
					btnLogin.doClick();
				}
			}

			@Override
			public void keyPressed(KeyEvent e)
			{
			}

			@Override
			public void keyReleased(KeyEvent e)
			{
			}
		});

		// User can press enter after entering username and it will click Login
		usernameField.addKeyListener(new KeyListener()
		{
			@Override
			public void keyTyped(KeyEvent e)
			{
				usernameField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
				passwordField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
				lblInvalidPassword.setText("");
				revalidate();
				repaint();
				if (e.getKeyChar() == KeyEvent.VK_ENTER)
				{
					btnLogin.doClick();
				}
			}

			@Override
			public void keyPressed(KeyEvent e)
			{
			}

			@Override
			public void keyReleased(KeyEvent e)
			{
			}
		});

		// btnGuest will show up as a button with "Guest" inside it
		JButton btnGuest = new JButton("Guest");
		btnGuest.setBounds(316, 272, 90, 23);
		btnGuest.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				StudentRegistrationMain.replaceMainWindowContents(new GUIViewCourses());
			}
		});
		add(btnGuest);

		// btnCreateApplication will show up as a button with "Apply for Registration" inside it
		JButton btnCreateApplication = new JButton("Apply for Registration");
		btnCreateApplication.setBounds(204, 329, 202, 23);
		btnCreateApplication.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				StudentRegistrationMain.replaceMainWindowContents(new GUICreateApplication());
			}
		});
		add(btnCreateApplication);
	}

	/**
	 * Creates error text
	 */
	void createError()
	{
		passwordField.setText("");
		lblInvalidPassword = new JLabel("Invalid Username/Password combination");
		passwordField.setBorder(BorderFactory.createLineBorder(Color.RED));
		usernameField.setBorder(BorderFactory.createLineBorder(Color.RED));
		lblInvalidPassword.setFont(new Font("Monospaced", Font.PLAIN, 12));
		lblInvalidPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblInvalidPassword.setForeground(new Color(255, 51, 0));
		lblInvalidPassword.setBounds(164, 300, 280, 25);
		add(lblInvalidPassword);
		revalidate();
		repaint();
	}

	/**
	 * 
	 * @param permLevel
	 *            the permission level of the user
	 * @return the GUI that should be shown based on perm level
	 */
	static JPanel getGUIFromPerm(int permLevel)
	{
		switch (permLevel)
		{
		case 0:
			return new GUILogin();
		case 1:
			return new GUIStudent();
		case 2:
			return new GUITeacherAssistant();
		case 3:
			return new GUITeacher();
		case 4:
			return new GUIAdmin();
		default:
			return new GUIViewCourses();
		}
	}
}
