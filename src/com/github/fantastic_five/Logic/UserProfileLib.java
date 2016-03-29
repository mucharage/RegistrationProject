package com.github.fantastic_five.Logic;

import java.util.ArrayList;

import javax.swing.JPanel;

import com.github.fantastic_five.GUI.GUIViewCourses;
import com.github.fantastic_five.GUIAdministrator.GUIAdmin;
import com.github.fantastic_five.GUIStudent.GUIStudent;
import com.github.fantastic_five.GUITeacher.GUITeacher;

public class UserProfileLib
{
	public static ArrayList<UserProfile> users = new ArrayList<UserProfile>();

	/**
	 * @param userID
	 *            the user ID that needs to be removed from the master course list
	 */
	public static void removeUser(String userID)
	{
		for (int i = 0; i < users.size(); i++)
		{
			if (users.get(i).getUserID().equalsIgnoreCase(userID))
			{
				users.remove(i);
				break;
			}
		}
	}
	
	public static UserProfile getUserProfile(String userID, char[] password)
	{
		for(UserProfile u : users)
		{
			if(u.getUserID().equalsIgnoreCase(userID))
			{
				String tempPass = new String(password);
				if(u.passwordIs(tempPass))
					return u;
			}
		}
		return null;
	}

	/**
	 * @param profile
	 *            the user profile that needs to be added to the course list
	 */
	public static void addUser(UserProfile profile)
	{
		users.add(profile);
	}
	
	public static JPanel getGUIFromPerm(int permLevel)
	{
		switch(permLevel)
		{
		case 0:return new GUIViewCourses();
		case 1:return new GUIStudent();
		case 3:return new GUITeacher();
		case 4:return new GUIAdmin();
		default:return new GUIViewCourses();
		}
	}
}
