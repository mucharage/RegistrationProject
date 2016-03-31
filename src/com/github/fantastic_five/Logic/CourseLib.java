package com.github.fantastic_five.Logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;

public class CourseLib
{
	// Essentially the master course list of the university
	public static ArrayList<Course> courseList = new ArrayList<Course>();
	// PrintWriter for courses
	static PrintStream courseOut;

	/**
	 * @param course
	 *            the course to be addedto the master course list
	 */
	public static void addCourseToCourseList(Course course)
	{
		// Clears the output file and writes a new, updated one
		courseList.add(course);
		updateCourseListFile();
	}

	/**
	 * Updates the course list file by resetting it and re-writing the contents
	 */
	public static void updateCourseListFile()
	{
		try
		{
			courseOut = new PrintStream(new File("courses.dat"));
			for (Course c : courseList)
			{
				courseOut.println(c.toString());
				System.out.println(c.toString());
			}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Removes the course based on the CRN
	 * 
	 * @param CRN
	 *            CRN to be removed from the course list
	 */
	public static void removeCourseFromCourseList(int CRN)
	{
		for (int i = 0; i < courseList.size(); i++)
		{
			if (courseList.get(i).getCRN() == CRN)
			{
				courseList.remove(i);
				updateCourseListFile();
				break;
			}
		}
	}

	/**
	 * @param CRN
	 *            CRN to be checked for pre-existence
	 * @return True if it does, False if it doesn't
	 */
	public static boolean doesCRNExist(int CRN)
	{
		for (Course c : courseList)
			if (c.getCRN() == CRN)
				return true;
		return false;
	}
}
