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
	 * Searches the database for a user
	 * @param userID
	 * @return
	 */
	public boolean hasUser(String userID)
	{
		UserProfile dummy = dummyUser(userID);
		return userPaymentInfo.containsKey(dummy);
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
