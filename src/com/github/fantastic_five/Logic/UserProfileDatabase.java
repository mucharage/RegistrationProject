package com.github.fantastic_five.Logic;

import java.io.Serializable;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * A database of UserProfiles with unique IDs. The UserProfiles are accessed by their IDs.
 * 
 * @author Fantastic Five (Stephen Clark)
 *
 */
public class UserProfileDatabase implements Serializable
{
	private static final long serialVersionUID = -8052666172712131697L;
	// The entire user list
	private TreeSet<UserProfile> userProfiles;

	/**
	 * Constructs a new UserProfileDatabase
	 */
	public UserProfileDatabase()
	{
		userProfiles = new TreeSet<UserProfile>(new UserProfileComparator());
	}

	/**
	 * Returns a set of the UserProfiles in the database, sorted by their IDs.
	 * 
	 * @return A set of the UserProfiles in the database, sorted by their IDs.
	 */
	@SuppressWarnings("unchecked")
	public TreeSet<UserProfile> copyUserProfiles()
	{
		return (TreeSet<UserProfile>) userProfiles.clone();
	}

	/**
	 * @param profile
	 *            the user profile that needs to be added to the course list
	 */
	public boolean addUser(UserProfile addition)
	{
		boolean rVal = false;

		if (!userProfiles.contains(addition))
		{
			rVal = true;
			userProfiles.add(addition);
			DatabaseIO.serializeEverything();
		}

		return rVal;
	}

	/**
	 * Checks to see if the database contains a UserProfile with the specified ID
	 * 
	 * @param userID
	 *            the UserID that needs to be
	 * @return true if the user exists, false if it does not
	 */
	public boolean hasUser(String userID)
	{
		UserProfile dummy = dummyUser(userID);
		return userProfiles.contains(dummy);
	}

	/**
	 * Returns the UserProfile object that matches with the userID
	 * 
	 * @param userID
	 *            the userID to check for
	 * @return the UserProfile object that matches with the userID, or null if
	 */
	public UserProfile getUserProfile(String userID)
	{
		UserProfile rVal = null;
		UserProfile dummy = dummyUser(userID);

		UserProfile possibleRVal = userProfiles.floor(dummy);

		if (possibleRVal != null && possibleRVal.equals(dummy))
		{
			rVal = possibleRVal;
		}

		return rVal;
	}

	private static class UserProfileComparator implements Serializable, Comparator<UserProfile>
	{
		private static final long serialVersionUID = -7658316379215204578L;

		public int compare(UserProfile arg0, UserProfile arg1)
		{
			return arg0.getUserID().compareTo(arg1.getUserID());
		}
	}

	private UserProfile dummyUser(String userID)
	{
		return new UserProfile(userID, null, 0, null, null, null);
	}
}
