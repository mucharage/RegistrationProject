package com.github.fantastic_five.Logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
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
	 * @return A random, non-conflicting CRN from 1000 - 9999
	 */
	public static int getCRN()
	{
		Random rand = new Random();
		// Gets a value between 0 and 8999 (inclusive) then adds 1000
		int ret = rand.nextInt(9000) + 1000;
		// Recursive call to get a CRN that is available
		if (!doesCRNExist(ret))
			return ret;
		else
			return getCRN();
	}

	/**
	 * 
	 * @param toTest
	 *            the CRN to confirm has been used
	 * @return true if it's already been used, false otherwise
	 */
	public static boolean doesCRNExist(int toTest)
	{
		TreeSet<Course> courses = StudentRegistrationMain.mainCourseManager.getCourses();
		for (Course c : courses)
			if (c.getCRN() == toTest)
				return true;
		return false;
	}

	/**
	 * initializes course variables using data stored in our .dat files
	 */
	public static void loadCoursesFromFile()
	{
		File coursesFile = new File("courses.dat");
		if (coursesFile != null)
		{
			try
			{
				Scanner courseIn = new Scanner(coursesFile);
				while (courseIn.hasNextLine())
				{
					// Separates the output file into smaller bits
					String line = courseIn.nextLine();
					String[] lineParts = line.split("_");
					if (lineParts.length == 7)
					{
						// Handles a few parts of the Course obj
						String title = lineParts[0];
						String description = lineParts[1];
						int CRN = Integer.parseInt(lineParts[2]);
						int studentCap = Integer.parseInt(lineParts[3]);
						// Handles the Days
						HashSet<Day> days = new HashSet<>();
						String[] tempParts = lineParts[4].split("[\\W]");
						for (String s : tempParts)
						{
							if (Day.getDayFromName(s) != null)
								days.add(Day.getDayFromName(s));
						}
						// Handles Start time
						tempParts = lineParts[5].split(":");
						int startHour = Integer.parseInt(tempParts[0]);
						int startMinute = Integer.parseInt(tempParts[1]);
						// Handles end time
						tempParts = lineParts[6].split(":");
						int endHour = Integer.parseInt(tempParts[0]);
						int endMinute = Integer.parseInt(tempParts[1]);

						Course c = new Course(title, description, CRN, studentCap, days, startHour, startMinute, endHour, endMinute);
						StudentRegistrationMain.mainCourseManager.addCourse(c);
					}
				}
				courseIn.close();
			}
			catch (FileNotFoundException e)
			{
				// doing nothing with this because no file found is to be expected at least once
			}
		}
	}

	/**
	 * initializes userprofile variables using data stored in our .dat files
	 */
	public static void loadUsersFromFile()
	{
		File usersFile = new File("users.dat");
		if (usersFile != null)
		{
			try
			{
				Scanner userIn = new Scanner(usersFile);
				while (userIn.hasNextLine())
				{

					// Separates the output file into smaller bits
					String line = userIn.nextLine();
					String[] lineParts = line.split("_");
					if (lineParts.length == 6)
					{
						// Handles all parts of a user profile
						String userID = lineParts[0];
						String password = lineParts[1];
						int permLevel = Integer.parseInt(lineParts[2]);
						String firstName = lineParts[3];
						String middleName = lineParts[4];
						String lastName = lineParts[5];

						// Creates a temporary userprofile
						UserProfile u = new UserProfile(userID, password, permLevel, firstName, middleName, lastName);
						UserProfileDatabase.addUser(u);
					}
				}
				userIn.close();
			}
			catch (FileNotFoundException e)
			{
				// doing nothing with this because no file found is to be expected at least once
			}
		}
	}
}