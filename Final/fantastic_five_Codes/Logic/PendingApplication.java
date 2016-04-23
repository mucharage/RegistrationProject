package com.github.fantastic_five.Logic;

import java.io.Serializable;

public class PendingApplication implements Serializable
{
	private static final long serialVersionUID = -2568389667038667208L;
	
	private String userID;
	private String password;
	private String firstName;
	private String middleName;
	private String lastName;
	
	/**
	 * Constructs a PendingApplication object.
	 * @param userID the userID for this applicant
	 * @param password the password for this applicant
	 * @param firstName the applicant's first name
	 * @param middleName the user's middle name
	 * @param lastName the user's last name
	 */
	public PendingApplication(String userID, String password, String firstName, String middleName, String lastName)
	{
		this.userID = userID;
		this.password = password;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
	}
	
	public boolean equals(PendingApplication other)
	{
		return (this == other) ? true : (this.userID.equals(other.userID));				
	}
	
	/**
	 * Returns the userID for this applicant
	 * 
	 * @return the userID for this applicant
	 */
	public String getUserID()
	{
		return userID;
	}
	
	/**
	 * Returns the password for this applicant
	 * 
	 * @return the password for this applicant
	 */
	public String getPassword()
	{
		return password;
	}

	/**
	 * Returns the applicant's first name
	 * 
	 * @return the applicant's first name
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * Returns the applicant's middle name
	 * 
	 * @return the user's middle name
	 */
	public String getMiddleName()
	{
		return middleName;
	}

	/**
	 * Returns the applicant's last name
	 * 
	 * @return the user's last name
	 */
	public String getLastName()
	{
		return lastName;
	}
}
