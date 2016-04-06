package com.github.fantastic_five.Logic;

import java.util.ArrayList;

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
	public static final int TA = 2;
	public static final int TEACHER = 3;
	public static final int ADMIN = 4;

	private String userID;
	private String password;
	private int permLevel;
	private String firstName;
	private String middleName;
	private String lastName;

	private ArrayList<Course> currSchedule;

	public UserProfile(String userID, String password, int permLevel, String firstName, String middleName, String lastName)
	{
		this.userID = userID;
		this.password = password;
		this.permLevel = permLevel;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.currSchedule = new ArrayList<Course>();
	}

	public boolean equals(UserProfile other)
	{
		boolean rVal = this.userID.equals(other.userID);
		return rVal;
	}

	public ArrayList<Course> getStudentSchedule()
	{
		return currSchedule;
	}

	/**
	 * Adds a class to the student's schedule by CRN
	 * 
	 * @param CRN
	 *            The CRN number to add to the user's schedule
	 */
	public void addClass(int CRN)
	{
		// TODO: New implementation using CourseManager?
	}

	/**
	 * Adds a class to the student's schedule using an object
	 * 
	 * @param course
	 *            The Course object to add to the user's schedule
	 */
	public void addClass(Course course)
	{
		currSchedule.add(course);
	}

	/**
	 * Removes a class from the user's schedule by CRN
	 * 
	 * @param CRN
	 *            The CRN number to add to the user's schedule
	 */
	public void removeClass(int CRN)
	{
		for (int i = 0; i < currSchedule.size(); i++)
		{
			if (currSchedule.get(i).getCRN() == CRN)
			{
				currSchedule.remove(i);
				break;
			}
		}
	}

	/**
	 * Removes a class from the user's schedule using a course object
	 * 
	 * @param course
	 *            The Course object to remove from the user's schedule
	 */
	public void removeClass(Course course)
	{
		for (int i = 0; i < currSchedule.size(); i++)
		{
			if (currSchedule.get(i) == course)
			{
				currSchedule.remove(i);
				break;
			}
		}
	}

	/**
	 * 
	 * @param CRN
	 *            The CRN to be checked
	 * @return True if the student is already enrolled in the course, false otherwise
	 */
	public boolean isTaking(int CRN)
	{
		for (Course c : currSchedule)
			if (c.getCRN() == CRN)
				return true;
		return false;
	}

	/**
	 * 
	 * @param course
	 *            The course object to be checked
	 * @return True if the studenet is already enrolled in the course, false otherwise
	 */
	public boolean isTaking(Course course)
	{
		for (Course c : currSchedule)
			if (c == course)
				return true;
		return false;
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
