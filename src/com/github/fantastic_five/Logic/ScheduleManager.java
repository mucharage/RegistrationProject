package com.github.fantastic_five.Logic;

import java.util.HashSet;
import java.util.Set;

import com.github.fantastic_five.StudentRegistrationMain;

public class ScheduleManager
{
	
	/**
	 * Returns the set of courses from a specified user's schedule which conflict with a specified course
	 * @param course The course which is being checked against
	 * @param user The user whose schedule is being viewed
	 * @return The set of courses from a specified user's schedule which conflict with a specified course
	 */
	public static Set<Course> courseAndScheduleConflict(Course course, UserProfile user)
	{
		Set<Course> rVal = new HashSet<Course>();
		CourseManager mainCourseManager = StudentRegistrationMain.mainCourseManager;
	
		Set<Course> userSchedule = mainCourseManager.getCoursesWithLearner(user);
		userSchedule.addAll(mainCourseManager.getCoursesWithInstructor(user));
		
		for(Course c: userSchedule)
		{
			if(course.conflictsWith(c))
			{
				rVal.add(c);
			}
		}
		
		return rVal;
	}
}
