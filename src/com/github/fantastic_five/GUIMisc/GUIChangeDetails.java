package com.github.fantastic_five.GUIMisc;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.github.fantastic_five.StudentRegistrationMain;
import com.github.fantastic_five.GUI.GUILogin;
import com.github.fantastic_five.Logic.DatabaseIO;
import com.github.fantastic_five.Logic.UserProfile;

@SuppressWarnings("serial")
public class GUIChangeDetails extends JPanel
{
	private JTextField fieldFirstName;
	private JTextField fieldMiddleName;
	private JTextField fieldLastName;
	private JPasswordField originalPass;
	private JPasswordField newPass;
	private JPasswordField confPass;

	public GUIChangeDetails()
	{
		setLayout(null);
		setBounds(0, 0, 618, 434);

		// Panel Labels
		JLabel lblChangeDetails = new JLabel("Change Account Details");
		lblChangeDetails.setForeground(Color.GRAY);
		lblChangeDetails.setFont(new Font("Verdana", Font.BOLD, 16));
		lblChangeDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblChangeDetails.setBounds(177, 11, 243, 23);
		add(lblChangeDetails);

		JLabel lblResetPass = new JLabel("Reset Password");
		lblResetPass.setForeground(Color.GRAY);
		lblResetPass.setFont(new Font("Verdana", Font.BOLD, 16));
		lblResetPass.setHorizontalAlignment(SwingConstants.CENTER);
		lblResetPass.setBounds(177, 225, 243, 23);
		add(lblResetPass);

		// First Name
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFirstName.setHorizontalAlignment(SwingConstants.LEFT);
		lblFirstName.setBounds(102, 100, 111, 14);
		add(lblFirstName);

		fieldFirstName = new JTextField();
		fieldFirstName.setColumns(10);
		fieldFirstName.setBounds(240, 98, 217, 20);
		fieldFirstName.setText(StudentRegistrationMain.getCurrentLoggedInUser().getFirstName());
		add(fieldFirstName);

		// Middle Name
		JLabel lblMiddleName = new JLabel("Middle Name:");
		lblMiddleName.setHorizontalAlignment(SwingConstants.LEFT);
		lblMiddleName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMiddleName.setBounds(102, 131, 111, 14);
		add(lblMiddleName);

		fieldMiddleName = new JTextField();
		fieldMiddleName.setColumns(10);
		fieldMiddleName.setBounds(240, 129, 217, 20);
		fieldMiddleName.setText(StudentRegistrationMain.getCurrentLoggedInUser().getMiddleName());
		add(fieldMiddleName);

		// Last NAme
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setHorizontalAlignment(SwingConstants.LEFT);
		lblLastName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLastName.setBounds(102, 162, 111, 14);
		add(lblLastName);

		fieldLastName = new JTextField();
		fieldLastName.setColumns(10);
		fieldLastName.setBounds(240, 160, 217, 20);
		fieldLastName.setText(StudentRegistrationMain.getCurrentLoggedInUser().getLastName());
		add(fieldLastName);

		// Update Button
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(240, 192, 127, 23);
		btnUpdate.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				UserProfile loggedIn = StudentRegistrationMain.getCurrentLoggedInUser();
				loggedIn.setFirstName(fieldFirstName.getText());
				loggedIn.setMiddleName(fieldMiddleName.getText());
				loggedIn.setLastName(fieldLastName.getText());
				DatabaseIO.serializeEverything();
				// TODO: show confirmation notification
			}
		});
		add(btnUpdate);

		fieldLastName.addKeyListener(new KeyListener()
		{
			@Override
			public void keyTyped(KeyEvent e)
			{
				if (e.getKeyChar() == KeyEvent.VK_ENTER)
				{
					btnUpdate.doClick();
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

		// Original Password
		JLabel lblOrigPass = new JLabel("Original Password:");
		lblOrigPass.setHorizontalAlignment(SwingConstants.LEFT);
		lblOrigPass.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblOrigPass.setBounds(100, 275, 113, 14);
		add(lblOrigPass);

		originalPass = new JPasswordField();
		originalPass.setColumns(10);
		originalPass.setBounds(240, 273, 217, 20);
		add(originalPass);

		// New Password
		JLabel lblNewPass = new JLabel("New Password:");
		lblNewPass.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewPass.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewPass.setBounds(100, 306, 113, 14);
		add(lblNewPass);

		newPass = new JPasswordField();
		newPass.setColumns(10);
		newPass.setBounds(240, 304, 217, 20);
		add(newPass);

		// Confirm new password
		JLabel lblConf = new JLabel("Confirm:");
		lblConf.setHorizontalAlignment(SwingConstants.LEFT);
		lblConf.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblConf.setBounds(100, 337, 113, 14);
		add(lblConf);

		confPass = new JPasswordField();
		confPass.setColumns(10);
		confPass.setBounds(240, 335, 217, 20);
		add(confPass);

		// Change password button
		JButton btnPass = new JButton("Update Password");
		btnPass.setBounds(240, 367, 127, 23);
		btnPass.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String newPassString = new String(newPass.getPassword());
				String newPassStringConf = new String(confPass.getPassword());
				// Confirms that at least ONE of the new passwords is filled and the other one has to match IT, so only check once
				if (newPassString.length() > 0 && newPassString.equals(newPassStringConf))
				{
					if (StudentRegistrationMain.getCurrentLoggedInUser().setPassword(newPassString, new String(originalPass.getPassword())))
					{
						resetFieldColors();
						originalPass.setText("");
						newPass.setText("");
						confPass.setText("");
						DatabaseIO.serializeEverything();
					}
					else
					{
						displayError(originalPass);
					}
				}
				else
				{
					displayError(newPass, confPass);
				}
			}
		});
		add(btnPass);

		confPass.addKeyListener(new KeyListener()
		{
			@Override
			public void keyTyped(KeyEvent e)
			{
				if (e.getKeyChar() == KeyEvent.VK_ENTER)
				{
					btnPass.doClick();
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

		JButton btnBack = new JButton("Back (to Login)");
		btnBack.setBounds(10, 400, 103, 23);
		btnBack.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// Checks to see TA status
				if (StudentRegistrationMain.getCurrentLoggedInUser() != null && StudentRegistrationMain.getCurrentLoggedInUser().getPermLevel() == UserProfile.TA)
				{
					// Gets all activev windows
					Frame frames[] = Frame.getFrames();

					// Closes all windows that aren't the main window
					for (Frame f : frames)
						if (f instanceof JFrame && !(f == StudentRegistrationMain.mainWindow))
							f.dispose();

					// Resets the main window
					StudentRegistrationMain.logOut();
					StudentRegistrationMain.mainWindow.getContentPane().removeAll();
					StudentRegistrationMain.mainWindow.getContentPane().add(new GUILogin());
					StudentRegistrationMain.mainWindow.pack();

				}
				// Isn't a TA:
				else
				{
					StudentRegistrationMain.logOut();
					StudentRegistrationMain.replaceMainWindowContents(new GUILogin());
				}
			}
		});
		add(btnBack);
	}

	/**
	 * Sets the background of the passed text field to be red to alert the user, as well as a red text notifier
	 * 
	 * @param erroredFields
	 *            The text field to set the background red of. Can be passed multiple fields to set red
	 */
	void displayError(JPasswordField... erroredFields)
	{
		resetFieldColors();
		for (JPasswordField field : erroredFields)
			field.setBackground(Color.RED);
		revalidate();
		repaint();
	}

	void resetFieldColors()
	{
		confPass.setBackground(Color.WHITE);
		newPass.setBackground(Color.WHITE);
		originalPass.setBackground(Color.WHITE);
	}
}