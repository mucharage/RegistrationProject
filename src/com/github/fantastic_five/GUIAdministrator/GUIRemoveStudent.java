package com.github.fantastic_five.GUIAdministrator;

/**
 * @author Fantastic Five (Jose Stovall)
 * A JPanel which removes a student UserProfile from the UserProfileDatabase
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.github.fantastic_five.StudentRegistrationMain;
import com.github.fantastic_five.GUIMisc.GUILogStatus;
import com.github.fantastic_five.Logic.UserProfileDatabase;

@SuppressWarnings("serial")
public class GUIRemoveStudent extends JPanel
{
	private JTextField userIDTextField;

	public GUIRemoveStudent()
	{
		setLayout(null);
		setBounds(0, 0, 618, 434);

		// Adds the login panel to this window
		JPanel loginPanel = new GUILogStatus();
		loginPanel.setBounds(0, 0, 618, 24);
		add(loginPanel);

		// Panel Label
		JLabel lblCreateTeacher = new JLabel("Remove Student Account");
		lblCreateTeacher.setForeground(Color.GRAY);
		lblCreateTeacher.setFont(new Font("Verdana", Font.BOLD, 16));
		lblCreateTeacher.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreateTeacher.setBounds(188, 44, 243, 21);
		add(lblCreateTeacher);

		// User ID Label & Field
		JLabel lblUserID = new JLabel("User ID To Remove:");
		lblUserID.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUserID.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserID.setBounds(242, 152, 128, 37);
		add(lblUserID);

		userIDTextField = new JTextField();
		userIDTextField.setBounds(212, 200, 190, 20);
		userIDTextField.setColumns(10);
		add(userIDTextField);

		// Remove Course Button and all of its actions
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener()
		{
			// Makes a pop-up dialog window
			public void actionPerformed(ActionEvent e)
			{
				// Creates a pop-up window
				JFrame popup = new JFrame("Confirmation");
				popup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				popup.setPreferredSize(new Dimension(307, 107));
				popup.setResizable(false);

				JPanel GUI = new JPanel();
				GUI.setLayout(null);

				// Confirmation label
				JLabel lblAreYouSure = new JLabel("Are you sure?");
				lblAreYouSure.setForeground(Color.RED);
				lblAreYouSure.setFont(new Font("Tahoma", Font.BOLD, 16));
				lblAreYouSure.setBounds(86, 11, 127, 20);
				GUI.add(lblAreYouSure);

				// No button should remove the CRN from the list
				JButton btnNo = new JButton("No");
				btnNo.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						popup.dispose();
					}
				});
				btnNo.setBounds(191, 49, 100, 23);
				GUI.add(btnNo);

				// Yes button closes the window
				JButton btnYes = new JButton("Yes");
				btnYes.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						UserProfileDatabase.removeUser(userIDTextField.getText());
						userIDTextField.setText("");
						popup.dispose();
					}
				});
				btnYes.setBounds(10, 49, 100, 23);
				GUI.add(btnYes);

				// Finalizes the popup window
				popup.getContentPane().add(GUI);
				popup.pack();
				popup.setVisible(true);
				popup.setLocationRelativeTo(StudentRegistrationMain.mainWindow);
			}
		});
		btnRemove.setBounds(212, 286, 190, 23);
		add(btnRemove);

		// Allows the user to hit "Enter" from the text box to continue
		userIDTextField.addKeyListener(new KeyListener()
		{
			@Override
			public void keyTyped(KeyEvent e)
			{
				if (e.getKeyChar() == KeyEvent.VK_ENTER)
				{
					btnRemove.doClick();
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
	}
}
