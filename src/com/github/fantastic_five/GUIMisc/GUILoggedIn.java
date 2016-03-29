package com.github.fantastic_five.GUIMisc;

/**
 * @author Fantastic Five (Jose Stovall)
 * A JPanel displaying all teachers in the university
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.github.fantastic_five.StudentRegistrationMain;
import com.github.fantastic_five.GUI.GUILogin;

@SuppressWarnings("serial")
public class GUILoggedIn extends JPanel
{
	/**
	 * @return bar-style GUI for the current logged in user, including log-out button
	 */
	public GUILoggedIn()
	{
		// Quick and easy child class for the login bar - this will be added by EVERY GUI to avoid issues
		setLayout(null);
		setBounds(0, 0, 618, 24);

		// Sets a button and its actions
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.setBounds(537, 0, 71, 23);
		btnLogOut.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				StudentRegistrationMain.loggedIn.remove(0);
				StudentRegistrationMain.replaceMainWindowContents(new GUILogin());
			}
		});
		add(btnLogOut);

		JLabel lblCurrentLoggedIn = new JLabel("Current Logged In User:" + StudentRegistrationMain.loggedIn.get(0).getUserID());
		lblCurrentLoggedIn.setBounds(10, 4, 517, 14);
		add(lblCurrentLoggedIn);
	}
}
