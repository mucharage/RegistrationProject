package com.github.fantastic_five.GUIAdministrator;

/**
 * @author Fantastic Five (Jose Stovall)
 * A JPanel which adds new teacher UserProfiles to the UserProfileDatabase
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.github.fantastic_five.StudentRegistrationMain;
import com.github.fantastic_five.GUIMisc.GUILogStatus;
import com.github.fantastic_five.Logic.UserProfile;
import com.github.fantastic_five.Logic.UserProfileDatabase;
import javax.swing.JCheckBox;

@SuppressWarnings("serial")
public class GUIAddTeacher extends JPanel
{
	private JTextField firstnameTextField;
	private JTextField middlenameTextField;
	private JTextField lastnameTextField;
	private JTextField userIDTextField;
	private JTextField passwordTextField;
	private JCheckBox checkBoxIsTA;

	public GUIAddTeacher()
	{
		setLayout(null);
		setBounds(0, 0, 618, 434);

		// Adds the login panel to this window
		JPanel loginPanel = new GUILogStatus();
		loginPanel.setBounds(0, 0, 618, 24);
		add(loginPanel);

		// Panel Label
		JLabel lblCreateTeacher = new JLabel("Add Teacher Account");
		lblCreateTeacher.setForeground(Color.GRAY);
		lblCreateTeacher.setFont(new Font("Verdana", Font.BOLD, 16));
		lblCreateTeacher.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreateTeacher.setBounds(183, 44, 243, 21);
		add(lblCreateTeacher);

		// First Name
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFirstName.setHorizontalAlignment(SwingConstants.LEFT);
		lblFirstName.setBounds(102, 136, 128, 14);
		add(lblFirstName);

		firstnameTextField = new JTextField();
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
		passwordTextField.setColumns(10);
		passwordTextField.setBounds(252, 258, 217, 20);
		add(passwordTextField);

		// Is TA checkbox
		JLabel labelIsTA = new JLabel("Is Teacher TA?:");
		labelIsTA.setHorizontalAlignment(SwingConstants.LEFT);
		labelIsTA.setFont(new Font("Tahoma", Font.BOLD, 12));
		labelIsTA.setBounds(102, 296, 128, 14);
		add(labelIsTA);
		
		checkBoxIsTA = new JCheckBox("");
		checkBoxIsTA.setBounds(248, 290, 21, 23);
		add(checkBoxIsTA);


		// Back Button
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(10, 386, 128, 23);
		btnBack.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				StudentRegistrationMain.replaceMainWindowContents(new GUIAdmin());
			}
		});
		add(btnBack);

		JButton btnCreate = new JButton("Create");
		btnCreate.setBounds(252, 330, 217, 23);
		btnCreate.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int permLvl = checkBoxIsTA.isSelected() ? UserProfile.TA : UserProfile.TEACHER;
				String fName = firstnameTextField.getText();
				String mName = middlenameTextField.getText();
				String lName = lastnameTextField.getText();
				String userID = userIDTextField.getText();
				String pwd = passwordTextField.getText();
				UserProfileDatabase.addUser(new UserProfile(userID, pwd, permLvl, fName, mName, lName));
				clearFields();
			}
		});
		add(btnCreate);

		passwordTextField.addKeyListener(new KeyListener()
		{
			@Override
			public void keyTyped(KeyEvent e)
			{
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

	/**
	 * Clears all of the text fields in the window
	 */
	void clearFields()
	{
		firstnameTextField.setText("");
		middlenameTextField.setText("");
		lastnameTextField.setText("");
		userIDTextField.setText("");
		passwordTextField.setText("");
		checkBoxIsTA.setSelected(false);
	}
}
