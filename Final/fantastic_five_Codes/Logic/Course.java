	package com.github.fantastic_five.Logic;

import java.io.Serializable;
/**
 * @author Clark Stephen
 * Group 5
 */
import java.security.InvalidParameterException;
import java.util.Set;
import java.util.TreeSet;

/**
 * 
 * @author Fantastic Five (Stephen Clark)
 *
 */
public class Course implements Serializable
{
	private static final long serialVersionUID = -1827693919442282177L;
	private String title;
	private String description;
	private int crn;
	private int studentCap;
	private int currentEnrolled;
	private TreeSet<Day> days;
	private Time startTime;
	private Time endTime;

	public static final int TWENTYFOUR_HR_CLOCK = Time.TWENTYFOUR_HR_CLOCK;
	public static final int TWELVE_HR_CLOCK = Time.TWELVE_HR_CLOCK;
	
	/**
	 * Constructs a new course object
	 * 
	 * @param title
	 *            The title of the course
	 * @param description
	 *            A description of the course
	 * @param crn
	 *            The course's CRN number
	 * @param studentCap
	 *            The maximum number of students that may enroll in the course;
	 * @param days
	 *            The days that the course meets on
	 * @param startTimeHr
	 *            A whole number less than 24, representing the hour of the day at which the course meets
	 * @param startTimeMin
	 *            A whole number less than 60, representing the minute of the hour at which the course meets
	 * @param endTimeHr
	 *            A whole number less than 24, representing the hour of the day at which the course ends
	 * @param endTimeMin
	 *            A whole number less than 60, representing the minute of the hour at which the course ends
	 */
	public Course(String title, String description, int crn, int studentCap, TreeSet<Day> days, int startTimeHr, int startTimeMin, int endTimeHr, int endTimeMin)
	{
		this.title = title;
		this.description = description;
		this.crn = crn;
		this.studentCap = studentCap;
		this.currentEnrolled = 0;
		this.days = days;
		startTime = new Time(startTimeHr, startTimeMin);
		endTime = new Time(endTimeHr, endTimeMin);
		checkTimes();
	}

	public boolean equals(Object other)
	{
		boolean rVal;
		if (other instanceof Course)
		{
			rVal = (this.crn == ((Course) other).crn);
		}
		else
		{
			rVal = false;
		}
		return rVal;
	}

	/**
	 * Returns the title of the course
	 * 
	 * @return The title of the course
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * Returns the description of the course
	 * 
	 * @return The description of the course
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * 
	 * @param newDescription
	 */
	public void setDescription(String newDescription)
	{
		description = newDescription;
	}

	/**
	 * Returns the crn of the course
	 * 
	 * @return The crn of the course
	 */
	public int getCRN()
	{
		return crn;
	}

	/**
	 * Returns the maximum number of student that can be enrolled in the course
	 * 
	 * @return The maximum number of student that can be enrolled in the course
	 */
	public int getStudentCap()
	{
		return studentCap;
	}

	/**
	 * Returns the remaining quantity of students able to enroll
	 * 
	 * @return the remaining quantity of students able to enroll
	 */
	public int getRemainingCap()
	{
		return this.studentCap - this.currentEnrolled;
	}

	/**
	 * Increases the number of students enrolled
	 */
	public void incrRemainingCap()
	{
		this.currentEnrolled++;
	}

	/**
	 * Decreases the number of students enrolled
	 */
	public void decrRemainingCap()
	{
		this.currentEnrolled--;
	}

	/**
	 * Returns the meeting days for the course
	 * 
	 * @return The meeting days for the course
	 */
	public TreeSet<Day> getDays()
	{
		return (TreeSet)days.clone();
	}

	/**
	 * @param daySet
	 *            the HashSet of days that needs to be formatted
	 * @return a formatted string with all the days in proper order
	 */
	public String getDaysFormatted()
	{
		String rVal = "";

		// Makes the string
		for (Day d : days)
			rVal += d.getAbbreviation() + " ";

		return rVal;
	}
	
	/**
	 * Returns a string representing the start time for the course, in either the 24 hour notation or the 12 hour notation
	 * 
	 * @param clockType
	 *            Must be either TWENTYFOUR_HOUR_CLOCK or TWELVE_HOUR_CLOCK
	 * @return A string representing the start time for the course
	 */
	public String getStartTime(int clockType)
	{
		String rVal = startTime.formatTime(clockType);
		return rVal;
	}

	/**
	 * Returns a string representing the end time for the course, in either the 24 hour notation or the 12 hour notation
	 * 
	 * @param clockType
	 *            Must be either TWENTYFOUR_HOUR_CLOCK or TWELVE_HOUR_CLOCK
	 * @return A string representing the end time for the course
	 */
	public String getEndTime(int clockType)
	{
		String rVal = endTime.formatTime(clockType);
		return rVal;
	}

