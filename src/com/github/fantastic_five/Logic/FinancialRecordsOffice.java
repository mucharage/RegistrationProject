package com.github.fantastic_five.Logic;

import java.io.Serializable;
import java.util.HashMap;

public class FinancialRecordsOffice implements Serializable
{
	private static final long serialVersionUID = 2105416738235334366L;
	
	private HashMap<UserProfile, Boolean> payments;
	
	/**
	 * Constructs 
	 */
	public FinancialRecordsOffice()
	{
		payments = new HashMap<UserProfile, Boolean>();
	}
	
	private UserProfile dummyUser(String userID)
	{
		return new UserProfile(userID, null, 0, null, null, null);
	}
}
