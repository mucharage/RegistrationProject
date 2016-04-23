package com.github.fantastic_five.GUI;

/**
 * @author Fantastic Five (Jose Stovall in AddUser; edited by Steven Hullander here)
 * A JPanel which creates an account that must be
 * accepted/declined by admin before becoming a userprofile.
 */

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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.github.fantastic_five.StudentRegistrationMain;
import com.github.fantastic_five.GUIMisc.GUILogStatus;
import com.github.fantastic_five.Logic.PendingApplication;

@SuppressWarnings("serial")
public class GUICreateApplication extends JPanel
{
	private JTextField firstnameTextField;
	private JTextField middlenameTextField;
	private JTextField lastnameTextField;
	private JTextField userIDTextField;
	private JTextField passwordTextField;
	private JLabel confirmation;

	public GUICreateApplication()
	{
		setLayout(null);
		setBounds(0, 0, 618, 434);

		// Adds the login panel to this window
		JPanel loginPanel = new GUILogStatus();
		loginPanel.setBounds(0, 0, 618, 24);
		add(loginPanel);

		// Panel Label
		JLabel lblCreateStudent = new JLabel("Student Registration");
		lblCreateStudent.setForeground(Color.GRAY);
		lblCreateStudent.setFont(new Font("Verdana", Font.BOLD, 16));
		lblCreateStudent.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreateStudent.setBounds(0, 56, 618, 21);
		add(lblCreateStudent);

		// First Name
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFirstName.setHorizontalAlignment(SwingConstants.LEFT);
		lblFirstName.setBounds(102, 136, 128, 14);
		add(lblFirstName);

		firstnameTextField = new JTextField();
		firstnameTextField.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyTyped(KeyEvent e)
			{
				firstnameTextField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
				confirmation.setText("");
				revalidate();
				repaint();
			}
		});
		firstnameTextField.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				firstnameTextField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
				confirmation.setText("");
				revalidate();
				repaint();
			}
		});
		firstnameTextField.setBounds(252, 132, 217, 20);
		add(firstnameTextField);
		firstnameTextField.setColumns(10);

		// Middle Name
		JLabel lblMiddleName = new JLabel("Middle Name:");
		lblMiddleName.setHorizontalAlignment(SwingConstants.LEFT);
		lblMiddleName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMiddleName.setBounds(102, 168, 128, 14);
		add(lblMiddleName);

		middlenameTextField = new JTextField();
		middlenameTextField.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				middlenameTextField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
				confirmation.setText("");
				revalidate();
				repaint();
			}
		});
		middlenameTextField.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyTyped(KeyEvent e)
			{
				middlenameTextField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
				confirmation.setText("");
				revalidate();
				repaint();
			}
		});
		middlenameTextField.setColumns(10);
		middlenameTextField.setBounds(252, 164, 217, 20);
		add(middlenameTextField);

		// Last Name
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setHorizontalAlignment(SwingConstants.LEFT);
		lblLastName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLastName.setBounds(102, 200, 128, 14);
		add(lblLastName);

		lastnameTextField = new JTextField();
		lastnameTextField.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				lastnameTextField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
				confirmation.setText("");
				revalidate();
				repaint();
			}
		});
		lastnameTextField.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyTyped(KeyEvent e)
			{
				lastnameTextField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
				confirmation.setText("");
				revalidate();
				repaint();
			}
		});
		lastnameTextField.setColumns(10);
		lastnameTextField.setBounds(252, 195, 217, 20);
		add(lastnameTextField);

		// User ID
		JLabel lblUserID = new JLabel("User ID:");
		lblUserID.setHorizontalAlignment(SwingConstants.LEFT);
		lblUserID.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUserID.setBounds(102, 232, 128, 14);
		add(lblUserID);

		userIDTextField = new JTextField();
		userIDTextField.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyTyped(KeyEvent e)
			{
				userIDTextField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
				confirmation.setText("");
				revalidate();
				repaint();
			}
		});
		userIDTextField.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				userIDTextField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
				confirmation.setText("");
				revalidate();
				repaint();
			}
		});
		userIDTextField.setColumns(10);
		userIDTextField.setBounds(252, 226, 217, 20);
		add(userIDTextField);

		// Password
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPassword.setBounds(102, 264, 128, 14);
		add(lblPassword);

		passwordTextField = new JTextField();
		passwordTextField.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				passwordTextField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
				confirmation.setText("");
				revalidate();
				repaint();
			}
		});
		passwordTextField.setColumns(10);
		passwordTextField.setBounds(252, 258, 217, 20);
		add(passwordTextField);

		// Confirmation thingy
		confirmation = new JLabel("");
		confirmation.setFont(new Font("Monospaced", Font.PLAIN, 32));
		confirmation.setHorizontalAlignment(SwingConstants.CENTER);
		confirmation.setBounds(252, 354, 217, 20);
		add(confirmation);

		JButton btnBack = new UniversalBackButton();
		btnBack.setBounds(10, 386, 128, 23);
		add(btnBack);

		JButton btnCreate = new JButton("Apply");
		btnCreate.setBounds(252, 300, 217, 23);
		btnCreate.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (checkFields())
				{
					displaySuccess();
					parseFields();
					// Initialize frame as a new JFrame
					JDialog popup = new JDialog(StudentRegistrationMain.mainWindow, "Application Created");
					// Set the dimensions of the frame
					popup.setBounds(100, 100, 303, 141);
					// When the frame is closed, it simply goes away
					popup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					popup.setLocationRelativeTo(StudentRegistrationMain.mainWindow);
					popup.getContentPane().setLayout(null);
					popup.setResizable(false);
					popup.setVisible(true);
					popup.setAlwaysOnTop(true);

					// Create a text area to go inside the frame
					JTextArea txtpnPleaseContactThe = new JTextArea();
					// Making the font "Tahoma", plain text, size 15
					txtpnPleaseContactThe.setFont(new Font("Tahoma", Font.PLAIN, 15));
					// Menu is the color of the text area background
					txtpnPleaseContactThe.setBackground(UIManager.getColor("menu"));
					// Make the text not editable by the user
					txtpnPleaseContactThe.setEditable(false);
					// The text that is displayed
					txtpnPleaseContactThe.setText("Congratulations! You have finished\r\nyour registration application. Please\r\n wait for administration to confirm\r\n your application before you login.\r\n               Thank you.");
					// Set the dimensions of the frame
					txtpnPleaseContactThe.setBounds(30, 6, 250, 100);
					// add the text area to the pane and frame
					popup.getContentPane().add(txtpnPleaseContactThe);
					resetFields();
					JButton fakeButton = new JButton("No");
					fakeButton.addActionListener(new ActionListener()
					{
						@Override
						public void actionPerformed(ActionEvent e)
						{
							popup.dispose();
						}
					});
					fakeButton.addKeyListener(new KeyAdapter()
					{
						public void keyPressed(KeyEvent ke)
						{ // handler
							if (ke.getKeyCode() == KeyEvent.VK_ESCAPE)
							{
								popup.dispose();
							}
						}
					});
					popup.getContentPane().add(fakeButton);
				}
			}
		});
		add(btnCreate);

		passwordTextField.addKeyListener(new KeyListener()
		{
			@Override
			public void keyTyped(KeyEvent e)
			{
				passwordTextField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
				confirmation.setText("");
				revalidate();
				repaint();

				if (e.getKeyChar() == KeyEvent.VK_ENTER)
				{
					btnCreate.doClick();
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
	}

	void parseFields()
	{
		String firstName = firstnameTextField.getText();
		String middleName = middlenameTextField.getText();
		String lastName = lastnameTextField.getText();
		String userID = userIDTextField.getText();
		String password = passwordTextField.getText();

		PendingApplication app = new PendingApplication(userID, password, firstName, middleName, lastName);
		StudentRegistrationMain.pendingApplications.addApplication(app);
	}

	/**
	 * Checks to see that all the text fields have SOMETHING in them..
	 * 
	 * @return True if all fields are populated and valid, false otherwise
	 */
	boolean checkFields()
	{
		if (firstnameTextField.getText().length() <= 0)
		{
			displayError(firstnameTextField);
			return false;
		}
		if (lastnameTextField.getText().length() <= 0)
		{
			displayError(lastnameTextField);
			return false;
		}
		if (userIDTextField.getText().length() <= 0 || StudentRegistrationMain.profiles.hasUser(userIDTextField.getText()) || StudentRegistrationMain.pendingApplications.hasApplication(userIDTextField.getText()))
		{
			displayError(userIDTextField);
			if (StudentRegistrationMain.profiles.hasUser(userIDTextField.getText()))
			{
				confirmation.setFont(new Font("Monospaced", Font.PLAIN, 12));
				confirmation.setForeground(Color.RED);
				confirmation.setText("User ID conflict");
				revalidate();
				repaint();
			}
			return false;
		}
		if (passwordTextField.getText().length() <= 0)
		{
			displayError(passwordTextField);
			return false;
		}
		resetFieldColors();
		return true;
	}

	/**
	 * Sets the background of the passed text field to be red to alert the user, as well as a red text notifier
	 * 
	 * @param erroredFields
	 *            The text field to set the background red of. Can be passed multiple fields to set red
	 */
	void displayError(JTextField... erroredFields)
	{
		resetFieldColors();
		for (JTextField field : erroredFields)
			field.setBorder(BorderFactory.createLineBorder(Color.RED));;
		confirmation.setText("\u2717");
		confirmation.setForeground(Color.RED);
		revalidate();
		repaint();
	}

	/**
	 * Clears all of the text fields in the window
	 */
	void resetFields()
	{
		firstnameTextField.setText("");
		middlenameTextField.setText("");
		lastnameTextField.setText("");
		userIDTextField.setText("");
		passwordTextField.setText("");
	}

	/**
	 * sets all field colors to white - separate because sometimes I need their colors reset but not their text
	 */
	void resetFieldColors()
	{
		firstnameTextField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		middlenameTextField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		lastnameTextField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		userIDTextField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		passwordTextField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
	}

	void displaySuccess()
	{
		confirmation.setText("\u2713");
		confirmation.setForeground(Color.GREEN);
		revalidate();
		repaint();
	}
}