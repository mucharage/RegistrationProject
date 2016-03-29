package com.github.fantastic_five.Logic;

import java.util.ArrayList;

public class CourseLib
{
	// Essentially the master course list of the university -> to be outputted
	public static ArrayList<Course> courseList = new ArrayList<Course>();

	/**
	 * @param course
	 *            the course to be addedto the master course list
	 */
	public static void addCourseToCourseList(Course course)
	{
		// TODO: implement output for .dat file
		courseList.add(course);
	}

	/**
	 * Removes the course based on the CRN
	 * 
	 * @param CRN
	 *            CRN to be removed from the course list
	 */
	public static void removeCourseFromCourseList(String CRN)
	{
		for (int i = 0; i < courseList.size(); i++)
		{
			if (courseList.get(i).getCRN().equalsIgnoreCase(CRN))
			{
				courseList.remove(i);
				// TODO: implement output for .dat file
				break;
			}
		}
	}
}
