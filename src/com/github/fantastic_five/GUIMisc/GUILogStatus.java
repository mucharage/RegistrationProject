package com.github.fantastic_five.GUIMisc;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
/**
 * @author Fantastic Five (Jose Stovall)
 * A JPanel displaying all teachers in the university
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

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
		add(btnLogOut);

		JLabel currentLoggedInPrefix = new JLabel("Current Logged In User: ");
		currentLoggedInPrefix.setBounds(10, 4, 120, 14);
		add(currentLoggedInPrefix);
		
		UserProfile u = StudentRegistrationMain.getCurrentLoggedInUser();
		JButton currentLoggedIn = new JButton(u.getFirstName() + " " + u.getLastName() + " (" + getPermDescriptionFromInt(u.getPermLevel()) + ")");
		currentLoggedIn.setToolTipText("Edit Account Information");
		currentLoggedIn.setHorizontalAlignment(SwingConstants.LEFT);
		currentLoggedIn.setContentAreaFilled(false);
		currentLoggedIn.setBorderPainted(false);
		currentLoggedIn.setForeground(Color.BLUE);
		Map<TextAttribute, Integer> fontAttributes = new HashMap<TextAttribute, Integer>();
		fontAttributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		currentLoggedIn.setFont(new Font("Tahoma", Font.PLAIN, 11).deriveFont(fontAttributes));
		currentLoggedIn.setBounds(114, 4, 413, 14);
		currentLoggedIn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				StudentRegistrationMain.replaceMainWindowContents(new GUIChangeDetails());
			}
		});
		add(currentLoggedIn);
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
