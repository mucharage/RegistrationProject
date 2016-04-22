package com.github.fantastic_five.GUIAdministrator;

/**
 * @author Fantastic Five (Jose Stovall)
 * A JPanel which adds new student UserProfiles to the UserProfileDatabase
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.github.fantastic_five.StudentRegistrationMain;
import com.github.fantastic_five.GUI.UniversalBackButton;
import com.github.fantastic_five.GUIMisc.GUILogStatus;
import com.github.fantastic_five.Logic.UserProfile;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;

@SuppressWarnings("serial")
public class GUIAddUser extends JPanel
{
	private JTextField firstnameTextField;
	private JTextField middlenameTextField;
	private JTextField lastnameTextField;
	private JTextField userIDTextField;
	private JTextField passwordTextField;
	private JLabel confirmation;
	private final String STUDENT = "Student";
	private final String TEACHER = "Teacher";
	private final String TA = "Teacher's Assistant";
	private final String ADMIN = "Administrator";

	public GUIAddUser()
	{
		setLayout(null);
		setBounds(0, 0, 618, 434);

		// Adds the login panel to this window
		JPanel loginPanel = new GUILogStatus();
		loginPanel.setBounds(0, 0, 618, 24);
		add(loginPanel);

		// Panel Label
		JLabel lblCreateStudent = new JLabel("Add User");
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
		firstnameTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				firstnameTextField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
				confirmation.setText("");
				revalidate();
				repaint();
			}
		});
		firstnameTextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
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
		middlenameTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				middlenameTextField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
				confirmation.setText("");
				revalidate();
				repaint();
			}
		});
		middlenameTextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
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
		lastnameTextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lastnameTextField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
				confirmation.setText("");
				revalidate();
				repaint();
			}
		});
		lastnameTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				lastnameTextField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
				confirmation.setText("");
				revalidate();
				repaint();
			}
		});
		lastnameTextField.setColumns(10);
		lastnameTextField.setBounds(252, 196, 217, 20);
		add(lastnameTextField);

		// User ID
		JLabel lblUserID = new JLabel("User ID:");
		lblUserID.setHorizontalAlignment(SwingConstants.LEFT);
		lblUserID.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUserID.setBounds(102, 232, 128, 14);
		add(lblUserID);

		userIDTextField = new JTextField();
		userIDTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				userIDTextField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
				confirmation.setText("");
				revalidate();
				repaint();
			}
		});
		userIDTextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
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
		passwordTextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				passwordTextField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
				confirmation.setText("");
				revalidate();
				repaint();
			}
		});
		passwordTextField.setColumns(10);
		passwordTextField.setBounds(252, 258, 217, 20);
		add(passwordTextField);

		// Confirmation thing
		confirmation = new JLabel("");
		confirmation.setHorizontalAlignment(SwingConstants.CENTER);
		confirmation.setBounds(252, 354, 217, 20);
		add(confirmation);

		JButton btnBack = new UniversalBackButton();
//		JButton btnBack = new JButton("Back");
		btnBack.setBounds(10, 386, 128, 23);
//		btnBack.addActionListener(new ActionListener()
//		{
//			public void actionPerformed(ActionEvent e)
//			{
//				StudentRegistrationMain.replaceMainWindowContents(new GUIAdmin());
//			}
//		});
		add(btnBack);

		JComboBox<String> permDropdown = new JComboBox<String>();
		permDropdown.addItem(this.STUDENT);
		permDropdown.addItem(this.TEACHER);
		permDropdown.addItem(this.TA);
		permDropdown.addItem(this.ADMIN);
		permDropdown.setEditable(false);
		permDropdown.setBounds(252, 290, 217, 20);
		add(permDropdown);

		JButton btnCreate = new JButton("Create");
		btnCreate.setBounds(252, 322, 217, 23);
		btnCreate.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String fName = firstnameTextField.getText();
				String mName = middlenameTextField.getText();
				String lName = lastnameTextField.getText();
				String userID = userIDTextField.getText();
				String pwd = passwordTextField.getText();
				if (checkFields())
				{
					int permLevel = getPermFromString((String) permDropdown.getSelectedItem());
					// Shouldn't ever be "GUEST", but let's be safe
					if (permLevel == UserProfile.GUEST)
					{
						permDropdown.setForeground(Color.RED);
						return;
					}

					StudentRegistrationMain.profiles.addUser(new UserProfile(userID, pwd, permLevel, fName, mName, lName));
					if (permLevel == UserProfile.STUDENT || permLevel == UserProfile.TA)
						StudentRegistrationMain.financialRecords.addUser(new UserProfile(userID, pwd, 1, fName, mName, lName));

					confirmation.setFont(new Font("Monospaced", Font.PLAIN, 32));
					confirmation.setText("\u2713");
					confirmation.setForeground(Color.GREEN);
					resetFields();
				}
				revalidate();
				repaint();
			}
		});
		add(btnCreate);

		JLabel lblRole = new JLabel("Role:");
		lblRole.setHorizontalAlignment(SwingConstants.LEFT);
		lblRole.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRole.setBounds(102, 293, 128, 14);
		add(lblRole);

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

	public int getPermFromString(String s)
	{
		switch (s)
		{
		case STUDENT:
			return UserProfile.STUDENT;
		case TEACHER:
			return UserProfile.TEACHER;
		case TA:
			return UserProfile.TA;
		case ADMIN:
			return UserProfile.ADMIN;
		default:
			return UserProfile.GUEST;
		}
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
		if (middlenameTextField.getText().length() <= 0)
		{
			displayError(middlenameTextField);
			return false;
		}
		if (lastnameTextField.getText().length() <= 0)
		{
			displayError(lastnameTextField);
			return false;
		}
		if (userIDTextField.getText().length() <= 0 || StudentRegistrationMain.profiles.hasUser(userIDTextField.getText()))
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
}