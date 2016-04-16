package com.github.fantastic_five.Logic;

import java.util.Comparator;
import java.util.HashSet;
import java.util.TreeSet;

import com.github.fantastic_five.StudentRegistrationMain;
import com.github.fantastic_five.Logic.Course.Day;

public class MiscUtils
{
	/**
	 * @param daySet
	 *            the HashSet of days that needs to be formatted
	 * @return a formatted string with all the days in proper order
	 */
	public static String getDaysFormatted(HashSet<Day> daySet)
	{
		String rVal = "";

		// Sorts the days in order because they should be
		TreeSet<Day> sortedDays = new TreeSet<>(new Comparator<Day>()
		{
			@Override
			public int compare(Day o1, Day o2)
			{
				if (o1 == o2)
					return 0;
				else if (o1.getOrder() > o2.getOrder())
					return 1;
				else
					return -1;
			}
		});

		// Copies the day set to the Sorted TreeSet
		for (Day d : daySet)
			sortedDays.add(d);

		// Makes the string
		for (Day d : sortedDays)
			rVal += d.getAbbreviation() + " ";

		return rVal;
	}

	/**
	 * @return a UserProfile object of who is currently logged in, null if no user is logged in (shouldn't be, but just in case)
	 */
	public static UserProfile getCurrentLoggedInUser()
	{
		return StudentRegistrationMain.loggedIn.size() > 0 ? StudentRegistrationMain.loggedIn.get(0) : null;
	}
}
