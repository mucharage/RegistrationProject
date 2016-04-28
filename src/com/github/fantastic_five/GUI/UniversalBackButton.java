package com.github.fantastic_five.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.github.fantastic_five.StudentRegistrationMain;
import com.github.fantastic_five.Logic.UserProfile;

/**
 * 
 * @author Fantastic Five (Stephen Clark)
 *
 */
public class UniversalBackButton extends JButton
{
	private static final long serialVersionUID = -754389320020909688L;

	public UniversalBackButton()
	{
		super("Back");

		this.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				UserProfile currentUser = StudentRegistrationMain.getCurrentLoggedInUser();
				int currentPermLevel = currentUser.getPermLevel();
				StudentRegistrationMain.replaceMainWindowContents(GUILogin.getGUIFromPerm(currentPermLevel));
			}
		});
	}
}
