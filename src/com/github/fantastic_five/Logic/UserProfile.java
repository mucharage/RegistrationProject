package com.github.fantastic_five.Logic;

/**
 * The account information for a person registered with the school.
 * 
 * @author Clark Stephen Group 5
 *
 */
public class UserProfile
{
	public static final int GUEST = 0;
	public static final int STUDENT = 1;
	// public static final int TA = 2;
	public static final int TEACHER = 3;
	public static final int ADMIN = 4;

	private String userID;
	private String password;
	private int permLevel;
	private String firstName;
	private String middleName;
	private String lastName;

	public UserProfile(String userID, String password, int permLevel, String firstName, String middleName, String lastName)
	{
		this.userID = userID;
		this.password = password;
		this.permLevel = permLevel;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
	}

	/**
	 * Returns the userID for this account
	 * 
	 * @return the userID for this account
	 */
	public String getUserID()
	{
		return userID;
	}

	/**
	 * Returns the user's first name
	 * 
	 * @return the user's first name
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * Returns the user's middle name
	 * 
	 * @return the user's middle name
	 */
	public String getMiddleName()
	{
		return middleName;
	}

	/**
	 * Returns the user's last name
	 * 
	 * @return the user's last name
	 */
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * Returns the user's permission level. This value is equal to GUEST, STUDENT, TEACHER, or ADMIN, depending on which of those the user is.
	 * 
	 * @return the user's permission level.
	 */
	public int getPermLevel()
	{
		return permLevel;
	}

	/**
	 * Tests to see if the implicit parameter's password equals guess
	 * 
	 * @param password
	 *            The String which the implicit parameter's password is being compared to
	 * @return Return true iff password.equals(guess)
	 */
	public boolean passwordIs(String guess)
	{
		return password.equals(guess);
	}

	/**
	 * Sets the password for this UserProfile to newPassword iff password.equals(guess)
	 * 
	 * @param newPassword
	 *            The new password
	 * @param oldPassword
	 *            The old password
	 * @return True iff the password is successfully changed
	 */
	public boolean setPassword(String newPassword, String guess)
	{
		boolean success = passwordIs(guess);
		if (success)
		{
			password = newPassword;
		}
		return success;
	}

}
