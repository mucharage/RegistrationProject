package com.github.fantastic_five.Logic;

import java.util.HashSet;
import java.util.Set;

import com.github.fantastic_five.StudentRegistrationMain;

/**
 * 
 * @author Fantastic Five (Stephen Clark)
 *
 */
public class ScheduleManager
{

	private static final int MAXIMUM_COURSES_PER_LEARNER = 5;
	private static final int MINIMUM_COURSES_PER_LEARNER = 3;
	private static final int MAXIMUM_COURSES_PER_INSTRUCTOR = 5;
	private static final int MINIMUM_COURSES_PER_INSTRUCTOR = 0;

	/**
	 * Returns the set of courses from a specified user's schedule which conflict with a specified course
	 * 
	 * @param course
	 *            The course which is being checked against
	 * @param user
	 *            The user whose schedule is being viewed
	 * @return The set of courses from a specified user's schedule which conflict with a specified course
	 */
	public static Set<Course> getConflictingCourses(Course course, UserProfile user)
	{
		Set<Course> rVal = new HashSet<Course>();
		CourseManager mainCourseManager = StudentRegistrationMain.mainCourseManager;

		Set<Course> userSchedule = new HashSet<Course>();

		Set<Course> additions = mainCourseManager.getCoursesWithLearner(user);
		if (additions != null)
		{
			userSchedule.addAll(additions);
		}
		additions = mainCourseManager.getCoursesWithInstructor(user);
		if (additions != null)
		{
			userSchedule.addAll(additions);
		}

		for (Course c : userSchedule)
		{
			if (course.conflictsWith(c))
			{
				rVal.add(c);
			}
		}

		return rVal;
	}

	/**
	 * Returns the set of courses from a specified user's schedule which conflict with a specified course
	 * 
	 * @param crn
	 *            The course which is being checked against
	 * @param user
	 *            The user whose schedule is being viewed
	 * @return The set of courses from a specified user's schedule which conflict with a specified course
	 */
	public static Set<Course> getConflictingCourses(int crn, UserProfile user)
	{
		Course course = StudentRegistrationMain.mainCourseManager.getCourse(crn);
		return getConflictingCourses(course, user);
	}

	public static boolean usersScheduleIsValid(UserProfile user)
	{
		boolean rVal = true;
		int permLevel = user.getPermLevel();

		if ((permLevel == UserProfile.STUDENT) || (permLevel == UserProfile.TA))
		{
			Set<Course> learningSchedule = StudentRegistrationMain.mainCourseManager.getCoursesWithLearner(user);
			if ((MINIMUM_COURSES_PER_LEARNER <= learningSchedule.size()) || (learningSchedule.size() <= MAXIMUM_COURSES_PER_LEARNER))
			{
				rVal = false;
			}
		}

		if ((UserProfile.TA <= permLevel) && (permLevel <= UserProfile.ADMIN))
		{
			Set<Course> learningSchedule = StudentRegistrationMain.mainCourseManager.getCoursesWithLearner(user);
			if ((MINIMUM_COURSES_PER_INSTRUCTOR >= learningSchedule.size()) || (learningSchedule.size() >= MAXIMUM_COURSES_PER_INSTRUCTOR))
			{
				rVal = false;
			}
		}

		return rVal;
	}

}
