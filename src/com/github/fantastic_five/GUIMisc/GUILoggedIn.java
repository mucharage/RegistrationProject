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
				// TODO Make button log out
			}
		});
		add(btnLogOut);

		// Sets a label, TODO: make the label show the logged in user
		JLabel lblCurrentLoggedIn = new JLabel("Current Logged In User:");
		lblCurrentLoggedIn.setBounds(10, 4, 117, 14);
		add(lblCurrentLoggedIn);
	}
}
