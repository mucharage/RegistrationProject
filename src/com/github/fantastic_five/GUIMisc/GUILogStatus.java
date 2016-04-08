package com.github.fantastic_five.GUIMisc;

import java.awt.Color;
import java.awt.Frame;
/**
 * @author Fantastic Five (Jose Stovall)
 * A JPanel displaying all teachers in the university
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.github.fantastic_five.StudentRegistrationMain;
import com.github.fantastic_five.GUI.GUILogin;
import com.github.fantastic_five.Logic.UserProfile;

@SuppressWarnings("serial")
public class GUILogStatus extends JPanel
{
	/**
	 * @return bar-style GUI for the current logged in user, including log-out button
	 */
	public GUILogStatus()
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
				// Checks to see TA status
				if (StudentRegistrationMain.loggedIn.size() > 0 && StudentRegistrationMain.loggedIn.get(0).getPermLevel() == UserProfile.TA)
				{
					// Gets all activev windows
					Frame frames[] = Frame.getFrames();

					// Closes all windows that aren't the main window
					for (Frame f : frames)
						if (f instanceof JFrame && !(f == StudentRegistrationMain.mainWindow))
							f.dispose();

					// Resets the main window
					StudentRegistrationMain.loggedIn.remove(0);
					StudentRegistrationMain.mainWindow.getContentPane().removeAll();
					StudentRegistrationMain.mainWindow.getContentPane().add(new GUILogin());
					StudentRegistrationMain.mainWindow.pack();

				}
				// Isn't a TA:
				else
				{
					StudentRegistrationMain.loggedIn.remove(0);
					StudentRegistrationMain.replaceMainWindowContents(new GUILogin());
				}
			}
		});
		add(btnLogOut);
		UserProfile u = StudentRegistrationMain.loggedIn.get(0);
		String currentUser = u.getUserID();
		JButton btnCurrentUser = new JButton(currentUser);
		if(u.getPermLevel() == 0){btnCurrentUser.setBounds(172, 1, 100, 20);}
		else if(u.getPermLevel() == 1 || u.getPermLevel() == 3){btnCurrentUser.setBounds(172, 1, 100, 20);}
		else if(u.getPermLevel() == 2){btnCurrentUser.setBounds(226, 1, 100, 20);}
		else if(u.getPermLevel() == 4){btnCurrentUser.setBounds(163, 1, 100, 20);}
		btnCurrentUser.setHorizontalAlignment(SwingConstants.LEFT);
		btnCurrentUser.setContentAreaFilled(false);
		btnCurrentUser.setBorderPainted(false);
		btnCurrentUser.setForeground(Color.BLUE);
		btnCurrentUser.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				{
					//TODO implement what happens when user name is clicked
				}
			}});
		add(btnCurrentUser);
					
		JLabel lblCurrentLoggedIn = new JLabel("Current Logged In User (" + getPermDescriptionFromInt(u.getPermLevel()) + "):");
		lblCurrentLoggedIn.setBounds(10, 4, 517, 14);
		add(lblCurrentLoggedIn);
		}
		
		
	public String getPermDescriptionFromInt(int level)
	{
		switch (level)
		{
		case 0:
			return "Guest";
		case 1:
			return "Student";
		case 2:
			return "Teacher's Assistant";
		case 3:
			return "Teacher";
		case 4:
			return "Admin";
		default:
			return "";
		}
	}
}
