	package com.github.fantastic_five.Logic;

import java.io.Serializable;
import java.util.Comparator;
import java.util.TreeMap;

public class FinancialRecordsOffice implements Serializable
{
	private static final long serialVersionUID = 2105416738235334366L;
	private TreeMap<UserProfile, Boolean> userPaymentInfo;

	/**
	 * Constructs a new FinancialRecordsOffice object
	 */
	public FinancialRecordsOffice()
	{
		userPaymentInfo = new TreeMap<UserProfile, Boolean>(new UserProfileComparator());
	}

	/**
	 * Returns a map containing all the UserProfiles and a Boolean which equals true iff they have unpaid charges on their account
	 * 
	 * @return A map containing all the UserProfiles and a Boolean which equals true iff they have unpaid charges on their account
	 */
	public TreeMap<UserProfile, Boolean> copyUserPaymentInfo()
	{
		return (TreeMap<UserProfile, Boolean>) userPaymentInfo.clone();
	}

	/**
	 * Searches the database for a user
	 * 
	 * @param userID
	 * @return
	 */
	public boolean hasUser(String userID)
	{
		UserProfile dummy = dummyUser(userID);
		return userPaymentInfo.containsKey(dummy);
	}

	/**
	 * Attempts to add the specified UserProfile with no charges to the database. Fails if the database already contains that user (ie when hasUser(user.getUserID()) returns true).
	 * 
	 * @param user The UserProfile being added.
	 * @return true iff The UserProfile was successfully added
	 */
	public boolean addUser(UserProfile user)
	{
		boolean rVal = hasUser(user.getUserID());

		if (!rVal)
		{
			userPaymentInfo.put(user, false);
			DatabaseIO.serializeEverything();
		}

		return rVal;
	}

	/**
	 * Checks to see if the specified user's account has any unpaid charges 
	 * @param userID The user who's accounts charges are being examined
	 * @return true if the user's account has unpaid charges
	 */
	public boolean userHasCharges(String userID)
	{
		if(hasUser(userID))
		{
			UserProfile dummy = dummyUser(userID);
			return userPaymentInfo.get(dummy);
		}
		else
		{
			throw new IllegalArgumentException("userID Not Found");
		}
	}
	
	/**
	 * Changes the boolean associated with a specified user
	 * @param userID
	 * @param newStatus The new value representing whether or not the specified UserProfile has unpaid charges
	 * @return true iff the value was successfully changed
	 */
	public boolean changeUsersPaymentStatus(String userID, boolean newStatus)
	{
		boolean rVal = false;
		
		if(hasUser(userID))
		{
			rVal = true;
			UserProfile dummy = dummyUser(userID);
			userPaymentInfo.replace(dummy, newStatus);
			DatabaseIO.serializeEverything();
		}
		
		return rVal;
	}

	private UserProfile dummyUser(String userID)
	{
		return new UserProfile(userID, null, 0, null, null, null);
	}

	private static class UserProfileComparator implements Serializable, Comparator<UserProfile>
	{
		private static final long serialVersionUID = 3594188557274484736L;

		public int compare(UserProfile arg0, UserProfile arg1)
		{
			return arg0.getUserID().compareTo(arg1.getUserID());
		}
	}
}
