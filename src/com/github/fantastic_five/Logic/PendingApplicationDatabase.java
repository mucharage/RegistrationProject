package com.github.fantastic_five.Logic;

import java.io.Serializable;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * 
 * @author Fantastic Five (Stephen Clark)
 *
 */
public class PendingApplicationDatabase implements Serializable
{
	private static final long serialVersionUID = -4842360659765401606L;
	
	private TreeSet<PendingApplication> database;
	
	public PendingApplicationDatabase()
	{
		database = new TreeSet<>();
	}
	
	public TreeSet<PendingApplication> copyDatabase()
	{
		return (TreeSet<PendingApplication>) database.clone();
	}
	
	/**
	 * @param application
	 *            the application that needs to be added to the database
	 */
	public boolean addApplication(PendingApplication addition)
	{
		boolean rVal = false;

		if (!database.contains(addition))
		{
			rVal = true;
			database.add(addition);
			DatabaseIO.serializeEverything();
		}

		return rVal;
	}
	
	/**
	 * Attempts to remove a userProfile with a specified userID from the database
	 * @param userID the userID of the applicant
	 * @return Returns true iff the specified application was removed from the database
	 */
	public boolean removeApplication(String userID)
	{
		boolean rVal = false;
		
		PendingApplication dummy = dummyApplication(userID);
		
		if (database.contains(dummy))
		{
			rVal = true;
			database.remove(dummy);
			DatabaseIO.serializeEverything();
		}
		
		return rVal;
	}
	
	/**
	 * Checks to see if the database contains a PendingApplication with the specified ID
	 * 
	 * @param userID
	 *            the UserID that needs to be
	 * @return true if the user exists, false if it does not
	 */
	public boolean hasApplication(String userID)
	{
		return database.contains(dummyApplication(userID));
	}
	
	/**
	 * Returns the PendingApplication object that matches with the userID
	 * 
	 * @param userID
	 *            the userID to check for
	 * @return the PendingApplication object that matches with the userID, or null if
	 */
	public PendingApplication getPendingApplication(String userID)
	{
		PendingApplication rVal = null;
		PendingApplication dummy = dummyApplication(userID);

		PendingApplication possibleRVal = database.floor(dummy);

		if (possibleRVal != null && possibleRVal.equals(dummy))
		{
			rVal = possibleRVal;
		}

		return rVal;
	}
	
	private PendingApplication dummyApplication(String id)
	{
		return new PendingApplication(id, null, null, null, null);
	}
	
	private static class ApplicationComparator implements Serializable, Comparator<PendingApplication>
	{
		private static final long serialVersionUID = -4566674384568387112L;

		public int compare(PendingApplication arg0, PendingApplication arg1)
		{
			return arg0.getUserID().compareTo(arg1.getUserID());
		}
	}
}