	/**
	 * Tests to see if this course and the specified course meet at conflicting times
	 * 
	 * @param other
	 *            The other course
	 * @return Returns true iff the courses conflict
	 */
	public boolean conflictsWith(Course other)
	{
		boolean coursesConflict;

		@SuppressWarnings("unchecked")
		Set<Day> intersection = (TreeSet<Day>) days.clone();
		intersection.retainAll(other.days);

		// Checks if the courses ever meet on the same day
		if (intersection.size() > 0)
		{
			// Checks if this course ends before the other starts
			if (this.endTime.compareTo(other.startTime) < 0)
			{
				coursesConflict = false;
			}
			else
			{
				// checks if this course starts after the other ends
				if (this.startTime.compareTo(other.endTime) > 0)
				{
					coursesConflict = false;
				}
				else
				{
					coursesConflict = true;
				}
			}
		}
		else
		{
			coursesConflict = false;
		}

		return coursesConflict;
	}

	/**
	 * Checks to see if the course's endTime is later than its startTime. If it isn't, throws an IllegalStateException.
	 */
	private void checkTimes()
	{
		if (startTime.compareTo(endTime) >= 0)
		{
			throw new IllegalStateException("Course must start before it can end");
		}

	}

	/**
	 * Represents a time of day, down to the minute.
	 * 
	 * @author Fantastic Five
	 *
	 */
	private class Time implements Comparable<Time>, Serializable
	{
		private static final long serialVersionUID = 5493607733966799493L;
		private int hr;
		private int min;

		public static final int TWENTYFOUR_HR_CLOCK = 24;
		public static final int TWELVE_HR_CLOCK = 12;

		/**
		 * 
		 * @param hr
		 *            A whole number less than 24, representing the hour of the day
		 * @param min
		 *            A whole number less than 60, representing the minute of the hour
		 */
		public Time(int hr, int min)
		{
			if (hr >= 23 || hr < 0)
			{
				throw new InvalidParameterException("hr is invalid");
			}

			if (min >= 60 || min < 0)
			{
				throw new InvalidParameterException("min is invalid");
			}

			this.hr = hr;
			this.min = min;
		}

		public String formatTime(int clockType)
		{
			String rVal;
			String fHour;
			String fMinute;
			String fPeriod;

			final int HALF_DAY = 12;
			final String AM = " a.m.";
			final String PM = " p.m.";

			if (clockType == TWELVE_HR_CLOCK)
			{
				if ((hr % HALF_DAY) == 0)
				{
					fHour = String.valueOf(HALF_DAY);

					if ((hr / HALF_DAY) == 0)
					{
						fPeriod = PM;
					}
					else
					{
						fPeriod = AM;
					}
				}
				else
				{
					fHour = String.valueOf(hr % HALF_DAY);

					if ((hr / HALF_DAY) == 0)
					{
						fPeriod = AM;
					}
					else
					{
						fPeriod = PM;
					}
				}
			}
			else if (clockType == TWENTYFOUR_HR_CLOCK)
			{
				fHour = String.valueOf(hr);
				fPeriod = "";
			}
			else
			{
				throw new InvalidParameterException("Invalid clockType");
			}

			if (min < 10)
			{
				fMinute = "0" + min;
			}
			else
			{
				fMinute = String.valueOf(min);
			}

			rVal = fHour + ":" + fMinute + fPeriod;

			return rVal;
		}

		public int compareTo(Time other)
		{
			int rVal;
			if (this.hr < other.hr)
			{
				rVal = -1;
			}
			else if (this.hr == other.hr)
			{
				if (this.min < other.min)
				{
					rVal = -1;
				}
				else if (this.min == other.min)
				{
					rVal = 0;
				}
				else
				{
					rVal = 1;
				}
			}
			else
			{
				rVal = 1;
			}

			return rVal;
		}

		@Override
		public String toString()
		{
			return formatTime(24);
		}
	}

	public static enum Day implements Serializable//, Comparable<Day>
	{
		MONDAY("Monday", "M", 0), TUESDAY("Tuesday", "T", 1), WEDNESDAY("Wednesday", "W", 2), THURSDAY("Thursday", "R", 3), FRIDAY("Friday", "F", 4), SATURDAY("Saturday", "S", 5), SUNDAY("Sunday", "U", 6);

		private Day(String name, String abbreviation, int order)
		{
			this.name = name;
			this.abbreviation = abbreviation;
			this.order = order;
		}

		private String name;
		private String abbreviation;
		private int order;

		public int getOrder()
		{
			return order;
		}

		public String getName()
		{
			return name;
		}

		public String getAbbreviation()
		{
			return abbreviation;
		}

		/**
		 * @param name
		 *            the full name of the day you want removed (ex: "Monday")
		 * @return the Enum object DAY corresponding
		 */
		public static Day getDayFromName(String name)
		{

			Day rVal = null;

			for (Day e : Day.values())
			{
				if (e.name.equalsIgnoreCase(name))
				{
					rVal = e;
					break;
				}
			}

			return rVal;
		}

		/**
		 * @param abbr
		 *            the abbreviation of the day you want removed (ex: "M")
		 * @return the Enum object DAY corresponding
		 */
		public static Day getDayFromAbbreviation(String abbr)
		{
			Day rVal = null;

			for (Day e : Day.values())
			{
				if (e.abbreviation.equalsIgnoreCase(abbr))
				{
					rVal = e;
					break;
				}
			}

			return rVal;
		}
	
//		public int compareTo(Day other)
//		{
//			return Integer.compare(this.order, other.order);
//		}
	}

}