package com.github.fantastic_five.GUIMisc;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
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
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class GUIChangeDetails extends JPanel
{
	private JTextField fieldFirstName;
	private JTextField fieldMiddleName;
	private JTextField fieldLastName;
	private JPasswordField originalPass;
	private JPasswordField newPass;
	private JPasswordField confPass;
	private JLabel nameChangeConfirmation;
	private JLabel passwordChangeConfirmation;

	public GUIChangeDetails()
	{
		setLayout(null);
		setBounds(0, 0, 618, 434);

		// Confirmation label for Name Change
		nameChangeConfirmation = new JLabel("");
		nameChangeConfirmation.setFont(new Font("Monospaced", Font.PLAIN, 32));
		nameChangeConfirmation.setHorizontalAlignment(SwingConstants.CENTER);
		nameChangeConfirmation.setBounds(353, 163, 56, 20);
		add(nameChangeConfirmation);

		// Confirmation label for Password Change
		passwordChangeConfirmation = new JLabel("");
		passwordChangeConfirmation.setFont(new Font("Monospaced", Font.PLAIN, 32));
		passwordChangeConfirmation.setHorizontalAlignment(SwingConstants.CENTER);
		passwordChangeConfirmation.setBounds(353, 377, 56, 20);
		add(passwordChangeConfirmation);

		// Panel Labels
		JLabel lblChangeDetails = new JLabel("Change Account Details");
		lblChangeDetails.setForeground(Color.GRAY);
		lblChangeDetails.setFont(new Font("Verdana", Font.BOLD, 16));
		lblChangeDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblChangeDetails.setBounds(10, 11, 598, 23);
		add(lblChangeDetails);

		JLabel lblResetPass = new JLabel("Reset Password");
		lblResetPass.setForeground(Color.GRAY);
		lblResetPass.setFont(new Font("Verdana", Font.BOLD, 16));
		lblResetPass.setHorizontalAlignment(SwingConstants.CENTER);
		lblResetPass.setBounds(10, 225, 598, 23);
		add(lblResetPass);

		// First Name
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFirstName.setHorizontalAlignment(SwingConstants.LEFT);
		lblFirstName.setBounds(102, 69, 111, 14);
		add(lblFirstName);

		fieldFirstName = new JTextField();
		fieldFirstName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fieldFirstName.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
				nameChangeConfirmation.setText("");				
				revalidate();
				repaint();
			}
		});
		fieldFirstName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				fieldFirstName.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
				nameChangeConfirmation.setText("");				
				revalidate();
				repaint();
			}
		});
		fieldFirstName.setColumns(10);
		fieldFirstName.setBounds(240, 67, 217, 20);
		fieldFirstName.setText(StudentRegistrationMain.getCurrentLoggedInUser().getFirstName());
		add(fieldFirstName);

		// Middle Name
		JLabel lblMiddleName = new JLabel("Middle Name:");
		lblMiddleName.setHorizontalAlignment(SwingConstants.LEFT);
		lblMiddleName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMiddleName.setBounds(102, 100, 111, 14);
		add(lblMiddleName);

		fieldMiddleName = new JTextField();
		fieldMiddleName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				fieldMiddleName.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
				nameChangeConfirmation.setText("");				
				revalidate();
				repaint();
			}
		});
		fieldMiddleName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fieldMiddleName.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
				nameChangeConfirmation.setText("");				
				revalidate();
				repaint();
			}
		});
		fieldMiddleName.setColumns(10);
		fieldMiddleName.setBounds(240, 98, 217, 20);
		fieldMiddleName.setText(StudentRegistrationMain.getCurrentLoggedInUser().getMiddleName());
		add(fieldMiddleName);

		// Last NAme
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setHorizontalAlignment(SwingConstants.LEFT);
		lblLastName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLastName.setBounds(102, 131, 111, 14);
		add(lblLastName);

		fieldLastName = new JTextField();
		fieldLastName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fieldLastName.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
				nameChangeConfirmation.setText("");				
				revalidate();
				repaint();
			}
		});
		fieldLastName.setColumns(10);
		fieldLastName.setBounds(240, 129, 217, 20);
		fieldLastName.setText(StudentRegistrationMain.getCurrentLoggedInUser().getLastName());
		add(fieldLastName);

		// Update Button
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(240, 160, 103, 23);
		btnUpdate.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (checkNameFields())
				{
					UserProfile loggedIn = StudentRegistrationMain.getCurrentLoggedInUser();
					loggedIn.setFirstName(fieldFirstName.getText());
					loggedIn.setMiddleName(fieldMiddleName.getText());
					loggedIn.setLastName(fieldLastName.getText());
					DatabaseIO.serializeEverything();
					displayNameChangeSuccess();
				}
				else
				{
					displayNameChangeError();
				}
			}
		});
		add(btnUpdate);

		fieldLastName.addKeyListener(new KeyListener()
		{			
			@Override
			public void keyTyped(KeyEvent e)
			{
				fieldLastName.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
				nameChangeConfirmation.setText("");				
				revalidate();
				repaint();
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
		newPass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				newPass.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
				confPass.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
				passwordChangeConfirmation.setText("");				
				revalidate();
				repaint();	
			}
		});
		newPass.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				newPass.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
				confPass.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
				passwordChangeConfirmation.setText("");				
				revalidate();
				repaint();				
			}
		});
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
		confPass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				newPass.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
				confPass.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
				passwordChangeConfirmation.setText("");				
				revalidate();
				repaint();	
			}
		});
		confPass.setColumns(10);
		confPass.setBounds(240, 335, 217, 20);
		add(confPass);

		// Change password button
		JButton btnPass = new JButton("Update");
		btnPass.setBounds(240, 374, 103, 23);
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
						displayPasswordChangeSuccess();
					}
					else
					{
						displayPasswordError(originalPass);
					}
				}
				else
				{
					displayPasswordError(newPass, confPass);
				}
			}
		});
		add(btnPass);

		confPass.addKeyListener(new KeyListener()
		{
			@Override
			public void keyTyped(KeyEvent e)
			{
				newPass.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
				confPass.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
				passwordChangeConfirmation.setText("");				
				revalidate();
				repaint();	
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
					// Gets all active windows
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
	 * Shows a green Check-mark to the user that the class was added okay
	 */
	void displayPasswordChangeSuccess()
	{
		resetFieldColors();
		passwordChangeConfirmation.setText("\u2713");
		passwordChangeConfirmation.setForeground(Color.GREEN);
		revalidate();
		repaint();
	}
	
	/**
	 * Sets the background of the passed text field to be red to alert the user, as well as a red text notifier
	 * 
	 * @param erroredFields
	 *            The text field to set the background red of. Can be passed multiple fields to set red
	 */
	void displayPasswordError(JPasswordField... erroredFields)
	{
		resetFieldColors();
		for (JPasswordField field : erroredFields)
			field.setBorder(BorderFactory.createLineBorder(Color.RED));
		passwordChangeConfirmation.setText("\u2717");
		passwordChangeConfirmation.setForeground(Color.RED);
		revalidate();
		repaint();
	}
	
	/**
	 * Shows a green Check-mark to the user that the class was added okay
	 */
	void displayNameChangeSuccess()
	{
		nameChangeConfirmation.setText("\u2713");
		nameChangeConfirmation.setForeground(Color.GREEN);
		revalidate();
		repaint();
	}
	
	/**
	 * @return true if all the name fields are full (of something at least), false otherwise
	 */
	
	boolean checkNameFields()
	{
		resetFieldColors();		
		if (fieldFirstName.getText().length() == 0)
		{
			displayNameChangeError(fieldFirstName);
			return false;
		}
		if (fieldMiddleName.getText().length() == 0)
		{
			displayNameChangeError(fieldMiddleName);
			return false;
		}
		if (fieldLastName.getText().length() == 0)
		{
			displayNameChangeError(fieldLastName);
			return false;
		}		
		return true;
	}

	/**
	 * Shows a red X to the user that the class was added okay
	 */
	void displayNameChangeError(JTextField... erroredFields)
	{
		resetFieldColors();
		for (JTextField field : erroredFields)
		{
			field.setBorder(BorderFactory.createLineBorder(Color.RED));
		}
		nameChangeConfirmation.setText("\u2717");
		nameChangeConfirmation.setForeground(Color.RED);
		revalidate();
		repaint();
	}

	/**
	 * Resets all field colors back to default color
	 */
	void resetFieldColors()
	{
		confPass.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		newPass.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		originalPass.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
	}
}